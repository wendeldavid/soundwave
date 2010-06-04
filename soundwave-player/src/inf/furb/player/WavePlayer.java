package inf.furb.player;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Player de arquivos <code>.wav</code>.
 */
public class WavePlayer implements Runnable {

	private AudioInputStream audioInputStream;

	/**
	 * Construtor que passa o caminho do arquivo que será pego o {@link AudioInputStream}.
	 * 
	 * @param fileName
	 *            caminho completo do arquivo a ser reproduzido.
	 * @throws UnsupportedAudioFileException
	 *             se não for um arquivo no formato <code>.wav</code>
	 */
	public WavePlayer(String fileName) throws UnsupportedAudioFileException {
		setAudioInputStream(fileName);
	}

	/**
	 * Construtor que passa direto o {@link AudioInputStream} do WAVE.
	 * 
	 * @param ais
	 */
	public WavePlayer(AudioInputStream ais) {
		if (ais != null) {
			this.audioInputStream = ais;
		}
	}

	/**
	 * Reproduz um aquivo wav com o endereço
	 * 
	 * @param fileName
	 *            caminho completo do arquivo a ser reproduzido.
	 * @throws UnsupportedAudioFileException
	 */
	private void setAudioInputStream(String fileName) throws UnsupportedAudioFileException {
		File soundFile = new File(fileName);

		// Create a stream from the given file.
		// Throws IOException or UnsupportedAudioFileException
		try {
			this.audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (IOException e) {
			throw new RuntimeException("Problem with file " + fileName + ":");
		}
		// AudioSystem.getAudioInputStream( inputStream ); // alternate audio stream from inputstream
	}

	/** Plays audio from the given audio input stream. */
	private void playAudioStream() {
		// Audio format provides information like sample rate, size, channels.
		AudioFormat audioFormat = audioInputStream.getFormat();

		// Open a data line to play our type of sampled audio.
		// Use SourceDataLine for play and TargetDataLine for record.
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		if (!AudioSystem.isLineSupported(info)) {
			return;
		}

		try {
			// Create a SourceDataLine for play back (throws LineUnavailableException).
			SourceDataLine dataLine = (SourceDataLine) AudioSystem.getLine(info);
			// System.out.println( "SourceDataLine class=" + dataLine.getClass() );

			// The line acquires system resources (throws LineAvailableException).
			dataLine.open(audioFormat);

			// Adjust the volume on the output line.
			if (dataLine.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
				FloatControl volume = (FloatControl) dataLine.getControl(FloatControl.Type.MASTER_GAIN);
				volume.setValue(4);
			}

			// Allows the line to move data in and out to a port.
			dataLine.start();

			// Create a buffer for moving data from the audio stream to the line.
			int bufferSize = (int) audioFormat.getSampleRate() * audioFormat.getFrameSize();
			byte[] buffer = new byte[bufferSize];

			// Move the data until done or there is an error.
			try {
				int bytesRead = 0;
				while (bytesRead >= 0) {
					bytesRead = audioInputStream.read(buffer, 0, buffer.length);
					if (bytesRead >= 0) {
//						System.out.println("Play.playAudioStream bytes read=" + bytesRead + ", frame size=" + audioFormat.getFrameSize() + ", frames read=" + bytesRead / audioFormat.getFrameSize());
						// Odd sized sounds throw an exception if we don't write the same amount.
						int framesWritten = dataLine.write(buffer, 0, bytesRead);
					}
				} // while
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Continues data line I/O until its buffer is drained.
			dataLine.drain();

			// Closes the data line, freeing any resources such as the audio device.
			dataLine.close();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		playAudioStream();
	}
}