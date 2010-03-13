package inf.furb.synthesis;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public final class Engine extends AbstractSynthElement implements ISynthElement {

	public static final String NAME = "name";
	public static final String DATA = "data";
	
	private IAttribute name;
	private IAttribute data;
	
	Engine(Element e) {
		name = new AttributeImpl(NAME);
		name.setValue(e.attributeValue(NAME));
		
		data = new AttributeImpl(DATA);
		data.setRequired(true);
		data.setValue(e.attributeValue(DATA));
		
		setMark(e.attributeValue(MARK));
		text = e.getTextTrim();
	}
	
	@Override
	public List<IAttribute> getAttributes() {
		List<IAttribute> ret = new ArrayList<IAttribute>(2);
		ret.add(name);
		ret.add(data);
		return ret;
	}

	@Override
	public List<ISynthElement> getInnerElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return "engine";
	}

	@Override
	public void setAttribute(String name, String value) {
		if(name.equals(DATA)) {
			this.data.setValue(value);
		}else if(name.equals(NAME)) {
			this.name.setValue(value);
		}
	}

}
