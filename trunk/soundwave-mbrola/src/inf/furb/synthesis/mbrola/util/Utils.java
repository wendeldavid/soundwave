package inf.furb.synthesis.mbrola.util;

import inf.furb.common.ResourcePool;
import inf.furb.synthesis.mbrola.MBRolaSynthesizer;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Classe utilit�ria utilizada pelo sintetizador.
 */
public final class Utils {

	private static final String RESOURCE_PATH = "inf/furb/synthesis/mbrola/resource/";
	
	/**
	 * Retorna o {@link File} do {@link ResourcePool} do arquivo procurado no classloader.
	 * @param res arquivo procurado
	 * @return arquivo do pool
	 */
	public static File getResource(String res) {
		URI resource;
		try {
			resource = MBRolaSynthesizer.class.getClassLoader().getResource(RESOURCE_PATH + res).toURI();
			return ResourcePool.copyFile(new File(resource));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}