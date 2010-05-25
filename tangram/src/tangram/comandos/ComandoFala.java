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

	private static ComandoFala instance = new ComandoFala();
	
	private static SpeakingPool speakingPool;

	private static SpeechDispatcher speechDispatcher;
	
	/**
	 * Retorna um singleton do comendo fala.
	 * @return instancia única do comando fala.
	 */
	public static ComandoFala getInstance() {
		return instance;
	}
	
	private ComandoFala() {
		speakingPool = new SpeakingPool();
		speechDispatcher = new SpeechDispatcher(speakingPool);
	}
	
	/**
	 * Invoca o sintetizador para falar o texto passado no documento JSML. Informa também se esta síntese deve ser assíncrona.
	 * @param filePath documento JSML.
	 * @param async se a síntese deste documento deverá ser assíncrona
	 */
	public void speech(String filePath, boolean async) {
		// FIXME está falando quando executa outros comandos
		System.out.println("fala arquivo - " + filePath);
		// para retirar as aspas simples da string
		int stringStart = 1;
		int stringEnd = filePath.length() - 1;
		filePath = filePath.substring(stringStart, stringEnd);
		// instancia o sintetizador
		File jsmlFile = new File(filePath);
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
		//to nothing
	}

}
