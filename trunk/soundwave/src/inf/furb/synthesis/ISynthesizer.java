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
	 * Nesta etapa o sintetizador inicia suas configura��es necess�rias como vozes dispon�veis, SO utilizado, I/O, etc.
	 * @param config
	 */
	public void configure(List<ISynthElement> config);
	
	/**
	 * Faz com que o sintetizador fale o texto atribu�do a ele.
	 */
	public void speech();
}
