package inf.furb.synthesis.jsml;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

public final class SayAs extends AbstractSynthElement implements ISynthElement {

	public static final String CLASS = "class";
	public static final String CLASS_LITERAL = "literal";
	public static final String CLASS_DATE = "date";
	public static final String CLASS_TIME = "time";
	public static final String CLASS_NAME = "name";
	public static final String CLASS_PHONE = "phone";
	public static final String CLASS_NET = "net";
	public static final String CLASS_ADDRESS = "address";
	public static final String CLASS_CURRENCY = "currency";
	public static final String CLASS_MEASURE = "measure";
	public static final String CLASS_NUMBER = "number";

	private Map<String, IAttribute> attributes = new HashMap<String, IAttribute>(1);

	SayAs(Element e) {
		final String[] validValues = { null, CLASS_LITERAL, CLASS_DATE, //
				CLASS_TIME, CLASS_NAME,// 
				CLASS_PHONE, CLASS_NET, //
				CLASS_ADDRESS, CLASS_CURRENCY, //
				CLASS_MEASURE, CLASS_NUMBER //
		};
		
		IAttribute clazz = new AttributeImpl(CLASS);
		clazz.setRequired(false);
		clazz.setValidValues(validValues, "");
		clazz.setValue(e.attributeValue(CLASS));
		attributes.put(CLASS, clazz);
		
		setMark(e.attributeValue(MARK));
		text = e.getTextTrim();
	}
	
	@Override
	public Collection<IAttribute> getAttributes() {
		return Collections.unmodifiableCollection(attributes.values());
	}

	@Override
	public String getName() {
		return "sayas";
	}

	@Override
	public IAttribute getAttribute(String attName) {
		return attributes.get(attName);
	}
}
