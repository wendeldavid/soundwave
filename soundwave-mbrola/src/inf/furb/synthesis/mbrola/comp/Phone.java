package inf.furb.synthesis.mbrola.comp;

/**
 * Classe respons�vel por falar os n�meros de um delefone.
 */
public final class Phone {

	public static String processPhone(String elementText) {
		StringBuilder sb = new StringBuilder();
		
		char[] digits = elementText.toCharArray();
		for (char digit : digits) {
			if(Character.isDigit(digit)) {
				long numericDigit = Long.parseLong(String.valueOf(digit));
				sb.append(NumToWord.convert(numericDigit)).append(" ");
			}
		}
		
		return sb.toString();
	}
}
