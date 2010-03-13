package inf.furb.synthesis;

import inf.furb.synthesis.ISynthElement;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.dom4j.Element;

/**
 * Fábrica de instancias de elementos do sintetizador
 */
public final class ElementFactory {

	/**
	 * Retorna a instancia de um {@link ISynthElement} correspondente ao nodo do documento JSML.<br>
	 * Retorna <code>null</code> se o elemento não for válido
	 * @param element elemento dom do documento JSML
	 * @param clazz tipo do elemento do sintetizador
	 * @return instancia do elemento do sintetizador
	 */
	public static ISynthElement getSynthElementInstance(Element element, Class<?> clazz) {
		try {
			Constructor<?> constructor = clazz.getDeclaredConstructor(Element.class);
			return (ISynthElement) constructor.newInstance(element);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
	
}
