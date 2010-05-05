package inf.furb.synthesis.mbrola.comp;

/**
 * Transforma o texto de moeda.
 */
public final class Currency {

	private static final String[][] currencyMap = { { "R$", "reais" }, { "$", "d�lares" } };

	public static final String processCurrency(String text) {
		String copyText = new String(text);
		for (String[] currency : currencyMap) {
			if (text.contains(currency[0])) {
				copyText = copyText.replace(currency[0], currency[1]);//converte o s�mbolo para o nome correspondente
				if(copyText.contains(",")) {
					if(!copyText.contains(",00")) {
						copyText = copyText.replaceAll("([a-z]+)(([0-9]+)([\\.,])([0-9]+))*", "$3 $1 e $5 centavos");//se existem centavos e n�o s�o zero zero (ex: R$6,14)
					}else {
						copyText = copyText.replaceAll("([a-z]+)(([0-9]+)([\\.,])([0-9]+))*", "$3 $1");//se existem centavos e s�o zero zero (ex: R$6,00)
					}
				}else {
					copyText = copyText.replaceAll("([a-z]+)([0-9]+)", "$2 $1");//se nao existe centavos
				}
				
				//escreve por extenso os n�meros
				String[] numbers = copyText.split("[a-zA-Z\\s]+");//retorna apenas grupos de n�meros, ignorando letras e espa�o
				for (int i = 0; i < numbers.length; i++) {
					String numberConverted = NumToWord.convert(Long.parseLong(numbers[i]));
					copyText = copyText.replaceFirst("[0-9]+", numberConverted);
				}
				
				break;
			}
		}
		return copyText;
	}
}
