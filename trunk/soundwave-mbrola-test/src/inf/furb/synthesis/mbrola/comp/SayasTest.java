package inf.furb.synthesis.mbrola.comp;

import org.junit.Test;

import inf.furb.synthesis.ISynthesizer;
import inf.furb.synthesis.mbrola.MBRolaSynthesizer;
import inf.furb.utils.AbstractTestCase;
import inf.furb.xml.JSMLParser;


public class SayasTest extends AbstractTestCase{

	@Test
	public void numeric() {
		JSMLParser parser = super.getNewParser("jsml_sayas.xml");
		parser.parse();
		ISynthesizer synth = new MBRolaSynthesizer();
		synth.configure(parser.getSynthElements());
		
		Thread t = new Thread(synth);
		t.start();
	}
	
	public static void main(String[] args) {
		new SayasTest().numeric();
	}
	
}
