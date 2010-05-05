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
			return "�fi";
		case 'g':
			return "ge";
		case 'h':
			return "ag�";
		case 'i':
			return "i";
		case 'j':
			return "j�ta";
		case 'k':
			return "ka";
		case 'l':
			return "�li";
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
			return "�rri";
		case 's':
			return "�ssi";
		case 't':
			return "te";
		case 'u':
			return "u";
		case 'v':
			return "ve";
		case 'w':
			return "d�bliu";
		case 'x':
			return "xis";
		case 'y':
			return "�psilon";
		case 'z':
			return "ze";
			//agora os caracteres especiais
		case '@':
			return "arroba";
		case '#':
			return "sustenido";
		case '$':
			return "sifr�o";
		case '%':
			return "porcento";
		case '/':
			return "barra";
		case '\\':
			return "contra barra";
		case ',':
			return "v�rgula";
		case '.':
			return "ponto";
		case '!':
			return "ponto de exclama��o";
		case '?':
			return "ponto de interroga��o";
		case ':':
			return "dois pontos";
		case ';':
			return "ponto e v�rgula";
		case '-':
			return "menos";
		case '+':
			return "mais";
		case '*':
			return "aster�stico";
		case '=':
			return "igual";
		case '(':
			return "abre par�nteses";
		case ')':
			return "f�cha par�nteses";
		case '[':
			return "abre colchete";
		case ']':
			return "f�cha colchete";
		case '{':
			return "abre chaves";
		case '}':
			return "f�cha chaves";
		case '\'':
			return "ap�strofo";
		case '"':
			return "aspas";
		case '�':
			return "trema";
		case '&':
			return "e comercial";
		case '`':
			return "acento grave";
		case '�':
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
