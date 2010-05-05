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
		addressesMap.put("nº", "número");
		addressesMap.put("UF", "Unidade Federativa");
		//estados
		addressesMap.put("AC", "Acre");
		addressesMap.put("AL", "Alagoas");
		addressesMap.put("AP", "Amapá");
		addressesMap.put("AM", "Amazonas");
		addressesMap.put("BA", "Bahia");
		addressesMap.put("CE", "Ceará");
		addressesMap.put("DF", "Distrito Federal");
		addressesMap.put("ES", "Espírito Santo");
		addressesMap.put("GO", "Goiás");
		addressesMap.put("MA", "Maranhão");
		addressesMap.put("MT", "Mato Grosso");
		addressesMap.put("MS", "Mato Grosso do Sul");
		addressesMap.put("MG", "Minas Gerais");
		addressesMap.put("PA", "Pará");
		addressesMap.put("PB", "Pernanbuco");
		addressesMap.put("PR", "Paraná");
		addressesMap.put("PE", "Pernambuco");
		addressesMap.put("PI", "Piauí");
		addressesMap.put("RJ", "Rio de Janeiro");
		addressesMap.put("RN", "Rio Grande do Norte");
		addressesMap.put("RS", "Rio Grande do Sul");
		addressesMap.put("RO", "Rondônia");
		addressesMap.put("RR", "Roraima");
		addressesMap.put("SC", "Santa Catarina");
		addressesMap.put("SP", "São Paulo");
		addressesMap.put("SE", "Sergipe");
		addressesMap.put("TO", "Tocantins");
	}
	
	/**
	 * Retorna por extenso siglas relacionadas a endereços, assim como siglas dos estados brasileiros.
	 * @param text texto contendo endereços
	 * @return texto de endereço formatado.
	 */
	public static String processAddress(String text) {
		String copyText = new String(text);
		for (String key : addressesMap.keySet()) {
			if (text.contains(key)) {
				copyText = copyText.replaceAll(key, addressesMap.get(key));
				break;
			}
		}
		return copyText;
	}
}
