package inf.furb.synthesis;

/**
 * Representa um atributo de um {@link ISynthElement}.
 */
public interface IAttribute {

	/**
	 * Retorna o nome do atributo.
	 * @return nome
	 */
	public String getName();
	
	/**
	 * Define o nome do atributo.
	 * @param name
	 */
	public void setName(String name);
	
	/**
	 * Retorna o valor do atributo.
	 * @return valor
	 */
	public String getValue();
	
	/**
	 * Define um valor ao atributo.
	 * @param valor
	 */
	public void setValue(String value);
	
	/**
	 * Define quais os valores v�lidos para este atributo.<br>
	 * Se n�o for informado nenhum array de valores v�lidos, o m�todo isValid sempre ir� retornar <code>true</code>.
	 * @param validValues valores v�lidos para este atributo
	 * @param defaultValue valor padr�o caso nenhum seja informado no setValue()
	 */
	public void setValidValues(String[] validValues, String defaultValue);
	
	/**
	 * Valida o valor do atributo.
	 * @param value valor a ser verificado.
	 * @return se o atributo � valido.
	 */
	public boolean isValid(String value);
	
	/**
	 * Retorna se o atributo � obrigat�rios ser setado.
	 * @return obrigatoriedade
	 */
	public boolean isRequired();
	
	/**
	 * Define se o atributo � requerido.
	 * @param required
	 */
	public void setRequired(boolean required);
	
}
