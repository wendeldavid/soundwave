package inf.furb.synthesis;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

final public class Div extends AbstractSynthElement implements ISynthElement {

	public static final String TYPE = "type";
	public static final String TYPE_PARA = "para";
	public static final String TYPE_PARAGRAPH = "paragraph";
	public static final String TYPE_SENT = "sent";
	public static final String TYPE_SENTENCE = "sentence";
	
	public IAttribute type;
	
	Div(Element e) {
		final String[] validValues = {TYPE_PARA, TYPE_PARAGRAPH, TYPE_SENT, TYPE_SENTENCE};
		
		type = new AttributeImpl(TYPE);
		type.setRequired(true);
		type.setValidValues(validValues, TYPE_SENTENCE);
		type.setValue(e.attributeValue(TYPE));
		
		text = e.getTextTrim();
		setMark(e.attributeValue(MARK));
	}
	
	@Override
	public List<IAttribute> getAttributes() {
		ArrayList<IAttribute> ret = new ArrayList<IAttribute>(1);
		ret.add(type);
		return ret;
	}

	@Override
	public void setAttribute(String name, String value) {
		if(name.equals(TYPE)) {
			this.type.setValue(value);
		}
	}
	
	@Override
	public List<ISynthElement> getInnerElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return "div";
	}

}
