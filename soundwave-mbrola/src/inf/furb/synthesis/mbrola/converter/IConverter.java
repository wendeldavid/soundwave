package inf.furb.synthesis.mbrola.converter;

import inf.furb.synthesis.jsml.ISynthElement;

/**
 * Interface de um conversor JSML to MBROLA.
 */
public interface IConverter {

	/**
	 * Converte o {@link ISynthElement} para o MBROLA e atualiza o script passado no StringBuilder.
	 * @param element elemento {@link ISynthElement}
	 * @param output script <code>.pho</code> com a fala convertida para o sintetizador acústico.
	 */
	public void convert(ISynthElement element, StringBuilder output);
	
}
