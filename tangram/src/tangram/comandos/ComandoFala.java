package tangram.comandos;

import inf.furb.synthesis.ISynthesizer;
import inf.furb.synthesis.mbrola.MBRolaSynthesizer;
import inf.furb.xml.JSMLParser;

import java.io.File;

import tangram.speech.SpeakingPool;
import tangram.speech.SpeechDispather;
import tangram.speech.SpeechThread;

public class ComandoFala implements Comando{

	private static ComandoFala instance = new ComandoFala();
	
	private static SpeakingPool speakingPool;

	private static SpeechDispather speechDispather;
	
	public static ComandoFala getInstance() {
		return instance;
	}
	
	private ComandoFala() {
		speakingPool = new SpeakingPool();
		speechDispather = new SpeechDispather(speakingPool);
	}
	
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
		if (!speechDispather.isAlive()) {
			speechDispather.start();
		}
	}
	
	@Override
	public void faca(Executor executor) throws ComandException {
		
	}

}
