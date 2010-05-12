/*
 * Principal.java
 *
 * Created on 28 de Fevereiro de 2008, 23:48
 */

package tangram.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import tangram.ModelMaker;
import tangram.Modelo;
import tangram.Mundo;
import tangram.MouseEventHandler.Modo;
import tangram.io.AbrirArquivo;

/**
 * 
 * @author GlauKo
 */
public class Principal extends javax.swing.JFrame {

	/** Creates new form Principal */
	public Principal() {
		initComponents();
		mudaLookAndFeel(looks[0].getName());
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((tela.width - getSize().width) / 2, (tela.height - getSize().height) / 2);
	}

	private void mudaLookAndFeel(String looksName) {
		try {
			for(int i = 0; i < looks.length; i++) {
				if(looks[i].getName().equals(looksName)){
					javax.swing.UIManager.setLookAndFeel(looks[i].getClassName());
					javax.swing.SwingUtilities.updateComponentTreeUI(this);
					if (modelo != null) {
						javax.swing.SwingUtilities.updateComponentTreeUI(modelo.getEditorModelos());
						modelo.getEditorModelos().pack();
						javax.swing.SwingUtilities.updateComponentTreeUI(modelo.getEditorTexto());
						modelo.getEditorTexto().pack();
					}
					pack();
				}
			}
		} catch (Exception e) {
			Msg.erro("Look and Feel escolhido n�o dispon�vel");
		}
	}

	private void novoModelo() {
		if (modelo != null)
			modelo.fechar();
		modelo = null;
		String nomeModelo = entrada("Nome do Modelo");
		if (nomeModelo == null)
			return;
		if (ModelMaker.isNomeAcaoValido(nomeModelo))
			modelo = new Modelo(nomeModelo);
		else
			Msg.erro("Nome Inv�lido para o Modelo!");
	}

	private void novaAnimacao() {
		if (modelo != null)
			modelo.fechar();
		modelo = null;
		String nomeDoMundo = entrada("Nome do Mundo");
		if (nomeDoMundo == null)
			return;
		if (ModelMaker.isNomeAcaoValido(nomeDoMundo))
			mundo = new Mundo(nomeDoMundo);
		else
			Msg.erro("Nome Inv�lido para o Mundo!");
	}

	private void salvarModelo() {
		if (modelo != null)
			modelo.salvar();
	}

	private void salvarMundo() {
		if (mundo != null)
			mundo.salvar();
	}

	private void salvarModeloComo() {
		if (modelo != null) {
			String novoNome = entrada("Salvar Modelo Como");
			if (ModelMaker.isNomeAcaoValido(novoNome)) {
				File file = new File(novoNome + ".mod");
				if (file.exists())
					if (!Msg.questao("Modelo j� Existe!\nDeseja sobrescrever?"))
						return;
				modelo.salvar(file, novoNome);
			} else
				Msg.erro("Nome Inv�lido para o Modelo!");
		} else {
			Msg.erro("Nenhum Modelo Aberto!");
		}
	}

	private void salvarMundoComo() {
		String novoNome = entrada("Salvar Modelo Como");
		if (ModelMaker.isNomeAcaoValido(novoNome)) {
			File file = new File(novoNome + ".mun");
			if (file.exists())
				if (!Msg.questao("Mundo j� Existe!\nDeseja sobrescrever?"))
					return;
			mundo.salvar(file, novoNome);
		} else
			Msg.erro("Nome Inv�lido para o Mundo!");
	}

	private void fecharModelo() {
		if (modelo != null)
			modelo.fechar();
		modelo = null;
	}

	private void fecharAnimacao() {
		if (mundo != null)
			mundo.fechar();
		mundo = null;
	}

	private void mudaModo(Modo modo) {
		modelo.getHandler().setModo(modo);
	}

	private String entrada(String texto) {
		return JOptionPane.showInputDialog(null, texto, "Tangram 3.0", JOptionPane.PLAIN_MESSAGE);
	}

	private ArrayList<String> getNomesPecas() {
		ArrayList<String> al = new ArrayList<String>();
		al.add(modelo.getNomeDoModelo());
		al.add("p1");
		al.add("p2");
		al.add("p3");
		al.add("p4");
		al.add("p5");
		al.add("p6");
		al.add("p7");
		return al;
	}

