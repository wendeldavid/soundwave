package inf.furb.synthesis.mbrola.comp;

/**
 * Classe responsável por falar os números de um delefone.
 */
public final class Phone {

	public static String processPhone(String elementText) {
		StringBuilder sb = new StringBuilder();
		
		char[] digits = elementText.toCharArray();
		for (int i = 0; i < digits.length; i++) {
			if(Character.isDigit(digits[i])) {
				long digit = Long.parseLong(String.valueOf(digits[i]));
				sb.append(NumToWord.convert(digit)).append(" ");
			}
		}
		
		return sb.toString();
	}
}
