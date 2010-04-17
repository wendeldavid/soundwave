package inf.furb.synthesis.jsml;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

final public class Emphasis extends AbstractSynthElement implements ISynthElement {

	public static final String LEVEL = "level";
	public static final String LEVEL_NONE = "none";
	public static final String LEVEL_MODERATE = "moderate";
	public static final String LEVEL_STRONG = "strong";
	
	private Map<String, IAttribute> attributes = new HashMap<String, IAttribute>(1);
	
	Emphasis(Element e) {
		final String[] validValues = {LEVEL_NONE, LEVEL_MODERATE, LEVEL_STRONG};
		AttributeImpl level = new AttributeImpl(LEVEL);
		level.setRequired(false);
		level.setValidValues(validValues, LEVEL_MODERATE);
		level.setValue(e.attributeValue(LEVEL));
		attributes.put(LEVEL, level);
		
		text = e.getTextTrim(); 
		setMark(e.attributeValue(MARK));
	}
	
	@Override
	public Collection<IAttribute> getAttributes() {
		return Collections.unmodifiableCollection(attributes.values());
	}

	@Override
	public String getName() {
		return "emphasis";
	}

	@Override
	public IAttribute getAttribute(String attName) {
		return attributes.get(attName);
	}

}
