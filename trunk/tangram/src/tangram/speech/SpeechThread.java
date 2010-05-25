package tangram.speech;

import inf.furb.synthesis.ISynthesizer;

public final class SpeechThread extends Thread {

	private boolean async = false;
	
	public SpeechThread(ISynthesizer target, boolean async) {
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
