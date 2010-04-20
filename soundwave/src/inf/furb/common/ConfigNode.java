package inf.furb.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe de configuração.
 */
public class ConfigNode {

	private String type;
	
	private Map<String, Object> map;
	
	/**
	 * Construtor padrão de um nó de configuração. 
	 */
	public ConfigNode() {
		map = new HashMap<String, Object>();
	}
	
	/**
	 * Seta uma configuração a esse nó.
	 * @param name node da configuração
	 * @param value valor da configuração
	 */
	public void setConfig(String name, Object value) {
		map.put(name, value);
	}

	/**
	 * Retorna a configuração.
	 * @param name nome da configuração
	 * @return configuração
	 */
	public Object getConfig(String name) {
		return map.get(name);
	}
	
	/**
	 * Remove a configuração desse nó.
	 * @param name nome da configuração a ser removida
	 */
	public void removeConfig(String name) {
		map.remove(name);
	}

	/**
	 * Retorna um mapa com todas as configurações desse nó.
	 * @return mapa con as configurações
	 */
	public Map<String, Object> getConfiMap(){
		return Collections.unmodifiableMap(map);
	}

	/**
	 * Define o tipo de configuração.
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Retorna o tipo da configuração.
	 * @return
	 */
	public String getType() {
		return type;
	}
	
}
