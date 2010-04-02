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

 *   Copyright (C) 2004 by Luiz Angelo Daros de Luca                       
 *   luizd@inf.ufsc.br   

 *   Created on 16/07/2004
 *   Modified on 02/04/2010
 */
package inf.furb.synthesis.mbrola.comp;

import java.math.BigInteger;

/**
 * Esta classe converte numero de 0 ateé 10^55 -1 por extenso para o Português do Brasil (pt_br).
 * 
 */
public final class NumToWord {

	/**
	 * Unidade zero. 0
	 */
	public static final String ZERO = "zero";

	/**
	 * Unidades numéricas.<br>
	 * um, dois, três, quatro, cinco, seis, sete, oito, nove, dez, onze, doze, treze, quatorze, quinze, dezesseis, dezessete, dezoito, dezenove
	 */
	public static final String[] UNITS = { //
	"", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove", "dez", //
			"onze", "doze", "treze", "quatorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove" };

	/**
	 * Dezenas numéricas.<br>
	 * vinte, trinta, quarenta, cinqüenta, sessenta, setenta, oitenta, noventa, cem
	 */
	public static final String[] TENS = { //
	"", "", "vinte", "trinta", "quarenta",// 
			"cinquenta", "sessenta", "setenta", "oitenta", "noventa", "cem" };

	/**
	 * Centenas numéricas.<br>
	 * cento, duzentos, trezentos, quatrocentos, quinhentos, seiscentos, setecentos, oitocentos, novecentos
	 */
	public static final String[] HUNDRED = {// 
	"", "cento", "duzentos", "trezentos", "quatrocentos",// 
			"quinhentos", "seiscentos", "setecentos", "oitocentos", "novecentos" };

	private static final String SEPARADOR_MENOR = " e ";
	private static final String SEPARADOR_MAIOR = " , "; // 2 espacos para ficar
	// igual ao " e "

	/**
	 * Ordem singular.<br>
	 * mil, milhão, bilhão, trilhão, quatrilhão, quintilhão, sextilhão, setilhão, octilhão, nonilhão, decilhão, undecilhão, dodecilhão, tredecilhão, quatordecilhão, quindecilhão, sedecilhão,
	 * septendecilhão
	 */
	public static final String[] SINGULAR_ORDER = { //
	"", "mil", "milhão", "bilhão", "trilhão", "quatrilhão", "quintilhão", //
			"sextilhão", "setilhão", "octilhão", "nonilhão", "decilhão", "undecilhão",// 
			"dodecilhão", "tredecilhão", "quatordecilhão", "quindecilhão", "sedecilhão", "septendecilhão" };

	/**
	 * Ordem em plural.<br>
	 * mil, milhões, bilhões, trilhões, quatrilhões, quintilhões, sextilhões, setilhões, octilhões, decilhões, undecilhões, dodecilhões, tredecilhões, quatordecilhões, quindecilhões, sedecilhões,
	 * septendecilhões
	 */
	public static final String[] PLURAL_ORDER = { //
	"", "mil", "milhões", "bilhões", "trilhões", "quatrilhões", "quintilhões", "sextilhões", "setilhões", "octilhões", //
			"decilhões", "undecilhões", "dodecilhões", "tredecilhões", "quatordecilhões", "quindecilhões", "sedecilhões", "septendecilhões" };

	private static final BigInteger CEM = BigInteger.valueOf(1000);
	public static final BigInteger NUMERO_MAXIMO = new BigInteger("999999999999999999999999999999999999999999999999999999");

	private static String unidades(int numero) {
		if (numero == 0)
			return "";
		return SEPARADOR_MENOR + UNITS[numero];
	}

	private static String dezenas(int numero) {
		if (numero == 0)
			return "";
		if (numero < 20)
			return unidades(numero);
		return SEPARADOR_MENOR + TENS[numero / 10] + unidades(numero % 10);
	}

	private static String centenas(int numero) {
		if (numero == 0)
			return "";
		if (numero <= 100)
			return dezenas(numero);
		return SEPARADOR_MAIOR + HUNDRED[numero / 100] + dezenas(numero % 100);
	}

	private static String ordens(long numero, int grandeza) {
		if (numero == 0)
			return "";
		if (numero < 1000)
			return centenas((int) numero);

		int menor = (int) (numero % 1000);
		long maior = numero / 1000;
		int proximoMenor = (int) (maior % 1000);

		if (proximoMenor == 0)
			return ordens(maior, grandeza + 1) + centenas(menor);
		if (proximoMenor == 1)
			return ordens(maior, grandeza + 1) + " " + SINGULAR_ORDER[grandeza] + centenas(menor);
		return ordens(maior, grandeza + 1) + " " + PLURAL_ORDER[grandeza] + centenas(menor);
	}

	private static String ordens(BigInteger numero, int grandeza) {
		if (numero.equals(BigInteger.ZERO))
			return "";
		if (numero.compareTo(CEM) == -1)
			return centenas((int) numero.longValue());

		BigInteger[] resultado = numero.divideAndRemainder(CEM);

		int menor = (int) resultado[1].longValue();
		BigInteger maior = resultado[0];
		int proximoMenor = (int) maior.remainder(CEM).longValue();

		if (proximoMenor == 0)
			return ordens(maior, grandeza + 1) + centenas(menor);
		if (proximoMenor == 1)
			return ordens(maior, grandeza + 1) + " " + SINGULAR_ORDER[grandeza] + centenas(menor);
		return ordens(maior, grandeza + 1) + " " + PLURAL_ORDER[grandeza] + centenas(menor);
	}

	/**
	 * Converte um número em extenso.
	 * 
	 * @param number
	 *            do tipo <code>long</code>
	 * @return número em extenso
	 */
	public static final String convert(long number) {
		if (number == 0)
			return ZERO;
		return ordens(number, 1).substring(3).replaceAll(" ,", ","); // tira o
		// espaco
		// extra
	}

	/**
	 * Converte um número em extenso.
	 * 
	 * @param number
	 *            do tipo {@link BigInteger}
	 * @return número em extenso
	 */
	public static final String convert(BigInteger number) {
		if (number.equals(BigInteger.ZERO))
			return ZERO;
		if (NUMERO_MAXIMO.compareTo(number) < 0)
			throw new RuntimeException("the number '" + number + "' exceeds the limit of " + NUMERO_MAXIMO);
		return ordens(number, 1).substring(3).replaceAll(" ,", ","); // tira o
		// espaco
		// extra
	}

}
