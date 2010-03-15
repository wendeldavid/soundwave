package inf.furb.synthesis.jsml;

import java.util.ArrayList;
import java.util.List;

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

	private IAttribute clazz;

	SayAs(Element e) {
		final String[] validValues = { CLASS_LITERAL, CLASS_DATE, //
				CLASS_TIME, CLASS_NAME,// 
				CLASS_PHONE, CLASS_NET, //
				CLASS_ADDRESS, CLASS_CURRENCY, //
				CLASS_MEASURE, CLASS_NUMBER //
		};
		
		clazz = new AttributeImpl(CLASS);
		clazz.setRequired(true);
		clazz.setValidValues(validValues, "");
		clazz.setValue(e.attributeValue(CLASS));
		
		setMark(e.attributeValue(MARK));
		text = e.getTextTrim();
	}
	
	@Override
	public List<IAttribute> getAttributes() {
		List<IAttribute> ret = new ArrayList<IAttribute>(1);
		ret.add(clazz);
		return ret;
	}

	@Override
	public List<ISynthElement> getInnerElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return "sayas";
	}

	@Override
	public void setAttribute(String name, String value) {
		if(name.equals(CLASS)) {
			clazz.setValue(value);
		}
	}
}
