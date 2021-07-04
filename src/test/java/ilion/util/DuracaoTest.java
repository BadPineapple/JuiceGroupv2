package ilion.util;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class DuracaoTest {

	@Test
	public void testDuracao1() {
		
		Date d1 = Uteis.converterDataHora("2020-07-06 07:00", "yyyy-MM-dd HH:mm");
		Date d2 = Uteis.converterDataHora("2020-07-07 07:00", "yyyy-MM-dd HH:mm");
		
		String duracao = Uteis.calcularDuracao(d1, d2);
		
		Assert.assertEquals("1d", duracao);
	}
	
	@Test
	public void testDuracao2() {
		
		Date d1 = Uteis.converterDataHora("2020-07-06 07:00", "yyyy-MM-dd HH:mm");
		Date d2 = Uteis.converterDataHora("2020-07-07 08:00", "yyyy-MM-dd HH:mm");
		
		String duracao = Uteis.calcularDuracao(d1, d2);
		
		Assert.assertEquals("1d 1h", duracao);
	}
	
	@Test
	public void testDuracao3() {
		
		Date d1 = Uteis.converterDataHora("2020-07-06 07:00", "yyyy-MM-dd HH:mm");
		Date d2 = Uteis.converterDataHora("2020-07-07 08:15", "yyyy-MM-dd HH:mm");
		
		String duracao = Uteis.calcularDuracao(d1, d2);
		
		Assert.assertEquals("1d 1h 15m", duracao);
	}
	
}