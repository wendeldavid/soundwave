package tangram.speech;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Enfilera as falas obedecendo o crit�rio de falas s�ncronas e ass�ncronas.<br>
 * Se a thread de fala selecionada for s�ncrona e n�o existe nenhuma outra thread falando no momento, ela toma a vez de falar exclusivamente, se tiver outra thread falando, ela volta para o pool.<br>
 * Se a thread de fala selecionada for ass�ncrona ela entra para uma fila de falas concorrentes e come�a a falar. Assim permite que outras falas ass�ncronas possam executar paralelamente.
 */
public final class SpeechDispatcher extends Thread {

	private SpeakingPool pool;

	private static ConcurrentLinkedQueue<SpeechThread> queue = new ConcurrentLinkedQueue<SpeechThread>();// falas que est�o aguardando sua vez

	/**
	 * Construtor do dispathcher de falas.
	 * @param pool � o pool de threads pendentes de serem faladas.
	 */
	public SpeechDispatcher(SpeakingPool pool) {
		this.pool = pool;
	}

	@Override
	public void run() {
		while (true) {
			try {
				SpeechThread t = pool.retrieveSpeech();

				// � assincrona (sobreposta)
				if (t.isAsync()) {
					queue.offer(t);
					t.start();
				} else {// � sincrona (n�o sobreposta)
					// tem alguem falando?
					if (!isSpeaking()) {
						t.start();
						t.join();
					} else {
						// tem alguem falando, ent�o devolve a fala pro pool
						pool.addSpeech(t);
					}
				}

				// limpa as falas que terminaram
				speechGC();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * varre todas as falas e verifica se tem alguma terminada. se encontrar alguma remove da fila.
	 */
	private synchronized void speechGC() {
		for (SpeechThread t : queue) {
			if (t.getState() == State.TERMINATED) {
				queue.remove(t);
			}
		}
	}

	/**
	 * Retorna se a thread do par�metro est� falando.
	 * 
	 * @param speech
	 * @return
	 */
	private static boolean isSpeaking(SpeechThread speech) {
		return speech != null && (speech.getState() == State.RUNNABLE && speech.getState() == State.TIMED_WAITING);
	}

	/**
	 * Retorna se alguem da lista est� falando.
	 * 
	 * @return
	 */
	private synchronized boolean isSpeaking() {
		// retorna se alguem da lista de falas est� executando
		for (SpeechThread t : queue) {
			if (isSpeaking(t)) {
				return true;
			}
		}
		return false;
	}

}
