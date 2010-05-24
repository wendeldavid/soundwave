package tangram.speech;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Pool de falas.<br>
 * Controla execu��o das falas. Podendo ter falas simult�neas e exclusivas.
 */
public final class SpeakingPool extends Thread {

	private static ConcurrentLinkedQueue<SpeechThread> pool = new ConcurrentLinkedQueue<SpeechThread>();// falas que est�o aguardando sua vez

	private static boolean started = false;

	/**
	 * Retorna se a thread do par�metro est� falando.
	 * 
	 * @param speech
	 * @return
	 */
	public static boolean isSpeaking(SpeechThread speech) {
		return speech != null && speech.getState() == State.RUNNABLE;
	}

	/**
	 * Adiciona uma fala na fila de falas.
	 * 
	 * @param t
	 *            thread de fala.
	 */
	public synchronized void addExecute(SpeechThread t) {
		// adiciona uma fala na fila
		pool.offer(t);
		if (!started && this.getState() == State.WAITING) {
			notify();
		}
	}
	
	private synchronized void execute() {
		for (;;) {
			started = true;
			try {
				// itera sempre na fila para consumir todas as falas
				while (!pool.isEmpty()) {
					// pega a primeira fala da fila
					SpeechThread t = null;
					synchronized (pool) {
						t = pool.poll();
					}
					// se a thread que foi selecionada n�o estiver falando
					if (!isSpeaking(t)) {
						// se for s�ncrona (n�o sobreposta)
						if (!t.isAsync()) {
							// e se n�o tiver mais nenhuma falando
							if (!isSpeaking()) {
								// ent�o come�a a falar exclusivamente
								t.start();
								t.join();
							}
						} else {
							System.out.println(t.getState());
							t.start();
							/*
							 * Ap�s come�ar a falar, coloca a thread no final da fila. Isto � feito pois pode ocorrer de que em alguma itera��o futura alguma thread seja s�ncrona (n�o sobreposta),
							 * assim � descoberto se existir uma fala s�ncrona (sobreposta). Ao final do loop � passado um gc de falas que terminaram.
							 */
							synchronized (pool) {
								pool.offer(t);
							}
						}
					}
					// passa o gc das falas
					speechGC();
				}
				// acabou de falar
				started = false;
				// aguarda alguem adicionar novamenta uma fala na fila
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IllegalThreadStateException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		execute();
	}

	/**
	 * varre todas as falas e verifica se tem alguma terminada. se encontrar alguma remove da fila.
	 */
	private synchronized void speechGC() {
		synchronized (pool) {
			for (SpeechThread t : pool) {
				if (t.getState() == State.TERMINATED) {
					pool.remove(t);
				}
			}
		}
	}

	/**
	 * Retorna se alguem da lista est� falando.
	 * 
	 * @return
	 */
	private synchronized boolean isSpeaking() {
		// retorna se alguem da lista de falas est� executando
		for (SpeechThread t : pool) {
			if (isSpeaking(t)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * retorna se alguma fala assincrona (sobreposta) est� sendo falada.
	 * 
	 * @return
	 */
	private synchronized boolean isAsynchronousSpeechRunning() {
		// itera por todas as falas que est�o sendo faladas
		for (SpeechThread curr : getCurrentSpeaking()) {
			// se alguma for ass�ncrona (n�o sobreposta) n�o fala nada
			if (curr.isAsync()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Retorna uma lista de todas as threads que est�o falando.
	 * 
	 * @return
	 */
	private synchronized List<SpeechThread> getCurrentSpeaking() {
		ArrayList<SpeechThread> list = new ArrayList<SpeechThread>();
		for (SpeechThread t : pool) {
			if (t.getState() == State.RUNNABLE) {
				list.add(t);
			}
		}
		return Collections.unmodifiableList(list);
	}
}
