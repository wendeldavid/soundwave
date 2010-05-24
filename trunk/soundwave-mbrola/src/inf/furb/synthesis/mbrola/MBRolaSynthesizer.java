package inf.furb.synthesis.mbrola;

import inf.furb.common.ResourcePool;
import inf.furb.player.WavePlayer;
import inf.furb.synthesis.ISynthesizer;
import inf.furb.synthesis.jsml.ISynthElement;
import inf.furb.synthesis.jsml.Voice;
import inf.furb.synthesis.mbrola.converter.ConverterFactory;
import inf.furb.synthesis.mbrola.converter.IConverter;
import inf.furb.synthesis.mbrola.util.Utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Implementação do sintetizador MBRola.
 */
public final class MBRolaSynthesizer implements ISynthesizer {

	private String mbrolabin;
	private String voice;
	private String input;
	private String output;
	
	/**
	 * Construtor padrão do sintetizador MBRola.
	 */
	public MBRolaSynthesizer() {
		long uniqueID = System.nanoTime();
		input = ResourcePool.TEMP_DIR + File.separator + uniqueID + "input.pho";
		output = ResourcePool.TEMP_DIR + File.separator + uniqueID + "output.wav";
	}

	@Override
	public void configure(List<ISynthElement> elements) {
		StringBuilder output = new StringBuilder();

		configureMBrolaApp();

		for (ISynthElement element : elements) {
			// por enquanto só a voz tem tratamento diferente
			if (element instanceof Voice) {
				configureVoice((Voice) element);
				continue;
			}

			IConverter converter = ConverterFactory.createConverter(element.getClass());
			if (converter != null) {
				converter.convert(element, output);
			}
		}

		// System.out.println(output.toString());
		GeneratePHOFile fileGenerator = new GeneratePHOFile(input);
		fileGenerator.writeFile(output.toString());
	}

	private void configureVoice(Voice voice) {
		// demais atributos da voz ignorados
		String voiceName = voice.getAttribute(Voice.NAME).getValue();
		File file = Utils.getResource(voiceName);
		try {
			this.voice = file.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void speech() {
		// <mbrola-bin> -e <voice> <input> <output>
		StringBuilder execLine = new StringBuilder();
		execLine.append("\"");
		execLine.append(mbrolabin).append("\" ").append("-e").append(" \"");
		execLine.append(voice).append("\" \"");
		execLine.append(input).append("\" \"");
		execLine.append(output).append("\"");

		// System.out.println(execLine.toString());
		try {
			Runtime.getRuntime().exec(execLine.toString()).waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
//			WavePlayer.playAudioFile(output);
			WavePlayer player = new WavePlayer(output);
			player.run();
//			Thread threadPlayer = new Thread(player);
//			threadPlayer.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}

	private void configureMBrolaApp() {
		String bin = null;
		final String osName = (String) System.getProperties().get("os.name");
		if (osName.toLowerCase().indexOf("windows") > -1) {
			bin = "mbrola.exe";
		} else if (osName.toLowerCase().indexOf("linux") > -1) {
			bin = "mbrola-linux-i386";
		} else if (osName.toLowerCase().indexOf("mac") > -1) {
			bin = "mbrola-darwin-ppc";
		} else {
			throw new OSNotSupportedException(osName);
		}

		File file = Utils.getResource(bin);
		file.setWritable(true);
		file.setReadable(true);
		file.setExecutable(true);

		try {
			mbrolabin = file.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		speech();
	}

}
