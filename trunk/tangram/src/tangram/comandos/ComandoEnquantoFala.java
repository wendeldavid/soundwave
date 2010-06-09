package tangram.comandos;

import java.util.ArrayList;

public class ComandoEnquantoFala implements Comando {

	private String jsmlPath = null;
	private ArrayList<Comando> comandos = null;

	public ComandoEnquantoFala(String jsmlPath, ArrayList<Comando> al) {
		this.jsmlPath = jsmlPath;
		this.comandos = al;
	}

	public void add(Comando c) {
		comandos.add(c);
	}

	@Override
	public void faca(Executor executor) throws ComandException {
		// TODO while(true) enquanto a fala estiver sendo executada

		System.out.println(executor.toString() + "\t" + jsmlPath);
		
		TesteThread t = new TesteThread();
		t.start();
		while (t.isAlive()) {
			// para cada comando da lista
			for (Comando c : comandos) {
				// executa o comando
				c.faca(executor);
				System.err.println("executou loop enquanto\t" + t.isAlive());
			}
		}
	}

	class TesteThread extends Thread {
		@Override
		public void run() {
			try {
				for (int i = 0; i < 5; i++) {
					Thread.sleep(1000);
					System.out.println(i + "s");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
