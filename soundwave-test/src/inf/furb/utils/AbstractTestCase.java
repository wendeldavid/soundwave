package inf.furb.utils;

import inf.furb.xml.JSMLParser;

import java.io.File;

public abstract class AbstractTestCase {

	protected File getXMLFile(String xmlFileName){
		return new File("resources/" + xmlFileName);
	}
	
	protected JSMLParser getNewParser(String xmlFileName){
		return new JSMLParser(getXMLFile(xmlFileName));
	}
	
}
