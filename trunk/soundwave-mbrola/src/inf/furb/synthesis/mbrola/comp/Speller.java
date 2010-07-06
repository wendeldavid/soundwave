package inf.furb.synthesis.mbrola.comp;

/**
 * Soletra uma palavra
 */
public final class Speller implements IComponent{

	private char[] text;
	private double frequency;
	private int time;

	public Speller(String text) {
		this(text.toCharArray());
	}
	
	public Speller(char[] text) {
		this.text = text;
		
	}

	@Override
	public void configure(double frequency, int time) {
		this.frequency = frequency;
		this.time = time;
	}

	@Override
	public String show() {
		StringBuilder sb = new StringBuilder();
		for (char c : this.text) {
			Text component = new Text(spell(c));
			component.configure(this.frequency, this.time);
			sb.append(component.show());
		}
		return sb.toString();
	}

	public static String spell(char c) {
		switch (Character.toLowerCase(c)) {
		case '0':
			return "zero";
		case '1':
			return "um";
		case '2':
			return "dois";
		case '3':
			return "três";
		case '4':
			return "quatro";
		case '5':
			return "cinco";
		case '6':
			return "seis";
		case '7':
			return "sete";
		case '8':
			return "oito";
		case '9':
			return "nove";
		case 'a':
			return "a";
		case 'b':
			return "be";
		case 'c':
			return "se";
		case 'd':
			return "de";
		case 'e':
			return "e";
		case 'f':
			return "éfi";
		case 'g':
			return "ge";
		case 'h':
			return "agá";
		case 'i':
			return "i";
		case 'j':
			return "jóta";
		case 'k':
			return "ka";
		case 'l':
			return "éli";
		case 'm':
			return "emi";
		case 'n':
			return "eni";
		case 'o':
			return "o";
		case 'p':
			return "pe";
		case 'q':
			return "ke";
		case 'r':
			return "érri";
		case 's':
			return "éssi";
		case 't':
			return "te";
		case 'u':
			return "u";
		case 'v':
			return "ve";
		case 'w':
			return "dábliu";
		case 'x':
			return "xis";
		case 'y':
			return "ípsilon";
		case 'z':
			return "ze";
			//agora os caracteres especiais
		case '@':
			return "arroba";
		case '#':
			return "sustenido";
		case '$':
			return "sifrão";
		case '%':
			return "porcento";
		case '/':
			return "barra";
		case '\\':
			return "contra barra";
		case ',':
			return "vírgula";
		case '.':
			return "ponto";
		case '!':
			return "ponto de exclamação";
		case '?':
			return "ponto de interrogação";
		case ':':
			return "dois pontos";
		case ';':
			return "ponto e vírgula";
		case '-':
			return "menos";
		case '+':
			return "mais";
		case '*':
			return "asterístico";
		case '=':
			return "igual";
		case '(':
			return "abre parênteses";
		case ')':
			return "fécha parênteses";
		case '[':
			return "abre colchete";
		case ']':
			return "fécha colchete";
		case '{':
			return "abre chaves";
		case '}':
			return "fécha chaves";
		case '\'':
			return "apóstrofo";
		case '"':
			return "aspas";
		case '¨':
			return "trema";
		case '&':
			return "e comercial";
		case '`':
			return "acento grave";
		case '´':
			return "acento agudo";
		case '^':
			return "acento circunflexo";
		case '~':
			return "til";
		default:
			return "_";
		}
	}
	
}
