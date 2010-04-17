package inf.furb.synthesis.jsml;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

public final class Voice extends AbstractSynthElement implements ISynthElement{

	public static final String GENDER = "gender";
	public static final String GENDER_MALE = "male";
	public static final String GENDER_FEMALE = "female";
	public static final String GENDER_NEUTRAL = "neutral";
	
	public static final String AGE = "age";
	public static final String VARIANT = "variant";
	public static final String NAME = "name";
	
	private Map<String, IAttribute> attributes = new HashMap<String, IAttribute>(3);;
	
	Voice(Element e) {
		
		final String[] genderValidValues = {GENDER_MALE, GENDER_FEMALE, GENDER_NEUTRAL};
		IAttribute gender = new AttributeImpl(GENDER);
		gender.setValidValues(genderValidValues, "");
		gender.setValue(e.attributeValue(GENDER));
		attributes.put(GENDER, gender);
		
		IAttribute age = new AttributeImpl(AGE);
		age.setValue(e.attributeValue(AGE));
		attributes.put(AGE, age);
		
		IAttribute variant = new AttributeImpl(VARIANT);
		variant.setValue(e.attributeValue(VARIANT));
		attributes.put(VARIANT, variant);
		
		IAttribute name = new AttributeImpl(NAME);
		name.setValue(e.attributeValue(NAME));
		attributes.put(NAME, name);
		
		
		setMark(e.attributeValue(MARK));
		text = e.getTextTrim();
	}
	
	@Override
	public Collection<IAttribute> getAttributes() {
		return Collections.unmodifiableCollection(attributes.values());
	}

	@Override
	public String getName() {
		return "voice";
	}

	@Override
	public IAttribute getAttribute(String attName) {
		return attributes.get(attName);
	}

}
