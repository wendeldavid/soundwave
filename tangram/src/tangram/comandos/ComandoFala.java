package tangram.comandos;

import inf.furb.synthesis.ISynthesizer;
import inf.furb.synthesis.mbrola.MBRolaSynthesizer;
import inf.furb.xml.JSMLParser;

import java.io.File;

import tangram.speech.SpeakingPool;
import tangram.speech.SpeechDispatcher;
import tangram.speech.SpeechThread;

/**
 * Singleton do comando fala.
 */
public class ComandoFala implements Comando{

	private static ComandoFala instance = null;
	
	private static SpeakingPool speakingPool = new SpeakingPool();;

	private static SpeechDispatcher speechDispatcher = new SpeechDispatcher(speakingPool);;
	
	private String jsmlPath = null;
	private boolean async = false;
	
	/**
	 * Retorna um singleton do comendo fala.
	 * @return instancia única do comando fala.
	 */
	public static ComandoFala getInstance() {
		return instance;
	}
	
	public ComandoFala(String jsmlPath, boolean async) {
		this.jsmlPath = jsmlPath;
		this.async = async;
	}
	
	/**
	 * Invoca o sintetizador para falar o texto passado no documento JSML. Informa também se esta síntese deve ser assíncrona.
	 */
	private void speech() {
		// para retirar as aspas simples da string
		int stringStart = 1;
		int stringEnd = jsmlPath.length() - 1;
		jsmlPath = jsmlPath.substring(stringStart, stringEnd);
		// instancia o sintetizador
		File jsmlFile = new File(jsmlPath);
		JSMLParser parser = new JSMLParser(jsmlFile);
		parser.parse();

		ISynthesizer synth = new MBRolaSynthesizer();
		synth.configure(parser.getSynthElements());

		SpeechThread t = new SpeechThread(synth, async);
		speakingPool.addSpeech(t);
		//verifica se o pool ja foi iniciado, senão não inicia de novo
		if (!speechDispatcher.isAlive()) {
			speechDispatcher.start();
		}
	}
	
	@Override
	public void faca(Executor executor) throws ComandException {
		speech();
	}

}
