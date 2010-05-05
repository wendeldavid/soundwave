package inf.furb.synthesis.mbrola.comp;

public final class Net {

	public static String processNet(String text) {
		String copyText = new String(text);
		
		//retira a / do final caso tenha
		copyText = copyText.endsWith("/") ? copyText.substring(0, copyText.length() - 1) : copyText;
		
		//soletra as ultima letras depois do .com/.gov/.net etc...
		final String[] netSplit = copyText.split("\\.");
		String endNet = netSplit[netSplit.length -1].split("/")[0];
		endNet = spell(endNet);
		copyText = copyText.replaceAll("(.+)\\.(..)(/(.+))?", "$1." + endNet + "$3");
		
		String net = ".";
		copyText = processOccurrence(net, copyText);
		
		net = "/";
		copyText = processOccurrence(net, copyText);
		
		net = "@";
		copyText = processOccurrence(net, copyText);
		
		net = ":";
		copyText = processOccurrence(net, copyText);
		
		net = "www";
		copyText = processOccurrence(net, copyText);
		
		net = "ftp";
		copyText = processOccurrence(net, copyText);
		
		net = "http";
		copyText = processOccurrence(net, copyText);
		
		net = "https";
		copyText = processOccurrence(net, copyText);
		
		return copyText.replace("  ", " ").trim();//para garantir que não haja espaço duplo e nas extremidades da string, pois o sintetizador dá erro
	}
	
	private static String processOccurrence(String occurence, String text) {
		String copyText = new String(text);
		if(copyText.contains(occurence)) {
			copyText = copyText.replace(occurence, spell(occurence));
		}
		return copyText;
	}
	
	private static String spell(String text) {
		StringBuilder sb = new StringBuilder();
		for (char c : text.toCharArray()) {
			sb.append(" ").append(Speller.spell(c)).append(" ");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String text = "teste@bla.com.br";
		System.out.println(processNet(text));
		
		text = "http://www.bla.com.br/asdf";
		System.out.println(processNet(text));
	}
	
}
