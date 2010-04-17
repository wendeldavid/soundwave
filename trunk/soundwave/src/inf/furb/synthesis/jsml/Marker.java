package inf.furb.synthesis.jsml;

import java.util.Collection;

import org.dom4j.Element;

public final class Marker extends AbstractSynthElement implements ISynthElement {

	Marker(Element e){
		text = e.getTextTrim();
		setMark(e.attributeValue(MARK));
	}
	
	@Override
	public String getName() {
		return "marker";
	}

	@Override
	public IAttribute getAttribute(String attName) {
		//do nothing
		return null;
	}

	@Override
	public Collection<IAttribute> getAttributes() {
		//do nothing
		return null;
	}

}
