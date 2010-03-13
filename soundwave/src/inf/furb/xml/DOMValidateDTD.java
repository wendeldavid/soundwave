package inf.furb.xml;

import inf.furb.common.ResourcePool;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DOMValidateDTD {
	public static void validate(File xmlFile) {
		File xml = ResourcePool.copyFile(xmlFile);
		File dtd = ResourcePool.copyFile(new File(DOMValidateDTD.class.getResource("jsml.dtd").getFile()));
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			builder.setErrorHandler(new org.xml.sax.ErrorHandler() {
				
				// Ignore the fatal errors
				@Override
				public void fatalError(SAXParseException exception) throws SAXException {
					exception.printStackTrace();
				}

				// Validation errors
				@Override
				public void error(SAXParseException e) throws SAXParseException {
					System.out.println("Error at " + e.getLineNumber() + " line.");
//					System.out.println(e.getMessage());
					e.printStackTrace();
				}

				// Show warnings
				@Override
				public void warning(SAXParseException err) throws SAXParseException {
//					System.out.println(err.getMessage());
					err.printStackTrace();
				}

			});
			
			
			Document xmlDocument = builder.parse(xml);
			DOMSource source = new DOMSource(xmlDocument);
			StreamResult result = new StreamResult(System.out);

			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, dtd.getAbsolutePath());
			
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}