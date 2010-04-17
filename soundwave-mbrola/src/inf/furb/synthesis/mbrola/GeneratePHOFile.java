package inf.furb.synthesis.mbrola;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Classe responsável por gerar o arquivo <code>.pho</code> com a descrição da fala que é utilizada pelo sintetizador acústico MBROLA. 
 */
public final class GeneratePHOFile {

	private File output;

	/**
	 * Construtor que recebe o arquivo que será gerado.
	 * @param outputFile caminho do arquivo que será gerado
	 */
	public GeneratePHOFile(String outputFile) {
		output = new File(outputFile);
	}

	/**
	 * Escreve a definição da fala no arquivo.
	 * @param content conteúdo do arquivo.
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
