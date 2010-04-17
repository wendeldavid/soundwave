package inf.furb.synthesis.jsml;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

public final class Prosody extends AbstractSynthElement implements ISynthElement {

	public static final String RATE = "rate";
	public static final String VOLUME = "volume";
	public static final String PITCH = "pitch"; 
	public static final String RANGE = "range";
	
	
	private Map<String, IAttribute> attributes = new HashMap<String, IAttribute>(4);
	
	Prosody(Element e) {
		IAttribute rate = new AttributeImpl(RATE);
		rate.setValue(e.attributeValue(RATE));
		
		IAttribute volume = new AttributeImpl(VOLUME);
		volume.setValue(e.attributeValue(VOLUME));
		
		IAttribute pitch = new AttributeImpl(PITCH);
		pitch.setValue(e.attributeValue(PITCH));
		
		IAttribute range = new AttributeImpl(RANGE);
		range.setValue(e.attributeValue(RANGE));
		
		setMark(e.attributeValue(MARK));
		text = e.getTextTrim();
	}
	
	@Override
	public Collection<IAttribute> getAttributes() {
		return Collections.unmodifiableCollection(attributes.values());
	}

	@Override
	public String getName() {
		return "phoneme";
	}

	@Override
	public IAttribute getAttribute(String attName) {
		return attributes.get(attName);
	}

}
