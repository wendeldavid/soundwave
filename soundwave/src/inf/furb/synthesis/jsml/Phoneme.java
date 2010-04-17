package inf.furb.synthesis.jsml;

import java.util.Collection;

import org.dom4j.Element;

final public class Phoneme extends AbstractSynthElement implements ISynthElement {

	public static final String PHONEME = "phoneme";

	Phoneme(Element e) {
		setMark(e.attributeValue(MARK));
		text = e.getTextTrim();
	}

	@Override
	public Collection<IAttribute> getAttributes() {
		return null;
	}

	@Override
	public String getName() {
		return "phoneme";
	}

	@Override
	public IAttribute getAttribute(String attName) {
		// do nothing
		return null;
	}

}
