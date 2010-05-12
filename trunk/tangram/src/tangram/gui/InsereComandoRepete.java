/*
 * InsereComandoRepete.java
 *
 * Created on 8 de Julho de 2008, 19:13
 */

package tangram.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author  GlauKo
 */
public class InsereComandoRepete extends javax.swing.JPanel {

    int repeticoes;
    boolean gerarCodigo;
    JDialog dialog;
    
    /** Creates new form InsereComandoRepete */
    public InsereComandoRepete() {
        initComponents();
    }
    
    public String showDialog(JFrame frame){
        String codigo = null;
        if( dialog == null){
            // cria um novo dialog
            dialog = new JDialog(frame, "Insere Comando Repete", true);
            // adiciona este painel no dialog
            dialog.add(this);
            dialog.pack();
            Dimension tela =  Toolkit.getDefaultToolkit().getScreenSize();  
            dialog.setLocation( (tela.width - dialog.getSize().width)/2, 
                    (tela.height - dialog.getSize().height)/2 );
        }
        // mostra o dialog e segura a execu�ão até o fechamento do mesmo
        dialog.setVisible(true);
        // se confirmou
        if(gerarCodigo){
            // cria nova string
            codigo = new String();
            codigo += "repita " + 
                    String.valueOf(repeticoes) + 
                    " vezes inicio\n    \nfim";
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jbtConfirma = new javax.swing.JButton();
        jbtCancela = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("N�mero de repeti��es"));

        jLabel1.setText("Repeti��es");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addGap(0, 180, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtConfirma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtCancela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 112, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton jbtCancela;
    private javax.swing.JButton jbtConfirma;
    // End of variables declaration//GEN-END:variables

    private boolean validaEntrada(){
        try{
            repeticoes = Integer.parseInt(jTextField1.getText());
        } catch(Exception e){
            Msg.erro("Entrada Inv�lida!");
            jTextField1.requestFocus();
            return false;
        }
        return true;
    }
}
