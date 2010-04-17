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

/**
 * Esta classe representa um fonema de uma sílaba.
 */
public final class Phoneme implements IComponent{

	private String phoneme;
	private int time;
	private double frequency;

	/**
	 * Construtor do fonema.<br>
	 * Representa digitalmente um som que será reproduzido.
	 * 
	 * @param phoneme
	 * @see Syllable
	 * @see ComponentGlobals
	 */
	public Phoneme(String phoneme) {
		this.phoneme = phoneme;
		this.time = 0;
		this.frequency = 0;
	}

	/**
	 * Exite o fonema no formato do mbrola.<br>
	 * <code>fonema tempo 100 frequencia</code><br>
	 * ex:<br>
	 * a 300 100 43
	 * 
	 * @return fonema
	 */
	@Override
	public String show() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.phoneme);
		sb.append(" ");
		sb.append(this.time);
		sb.append(" 100 ");
		sb.append(this.frequency);
		sb.append("\n");

		return sb.toString();
	}

	/**
	 * Configura a frequencia e o tempo que o fonema levará para ser reproduzido.
	 * 
	 * @param frequency
	 *            frequencia em Hz
	 * @param time
	 *            tempo em millisegundos
	 */
	@Override
	public void configure(double frequency, int time) {
		this.frequency = frequency;
		this.time = time;
	}

	/**
	 * Retorna o fonema.
	 * 
	 * @return fonema
	 */
	public String getPhoneme() {
		return phoneme;
	}

	/**
	 * Retorna o tempo em millisegundos.
	 * 
	 * @return tempo
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Retorna a ferquencia em Hz.
	 * 
	 * @return frequencia
	 */
	public double getFrequency() {
		return frequency;
	}

	/**
	 * Seta o tempo em millisegundos.
	 * 
	 * @param time
	 *            tempo
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * Define a frequencia em Hertz.
	 * 
	 * @param frequency
	 *            frequencia
	 */
	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}
}