	private void insereComando(String comando) {
		if (modelo != null && comando != null)
			modelo.getEditorTexto().insereComando(comando);
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jMenuBar = new javax.swing.JMenuBar();
		jmArquivo = new javax.swing.JMenu();
		jmiNovoModelo = new javax.swing.JMenuItem();
		jmiCarregar = new javax.swing.JMenuItem();
		jSeparator1 = new javax.swing.JSeparator();
		jmNovaAnimacao = new javax.swing.JMenuItem();
		jmCarregarAnimacao = new javax.swing.JMenuItem();
		jSeparator4 = new javax.swing.JSeparator();
		jmiSalvar = new javax.swing.JMenuItem();
		jmSalvaComo = new javax.swing.JMenuItem();
		jSeparator5 = new javax.swing.JSeparator();
		jmLookAndFeel = new javax.swing.JMenu();
		jSeparator6 = new javax.swing.JSeparator();
		jmFechar = new javax.swing.JMenuItem();
		jmEditar = new javax.swing.JMenu();
		jmInseirComando = new javax.swing.JMenu();
		jMenuItem1 = new javax.swing.JMenuItem();
		jMenuItem2 = new javax.swing.JMenuItem();
		jMenuItem3 = new javax.swing.JMenuItem();
		jMenuItem4 = new javax.swing.JMenuItem();
		jMenuItem5 = new javax.swing.JMenuItem();
		jMenuItem6 = new javax.swing.JMenuItem();
		jMenuItem7 = new javax.swing.JMenuItem();
		jSeparator3 = new javax.swing.JSeparator();
		jmCut = new javax.swing.JMenuItem();
		jmCopy = new javax.swing.JMenuItem();
		jmPaste = new javax.swing.JMenuItem();
		jmEditorGrafico = new javax.swing.JMenu();
		jmModos = new javax.swing.JMenu();
		jmiModoMovePeca = new javax.swing.JMenuItem();
		jmiModoMoveModelo = new javax.swing.JMenuItem();
		jmiModoEspelhar = new javax.swing.JMenuItem();
		jmMudaCor = new javax.swing.JMenuItem();
		jSeparator2 = new javax.swing.JSeparator();
		jmiModoPan = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Tangram 3.0");
		setIconImage(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/imagens/Tangram.png")));
		setName("Principal"); // NOI18N
		setResizable(false);

		jmArquivo.setText("Arquivo");

		jmiNovoModelo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Tangram.png"))); // NOI18N
		jmiNovoModelo.setText("Novo Modelo");
		jmiNovoModelo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiNovoModeloActionPerformed(evt);
			}
		});
		jmArquivo.add(jmiNovoModelo);

		jmiCarregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/open.png"))); // NOI18N
		jmiCarregar.setText("Carregar Modelo");
		jmiCarregar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiCarregarActionPerformed(evt);
			}
		});
		jmArquivo.add(jmiCarregar);
		jmArquivo.add(jSeparator1);

		jmNovaAnimacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/EditorMundo.png"))); // NOI18N
		jmNovaAnimacao.setText("Novo Mundo");
		jmNovaAnimacao.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmNovaAnimacaoActionPerformed(evt);
			}
		});
		jmArquivo.add(jmNovaAnimacao);

		jmCarregarAnimacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/open.png"))); // NOI18N
		jmCarregarAnimacao.setText("Carregar Mundo");
		jmCarregarAnimacao.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmCarregarAnimacaoActionPerformed(evt);
			}
		});
		jmArquivo.add(jmCarregarAnimacao);
		jmArquivo.add(jSeparator4);

		jmiSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/save.png"))); // NOI18N
		jmiSalvar.setText("Salvar");
		jmiSalvar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiSalvarActionPerformed(evt);
			}
		});
		jmArquivo.add(jmiSalvar);

		jmSalvaComo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/saveas.png"))); // NOI18N
		jmSalvaComo.setText("Salvar como");
		jmSalvaComo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmSalvaComoActionPerformed(evt);
			}
		});
		jmArquivo.add(jmSalvaComo);
		jmArquivo.add(jSeparator5);

		jmLookAndFeel.setText("Look & Feel");

		for(int i = 0; i < looks.length; i++) {
			JMenuItem jMenuItemLookAndFeel = new JMenuItem(looks[i].getName());
			addLookAndFeelListener(jMenuItemLookAndFeel);
			jmLookAndFeel.add(jMenuItemLookAndFeel);
		}

		jmArquivo.add(jmLookAndFeel);
		jmArquivo.add(jSeparator6);

		jmFechar.setText("Fechar");
		jmFechar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmFecharActionPerformed(evt);
			}
		});
		jmArquivo.add(jmFechar);

		jMenuBar.add(jmArquivo);

		jmEditar.setText("Editar");

		jmInseirComando.setText("Inserir Comando");

		jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
		jMenuItem1.setText("move");
		jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem1ActionPerformed(evt);
			}
		});
		jmInseirComando.add(jMenuItem1);

		jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
		jMenuItem2.setText("gira");
		jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem2ActionPerformed(evt);
			}
		});
		jmInseirComando.add(jMenuItem2);

		jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
		jMenuItem3.setText("espelha");
		jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem3ActionPerformed(evt);
			}
		});
		jmInseirComando.add(jMenuItem3);

		jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
		jMenuItem4.setText("cor");
		jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem4ActionPerformed(evt);
			}
		});
		jmInseirComando.add(jMenuItem4);

		jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
		jMenuItem5.setText("pisca");
		jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem5ActionPerformed(evt);
			}
		});
		jmInseirComando.add(jMenuItem5);

		jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
		jMenuItem6.setText("repete");
		jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem6ActionPerformed(evt);
			}
		});
		jmInseirComando.add(jMenuItem6);

		jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
		jMenuItem7.setText("fa�a");
		jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem7ActionPerformed(evt);
			}
		});
		jmInseirComando.add(jMenuItem7);

		jmEditar.add(jmInseirComando);
		jmEditar.add(jSeparator3);

		jmCut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cut.png"))); // NOI18N
		jmCut.setText("Recortar");
		jmEditar.add(jmCut);

		jmCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/copy.png"))); // NOI18N
		jmCopy.setText("Copiar");
		jmEditar.add(jmCopy);

		jmPaste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/paste.png"))); // NOI18N
		jmPaste.setText("Colar");
		jmEditar.add(jmPaste);

		jMenuBar.add(jmEditar);

		jmEditorGrafico.setText("Editor Gr�fico");

		jmModos.setText("Modo");

		jmiModoMovePeca.setText("Usar Pe�as");
		jmiModoMovePeca.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiModoMovePecaActionPerformed(evt);
			}
		});
		jmModos.add(jmiModoMovePeca);

		jmiModoMoveModelo.setText("Usar Modelo");
		jmiModoMoveModelo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiModoMoveModeloActionPerformed(evt);
			}
		});
		jmModos.add(jmiModoMoveModelo);

		jmiModoEspelhar.setText("Espelhar");
		jmiModoEspelhar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiModoEspelharActionPerformed(evt);
			}
		});
		jmModos.add(jmiModoEspelhar);

		jmMudaCor.setText("Muda Cor");
		jmModos.add(jmMudaCor);
		jmModos.add(jSeparator2);

		jmiModoPan.setText("Pan");
		jmiModoPan.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiModoPanActionPerformed(evt);
			}
		});
		jmModos.add(jmiModoPan);

		jmEditorGrafico.add(jmModos);

		jMenuBar.add(jmEditorGrafico);

		setJMenuBar(jMenuBar);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 355, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 225, Short.MAX_VALUE));

		pack();
	}// </editor-fold>//GEN-END:initComponents
	
	private void addLookAndFeelListener(JMenuItem jMenuItem) {
		jMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				mudaLookAndFeel(evt.getActionCommand());
			}
		});
	}

	private void jmiNovoModeloActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiNovoModeloActionPerformed
		novoModelo();
	}// GEN-LAST:event_jmiNovoModeloActionPerformed

	private void jmiSalvarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiSalvarActionPerformed
		salvarModelo();
		salvarMundo();
	}// GEN-LAST:event_jmiSalvarActionPerformed

	private void jmiModoMovePecaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiModoMovePecaActionPerformed
		mudaModo(Modo.SELECIONA_PECA);
	}// GEN-LAST:event_jmiModoMovePecaActionPerformed

	private void jmiModoMoveModeloActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiModoMoveModeloActionPerformed
		mudaModo(Modo.SELECIONA_MODELO);
	}// GEN-LAST:event_jmiModoMoveModeloActionPerformed

	private void jmiModoEspelharActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiModoEspelharActionPerformed
		mudaModo(Modo.MIRROR);
	}// GEN-LAST:event_jmiModoEspelharActionPerformed

	private void jmiModoPanActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiModoPanActionPerformed
		mudaModo(Modo.PAN);
	}// GEN-LAST:event_jmiModoPanActionPerformed

	private void jmFecharActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmFecharActionPerformed
		fecharModelo();
	}// GEN-LAST:event_jmFecharActionPerformed

	private void jmSalvaComoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmSalvaComoActionPerformed
		if (modelo != null)
			salvarModeloComo();
		else if (mundo != null)
			salvarMundoComo();
		else
			Msg.aviso("Nenhum Modelo ou Mundo Aberto!");
	}// GEN-LAST:event_jmSalvaComoActionPerformed

	private void jmiCarregarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiCarregarActionPerformed
		fecharModelo();
		File f = new AbrirArquivo(true, false).showDialog(this);
		if (f != null)
			modelo = new Modelo(f);
	}// GEN-LAST:event_jmiCarregarActionPerformed

	private void jmNovaAnimacaoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmNovaAnimacaoActionPerformed
		novaAnimacao();
	}// GEN-LAST:event_jmNovaAnimacaoActionPerformed

	private void jmCarregarAnimacaoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmCarregarAnimacaoActionPerformed
		fecharAnimacao();
		File f = new AbrirArquivo(false, true).showDialog(this);
		if (f != null)
			mundo = new Mundo(f);
	}// GEN-LAST:event_jmCarregarAnimacaoActionPerformed

	private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem1ActionPerformed
		if (modelo != null) {
			insereComando(new InsereComando(getNomesPecas()).showDialog(this));
		}
	}// GEN-LAST:event_jMenuItem1ActionPerformed

	private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem2ActionPerformed
		if (modelo != null) {
			insereComando(new InsereComandoGira(getNomesPecas()).showDialog(this));
		}
	}// GEN-LAST:event_jMenuItem2ActionPerformed

	private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem3ActionPerformed
		if (modelo != null) {
			insereComando(new InsereComandoEspelha(getNomesPecas()).showDialog(this));
		}
	}// GEN-LAST:event_jMenuItem3ActionPerformed

	private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem4ActionPerformed
		if (modelo != null) {
			insereComando(new InsereComandoCor(getNomesPecas()).showDialog(this));
		}
	}// GEN-LAST:event_jMenuItem4ActionPerformed

	private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem5ActionPerformed
		if (modelo != null) {
			insereComando(new InsereComandoPisca().showDialog(this));
		}
	}// GEN-LAST:event_jMenuItem5ActionPerformed

	private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem6ActionPerformed
		if (modelo != null) {
			insereComando(new InsereComandoRepete().showDialog(this));
		}
	}// GEN-LAST:event_jMenuItem6ActionPerformed

	private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem7ActionPerformed
		if (modelo != null) {
			insereComando(new InsereComandoFaca(modelo.getNomesDasAcoes()).showDialog(this));
		}
	}// GEN-LAST:event_jMenuItem7ActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JMenuBar jMenuBar;
	private javax.swing.JMenuItem jMenuItem1;
	private javax.swing.JMenuItem jMenuItem2;
	private javax.swing.JMenuItem jMenuItem3;
	private javax.swing.JMenuItem jMenuItem4;
	private javax.swing.JMenuItem jMenuItem5;
	private javax.swing.JMenuItem jMenuItem6;
	private javax.swing.JMenuItem jMenuItem7;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JSeparator jSeparator2;
	private javax.swing.JSeparator jSeparator3;
	private javax.swing.JSeparator jSeparator4;
	private javax.swing.JSeparator jSeparator5;
	private javax.swing.JSeparator jSeparator6;
	private javax.swing.JMenu jmArquivo;
	private javax.swing.JMenuItem jmCarregarAnimacao;
	private javax.swing.JMenuItem jmCopy;
	private javax.swing.JMenuItem jmCut;
	private javax.swing.JMenu jmEditar;
	private javax.swing.JMenu jmEditorGrafico;
	private javax.swing.JMenuItem jmFechar;
	private javax.swing.JMenu jmInseirComando;
	private javax.swing.JMenu jmLookAndFeel;
	private javax.swing.JMenu jmModos;
	private javax.swing.JMenuItem jmMudaCor;
	private javax.swing.JMenuItem jmNovaAnimacao;
	private javax.swing.JMenuItem jmPaste;
	private javax.swing.JMenuItem jmSalvaComo;
	private javax.swing.JMenuItem jmiCarregar;
	private javax.swing.JMenuItem jmiModoEspelhar;
	private javax.swing.JMenuItem jmiModoMoveModelo;
	private javax.swing.JMenuItem jmiModoMovePeca;
	private javax.swing.JMenuItem jmiModoPan;
	private javax.swing.JMenuItem jmiNovoModelo;
	private javax.swing.JMenuItem jmiSalvar;
	// End of variables declaration//GEN-END:variables

	// Variavel que contém os tipos de look and feel
	private LookAndFeelInfo looks[] = UIManager.getInstalledLookAndFeels();

	private Modelo modelo;
	private Mundo mundo;

}
