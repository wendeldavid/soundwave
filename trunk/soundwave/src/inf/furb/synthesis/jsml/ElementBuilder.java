package inf.furb.synthesis.jsml;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.dom4j.Element;

/**
 * Builder de instancias de elementos do sintetizador
 */
public final class ElementBuilder {

	/**
	 * Retorna a instancia de um {@link ISynthElement} correspondente ao nodo do documento JSML.<br>
	 * Retorna <code>null</code> se o elemento não for válido
	 * @param element elemento dom do documento JSML
	 * @param clazz tipo do elemento do sintetizador
	 * @return instancia do elemento do sintetizador
	 */
	private static ISynthElement getInstance(Element element, Class<?> clazz) {
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
	
	/**
	 * Retorna a instancia de um {@link ISynthElement} correspondente ao nodo do documento JSML.<br>
	 * Retorna <code>null</code> se o elemento não for válido
	 * @param element {@link Element}
	 * @return instancia do elemento do sintetizador
	 */
	public static ISynthElement getSynthElementInstance(Element element) {
		Class<?> clazz = null;
		final String nodeName = element.getName();
		
		if(nodeName.equals(ISynthElement.JSML)) {
			clazz = JSML.class;
		}else if(nodeName.equals(ISynthElement.BREAK)) {
			clazz = Break.class;
		}else if(nodeName.equals(ISynthElement.DIV)) {
			clazz = Div.class;
		}else if(nodeName.equals(ISynthElement.EMPHASIS)) {
			clazz = Emphasis.class;
		}else if(nodeName.equals(ISynthElement.ENGINE)) {
			clazz = Engine.class;
		}else if(nodeName.equals(ISynthElement.MARKER)) {
			clazz = Marker.class;
		}else if(nodeName.equals(ISynthElement.PHONEME)) {
			clazz = Phoneme.class;
		}else if(nodeName.equals(ISynthElement.PROSODY)) {
			clazz = Prosody.class;
		}else if(nodeName.equals(ISynthElement.SAYAS)) {
			clazz = SayAs.class;
		}else if(nodeName.equals(ISynthElement.VOICE)) {
			clazz = Voice.class;
		}else {
			throw new RuntimeException("\"" + element.getName() + "\" is not a valid JSML element in line ");
		}
		
		return ElementBuilder.getInstance(element, clazz);
	}
	
}
