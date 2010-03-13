package inf.furb.xml;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;

public class TestXMLParse {

	private File getXMLFile(String xmlFileName){
		return new File("resources/" + xmlFileName);
	}
	
	private JSMLParser getNewParser(String xmlFileName){
		return new JSMLParser(getXMLFile(xmlFileName));
	}

	@Ignore
	@Test
	public void validateJSMLFile(){
		DOMValidateDTD.validate(getXMLFile("jsml_01.xml"));
	}
	
	@Test
	public void parseJSMLFiles1() {
		JSMLParser parser = getNewParser("jsml_01.xml");
		parser.parse();
	}
	
	@Test
	public void parseJSMLFiles2() {
		boolean exceptionThrowed = false;
		
		JSMLParser parser = getNewParser("jsml_02.xml");
		try {
			parser.parse();
		}catch (RuntimeException e) {
			//ignore exception
			exceptionThrowed = true;
		}
		assertTrue(exceptionThrowed);
	}

	@Test
	public void parseJSMLFiles3() {
		JSMLParser parser = getNewParser("jsml_03.xml");
		parser.parse();
	}
	
}
