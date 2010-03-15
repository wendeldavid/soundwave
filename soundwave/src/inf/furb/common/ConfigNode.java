package inf.furb.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe de configura��o.
 */
public class ConfigNode {

	private Map<String, Object> map;
	
	/**
	 * Construtor padr�o de um n� de configura��o. 
	 */
	public ConfigNode() {
		map = new HashMap<String, Object>();
	}
	
	/**
	 * Seta uma configura��o a esse n�.
	 * @param name node da configura��o
	 * @param value valor da configura��o
	 */
	public void setConfig(String name, Object value) {
		map.put(name, value);
	}

	/**
	 * Retorna a configura��o.
	 * @param name nome da configura��o
	 * @return configura��o
	 */
	public Object getConfig(String name) {
		return map.get(name);
	}
	
	/**
	 * Remove a configura��o desse n�.
	 * @param name nome da configura��o a ser removida
	 */
	public void removeConfig(String name) {
		map.remove(name);
	}

	/**
	 * Retorna um mapa com todas as configura��es desse n�.
	 * @return mapa con as configura��es
	 */
	public Map<String, Object> getConfiMap(){
		return Collections.unmodifiableMap(map);
	}
	
}
