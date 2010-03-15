package inf.furb.synthesis.jsml;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

final public class Emphasis extends AbstractSynthElement implements ISynthElement {

	public static final String LEVEL = "level";
	public static final String LEVEL_NONE = "none";
	public static final String LEVEL_MODERATE = "moderate";
	public static final String LEVEL_STRONG = "strong";
	
	private IAttribute level;
	
	Emphasis(Element e) {
		final String[] validValues = {LEVEL_NONE, LEVEL_MODERATE, LEVEL_STRONG};
		level = new AttributeImpl(LEVEL);
		level.setRequired(false);
		level.setValidValues(validValues, LEVEL_MODERATE);
		level.setValue(e.attributeValue(LEVEL));
		
		text = e.getTextTrim(); 
		setMark(e.attributeValue(MARK));
	}
	
	@Override
	public List<IAttribute> getAttributes() {
		List<IAttribute> ret = new ArrayList<IAttribute>(1);
		ret.add(level);
		return ret;
	}

	@Override
	public List<ISynthElement> getInnerElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return "emphasis";
	}

	@Override
	public void setAttribute(String name, String value) {
		if(name.equals(LEVEL)) {
			level.setValue(value);
		}
	}

}
