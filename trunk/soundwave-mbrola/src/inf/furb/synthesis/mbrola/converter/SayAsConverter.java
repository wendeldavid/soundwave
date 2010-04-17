package inf.furb.synthesis.mbrola.converter;

import java.math.BigInteger;

import inf.furb.synthesis.jsml.ISynthElement;
import inf.furb.synthesis.jsml.SayAs;
import inf.furb.synthesis.mbrola.comp.ComponentGlobals;
import inf.furb.synthesis.mbrola.comp.IComponent;
import inf.furb.synthesis.mbrola.comp.NumToWord;
import inf.furb.synthesis.mbrola.comp.Speller;
import inf.furb.synthesis.mbrola.comp.Text;

class SayAsConverter implements IConverter{

	@Override
	public void convert(ISynthElement element, StringBuilder output) {
		IComponent text = null;
		final String elementText = element.getText();
		final String elementClass = element.getAttribute(SayAs.CLASS).getValue();
		if (SayAs.CLASS_NUMBER.equals(elementClass)) {
			String numToWord = NumToWord.convert(new BigInteger(elementText));
			text = new Text(numToWord);
			
		}else if(SayAs.CLASS_LITERAL.equals(elementClass)) {
			text = new Speller(elementText);
			
		}else {
			text = new Text(element.getText());
		}

		if (text != null) {
			text.configure(ComponentGlobals.BASE_FREQUENCY, ComponentGlobals.BASE_TIME);
			output.append(text.show());
		}
	}

	
	
}
