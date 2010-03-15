package inf.furb.synthesis;

import java.net.URLClassLoader;

/**
 * Carrega um sintetizador qualquer que implemente {@link ISynthesizer} e esteja definido pela propriedate <code>inf.furb.synthesizer.class</code>
 */
public final class GenericSynthesizerFactory {

	public static final String SYNTHESIZER_PROP = "inf.furb.synthesizer.class";
	private static String synthesizerClassName;

	static {
		synthesizerClassName = System.getProperty(SYNTHESIZER_PROP);
		if(synthesizerClassName == null) {
			throw new RuntimeException("The property \"" + SYNTHESIZER_PROP + "\" not configured");
		}
	}

	/**
	 * Retorna a instância do sintetizador configurado na propriedade <code>inf.furb.synthesizer.class</code>.
	 * 
	 * @return instancia de ISynthesizer. Retorna <code>null</code> se não encontrar a classe ou não conseguir o sintetizador.
	 */
	public static ISynthesizer getSynthesizerInstance() {

		URLClassLoader sysLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		try {

			Class<?> synthesizer = sysLoader.loadClass(synthesizerClassName);
			Class<?>[] interfaces = synthesizer.getInterfaces();
			for (int i = 0; i < interfaces.length; i++) {
				if (interfaces[i].isInterface() && interfaces[i].getName().equals(ISynthesizer.class.getName())) {
					return (ISynthesizer) synthesizer.newInstance();
				}
			}
			
			throw new RuntimeException("\"" + synthesizerClassName + "\" is not an instance of " + ISynthesizer.class.getName());

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("The synthesizer \"" + synthesizerClassName + "\" could not be found", e);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return null;
	}

}
