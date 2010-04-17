package inf.furb.synthesis.mbrola.converter;

import java.math.BigInteger;

import inf.furb.synthesis.jsml.ISynthElement;
import inf.furb.synthesis.jsml.SayAs;
import inf.furb.synthesis.mbrola.comp.ComponentGlobals;
import inf.furb.synthesis.mbrola.comp.IComponent;
import inf.furb.synthesis.mbrola.comp.NumToWord;
import inf.furb.synthesis.mbrola.comp.Text;

class SayAsConverter implements IConverter{

	@Override
	public void convert(ISynthElement element, StringBuilder output) {
		IComponent text = null;
		if (SayAs.CLASS_NUMBER.equals(element.getAttribute(SayAs.CLASS).getValue())) {
			String numToWord = NumToWord.convert(new BigInteger(element.getText()));
			text = new Text(numToWord);
		}else {
			text = new Text(element.getText());
		}

		if (text != null) {
			text.configure(ComponentGlobals.BASE_FREQUENCY, ComponentGlobals.BASE_TIME);
			output.append(text.show());
		}
	}

	
	
}
