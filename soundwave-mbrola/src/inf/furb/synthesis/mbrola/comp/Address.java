package inf.furb.synthesis.mbrola.comp;

import java.util.HashMap;

/**
 *q
 */
public final class Address {

	private static final HashMap<String, String> addressesMap = new HashMap<String, String>();
	
	static {
		addressesMap.put("av.", "avenida");
		addressesMap.put("r.", "rua");
		addressesMap.put("n�", "n�mero");
		addressesMap.put("UF", "Unidade Federativa");
		//estados
		addressesMap.put("AC", "Acre");
		addressesMap.put("AL", "Alagoas");
		addressesMap.put("AP", "Amap�");
		addressesMap.put("AM", "Amazonas");
		addressesMap.put("BA", "Bahia");
		addressesMap.put("CE", "Cear�");
		addressesMap.put("DF", "Distrito Federal");
		addressesMap.put("ES", "Esp�rito Santo");
		addressesMap.put("GO", "Goi�s");
		addressesMap.put("MA", "Maranh�o");
		addressesMap.put("MT", "Mato Grosso");
		addressesMap.put("MS", "Mato Grosso do Sul");
		addressesMap.put("MG", "Minas Gerais");
		addressesMap.put("PA", "Par�");
		addressesMap.put("PB", "Pernanbuco");
		addressesMap.put("PR", "Paran�");
		addressesMap.put("PE", "Pernambuco");
		addressesMap.put("PI", "Piau�");
		addressesMap.put("RJ", "Rio de Janeiro");
		addressesMap.put("RN", "Rio Grande do Norte");
		addressesMap.put("RS", "Rio Grande do Sul");
		addressesMap.put("RO", "Rond�nia");
		addressesMap.put("RR", "Roraima");
		addressesMap.put("SC", "Santa Catarina");
		addressesMap.put("SP", "S�o Paulo");
		addressesMap.put("SE", "Sergipe");
		addressesMap.put("TO", "Tocantins");
	}
	
	/**
	 * Retorna por extenso siglas relacionadas a endere�os, assim como siglas dos estados brasileiros.
	 * @param text texto contendo endere�os
	 * @return texto de endere�o formatado.
	 */
	public static String processAddress(String text) {
		String copyText = new String(text);
		for (String key : addressesMap.keySet()) {
			if (text.contains(key)) {
				copyText = copyText.replaceAll(key, addressesMap.get(key));
			}
		}
		return copyText;
	}
}
