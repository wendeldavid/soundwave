package inf.furb.xml;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SimpleErrorHandler implements ErrorHandler {
    public void warning(SAXParseException e) throws SAXException {
        e.printStackTrace();
    }

    public void error(SAXParseException e) throws SAXException {
        e.printStackTrace();
    }

    public void fatalError(SAXParseException e) throws SAXException {
        e.printStackTrace();
    }
}
