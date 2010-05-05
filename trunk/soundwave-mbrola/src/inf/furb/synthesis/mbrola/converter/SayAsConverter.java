package inf.furb.synthesis.mbrola.converter;

import inf.furb.synthesis.jsml.ISynthElement;
import inf.furb.synthesis.jsml.SayAs;
import inf.furb.synthesis.mbrola.comp.Address;
import inf.furb.synthesis.mbrola.comp.Calendar;
import inf.furb.synthesis.mbrola.comp.ComponentGlobals;
import inf.furb.synthesis.mbrola.comp.Currency;
import inf.furb.synthesis.mbrola.comp.IComponent;
import inf.furb.synthesis.mbrola.comp.Measures;
import inf.furb.synthesis.mbrola.comp.Net;
import inf.furb.synthesis.mbrola.comp.NumToWord;
import inf.furb.synthesis.mbrola.comp.Phone;
import inf.furb.synthesis.mbrola.comp.Speller;
import inf.furb.synthesis.mbrola.comp.Text;

import java.math.BigInteger;

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

		}else if(SayAs.CLASS_DATE.equals(elementClass)) {
			String date = Calendar.formatDate(elementText);
			text = new Text(date);
			
		}else if(SayAs.CLASS_TIME.equals(elementClass)) {
			String time = Calendar.formatTime(elementText);
			text = new Text(time);
			
		}else if(SayAs.CLASS_MEASURE.equals(elementClass)) {
			String measure = Measures.processMeasure(elementText, true);
			text = new Text(measure);
			
		}else if(SayAs.CLASS_NET.equals(elementClass)) {
			String net = Net.processNet(elementText);
			text = new Text(net);
			
		}else if(SayAs.CLASS_ADDRESS.equals(elementClass)) {
			String address = Address.processAddress(elementText);
			text = new Text(address);
			
		}else if(SayAs.CLASS_CURRENCY.equals(elementClass)) {
			String currency = Currency.processCurrency(elementText);
			text = new Text(currency);
			
		}else if(SayAs.CLASS_PHONE.equals(elementClass)) {
			String phone = Phone.processPhone(elementText);
			text = new Text(phone);
			
		}else {
			text = new Text(element.getText());
		}

		if (text != null) {
			text.configure(ComponentGlobals.BASE_FREQUENCY, ComponentGlobals.BASE_TIME);
			output.append(text.show());
		}
	}

	
	
}
