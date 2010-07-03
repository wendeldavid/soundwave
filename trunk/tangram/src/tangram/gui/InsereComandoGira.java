/*
 * InsereComandoGira.java
 *
 * Created on 6 de Julho de 2008, 19:18
 */

package tangram.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author  GlauKo
 */
public class InsereComandoGira extends javax.swing.JPanel {

    boolean gerarCodigo;
    JDialog dialog;
    float angulo, ponto;
    
    /** Creates new form InsereComandoGira */
    public InsereComandoGira(ArrayList metodos) {
        initComponents();
        gerarCodigo = false;
        painelSelecionaPeca1.setListaParaSelecao(metodos);
        jtAngulo.setText("");
    }
    
    public String showDialog(JFrame frame){
        String codigo = null;
        if( dialog == null){
            // cria um novo dialog
            dialog = new JDialog(frame, "Insere Comando Gira", true);
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
            codigo = "";
            codigo += painelSelecionaPeca1.getSelected() + ".gira(" + 
                    String.valueOf((int)angulo) + ")";
            if(ponto > 0)
                codigo += " no ponto(" + String.valueOf(((int)ponto)) + ")";
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

        painelSelecionaPeca1 = new PainelSelecionaPeca();
        distancia = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtAngulo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtPonto = new javax.swing.JTextField();
        jbtConfirma = new javax.swing.JButton();
        jbtCancela = new javax.swing.JButton();

        distancia.setBorder(javax.swing.BorderFactory.createTitledBorder("Valor do �ngulo a girar"));

        jLabel1.setText("�ngulo");

        jtAngulo.setText("123456");

        jLabel2.setText("Ponto");

        javax.swing.GroupLayout distanciaLayout = new javax.swing.GroupLayout(distancia);
        distancia.setLayout(distanciaLayout);
        distanciaLayout.setHorizontalGroup(
            distanciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(distanciaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtAngulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtPonto, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        distanciaLayout.setVerticalGroup(
            distanciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(distanciaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(distanciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jtPonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtAngulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(distancia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(painelSelecionaPeca1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbtConfirma, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                        .addGap(162, 162, 162)
                        .addComponent(jbtCancela, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelSelecionaPeca1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(distancia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private javax.swing.JPanel distancia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbtCancela;
    private javax.swing.JButton jbtConfirma;
    private javax.swing.JTextField jtAngulo;
    private javax.swing.JTextField jtPonto;
    private PainelSelecionaPeca painelSelecionaPeca1;
    // End of variables declaration//GEN-END:variables
    
    private boolean validaEntrada() {
        try{
            angulo = Float.parseFloat(jtAngulo.getText());
        } catch(Exception e) {
            Msg.erro("Entrada inv�lida!");
            jtAngulo.requestFocus();
            return false;
        }
        try{
            if(jtPonto.getText().equals(""))
                ponto = 0;
            else
                ponto = Float.parseFloat(jtPonto.getText());
        } catch(Exception e) {
            Msg.erro("Entrada inv�lida!");
            jtPonto.requestFocus();
            return false;
        }
        if(painelSelecionaPeca1.getSelected() == null){
            Msg.erro("Entrada inv�lida!");
            painelSelecionaPeca1.requestFocus();
            return false;
        }
        return true;
    }
}
