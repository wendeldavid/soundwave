package tangram.comandos;

import tangram.speech.SpeechDispatcher;

/**
 * Comando que espera uma fala terminar para só então dar continuação à execução.
 */
public class ComandoEsperaFala implements Comando, Observer {

	@Override
	public synchronized void faca(Executor executor) throws ComandException {
		SpeechDispatcher.registerObserver(this);
		
		try {
			Thread.sleep(200);
			if (SpeechDispatcher.isSpeaking()) {
				wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void notifyObserver() {
		notify();
	}

}
