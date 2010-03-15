package inf.furb.synthesis.jsml;

import java.util.List;

import org.dom4j.Element;

final public class Phoneme extends AbstractSynthElement implements ISynthElement {

	public static final String PHONEME = "phoneme";
	
	Phoneme(Element e) {
		setMark(e.attributeValue(MARK));
		text = e.getTextTrim();
	}
	
	@Override
	public List<IAttribute> getAttributes() {
		return null;
	}

	@Override
	public List<ISynthElement> getInnerElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return "phoneme";
	}

	@Override
	public void setAttribute(String name, String value) {
		//do nothing
	}

}
