package inf.furb.synthesis.mbrola;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Classe respons�vel por gerar o arquivo <code>.pho</code> com a descri��o da fala que � utilizada pelo sintetizador ac�stico MBROLA. 
 */
public final class GeneratePHOFile {

	private File output;

	/**
	 * Construtor que recebe o arquivo que ser� gerado.
	 * @param outputFile caminho do arquivo que ser� gerado
	 */
	public GeneratePHOFile(String outputFile) {
		output = new File(outputFile);
	}

	/**
	 * Escreve a defini��o da fala no arquivo.
	 * @param content conte�do do arquivo.
	 */
	public void writeFile(String content) {
		try {
			FileWriter outFile = new FileWriter(output);
			PrintWriter out = new PrintWriter(outFile);
			out.println(content);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
