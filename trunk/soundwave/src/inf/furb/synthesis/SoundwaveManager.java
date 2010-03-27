package inf.furb.synthesis;

import inf.furb.xml.JSMLParser;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Classe <b>power</b> que tem a responsabilidade primordial de receber o documento JSML, enviar ao parser e receber a estrutura e repassar ao sintetizador.<br>
 * Imagine o Megatron dando ordens ao Soundwave.
 */
public class SoundwaveManager {

	private JSMLParser parser;
	private ISynthesizer synthesizer;

	public SoundwaveManager(File jsmlDocument, String synthesizer) {
		createJSMLParser(jsmlDocument);
		createSynthesizer(synthesizer);
	}

	private void createJSMLParser(File jsmlFile) {
		parser = new JSMLParser(jsmlFile);
	}

	private void createSynthesizer(String synthesizer) {
		Exception exception = null;
		try {
			Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass(synthesizer);

			for (int i = 0; i < clazz.getInterfaces().length; i++) {
				if (ISynthesizer.class.getName().equals(clazz.getInterfaces()[i].getName())) {
					Constructor<?> constructor = clazz.getDeclaredConstructor();
					this.synthesizer = (ISynthesizer) constructor.newInstance(null);
					return;
				}
			}
		} catch (IllegalArgumentException e) {
			exception = e;
		} catch (InstantiationException e) {
			exception = e;
		} catch (IllegalAccessException e) {
			exception = e;
		} catch (InvocationTargetException e) {
			exception = e;
		} catch (SecurityException e) {
			exception = e;
		} catch (NoSuchMethodException e) {
			exception = e;
		} catch (ClassNotFoundException e) {
			exception = e;
		}
		
		if(exception != null) {
			exception.printStackTrace();
			throw new RuntimeException(exception);
		}
	}
}
