package inf.furb.synthesis.mbrola.comp;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

public class NumToWordTest {

	@Test
	public void unidades() {
		Assert.assertEquals("zero", NumToWord.convert(0));
		Assert.assertEquals("seis", NumToWord.convert(6));
	}

	@Test
	public void dezenas() {
		Assert.assertEquals("dezesseis", NumToWord.convert(16));
		Assert.assertEquals("cinqüenta e um", NumToWord.convert(51));
	}

	@Test
	public void centenas() {
		Assert.assertEquals("cem", NumToWord.convert(100));
		Assert.assertEquals("seiscentos e sessenta e seis", NumToWord.convert(666));
	}

	@Test
	public void milhar() {
		Assert.assertEquals("um mil", NumToWord.convert(1000));
		Assert.assertEquals("um mil, duzentos e trinta e quatro", NumToWord.convert(1234));
		Assert.assertEquals("trezentos mil e um", NumToWord.convert(300001));
	}

	@Test
	public void milhoes() {
		Assert.assertEquals("seis milhões", NumToWord.convert(6000000));
	}

	@Test
	public void bilhoes() {
		Assert.assertEquals("novecentos bilhões", NumToWord.convert(new BigInteger("900000000000")));
	}

}