package inf.furb.synthesis.mbrola;

import inf.furb.common.ConfigNode;
import inf.furb.common.ResourcePool;
import inf.furb.synthesis.ISynthesizer;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Implementação do sintetizador MBRola.
 */
public final class MBRolaSynthesizer implements ISynthesizer {

	private static final String RESOURCE_PATH = "inf/furb/synthesis/mbrola/resource/";

	private String mbrolabin;
	private String voice;
	private String input;
	private String output;

	@Override
	public void configure(ConfigNode node) {
		// TODO Auto-generated method stub
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

	private File getMBrolaApp() {
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

		File file = getResource(bin);
		file.setWritable(true);
		file.setReadable(true);
		file.setExecutable(true);
		
		return file;
	}

	private File getResource(String bin) {
		final URL resource = MBRolaSynthesizer.class.getClassLoader().getResource(RESOURCE_PATH + bin);
		return ResourcePool.copyFile(new File(resource.getFile()));
	}
	
}
