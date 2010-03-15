package inf.furb.synthesis.jsml;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public final class Prosody extends AbstractSynthElement implements ISynthElement {

	public static final String RATE = "rate";
	public static final String VOLUME = "volume";
	public static final String PITCH = "pitch"; 
	public static final String RANGE = "range";
	
	private IAttribute rate;
	private IAttribute volume;
	private IAttribute pitch;
	private IAttribute range;
	
	Prosody(Element e) {
		rate = new AttributeImpl(RATE);
		rate.setValue(e.attributeValue(RATE));
		
		volume = new AttributeImpl(VOLUME);
		volume.setValue(e.attributeValue(VOLUME));
		
		pitch = new AttributeImpl(PITCH);
		pitch.setValue(e.attributeValue(PITCH));
		
		range = new AttributeImpl(RANGE);
		range.setValue(e.attributeValue(RANGE));
		
		setMark(e.attributeValue(MARK));
		text = e.getTextTrim();
	}
	
	@Override
	public List<IAttribute> getAttributes() {
		List<IAttribute> ret = new ArrayList<IAttribute>(4);
		ret.add(rate);
		ret.add(volume);
		ret.add(pitch);
		ret.add(range);
		return ret;
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
		if(name.equals(RATE)){
			rate.setValue(value);
		}else if(name.equals(VOLUME)) {
			volume.setValue(value);
		}else if(name.equals(pitch)) {
			pitch.setValue(value);
		}else if(name.equals(RANGE)) {
			range.setValue(RANGE);
		}
	}

}
