package tangram.speech;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Pool de falas.
 */
public final class SpeakingPool {

	private static ConcurrentLinkedQueue<SpeechThread> pool = new ConcurrentLinkedQueue<SpeechThread>();// falas que est�o aguardando sua vez

	/**
	 * Adiciona uma fala no final da fila de falas.
	 * 
	 * @param t
	 *            thread de fala.
	 */
	public synchronized void addSpeech(SpeechThread t) {
		// adiciona uma fala na fila
		pool.offer(t);
		notify();
	}

	/**
	 * Retorna uma {@link SpeechThread} do pool removendo-a. Caso o pool esteja vazio, aguarda o m�todo addSpeech(SpeechThread) ser chamado para ent�o devolver a fala solicidata.
	 * 
	 * @return retorna a primeira fala da fila.
	 * @throws InterruptedException
	 */
	public synchronized SpeechThread retrieveSpeech() throws InterruptedException {
		notify();
		if (pool.isEmpty()) {
			// aguarda o pool n�o estar mais vazio
			wait();
		}
		return pool.poll();
	}

}
