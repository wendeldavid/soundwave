package inf.furb.synthesis.jsml;

import java.util.List;

import org.dom4j.Element;

public final class Marker extends AbstractSynthElement implements ISynthElement {

	Marker(Element e){
		text = e.getTextTrim();
		setMark(e.attributeValue(MARK));
	}
	
	@Override
	public List<IAttribute> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ISynthElement> getInnerElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return "marker";
	}

	@Override
	public void setAttribute(String name, String value) {
		// TODO Auto-generated method stub

	}

}
