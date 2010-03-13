package inf.furb.synthesis;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public final class JSML extends AbstractSynthElement implements ISynthElement {
	
	public static final String LANG = "lang";

	private IAttribute lang;

	JSML(Element e) {
		lang = new AttributeImpl(LANG);
		lang.setRequired(false);
		lang.setValue(e.attributeValue(LANG));
		
		setMark(e.attributeValue(MARK));
		text = e.getTextTrim();
	}
	
	@Override
	public List<IAttribute> getAttributes() {
		List<IAttribute> ret = new ArrayList<IAttribute>(1);
		ret.add(lang);
		return ret;
	}

	@Override
	public List<ISynthElement> getInnerElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return "jsml";
	}

	@Override
	public void setAttribute(String name, String value) {
		if(name.equals(LANG)) {
			lang.setValue(value);
		}
	}

}
