package inf.furb.xml;

import inf.furb.synthesis.jsml.ElementBuilder;
import inf.furb.synthesis.jsml.ISynthElement;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

/**
 * Parser XML que suporta JSML.
 * 
 * @author wendel
 */
public final class JSMLParser {

	private SAXReader reader;
	private File jsmlFile = null;
	private List<ISynthElement> synthElements;

	public JSMLParser(File jsmlFile) {
		if (jsmlFile == null) {
			throw new RuntimeException("The JSML file cannot be null");
		}
		if (!jsmlFile.exists() && !jsmlFile.isFile()) {
			throw new RuntimeException("The " + jsmlFile.getAbsolutePath()
					+ " is not valid");
		}
		
		this.jsmlFile = jsmlFile;
		reader = new SAXReader(true);
		reader.setIgnoreComments(false);
		reader.setValidation(false);
//		reader.setIncludeExternalDTDDeclarations(true);
//		reader.setIncludeInternalDTDDeclarations(true);
//		reader.setEntityResolver(getEntityResolver());
		reader.setErrorHandler(new SimpleErrorHandler());
		
		synthElements = new ArrayList<ISynthElement>(2);
	}

	private static EntityResolver getEntityResolver() {
		EntityResolver resolver = new EntityResolver() {
			public InputSource resolveEntity(String publicId, String systemId) {
				InputStream in = getClass().getResourceAsStream("jsml.dtd");
				return new InputSource(in);
			}
		};
		return resolver;
	}
	
	public List<ISynthElement> getSynthElements(){
		return synthElements;
	}

	public void parse() {
		try {
			Document document = reader.read(jsmlFile);
			Element root = document.getRootElement();
			parse(root);
			
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void parse(Element e) {
		synthElements.add(ElementBuilder.getSynthElementInstance(e));
		List<Element> elements = e.elements();
		for (Element element : elements) {
			parse(element);
		}
	}

	
	
}
