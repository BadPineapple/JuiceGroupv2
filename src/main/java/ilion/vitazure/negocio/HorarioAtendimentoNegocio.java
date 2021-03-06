package ilion.vitazure.negocio;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.util.Uteis;
import ilion.util.persistencia.HibernateUtil;
import ilion.vitazure.model.HorarioAtendimento;
import ilion.vitazure.model.Profissional;

@Service
public class HorarioAtendimentoNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	public HorarioAtendimento consultarPorProfissional(Long idProfissional) {
		DetachedCriteria dc = DetachedCriteria.forClass(HorarioAtendimento.class);
		dc.createAlias("profissional", "p");
		dc.add(Restrictions.eq("p.id", idProfissional));
		HorarioAtendimento horarioAtendimento = (HorarioAtendimento) hibernateUtil.consultarUniqueResult(dc);
		if (horarioAtendimento == null) {
			return new HorarioAtendimento();
		}
		return horarioAtendimento;
	}
	
	@Transactional
	public List<HorarioAtendimento> validarItens(List<HorarioAtendimento> listHorarioAtendimento , Profissional profissional) throws Exception{
		
		List<HorarioAtendimento> horarioAtendimento = consultarHorariosAtendimentoPorProfissional(profissional.getId() , false , false);
		
		List<HorarioAtendimento> itensDuplicados = horarioAtendimento.stream()
	        .filter( o1 -> {
	            return listHorarioAtendimento.stream()
	                    .map(HorarioAtendimento::getId)
	                    .anyMatch(i2 -> i2.equals(o1.getId()));
	        }).collect(Collectors.toList());
				
		horarioAtendimento.removeAll(itensDuplicados);
		
		horarioAtendimento.forEach(horarioExcluir-> {
			try {
				excluir(horarioExcluir);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		if (!listHorarioAtendimento.isEmpty()) {
			listHorarioAtendimento.forEach(horario -> {
  			  try {
  				horario.setProfissional(profissional);
  				  incluirAtualizar(horario);
  			  } catch (Exception e) {
  				  e.printStackTrace();
  			  }
  		  });
  	  }
		
		return listHorarioAtendimento;
	}
	
	@Transactional
	public HorarioAtendimento incluirAtualizar(HorarioAtendimento horarioAtendimento) {
		if (horarioAtendimento.getId() == null || horarioAtendimento.getId() == 0) {
			if(horarioAtendimento.getEnderecoAtendimento() == null || horarioAtendimento.getEnderecoAtendimento().getId() == null) {
				horarioAtendimento.setEnderecoAtendimento(null);
			}
			horarioAtendimento = (HorarioAtendimento) hibernateUtil.save(horarioAtendimento);
		} else {
			hibernateUtil.update(horarioAtendimento);
		}
		return horarioAtendimento;
	}
	
	public List<HorarioAtendimento> consultarHorariosAtendimentoPorProfissional(Long idProfissional , Boolean atendimentoOnline , Boolean atendimentoPresencial) {
		DetachedCriteria dc = DetachedCriteria.forClass(HorarioAtendimento.class);
		dc.createAlias("profissional", "p");
		dc.add(Restrictions.eq("p.id", idProfissional));
		if (atendimentoOnline) {
			dc.add(Restrictions.eq("atendimentoOnline", atendimentoOnline));
		}
		if (atendimentoPresencial) {
			dc.add(Restrictions.eq("atendimentoPresencial", atendimentoPresencial));
		}
		dc.addOrder(Order.asc("horaInicio"));	
		return (List<HorarioAtendimento>) hibernateUtil.list(dc);
	}
	
	@Transactional
	public void excluir(HorarioAtendimento horarioAtendimento) {
		hibernateUtil.delete(horarioAtendimento);
	}
	
	public void validarDiasFerias(Profissional profissional) {
		List<Date> listRemover = new ArrayList<Date>();
		profissional.getDatasPossivelAgendamento().stream().forEach(datasPossivelAgendamento -> {
			try {
				if(Uteis.isDataDentroDoPeriodo(Uteis.convert(profissional.getDataInicioAvisoFerias()) , Uteis.convert(profissional.getDataFimAvisoFerias()) , datasPossivelAgendamento)) {
					listRemover.add(datasPossivelAgendamento);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		});
		profissional.getDatasPossivelAgendamento().removeAll(listRemover);
	}
	
	public void validarTipoAtendimento(Profissional profissional , List<HorarioAtendimento> listaHorarioatendimento) {
		profissional.setAtendimentoOnline(listaHorarioatendimento.stream().anyMatch(horarioAtendimento -> horarioAtendimento.getAtendimentoOnline()));
		profissional.setAtendimentoPresencial(listaHorarioatendimento.stream().anyMatch(horarioAtendimento -> horarioAtendimento.getAtendimentoPresencial()));
		
	}
	
}