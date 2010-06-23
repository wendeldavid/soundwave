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

	private static final String DEFAULT_SYNTHESIZER = "inf.furb.synthesis.mbrola.MBRolaSynthesizer";
	
	private JSMLParser parser;
	private ISynthesizer synthesizer;

	public SoundwaveManager(File jsmlDocument, String synthesizer) {
		createJSMLParser(jsmlDocument);
		createSynthesizer(synthesizer);
		
		this.parser.parse();
		this.synthesizer.configure(parser.getSynthElements());
	}

	private void createJSMLParser(File jsmlFile) {
		parser = new JSMLParser(jsmlFile);
	}
	
	private void createSynthesizer(String synthesizer) {
		if(synthesizer == null) {
			synthesizer = DEFAULT_SYNTHESIZER;
		}
		
		Exception exception = null;
		try {
			Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass(synthesizer);

			for (int i = 0; i < clazz.getInterfaces().length; i++) {
				if (ISynthesizer.class.getName().equals(clazz.getInterfaces()[i].getName())) {
					Constructor<?> constructor = clazz.getDeclaredConstructor();
					Object[] params = null;//para evitar warning do compilador
					this.synthesizer = (ISynthesizer) constructor.newInstance(params);
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
