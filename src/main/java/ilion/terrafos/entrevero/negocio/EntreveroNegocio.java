package ilion.terrafos.entrevero.negocio;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.admin.negocio.Usuario;
import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.terrafos.cadastros.negocio.FazendaPastosNoMapaVH;
import ilion.terrafos.cadastros.negocio.Pasto;
import ilion.terrafos.cadastros.negocio.PastoMapaVH;
import ilion.terrafos.cadastros.negocio.PastoNegocio;
import ilion.terrafos.relatorios.negocio.LogDeAcao;
import ilion.terrafos.relatorios.negocio.LogDeAcaoBuilder;
import ilion.terrafos.relatorios.negocio.LogDeAcaoThread;
import ilion.terrafos.relatorios.negocio.LogDeAcaoTipo;
import ilion.util.exceptions.ValidacaoException;
import ilion.util.persistencia.HibernateUtil;

@Service
public class EntreveroNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private PastoNegocio pastoNegocio;
	
	@Transactional
	public void incluir(EntreveroDTO entreveroDTO, Usuario usuario) {
		
		if( entreveroDTO.getIdPasto() == null ) {
			throw new ValidacaoException("Id do pasto deve ser preenchido.");
		}
		
		if( entreveroDTO.getStatus() == null ) {
			throw new ValidacaoException("Status deve ser preenchido.");
		}
		
		Entrevero e = new Entrevero();
		e.setPasto(new Pasto(entreveroDTO.getIdPasto()));
		e.setStatus(entreveroDTO.getStatus());
		e.setUsuario(usuario);
		
		hibernateUtil.save(e);
		
		LogDeAcao logDeAcao = new LogDeAcaoBuilder()
				.doTipo(LogDeAcaoTipo.ENTREVEROS)
				.doPasto(e.getPasto())
				.doUsuario(usuario)
				.comInfo("status", entreveroDTO.getStatus())
				.build();
		
		LogDeAcaoThread.incluiLog(logDeAcao);
		
	}
	
	public FazendaPastosNoMapaVH monitoramento(Fazenda fazenda) {
		
		FazendaPastosNoMapaVH fazendaPastosNoMapaVH = pastoNegocio.listarRegioesDosPastos(fazenda);
		
		List<PastoMapaVH> pastos = fazendaPastosNoMapaVH.getPastos();
		
		List<Map<String, Object>> entreveros = listarUltimosRegistrosDeEntreveroDosPastos(fazenda);
		
		for (int i = 0; i < pastos.size(); i++) {
			PastoMapaVH p = pastos.get(i);
			
			for (Map<String, Object> m : entreveros) {
				Number idPasto = (Number) m.get("pasto_id");
				
				if( idPasto.longValue() == p.getIdPasto().longValue() ) {
					
					Boolean status = (Boolean) m.get("status");
					
					p.setEntrevero(status);
					
					if( status ) {
						p.setCor(PastoMapaVH.COR_ALERTA);
					}
					
				}
				
			}
			
		}
		
		return fazendaPastosNoMapaVH;
	}
	
	public List<Map<String, Object>> listarUltimosRegistrosDeEntreveroDosPastos(Fazenda fazenda) {
		
		String sql = "";
		sql += " select e.status, e.pasto_id, p.nome pasto_nome "; 
		sql += " from terrafos_entrevero e ";
		sql += " join (select max(datacriacao) as datacriacao, pasto_id from terrafos_entrevero group by pasto_id) as maximos "; 
		sql += " on maximos.datacriacao=e.datacriacao and maximos.pasto_id=e.pasto_id ";
		sql += " join terrafos_pasto p on p.id=e.pasto_id ";
		sql += " where e.pasto_id in (select id from terrafos_pasto where retiro_id in (select id from terrafos_retiro where fazenda_id="+fazenda.getId()+")) ";
		
		List<Map<String, Object>> entreveros = hibernateUtil.listarSQLMap(sql);
		
		return entreveros;
	}
}
