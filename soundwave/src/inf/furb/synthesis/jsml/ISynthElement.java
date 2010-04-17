package inf.furb.synthesis.jsml;

import java.util.Collection;

/**
 * Elemento que contém informações semâmticas do texto para o sintetizador.
 */
public interface ISynthElement {

	//atributo comum a todos os elementos do sintetizador
	public static final String MARK = "mark";
	
	public static final String JSML = "jsml";
	public static final String DIV = "div";
	public static final String BREAK = "break";
	public static final String EMPHASIS = "emphasis";
	public static final String ENGINE = "engine";
	public static final String MARKER = "marker";
	public static final String PHONEME = "phoneme";
	public static final String PROSODY = "prosody";
	public static final String SAYAS = "sayas";
	public static final String VOICE = "voice";
	
	/**
	 * Retorna uma coleção com os atributos válidos.
	 * @return coleção de atributos
	 */
	public Collection<IAttribute> getAttributes();
	
	/**
	 * Retorna o atributo solicitado no parâmetro.
	 * @param attName
	 * @return {@link IAttribute}
	 */
	public IAttribute getAttribute(String attName);
	
	/**
	 * Retorna o nome do elemento.
	 * @return nome do elemento
	 */
	public String getName();
	
	/**
	 * Retorna o texto contido neste elemento.
	 * @return texto contido
	 */
	public String getText();
	
	/**
	 * TODO
	 * @return
	 */
	public String getMark();
	
	/**
	 * TODO
	 * @param mark
	 */
	public void setMark(String mark);
	
}
