package inf.furb.synthesis;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

final public class Break extends AbstractSynthElement implements ISynthElement {

	public static final String SIZE = "size";
	public static final String TIME = "time";

	public static final String SIZE_NONE = "none";
	public static final String SIZE_SMALL = "small";
	public static final String SIZE_MEDIUM = "medium";
	public static final String SIZE_LARGE = "large";
	
	private IAttribute size;
	private IAttribute time;
	
	Break(Element e) {
		final String[] sizeValidValues = {SIZE_NONE, SIZE_SMALL, SIZE_MEDIUM, SIZE_LARGE};
		size = new AttributeImpl(SIZE);
		size.setRequired(false);
		size.setValidValues(sizeValidValues, SIZE_MEDIUM);
		size.setValue(e.attributeValue(SIZE));
		
		time = new AttributeImpl(TIME);
		time.setValue(e.attributeValue(TIME));
		
		text = e.getTextTrim();
		setMark(e.attributeValue(MARK));
	}
	
	@Override
	public List<IAttribute> getAttributes() {
		ArrayList<IAttribute> ret = new ArrayList<IAttribute>(2);
		ret.add(size);
		ret.add(time);
		return ret;
	}

	@Override
	public void setAttribute(String name, String value) {
		if(name.equals(SIZE)) {
			size.setValue(value);
		}else if(name.equals(TIME)) {
			time.setValue(value);
		}
	}
	
	@Override
	public List<ISynthElement> getInnerElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return "break";
	}

}
