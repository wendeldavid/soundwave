/*
 * InsereComandoFaca.java
 *
 * Created on 8 de Julho de 2008, 19:22
 */

package tangram.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

import tangram.ModelMaker;

/**
 *
 * @author  GlauKo
 */
public class InsereComandoFaca extends javax.swing.JPanel {

    boolean gerarCodigo;
    JDialog dialog;
    String acao;
    
    /** Creates new form InsereComandoFaca */
    public InsereComandoFaca(ArrayList<String> acoes) {
        initComponents();
        jcbAcao.removeAllItems();
        for (String s : acoes){
            if(!s.equals(ModelMaker.CRIA))
                jcbAcao.addItem(s);
        }
    }
    
    public String showDialog(JFrame frame){
        String codigo = null;
        if( dialog == null){
            // cria um novo dialog
            dialog = new JDialog(frame, "Insere Comando Faça", true);
            // adiciona este painel no dialog
            dialog.add(this);
            dialog.pack();
            Dimension tela =  Toolkit.getDefaultToolkit().getScreenSize();  
            dialog.setLocation( (tela.width - dialog.getSize().width)/2, 
                    (tela.height - dialog.getSize().height)/2 );
        }
        // mostra o dialog e segura a execução até o fechamento do mesmo
        dialog.setVisible(true);
        // se confirmou
        if(gerarCodigo){
            // cria nova string
            codigo = new String();
            codigo += "faça " + acao;
        }
        return codigo;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        acaoAExecutar = new javax.swing.JPanel();
        jcbAcao = new javax.swing.JComboBox();
        jbtConfirma = new javax.swing.JButton();
        jbtCancela = new javax.swing.JButton();

        acaoAExecutar.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleciona a Ação"));

        jcbAcao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout acaoAExecutarLayout = new javax.swing.GroupLayout(acaoAExecutar);
        acaoAExecutar.setLayout(acaoAExecutarLayout);
        acaoAExecutarLayout.setHorizontalGroup(
            acaoAExecutarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(acaoAExecutarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcbAcao, 0, 201, Short.MAX_VALUE)
                .addContainerGap())
        );
        acaoAExecutarLayout.setVerticalGroup(
            acaoAExecutarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jcbAcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jbtConfirma.setText("Confirmar");
        jbtConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtConfirmaconfirma(evt);
            }
        });

        jbtCancela.setText("Cancelar");
        jbtCancela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelacancelar(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(acaoAExecutar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtConfirma, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                        .addGap(75, 75, 75)
                        .addComponent(jbtCancela, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(acaoAExecutar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtConfirma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtCancela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

private void jbtConfirmaconfirma(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtConfirmaconfirma
    // Valida os Campos
    if(validaEntrada()){
        // se valiu retorna
        gerarCodigo = true;
        dialog.setVisible(false);
    }
}//GEN-LAST:event_jbtConfirmaconfirma

private void jbtCancelacancelar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelacancelar
    dialog.setVisible(false);
}//GEN-LAST:event_jbtCancelacancelar


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel acaoAExecutar;
    private javax.swing.JButton jbtCancela;
    private javax.swing.JButton jbtConfirma;
    private javax.swing.JComboBox jcbAcao;
    // End of variables declaration//GEN-END:variables

    private boolean validaEntrada() {
        try{
            acao = (String) jcbAcao.getSelectedItem();
        } catch (Exception e){
            Msg.erro("Seleciona uma ação!");
            jcbAcao.requestFocus();
            return false;
        }
        return true;
    }
}