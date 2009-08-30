/*
 * EditorGrafico.java
 *
 * Created on 13 de Janeiro de 2008, 19:36
 */

package tangram.gui;

import java.awt.Color;
import java.awt.Toolkit;

import javax.media.opengl.GLCanvas;
import javax.swing.JButton;

import tangram.MEHforModels;
import tangram.MEHforMundo;
import tangram.MouseEventHandler;
import tangram.MouseEventHandler.Modo;

import com.sun.opengl.util.Animator;

/**
 *
 * @author  GlauKo
 */
public class EditorGrafico extends javax.swing.JFrame {
    
    private MouseEventHandler handler;
    
    private JButton botaoMarcado;
    private Color corMarcada;
    
    /** Creates new form EditorGrafico */
    public EditorGrafico(final GLCanvas cavas, MouseEventHandler handler) {
        initComponents();
        
        if(handler.getClass() != MEHforModels.class)
            toolBarModelos.setVisible(false);
        if(handler.getClass() != MEHforMundo.class)
            toolBarMundo.setVisible(false);
        else
            getContentPane().add(toolBarMundo,java.awt.BorderLayout.PAGE_START);
        
        this.handler = handler;
        
        // adiciona o canvas ao frame
        getContentPane().add(cavas);
        cavas.requestFocus();
        
        // Animator fica fazendo o display constantemente.
        Animator anime = new Animator(cavas);
        anime.start();
    }
    
    public void mudaModo(Modo modo){
        handler.setModo(modo);
    }
    
    private void marcaBotao(JButton botao){
        if(botaoMarcado != null)
            botaoMarcado.setBackground(corMarcada);
        corMarcada = botao.getBackground();
        botaoMarcado = botao;
        botao.setBackground(new Color(0.25f,0.25f,0.9f,0.5f));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBarModelos = new javax.swing.JToolBar();
        btnPeca = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnModelo = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnEspelha = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnMudaCor = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnPan = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        toolBarMundo = new javax.swing.JToolBar();
        btnModelo2 = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        btnInsere = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        btnMudaCor1 = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JToolBar.Separator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tangram 3.0 - Editor Gráfico de Modelos");
        setIconImage(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/Imagens/Tangram.png")));

        toolBarModelos.setRollover(true);

        btnPeca.setText("Seleciona Peça");
        btnPeca.setFocusable(false);
        btnPeca.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPeca.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPeca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPecaActionPerformed(evt);
            }
        });
        toolBarModelos.add(btnPeca);
        toolBarModelos.add(jSeparator1);

        btnModelo.setText("Seleciona Modelo");
        btnModelo.setFocusable(false);
        btnModelo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnModelo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModeloActionPerformed(evt);
            }
        });
        toolBarModelos.add(btnModelo);
        toolBarModelos.add(jSeparator2);

        btnEspelha.setText("Espelhar");
        btnEspelha.setFocusable(false);
        btnEspelha.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEspelha.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEspelha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEspelhaActionPerformed(evt);
            }
        });
        toolBarModelos.add(btnEspelha);
        toolBarModelos.add(jSeparator3);

        btnMudaCor.setText("Muda Cor");
        btnMudaCor.setFocusable(false);
        btnMudaCor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMudaCor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMudaCor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMudaCorActionPerformed(evt);
            }
        });
        toolBarModelos.add(btnMudaCor);
        toolBarModelos.add(jSeparator4);

        btnPan.setText("Pan");
        btnPan.setFocusable(false);
        btnPan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPanActionPerformed(evt);
            }
        });
        toolBarModelos.add(btnPan);
        toolBarModelos.add(jSeparator5);

        getContentPane().add(toolBarModelos, java.awt.BorderLayout.PAGE_START);

        toolBarMundo.setRollover(true);

        btnModelo2.setText("Seleciona Modelo");
        btnModelo2.setFocusable(false);
        btnModelo2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnModelo2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnModelo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModeloActionPerformed(evt);
            }
        });
        toolBarMundo.add(btnModelo2);
        toolBarMundo.add(jSeparator6);

        btnInsere.setText("Inserir Modelo");
        btnInsere.setFocusable(false);
        btnInsere.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnInsere.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnInsere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsereActionPerformed(evt);
            }
        });
        toolBarMundo.add(btnInsere);
        toolBarMundo.add(jSeparator7);

        btnMudaCor1.setText("Muda Cor");
        btnMudaCor1.setFocusable(false);
        btnMudaCor1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMudaCor1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMudaCor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMudaCorActionPerformed(evt);
            }
        });
        toolBarMundo.add(btnMudaCor1);
        toolBarMundo.add(jSeparator8);

        getContentPane().add(toolBarMundo, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPecaActionPerformed
        mudaModo(Modo.SELECIONA_PECA);
        marcaBotao((JButton)evt.getSource());
}//GEN-LAST:event_btnPecaActionPerformed

    private void btnModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModeloActionPerformed
        mudaModo(Modo.SELECIONA_MODELO);
        marcaBotao((JButton)evt.getSource());
}//GEN-LAST:event_btnModeloActionPerformed

    private void btnEspelhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEspelhaActionPerformed
        mudaModo(Modo.MIRROR);
        marcaBotao((JButton)evt.getSource());
    }//GEN-LAST:event_btnEspelhaActionPerformed

    private void btnPanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPanActionPerformed
        mudaModo(Modo.PAN);
        marcaBotao((JButton)evt.getSource());
    }//GEN-LAST:event_btnPanActionPerformed

    private void btnMudaCorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMudaCorActionPerformed
        mudaModo(Modo.MUDA_COR);
        marcaBotao((JButton)evt.getSource());
    }//GEN-LAST:event_btnMudaCorActionPerformed

    private void btnInsereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsereActionPerformed
        mudaModo(Modo.CRIA_MODELO);
        marcaBotao((JButton)evt.getSource());
    }//GEN-LAST:event_btnInsereActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEspelha;
    private javax.swing.JButton btnInsere;
    private javax.swing.JButton btnModelo;
    private javax.swing.JButton btnModelo2;
    private javax.swing.JButton btnMudaCor;
    private javax.swing.JButton btnMudaCor1;
    private javax.swing.JButton btnPan;
    private javax.swing.JButton btnPeca;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JToolBar toolBarModelos;
    private javax.swing.JToolBar toolBarMundo;
    // End of variables declaration//GEN-END:variables
    
}
