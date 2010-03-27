package inf.furb.synthesis.mbrola;

import inf.furb.common.ConfigNode;
import inf.furb.synthesis.ISynthesizer;
import inf.furb.synthesis.mbrola.util.Utils;

import java.io.File;
import java.io.IOException;

/**
 * Implementação do sintetizador MBRola.
 */
public final class MBRolaSynthesizer implements ISynthesizer {

	private String mbrolabin;
	private String voice;
	private String input;
	private String output;

	@Override
	public void configure(ConfigNode node) {
		// TODO Auto-generated method stub
		configureMBrolaApp();
		configureVoice(node.getConfig("voice").toString());
		configureInputVoice("");
		configureOutputVoice("");
	}

	@Override
	public void start(ConfigNode node) {
		// TODO Auto-generated method stub
	}

	@Override
	public void speech() {
		// <mbrola-bin> -e <voice> <input> <output>
		StringBuilder execLine = new StringBuilder();
		execLine.append(mbrolabin).append(" ").append("-e").append(" ");
		execLine.append(voice).append(" ");
		execLine.append(input).append(" ");
		execLine.append(output);

		try {
			Runtime.getRuntime().exec(execLine.toString()).waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
	
	private void configureVoice(String voiceName) {
		File voice = Utils.getResource(voiceName);
		this.voice = voice.getName();
	}
	
	private void configureInputVoice(String string) {
		// TODO Auto-generated method stub
	}
	
	private void configureOutputVoice(String string) {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		speech();
	}

}
