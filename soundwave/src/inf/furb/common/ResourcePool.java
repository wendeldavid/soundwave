package inf.furb.common;

import java.io.File;

import org.apache.tools.ant.taskdefs.Copy;
import org.apache.tools.ant.taskdefs.Delete;

/**
 * Manipula recursos do disco que encontram-se dentro de um diretório chamado <code>.soundwave</code> que fica dentro do diretorio temporario.
 */
public final class ResourcePool {

	private static final File TEMP_DIR = new File(System.getProperty("java.io.tmpdir") + File.separator + ".soundwave");

	static {
		verifyCreatePoolDir();
		cleanPool();
	}

	/**
	 * Verifica se o diretório de pool existe, senão cria ele.
	 */
	public static void verifyCreatePoolDir() {
		if (!TEMP_DIR.exists()) {
			TEMP_DIR.mkdir();
		}
	}

	/**
	 * Copia o arquivo passado por parametro sempre sobrescrevendo caso ja exista um com o mesmo nome.
	 * 
	 * @param file
	 * @return
	 * @see ResourcePool.copyFile(file, boolean)
	 */
	public static File copyFile(File file) {
		return copyFile(file, true);
	}

	/**
	 * Copia o arquivo passado por parametro para o diretorio temporario e retorna a referencia do novo arquivo.
	 * 
	 * @param file
	 *            arquivo a ser copiado
	 * @param overwrite
	 *            sobrescreve se já existir um aquivo no pool
	 * @return referencia do arquivo copiado
	 */
	public static File copyFile(File file, boolean overwrite) {
		Copy copy = new Copy();
		copy.setFile(file);
		copy.setTodir(TEMP_DIR);
		copy.setOverwrite(overwrite);
		copy.execute();
		return new File(TEMP_DIR, file.getName());
	}

	/**
	 * Exclui todos os arquivos e pastas do diretório de trabalho.
	 */
	public static void cleanPool() {
		Delete delete = new Delete();
		delete.setIncludeEmptyDirs(true);
		delete.setQuiet(true);
		delete.setVerbose(false);
		delete.setDir(TEMP_DIR);
		delete.execute();
	}
}
