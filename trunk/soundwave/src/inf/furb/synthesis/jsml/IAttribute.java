package inf.furb.synthesis.jsml;

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
	 * Define quais os valores válidos para este atributo.<br>
	 * Se não for informado nenhum array de valores válidos, o método isValid sempre irá retornar <code>true</code>.
	 * @param validValues valores válidos para este atributo
	 * @param defaultValue valor padrão caso nenhum seja informado no setValue()
	 */
	public void setValidValues(String[] validValues, String defaultValue);
	
	/**
	 * Valida o valor do atributo.
	 * @param value valor a ser verificado.
	 * @return se o atributo é valido.
	 */
	public boolean isValid(String value);
	
	/**
	 * Retorna se o atributo é obrigatórios ser setado.
	 * @return obrigatoriedade
	 */
	public boolean isRequired();
	
	/**
	 * Define se o atributo é requerido.
	 * @param required
	 */
	public void setRequired(boolean required);
	
}
