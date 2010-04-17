package inf.furb.synthesis.jsml;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

final public class Div extends AbstractSynthElement implements ISynthElement {

	public static final String TYPE = "type";
	public static final String TYPE_PARA = "para";
	public static final String TYPE_PARAGRAPH = "paragraph";
	public static final String TYPE_SENT = "sent";
	public static final String TYPE_SENTENCE = "sentence";

	private Map<String, IAttribute> attributes = new HashMap<String, IAttribute>(1);
	
	Div(Element e) {
		final String[] validValues = {TYPE_PARA, TYPE_PARAGRAPH, TYPE_SENT, TYPE_SENTENCE};
		
		AttributeImpl type = new AttributeImpl(TYPE);
		type.setRequired(true);
		type.setValidValues(validValues, TYPE_SENTENCE);
		type.setValue(e.attributeValue(TYPE));
		attributes.put(TYPE, type);
		
		text = e.getTextTrim();
		setMark(e.attributeValue(MARK));
	}
	
	@Override
	public Collection<IAttribute> getAttributes() {
		return Collections.unmodifiableCollection(attributes.values());
	}

	@Override
	public String getName() {
		return "div";
	}

	@Override
	public IAttribute getAttribute(String attName) {
		return attributes.get(attName);
	}

}
