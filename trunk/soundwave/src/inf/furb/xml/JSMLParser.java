package inf.furb.xml;

<<<<<<< .mine
import inf.furb.synthesis.jsml.Break;
import inf.furb.synthesis.jsml.Div;
import inf.furb.synthesis.jsml.ElementBuilder;
import inf.furb.synthesis.jsml.Emphasis;
import inf.furb.synthesis.jsml.Engine;
import inf.furb.synthesis.jsml.ISynthElement;
import inf.furb.synthesis.jsml.JSML;
import inf.furb.synthesis.jsml.Marker;
import inf.furb.synthesis.jsml.Phoneme;
import inf.furb.synthesis.jsml.Prosody;
import inf.furb.synthesis.jsml.SayAs;
import inf.furb.synthesis.jsml.Voice;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
=======
import inf.furb.synthesis.Break;
import inf.furb.synthesis.Div;
import inf.furb.synthesis.ElementFactory;
import inf.furb.synthesis.Emphasis;
import inf.furb.synthesis.Engine;
import inf.furb.synthesis.ISynthElement;
import inf.furb.synthesis.JSML;
import inf.furb.synthesis.Marker;
import inf.furb.synthesis.Phoneme;
import inf.furb.synthesis.Prosody;
import inf.furb.synthesis.SayAs;
import inf.furb.synthesis.Voice;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
>>>>>>> .r34
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

/**
 * Parser XML que suporta JSML.
 * 
 * @author wendel
 */
<<<<<<< .mine
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
=======
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
		synthElements.add(readElement(e));
		List<Element> elements = e.elements();
		for (Element element : elements) {
			parse(element);
		}
	}

	private ISynthElement readElement(Element element) {
		Class<?> clazz = null;
		final String nodeName = element.getName();
>>>>>>> .r34
		
<<<<<<< .mine
		this.jsmlFile = jsmlFile;
		reader = new SAXReader(true);
		reader.setIgnoreComments(false);
		reader.setValidation(false);
//		reader.setIncludeExternalDTDDeclarations(true);
//		reader.setIncludeInternalDTDDeclarations(true);
//		reader.setEntityResolver(getEntityResolver());
		reader.setErrorHandler(new SimpleErrorHandler());
		
		synthElements = new ArrayList<ISynthElement>(2);
=======
		if(nodeName.equals(ISynthElement.JSML)) {
			clazz = JSML.class;
		}else if(nodeName.equals(ISynthElement.BREAK)) {
			clazz = Break.class;
		}else if(nodeName.equals(ISynthElement.DIV)) {
			clazz = Div.class;
		}else if(nodeName.equals(ISynthElement.EMPHASIS)) {
			clazz = Emphasis.class;
		}else if(nodeName.equals(ISynthElement.ENGINE)) {
			clazz = Engine.class;
		}else if(nodeName.equals(ISynthElement.MARKER)) {
			clazz = Marker.class;
		}else if(nodeName.equals(ISynthElement.PHONEME)) {
			clazz = Phoneme.class;
		}else if(nodeName.equals(ISynthElement.PROSODY)) {
			clazz = Prosody.class;
		}else if(nodeName.equals(ISynthElement.SAYAS)) {
			clazz = SayAs.class;
		}else if(nodeName.equals(ISynthElement.VOICE)) {
			clazz = Voice.class;
		}else {
			throw new RuntimeException("\"" + element.getName() + "\" is not a valid JSML element in line ");
		}
		
		//FIXME remover sysout
		System.out.println(clazz.getName());
		
		return ElementFactory.getSynthElementInstance(element, clazz);
>>>>>>> .r34
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
