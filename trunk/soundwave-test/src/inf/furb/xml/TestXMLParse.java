package inf.furb.xml;

import static org.junit.Assert.assertTrue;
import inf.furb.utils.AbstractTestCase;

import org.junit.Ignore;
import org.junit.Test;

public class TestXMLParse extends AbstractTestCase{

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
