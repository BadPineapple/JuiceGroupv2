package ilion.terrafos.animais.negocio;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.admin.negocio.Usuario;
import ilion.terrafos.cadastros.negocio.CategoriaAnimal;
import ilion.terrafos.cadastros.negocio.Pasto;
import ilion.terrafos.relatorios.negocio.LogDeAcao;
import ilion.terrafos.relatorios.negocio.LogDeAcaoBuilder;
import ilion.terrafos.relatorios.negocio.LogDeAcaoThread;
import ilion.terrafos.relatorios.negocio.LogDeAcaoTipo;
import ilion.util.persistencia.HibernateUtil;

@Service
public class PesoMedioNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Transactional
	public void salvar(PesoMedio pm, Usuario usuario) throws Exception {
		
		pm.ehValido();
		
		if( pm.getId() == null ) {
			pm = (PesoMedio) hibernateUtil.save(pm);
		} else {
			hibernateUtil.update(pm);
		}
		
	}

	@Transactional
	public void salvar(PesagensDTO pesagens, Usuario usuario) throws Exception {
		
		List<PesoMedio> pesos = pesagens.toPesosMedios(usuario);
		
		for (PesoMedio pm : pesos) {
			
			salvar(pm, usuario);
			
		}
		
		if( ! pesos.isEmpty() ) {
			PesoMedio pm = pesos.get(0);
			LogDeAcao logDeAcao = new LogDeAcaoBuilder()
					.doTipo(LogDeAcaoTipo.PESAGENS)
					.doPasto(pm.getPasto())
					.doUsuario(usuario)
					.build();
			
			LogDeAcaoThread.incluiLog(logDeAcao);
		}
		
	}
	
	public PesoMedio consultarUltimoPesoMedio(Pasto pasto, CategoriaAnimal categoriaAnimal) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(PesoMedio.class);
		
		dc.add(Restrictions.eq("pasto", pasto));
		dc.add(Restrictions.eq("categoriaAnimal", categoriaAnimal));
		
		dc.addOrder(Order.desc("dataCriacao"));
		
		List<PesoMedio> lista = hibernateUtil.buscar(dc, 1, 1);
		
		if( lista.isEmpty() ) {
			return null;
		}
		
		return lista.get(0);
	}
}