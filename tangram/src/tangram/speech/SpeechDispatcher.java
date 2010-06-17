package tangram.speech;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import tangram.comandos.Observer;

/**
 * Enfilera as falas obedecendo o crit�rio de falas s�ncronas e ass�ncronas.<br>
 * Se a thread de fala selecionada for s�ncrona e n�o existe nenhuma outra thread falando no momento, ela toma a vez de falar exclusivamente, se tiver outra thread falando, ela volta para o pool.<br>
 * Se a thread de fala selecionada for ass�ncrona ela entra para uma fila de falas concorrentes e come�a a falar. Assim permite que outras falas ass�ncronas possam executar paralelamente.
 */
public final class SpeechDispatcher extends Thread {

	private SpeakingPool pool;
	private static List<Observer> observerList;

	private static ConcurrentLinkedQueue<SpeechThread> queue = new ConcurrentLinkedQueue<SpeechThread>();// falas que est�o aguardando sua vez

	/**
	 * Construtor do dispathcher de falas.
	 * 
	 * @param pool
	 *            � o pool de threads pendentes de serem faladas.
	 */
	public SpeechDispatcher(SpeakingPool pool) {
		this.pool = pool;
	}

	@Override
	public void run() {
		while (true) {
			try {
				// retiva a fala do pool e adiciona na fila de falas
				SpeechThread t = pool.retrieveSpeech();
				queue.offer(t);

				// � assincrona (sobreposta)
				if (t.isAsync()) {
					t.start();
				} else {// � sincrona (n�o sobreposta)
					// tem alguem falando?
					if (!isSpeaking()) {
						t.start();
						t.join();
					} else {
						// tem alguem falando, ent�o retira a fala da fila e devolve pro pool
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
		
		if(queue.isEmpty()) {
			notifyObservers();
		}
	}

	/**
	 * Retorna se a thread do par�metro est� falando.
	 * 
	 * @param speech
	 * @return
	 */
	private static boolean isSpeaking(SpeechThread speech) {
		return speech != null && (speech.getState() == State.RUNNABLE || speech.getState() == State.TIMED_WAITING);
	}

	/**
	 * Retorna se alguem da lista est� falando.
	 * 
	 * @return
	 */
	public static synchronized boolean isSpeaking() {
		// retorna se alguem da lista de falas est� executando
		for (SpeechThread t : queue) {
			if (isSpeaking(t)) {
				return true;
			}
		}
		return false;
	}

	/* lisetners */
	/**
	 * Dipsara as notifica��es dos observadores.
	 */
	private static void notifyObservers() {
		for (Observer observer : observerList) {
			observer.notifyObserver();
		}
	}
	
	/**
	 * Registra um observer no dispatcher.
	 * @param observer
	 */
	public static void registerObserver(Observer observer) {
		if(observerList == null) {
			observerList = new ArrayList<Observer>();
		}
		observerList.add(observer);
	}
	
	/**
	 * Remove o observer do dispatcher.
	 * @param observer
	 */
	public static void unregisterObserver(Observer observer) {
		observerList.remove(observer);
	}
}
