package inf.furb.synthesis;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public final class Voice extends AbstractSynthElement implements ISynthElement{

	public static final String GENDER = "gender";
	public static final String GENDER_MALE = "male";
	public static final String GENDER_FEMALE = "female";
	public static final String GENDER_NEUTRAL = "neutral";
	
	public static final String AGE = "age";
	public static final String VARIANT = "variant";
	public static final String NAME = "name";
	
	private IAttribute gender;
	private IAttribute age;
	private IAttribute variant;
	private IAttribute name;
	
	Voice(Element e) {
		final String[] genderValidValues = {GENDER_MALE, GENDER_FEMALE, GENDER_NEUTRAL};
		gender = new AttributeImpl(GENDER);
		gender.setValidValues(genderValidValues, "");
		gender.setValue(e.attributeValue(GENDER));
		
		age = new AttributeImpl(AGE);
		age.setValue(e.attributeValue(AGE));
		
		variant = new AttributeImpl(VARIANT);
		variant.setValue(e.attributeValue(VARIANT));
		
		name = new AttributeImpl(NAME);
		name.setValue(e.attributeValue(NAME));
		
		setMark(e.attributeValue(MARK));
		text = e.getTextTrim();
	}
	
	@Override
	public List<IAttribute> getAttributes() {
		List<IAttribute> ret = new ArrayList<IAttribute>(4);
		ret.add(gender);
		ret.add(age);
		ret.add(variant);
		ret.add(name);
		return ret;
	}

	@Override
	public List<ISynthElement> getInnerElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return "voice";
	}

	@Override
	public void setAttribute(String name, String value) {
		if(name.equals(GENDER)) {
			this.gender.setValue(value);
		}else if(name.equals(AGE)) {
			this.age.setValue(value);
		}else if(name.equals(VARIANT)) {
			this.variant.setValue(value);
		}else if(name.equals(NAME)) {
			this.name.setValue(value);
		}
	}
}
