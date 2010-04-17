package inf.furb.synthesis.mbrola.converter;

import inf.furb.synthesis.jsml.Break;
import inf.furb.synthesis.jsml.Div;
import inf.furb.synthesis.jsml.Emphasis;
import inf.furb.synthesis.jsml.Engine;
import inf.furb.synthesis.jsml.JSML;
import inf.furb.synthesis.jsml.Marker;
import inf.furb.synthesis.jsml.Phoneme;
import inf.furb.synthesis.jsml.Prosody;
import inf.furb.synthesis.jsml.SayAs;
import inf.furb.synthesis.jsml.Voice;


/**
 * Classe construtora de conversores JSML to MBROLA.
 */
public final class ConverterFactory {

	/**
	 * Retorna a implementação do conversor MBROLA específico para cada tipo de elemento JSML.
	 * @param clazz tipo do elemento JSML
	 * @return implementação do conversor MBROLA
	 */
	public static IConverter createConverter(Class<?> clazz){
		if(JSML.class.equals(clazz)) {
			return new JSMLConverter();	 
		} else if (Div.class.equals(clazz)) {
			return new DivConverter();
		} else if (Break.class.equals(clazz)) {
			return new BreakConverter();
		} else if (Marker.class.equals(clazz)) {
			return new MarkerConverter();
		} else if (Emphasis.class.equals(clazz)) {
			return new EmphasisConverter();
		} else if (Engine.class.equals(clazz)) {
			return new EngineConverter();
		} else if (Phoneme.class.equals(clazz)) {
			return new PhonemeConverter();
		} else if (Prosody.class.equals(clazz)) {
			return new ProsodyConverter();
		} else if (SayAs.class.equals(clazz)) {
			return new SayAsConverter();
		} else if (Voice.class.equals(clazz)) {
			//voice não é suportado aqui, veja lá no MBRolaSytnhesizer.configureVoice
			return null;
		}
		return null;
	}
	
}
