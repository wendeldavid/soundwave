package inf.furb.synthesis.jsml;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

public final class JSML extends AbstractSynthElement implements ISynthElement {
	
	public static final String LANG = "lang";

	private Map<String, IAttribute> attributes = new HashMap<String, IAttribute>(1);;

	JSML(Element e) {
		IAttribute lang = new AttributeImpl(LANG);
		lang.setRequired(false);
		lang.setValue(e.attributeValue(LANG));
		attributes.put(LANG, lang);
		
		setMark(e.attributeValue(MARK));
		text = e.getTextTrim();
	}
	
	@Override
	public Collection<IAttribute> getAttributes() {
		return Collections.unmodifiableCollection(attributes.values());
	}

	@Override
	public String getName() {
		return "jsml";
	}

	@Override
	public IAttribute getAttribute(String attName) {
		return attributes.get(attName);
	}

}
