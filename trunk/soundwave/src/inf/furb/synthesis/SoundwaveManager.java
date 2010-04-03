package inf.furb.synthesis;

import inf.furb.common.ConfigNode;
import inf.furb.synthesis.jsml.Break;
import inf.furb.synthesis.jsml.Div;
import inf.furb.synthesis.jsml.Emphasis;
import inf.furb.synthesis.jsml.Engine;
import inf.furb.synthesis.jsml.ISynthElement;
import inf.furb.synthesis.jsml.JSML;
import inf.furb.synthesis.jsml.Marker;
import inf.furb.synthesis.jsml.Phoneme;
import inf.furb.synthesis.jsml.Prosody;
import inf.furb.synthesis.jsml.SayAs;
import inf.furb.synthesis.jsml.Voice;
import inf.furb.xml.JSMLParser;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

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
		List<ISynthElement> elements = parser.getSynthElements();
		this.synthesizer.configure(getConfigNode(elements));
	}

	private ConfigNode getConfigNode(List<ISynthElement> elements) {
		ConfigNode configNode = new ConfigNode();
		//TODO ver como vai ficar o confignode para mandar as configurações para o sintetizador
		for (ISynthElement element : elements) {
			if(element instanceof JSML) {
				
			}else if(element instanceof Div) {
				
			}else if(element instanceof Break) {
				
			}else if(element instanceof Marker) {
				
			}else if(element instanceof Emphasis) {
				
			}else if(element instanceof Engine) {
				
			}else if(element instanceof Phoneme) {
				
			}else if(element instanceof Prosody) {
				
			}else if(element instanceof SayAs) {
				
			}else if(element instanceof Voice) {
				
			}
		}
		return configNode;
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
