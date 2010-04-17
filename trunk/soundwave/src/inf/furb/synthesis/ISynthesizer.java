package inf.furb.synthesis;

import inf.furb.synthesis.jsml.ISynthElement;

import java.util.List;


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
	 * Nesta etapa o sintetizador inicia suas configurações necessárias como vozes disponíveis, SO utilizado, I/O, etc.
	 * @param config
	 */
	public void configure(List<ISynthElement> config);
	
	/**
	 * Faz com que o sintetizador fale o texto atribuído a ele.
	 */
	public void speech();
}
