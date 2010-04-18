package inf.furb.synthesis.mbrola.comp;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe que define padrões de medidas.
 */
public final class Measures {

	private static final Map<String, String> measuresMapSpeed = new HashMap<String, String>(3);
	
	private static final Map<String, String> measuresMap = new HashMap<String, String>();
	static {
		//velocidade
		measuresMapSpeed.put("/h", "por hora");
		measuresMapSpeed.put("/m", "por minuto");
		measuresMapSpeed.put("/s", "por segundo");
		
		// comprimento/area
		measuresMap.put("Km", "quilômetros");
		measuresMap.put("Hm", "hectômetros");
		measuresMap.put("Dm", "decâmetros");
		measuresMap.put("m", "metros");
		measuresMap.put("dc", "decímetros");
		measuresMap.put("cm", "centímetros");
		measuresMap.put("mm", "milímetros");
		measuresMap.put("mi", "milhas");
		measuresMap.put("yd", "jardas");
		measuresMap.put("ft", "psé");
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
		// pressão
		measuresMap.put("atm", "atmosferas");
		measuresMap.put("psi", "libras");
		// potência
		measuresMap.put("W", "watts");
		measuresMap.put("cv", "cavalos vapor");
		measuresMap.put("hp", "cavalos de potência");
		// temperatura
		measuresMap.put("ºC", "graus celcius");
		measuresMap.put("ºF", "graus farenheints");
		measuresMap.put("K", "kelvins");
		// volume
		measuresMap.put("KL", "quilolitros");
		measuresMap.put("L", "litros");
		measuresMap.put("mL", "mililitros");
		measuresMap.put("bbl", "barris");
		measuresMap.put("gal", "galões");
	}

	/**
	 * Retorna a medida por extenso.Ex:<br>
	 * <code>
	 * símbolo = Km<br>
	 * retorno = quilômetros
	 * </code>
	 * 
	 * @param measureSymbol
	 *            símbolo da medida
	 * @return medida por extenso
	 */
	public static String getMeasure(String measureSymbol) {
		return measuresMap.get(measureSymbol);
	}

	/**
	 * Processa o texto procurando por alguma ocorrência de símbolos de alguma medida e substitui pela medida por extenso.<br>
	 * <b>Este método pode se tornar lento dependendo do tamanho da string analisada. Tente restringir o texto a ser analisado.</b>
	 * 
	 * @param text
	 *            texto a ser analisado
	 * @param number
	 *            transformar números por extenso
	 * @return texto com as medidas substituídas e os valores por extenso
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
	 * Processa o texto procurando por alguma ocorrência de símbolos de alguma medida e substitui pela medida por extenso.<br>
	 * <b>Este método pode se tornar lento dependendo do tamanho da string analisada. Tente restringir o texto a ser analisado.</b>
	 * 
	 * @param text
	 *            texto a ser analisado
	 * @return texto com as medidas substituídas
	 */
	public static String processMeasure(String text) {
		String copyText = new String(text);
		copyText = findReplaceMeasureOccurrence(copyText);
		return copyText;
	}

	/**
	 * Converte os números em texto por extenso.
	 * 
	 * @param text
	 *            texto
	 * @return texto com números por extenso
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
				copyText = copyText.replaceAll(key, " " + measuresMap.get(key));
				break;
			}
		}
		//gambi das braba pra fazer replace do /h se for Km/h por exemplo
		for (String key : measuresMapSpeed.keySet()) {
			if (text.contains(key)) {
				copyText = copyText.replaceAll(key, " " + measuresMapSpeed.get(key));
				break;
			}
		}
		return copyText;
	}
}
