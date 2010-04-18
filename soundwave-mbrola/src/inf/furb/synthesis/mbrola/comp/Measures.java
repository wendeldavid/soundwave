package inf.furb.synthesis.mbrola.comp;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe que define padr�es de medidas.
 */
public final class Measures {

	private static final Map<String, String> measuresMap = new HashMap<String, String>();
	static {
		// comprimento/area
		measuresMap.put("Km", "quil�metros");
		measuresMap.put("Hm", "hect�metros");
		measuresMap.put("Dm", "dec�metros");
		measuresMap.put("m", "metros");
		measuresMap.put("dc", "dec�metros");
		measuresMap.put("cm", "cent�metros");
		measuresMap.put("mm", "mil�metros");
		measuresMap.put("mi", "milhas");
		measuresMap.put("yd", "jardas");
		measuresMap.put("ft", "ps�");
		measuresMap.put("", "");
		measuresMap.put("\"", "polegadas");
		// massa
		measuresMap.put("lb", "libras");
		measuresMap.put("@", "arrobas");
		measuresMap.put("t", "toneladas");
		measuresMap.put("Kg", "quilogramas");
		measuresMap.put("Hg", "hectogramas");
		measuresMap.put("Dg", "decagramas");
		measuresMap.put("g", "gramas");
		measuresMap.put("dg", "decigramas");
		measuresMap.put("cg", "centrigramas");
		measuresMap.put("mg", "miligramas");
		// press�o
		measuresMap.put("atm", "atmosferas");
		measuresMap.put("psi", "libras");
		// pot�ncia
		measuresMap.put("W", "watts");
		measuresMap.put("cv", "cavalos vapor");
		measuresMap.put("hp", "cavalos de pot�ncia");
		// temperatura
		measuresMap.put("�C", "graus celcius");
		measuresMap.put("�F", "graus farenheints");
		measuresMap.put("K", "kelvins");
		// volume
		measuresMap.put("KL", "quilolitros");
		measuresMap.put("L", "litros");
		measuresMap.put("mL", "mililitros");
		measuresMap.put("bbl", "barris");
		measuresMap.put("gal", "gal�es");
	}

	/**
	 * Retorna a medida por extenso.Ex:<br>
	 * <code>
	 * s�mbolo = Km<br>
	 * retorno = quil�metros
	 * </code>
	 * 
	 * @param measureSymbol
	 *            s�mbolo da medida
	 * @return medida por extenso
	 */
	public static String getMeasure(String measureSymbol) {
		return measuresMap.get(measureSymbol);
	}

	/**
	 * Processa o texto procurando por alguma ocorr�ncia de s�mbolos de alguma medida e substitui pela medida por extenso.<br>
	 * <b>Este m�todo pode se tornar lento dependendo do tamanho da string analisada. Tente restringir o texto a ser analisado.</b>
	 * 
	 * @param text
	 *            texto a ser analisado
	 * @param number
	 *            transformar n�meros por extenso
	 * @return texto com as medidas substitu�das e os valores por extenso
	 */
	public static String processMeasure(final String text, boolean number) {
		String copyText = new String(text);
		copyText = processMeasure(copyText);
		
		if (number) {
			copyText = findReplaceNumberOccurrence(copyText);
		}
		return copyText;
	}

	/**
	 * Processa o texto procurando por alguma ocorr�ncia de s�mbolos de alguma medida e substitui pela medida por extenso.<br>
	 * <b>Este m�todo pode se tornar lento dependendo do tamanho da string analisada. Tente restringir o texto a ser analisado.</b>
	 * 
	 * @param text
	 *            texto a ser analisado
	 * @return texto com as medidas substitu�das
	 */
	public static String processMeasure(String text) {
		String copyText = new String(text);
		copyText = findReplaceMeasureOccurrence(copyText);
		return copyText;
	}

	/**
	 * Converte os n�meros em texto por extenso.
	 * 
	 * @param text
	 *            texto
	 * @return texto com n�meros por extenso
	 */
	private static String findReplaceNumberOccurrence(String text) {
		String copyText = new String(text);

		Pattern pattern = Pattern.compile("([0-9])+([\\.,]([0-9]+))*");
		Matcher matcher = pattern.matcher(text);

		while (matcher.find()) {
			String numberFinded = matcher.group();
			BigInteger number = new BigInteger(numberFinded.replaceAll("[\\.,]", ""));
			copyText = copyText.replace(numberFinded, NumToWord.convert(number));
		}

		return copyText;
	}

	/**
	 * Procura e troca o simbolo da medida por ela em extenso.
	 * 
	 * @param text
	 * @return texto com as medidas substituidas
	 */
	private static String findReplaceMeasureOccurrence(String text) {
		String copyText = new String(text);
		for (String key : measuresMap.keySet()) {
			if (text.contains(key)) {
				copyText = copyText.replaceAll(key, measuresMap.get(key)) + " ";
			}
		}
		return copyText;
	}

	public static void main(String[] args) {
		String s = "123Km 6,45psi";

		System.out.println(processMeasure(s, true));
	}

}
