package inf.furb.synthesis.mbrola.comp;

/**
 * Define uma interface m�todos comuns para os componentes ac�sticos do MBROLA. 
 */
public interface IComponent {

	/**
	 * Configura o componente.
	 * @param frequency frequ�ncia utilizada neste componente
	 * @param time dura��o da reprodu��o do componente (em millisegundos)
	 */
	public void configure(double frequency, int time);
	
	/**
	 * Processa e retorna a descri��o <code>pho</code> utilizada no sintetizador ac�stico MBROLA. 
	 * @return descri��o do componente
	 */
	public String show();
	
}
