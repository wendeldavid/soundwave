package inf.furb.synthesis;

import inf.furb.utils.AbstractTestCase;

import java.io.File;

import org.junit.Test;

public class SoundwaveManagerTest extends AbstractTestCase {
	
	@Test
	public void testMBRola01(){
		final File jsmlDocument = getXMLFile("jsml_01.xml");
		final String synthesizer = "inf.furb.synthesis.mbrola.MBRolaSynthesizer";
		
		new SoundwaveManager(jsmlDocument, synthesizer);
	}
	
}
