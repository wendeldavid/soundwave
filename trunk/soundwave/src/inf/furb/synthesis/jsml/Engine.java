package inf.furb.synthesis.jsml;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

public final class Engine extends AbstractSynthElement implements ISynthElement {

	public static final String NAME = "name";
	public static final String DATA = "data";
	
	private Map<String, IAttribute> attributes = new HashMap<String, IAttribute>(2);
	
	Engine(Element e) {
		AttributeImpl name = new AttributeImpl(NAME);
		name.setValue(e.attributeValue(NAME));
		attributes.put(NAME, name);
		
		AttributeImpl data = new AttributeImpl(DATA);
		data.setRequired(true);
		data.setValue(e.attributeValue(DATA));
		attributes.put(DATA, data);
		
		setMark(e.attributeValue(MARK));
		text = e.getTextTrim();
	}
	
	@Override
	public Collection<IAttribute> getAttributes() {
		return Collections.unmodifiableCollection(attributes.values());
	}

	@Override
	public String getName() {
		return "engine";
	}

	@Override
	public IAttribute getAttribute(String attName) {
		return attributes.get(attName);
	}

}
