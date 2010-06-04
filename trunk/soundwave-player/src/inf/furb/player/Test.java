package inf.furb.player;

import javax.sound.sampled.UnsupportedAudioFileException;

public class Test {

	public static void main(String[] args) {
		String[] files = { "C:/Documents and Settings/wendel/workspace/furb-speech/target/test-classes/output/myFile.wav", //
				"C:/Documents and Settings/wendel/workspace/furb-speech/target/test-classes/output/speech.wav",//
				"C:/Documents and Settings/wendel/workspace/furb-speech/target/test-classes/output/output.wav" };

		// Process arguments.
		for (int i = 0; i < files.length; i++)
			try {
				WavePlayer player = new WavePlayer(files[i]);
				Thread playerThread = new Thread(player);
				playerThread.start();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
	}

}
