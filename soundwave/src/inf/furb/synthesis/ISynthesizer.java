package inf.furb.synthesis;

import inf.furb.common.ConfigNode;


/**
 * Iterface que define um sintetizador.
 *
 */
public interface ISynthesizer extends Runnable{

	static final String CONFIG_VOICE = "voice";
	static final String CONFIG_IMPUT = "input";
	static final String CONFIG_OUTPUT = "output";
	
	 /**
	 * Configura o sintetizador.<br>
	 * Nesta etapa o sintetizador inicia suas configura��es necess�rias como vozes dispon�veis, SO utilizado, I/O, etc.
	 * @param node
	 */
	public void configure(ConfigNode node);
	
	/**
	 * Inicia o sintetizador.<br>
	 * Nesta etapa o sintetizador recebe o texto e se prepara para "falar" conforme sua configura��o. 
	 */
	public void start(ConfigNode node);
	
	/**
	 * Faz com que o sintetizador fale o texto atribu�do a ele.
	 */
	public void speech();
	
}
