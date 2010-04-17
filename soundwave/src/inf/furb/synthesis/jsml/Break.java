package inf.furb.synthesis.jsml;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

/**
 * Representa uma pausa.
 */
final public class Break extends AbstractSynthElement implements ISynthElement {

	public static final String SIZE = "size";
	public static final String TIME = "time";

	public static final String SIZE_NONE = "none";
	public static final String SIZE_SMALL = "small";
	public static final String SIZE_MEDIUM = "medium";
	public static final String SIZE_LARGE = "large";
	
	private Map<String, IAttribute> attributes = new HashMap<String, IAttribute>(2);
	
	Break(Element e) {
		final String[] sizeValidValues = {null, SIZE_NONE, SIZE_SMALL, SIZE_MEDIUM, SIZE_LARGE};
		AttributeImpl size = new AttributeImpl(SIZE);
		size.setRequired(false);
		size.setValidValues(sizeValidValues, SIZE_MEDIUM);
		size.setValue(e.attributeValue(SIZE));
		attributes.put(SIZE, size);
		
		AttributeImpl time = new AttributeImpl(TIME);
		time.setRequired(false);
		time.setValue(e.attributeValue(TIME));
		attributes.put(TIME, time);
		
		text = e.getTextTrim();
		setMark(e.attributeValue(MARK));
	}
	
	@Override
	public Collection<IAttribute> getAttributes() {
		return Collections.unmodifiableCollection(attributes.values());
	}

	@Override
	public String getName() {
		return "break";
	}

	@Override
	public IAttribute getAttribute(String attName) {
		return attributes.get(attName);
	}

}
