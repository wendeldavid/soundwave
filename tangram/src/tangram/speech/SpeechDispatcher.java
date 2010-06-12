package tangram.speech;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Enfilera as falas obedecendo o critério de falas síncronas e assíncronas.<br>
 * Se a thread de fala selecionada for síncrona e não existe nenhuma outra thread falando no momento, ela toma a vez de falar exclusivamente, se tiver outra thread falando, ela volta para o pool.<br>
 * Se a thread de fala selecionada for assíncrona ela entra para uma fila de falas concorrentes e começa a falar. Assim permite que outras falas assíncronas possam executar paralelamente.
 */
public final class SpeechDispatcher extends Thread {

	private SpeakingPool pool;

	private static ConcurrentLinkedQueue<SpeechThread> queue = new ConcurrentLinkedQueue<SpeechThread>();// falas que estão aguardando sua vez

	/**
	 * Construtor do dispathcher de falas.
	 * @param pool é o pool de threads pendentes de serem faladas.
	 */
	public SpeechDispatcher(SpeakingPool pool) {
		this.pool = pool;
	}

	@Override
	public void run() {
		while (true) {
			try {
				//retiva a fala do pool e adiciona na fila de falas
				SpeechThread t = pool.retrieveSpeech();
				queue.offer(t);

				// é assincrona (sobreposta)
				if (t.isAsync()) {
					t.start();
				} else {// é sincrona (não sobreposta)
					// tem alguem falando?
					if (!isSpeaking()) {
						t.start();
						t.join();
					} else {
						// tem alguem falando, então retira a fala da fila e devolve pro pool
						pool.addSpeech(queue.poll());
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
	 * Retorna se a thread do parâmetro está falando.
	 * 
	 * @param speech
	 * @return
	 */
	private static boolean isSpeaking(SpeechThread speech) {
		return speech != null && (speech.getState() == State.RUNNABLE || speech.getState() == State.TIMED_WAITING);
	}

	/**
	 * Retorna se alguem da lista está falando.
	 * 
	 * @return
	 */
	public static synchronized boolean isSpeaking() {
		// retorna se alguem da lista de falas está executando
		for (SpeechThread t : queue) {
			if (isSpeaking(t)) {
				return true;
			}
		}
		return false;
	}
	
}
