/**
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package inf.furb.synthesis.mbrola.comp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Classe utilit�ria para opera��es de baixo n�vel sobre as letras que est�o sendo configuradas nos textos.
 * @author Germano
 */
public final class ComponentGlobals {

	/**
	 * Frequ�ncia base do sitetizador.
	 */
	public static final double BASE_FREQUENCY = 90;
	
	/**
	 * tempo base utilizado pelo sintetizador
	 */
	public static final int BASE_TIME = 105;
	
	/**
	 * Pontua��o reconhecida. (. ! ?)
	 */
	public static final String[] PONTOS = {
		".", "!", "?"
	};
	
	/**
	 * Separadores de uma senten�a. (, -)
	 */
	public static final String[] SEPARATORS = {
		",", "-"
	};
	
	/**
	 * Vogais normais e acentuadas.
	 */
	public static final String[] VOGALS = {
		"a", "�", "�", "�", "�", "e", "�", "�", "i", "�", "o", "�", "�", "�", "u", "�", "�"
	};
	
	/**
	 * Todas as consoantes
	 */
	public static final String[] CONSONANTS = {
		"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z"
	};

	/**
	 * Consoantes regulares
	 */
	public static final String[] REGULAR_CONSONANTS = {
		"b", "d", "f", "j", "k", "m", "p", "t", "v", "w", "y"
	};
	
	/**
	 * Consoantes irregulares
	 */
	public static final String[] IRREGULAR_CONSONANTS = {
		"c", "g", "h", "l", "n", "q", "r", "s", "x", "z"
	};
	
	/**
	 * Retorna um mapa de vogais dos fonemas.
	 * @return
	 */
	public static Map<String, String[]> getVogalsInPhonemes() {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("a", new String[] {"a", "�", "�", "�"});
		map.put("@", new String[] {"�"});
		map.put("e", new String[] {"e", "�"});
		map.put("ee", new String[] {"�"});
		map.put("i", new String[] {"i", "�"});
		map.put("o", new String[] {"o", "�", "�"});
		map.put("oo", new String[] {"�"});
		map.put("u", new String[] {"u", "�", "�"});
		return map;
	}
	
	/**
	 * Retorna um mapa com vogais simples
	 * @return
	 */
	public static Map<String, String[]> getVogalsInBasic() {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("a", new String[] {"a", "�", "�", "�", "�"});
		map.put("e", new String[] {"e", "�", "�"});
		map.put("i", new String[] {"i", "�"});
		map.put("o", new String[] {"o", "�", "�", "�"});
		map.put("u", new String[] {"u", "�", "�"});
		
		return map;
	}
	
	/**
	 * Retorna a vogal
	 * @param v
	 * @return
	 */
	public static String vogal(String v) {
		if (inArray(v, new String[]{"a","�","�","�","�","�","A","�","�","�","�","�"})) return "A";
		if (inArray(v, new String[]{"e","�","�","�","�","E","�","�","�","�"})) return "E";
		if (inArray(v, new String[]{"i","�","�","�","�","I","�","�","�","�"})) return "I";
		if (inArray(v, new String[]{"o","�","�","�","�","�","O","�","�","�","�","�"})) return "O";
		if (inArray(v, new String[]{"u","�","�","�","�","U","�","�","�","�"})) return "U";
		
		throw new IllegalArgumentException("The argument '" + v + "' is not a valid vogal.");
	}
	
	/**
	 * Retorna se uma letra � vogal.
	 * @param v
	 * @return
	 */
	public static boolean isVogal(String v) {
		String[] allVogals = {
				"a","e","i","o","u",
				"�","�","�","�","�",
				"�","�","�","�","�",
				"�","�","�","�","�",
				"�","�","�","�","�",
				"�","�",
				"A","E","I","O","U",
				"�","�","�","�","�",
				"�","�","�","�","�",
				"�","�","�","�","�",
				"�","�","�","�","�",
				"�","�"
		};
		
		return inArray(v, allVogals);
	}
	
	/**
	 * Retorna se uma letra � consoante.
	 * @param c
	 * @return
	 */
	public static boolean isConsonant(String c) {
		return (c.equals("")) || (!isVogal(c) && !Character.isDigit(c.charAt(0)));
	}
	
	public static boolean isGreater(String f, String s) {
		if ((vogal(f).equals("I")) && (vogal(s).equals("U"))) return true;
		if ((vogal(f).equals("I")) || (vogal(f).equals("U"))) return false;
		if ((vogal(s).equals("I")) || (vogal(s).equals("U"))) return true;
		if (vogal(f).equals("A")) return true;
		if (vogal(s).equals("A")) return false;
		return ((vogal(f).equals("O")) && (vogal(s).equals("E")));
	}
	
	/**
	 * Retorna se � uma letra do alfabedo portugu�s.
	 * @param c
	 * @return
	 */
	public static boolean isLetter(String c) {
		String[] letters = new String[] {
			"a","�","�","�","�","e","�","�","i","�","o","�","�","�","u","�","b","c",
			"d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","y","z"
		};
		
		return inArray(c, letters);
	}
	
	/**
	 * Retorna se � uma vogal anasalada.
	 * @param c
	 * @return
	 */
	public static boolean isNasal(String c) {
		String[] chars = new String[] {
			"�","�"
		};
		
		return inArray(c, chars);
	}

	/**
	 * Retorna se o caractere � de pontua��o
	 * @param c
	 * @return
	 */
	public static boolean isPontuacao(String c) {
		String[] chars = new String[] {
			".","?","!"
		};
		
		return inArray(c, chars);
	}
	
	public static String convert(String vogal, Map<String, String[]> convertionMap) {
		for (Iterator<String> iterator = convertionMap.keySet().iterator(); iterator.hasNext(); ) {
			String xVogal = iterator.next();
			String[] vogalsToCompare = convertionMap.get(xVogal);
			for (String string : vogalsToCompare) {
				if (string.equals(vogal)) {
					return xVogal; 
				}
			}
		}
		return "_";
	}
	
	/**
	 * Retorna se a vogal possui acentua��o.
	 * @param syllable
	 * @return
	 */
	public static boolean hasAccentuation(String syllable) {
		String[] accentuatedVogals = new String[] {
			"�","�","�","�","�","�","�","�","�","�","�","�","�","�","�","�","�",
			"�","�","�","�","�","�","�","�","�","�","�","�","�","�","�","�","�"
		};
		int syllableLenght = syllable.length();
		for (int i = 0; i < syllableLenght; i++) {
			if (inArray(String.valueOf(syllable.charAt(i)), accentuatedVogals)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Retorna se a s�laba � oxitona
	 * @param syllable
	 * @return
	 */
	public static boolean isOxitona(String syllable) {
		int syllableLenght = syllable.length();
		
		String lastChar = "";
		String lastLastChar = "";
		if (syllableLenght -1 > 0) {
			lastChar = String.valueOf(syllable.charAt(syllableLenght-1));
		}
		if (syllableLenght -2 > 0) {
			lastLastChar = String.valueOf(syllable.charAt(syllableLenght-2));
		}
		
		if (inArray(lastChar.toUpperCase(), new String[]{"L"}) && isVogal(lastLastChar)) {
			return true;
		}
		
		return (inArray(lastChar.toUpperCase(), new String[]{"U", "I"}) && isConsonant(lastLastChar));
	}
	
	public static boolean inArray(String s, String[] array) {
		for (String string : array) {
			if (string.equals(s)) {
				return true;
			}
		}
		return false;
	}
	
	public static String getClearDirAbsolutePath(String baseDir) {
		return baseDir.replace("%20", " ");
	}
}
