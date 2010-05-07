package inf.furb.synthesis.mbrola.comp;

import org.junit.Test;

import inf.furb.synthesis.ISynthesizer;
import inf.furb.synthesis.mbrola.MBRolaSynthesizer;
import inf.furb.utils.AbstractTestCase;
import inf.furb.xml.JSMLParser;


public class ParallelTest extends AbstractTestCase{

	@Test
	public void testParallel() {
		JSMLParser parser = super.getNewParser("jsml_parallel_1.xml");
		parser.parse();
		ISynthesizer synth1 = new MBRolaSynthesizer();
		synth1.configure(parser.getSynthElements());

		parser = super.getNewParser("jsml_parallel_2.xml");
		parser.parse();
		ISynthesizer synth2 = new MBRolaSynthesizer();
		synth2.configure(parser.getSynthElements());
		
		Thread t1 = new Thread(synth1);
		Thread t2 = new Thread(synth2);
		t1.start();
		t2.start();
	}
	
	public static void main(String[] args) {
		new ParallelTest().testParallel();
	}
	
}
