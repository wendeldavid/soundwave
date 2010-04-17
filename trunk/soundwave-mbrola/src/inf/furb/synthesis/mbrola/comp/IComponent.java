package inf.furb.synthesis.mbrola.comp;

/**
 * Define uma interface métodos comuns para os componentes acústicos do MBROLA. 
 */
public interface IComponent {

	/**
	 * Configura o componente.
	 * @param frequency frequência utilizada neste componente
	 * @param time duração da reprodução do componente (em millisegundos)
	 */
	public void configure(double frequency, int time);
	
	/**
	 * Processa e retorna a descrição <code>pho</code> utilizada no sintetizador acústico MBROLA. 
	 * @return descrição do componente
	 */
	public String show();
	
}
