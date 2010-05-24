package tangram.speech;

public final class SpeechThread extends Thread {

	private boolean async = false;
	
	public SpeechThread(Runnable target, boolean async) {
		super(target);
		this.setAsync(async);
	}

	private void setAsync(boolean async) {
		this.async = async;
	}

	public boolean isAsync() {
		return async;
	}

	
}
