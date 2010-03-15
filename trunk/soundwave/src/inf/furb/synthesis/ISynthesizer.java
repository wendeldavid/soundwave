package inf.furb.synthesis;

import inf.furb.common.ConfigNode;


/**
 * Iterface que define um sintetizador.
 *
 */
public interface ISynthesizer {

	static final String CONFIG_VOICE = "voice";
	static final String CONFIG_IMPUT = "input";
	static final String CONFIG_OUTPUT = "output";
	
	 /**
	 * Configura o sintetizador.<br>
	 * Nesta etapa o sintetizador inicia suas configurações necessárias como vozes disponíveis, SO utilizado, I/O, etc.
	 * @param node
	 */
	public void configure(ConfigNode node);
	
	/**
	 * Inicia o sintetizador.<br>
	 * Nesta etapa o sintetizador recebe o texto e se prepara para "falar" conforme sua configuração. 
	 */
	public void start(ConfigNode node);
	
	/**
	 * Faz com que o sintetizador fale o texto atribuído a ele.
	 */
	public void speech();
	
}
