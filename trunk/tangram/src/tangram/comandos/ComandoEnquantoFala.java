package tangram.comandos;

import java.util.ArrayList;

import tangram.speech.SpeechDispatcher;

public class ComandoEnquantoFala extends ComandoLaco implements Comando {

	public ComandoEnquantoFala(ArrayList<Comando> al) {
		super(al);
	}

	@Override
	public void faca(Executor executor) throws ComandException {
		try {
			Thread.sleep(200);//WTF!!!! tive que dar um tempo para a fala passar pelo pool até chegar no dispatcher 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(SpeechDispatcher.isSpeaking()) {
			// para cada comando da lista
			for (Comando c : comandos) {
				// executa o comando
				c.faca(executor);
			}
		}
	}

}
