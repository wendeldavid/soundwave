/*
 * EditorTextual.java
 *
 * Created on 29 de Fevereiro de 2008, 00:18
 */

package tangram.gui;

import java.awt.Toolkit;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.text.Element;

import tangram.Figura;
import tangram.ModelMaker;
import tangram.Modelo;
import tangram.comandos.ModeloExecutavel;
import tangram.comandos.CodeMaker.Gerador;

/**
 *
 * @author  GlauKo
 */
public class EditorTextual extends javax.swing.JFrame {
    
    
    /** Creates new form EditorTextual */
    public EditorTextual(Modelo modelo) {
        this.modelo = modelo;
        initComponents();
    }
    
    public void carregaAcoesParaTela(){
        Set acoes = getModelo().getAcoes().keySet();
        jcbAcao.removeAllItems();
        if(!acoes.isEmpty()){
            for(Iterator it = acoes.iterator(); it.hasNext();){
                jcbAcao.addItem((String)it.next());
            }
            jcbAcao.setSelectedItem(ModelMaker.CRIA);
            carregaCodigoDaAcaoSelecionada();
        }
    }
    
    public void insereComando(String comando){
        if(!modelo.getNomeAcaoSelecionada().equals(ModelMaker.CRIA)){
            String text = jtaCodigo.getText();
            String newText;
            int posicaoFinal = lineEndOffset - 1;

            if(lineEndOffset == 0)
                newText = comando + text;
            else
                newText = text.substring(0, lineEndOffset - 1) + 
                        (lineEndOffset-1 != lineStartOffset ? "\n" : "") +
                        comando + text.substring(lineEndOffset - 1);
            jtaCodigo.setText(newText);
            jtaCodigo.requestFocus();
            try{
                jtaCodigo.setCaretPosition(posicaoFinal + 1);
                modelo.getEditorModelos().requestFocus();
            } catch (Exception e){}
        }
    }
    
    public void insereComandosCRIA(String comando){
        if(modelo.getNomeAcaoSelecionada().equals(ModelMaker.CRIA))
            jtaCodigo.setText(comando);
    }
    
    private void criaNovaAcao(){
        // pergunto o nome da nova acao
        String novoNome = entrada("Nome da Nova A��o");
        
        if (novoNome == null)
            return;
            
        // valida o nome
        if(!ModelMaker.isNomeAcaoValido(novoNome)){
            Msg.erro("Nome Inv�lido para A��o!");
            return;
        }
        // verifica se n�o existe um nome igual para o modelo
        if(getModelo().getAcoes().containsKey(novoNome)){
            Msg.erro("Nome da A��o j� Existe!");
            return;
        }
        // adciona acao no modelo
        getModelo().addAcao(novoNome);
        // adciona acao no comboBox
        jcbAcao.addItem(novoNome);
        // seleciona a acao criada
        jcbAcao.getModel().setSelectedItem(novoNome);
    }
    
    public void carregaCodigoDaAcaoSelecionada(){
        jtaCodigo.setText(modelo.getCodigoAcaoSelecionada());
    }
    
    public void retornaPosicaoCria(){
        ModeloExecutavel exe = modelo.compilaCria();
        // Se Ocorreu Algum erro na compila��o n�o faz metodo.
        if(exe == null)
            return;
        Figura figura = (Figura) modelo.getFiguras().get(0);
        figura.resetPecas();
        exe.setFigura(figura);
        exe.execute(ModelMaker.CRIA);
    }
    
    private String entrada(String texto){
        return JOptionPane.showInputDialog(null,
                texto, 
                "Tangram 3.0", 
                JOptionPane.PLAIN_MESSAGE);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jpAcoes = new javax.swing.JPanel();
        jcbAcao = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaCodigo = new javax.swing.JTextArea();
        jpAngulo = new javax.swing.JPanel();
        jcbAngulo = new javax.swing.JComboBox();
        jlbAngulo = new javax.swing.JLabel();
        jlbSelecPonto = new javax.swing.JLabel();
        jcbSelecPonto = new javax.swing.JComboBox();
        jbLinha = new javax.swing.JLabel();
        lbRodape = new javax.swing.JLabel();
        jpGerador = new javax.swing.JPanel();
        jcbGerador = new javax.swing.JComboBox();
        jpBotoes = new javax.swing.JPanel();
        jbtEditorGrafico = new javax.swing.JButton();
        jbtNovaAcao = new javax.swing.JButton();
        jbtPreExecutar = new javax.swing.JButton();

        setTitle("Tangram 3.0 - Editor Textual - " + modelo.getNomeDoModelo());
        setIconImage(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/Imagens/Tangram.png")));
        setName("EditorTexto"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jpAcoes.setBorder(javax.swing.BorderFactory.createTitledBorder("A��es"));

        jcbAcao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Posi��o_Inicial", "Vida" }));
        jcbAcao.setToolTipText("<html> Escolha aqui a A��o do Modelo para ser editado. </html>");
        jcbAcao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbAcaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpAcoesLayout = new javax.swing.GroupLayout(jpAcoes);
        jpAcoes.setLayout(jpAcoesLayout);
        jpAcoesLayout.setHorizontalGroup(
            jpAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAcoesLayout.createSequentialGroup()
                .addComponent(jcbAcao, 0, 307, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpAcoesLayout.setVerticalGroup(
            jpAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAcoesLayout.createSequentialGroup()
                .addComponent(jcbAcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtaCodigo.setColumns(20);
        jtaCodigo.setRows(5);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${modelo.codigoAcaoSelecionada}"), jtaCodigo, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jtaCodigo.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jtaCodigoCaretUpdate(evt);
            }
        });
        jScrollPane1.setViewportView(jtaCodigo);

        jpAngulo.setBorder(javax.swing.BorderFactory.createTitledBorder("Op��es de Rota��o"));

        jcbAngulo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "45", "15", "5", "-5", "-15", "-45" }));
        jcbAngulo.setToolTipText("Seleciona o Angulo para Rota��o de Pe�as ou Modelos no Editor de Modelos");
        jcbAngulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbAnguloActionPerformed(evt);
            }
        });

        jlbAngulo.setText("�ngulo");

        jlbSelecPonto.setText("Selec. Ponto");

        jcbSelecPonto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "N�o", "Sim" }));
        jcbSelecPonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizaSelecPonto(evt);
            }
        });

        javax.swing.GroupLayout jpAnguloLayout = new javax.swing.GroupLayout(jpAngulo);
        jpAngulo.setLayout(jpAnguloLayout);
        jpAnguloLayout.setHorizontalGroup(
            jpAnguloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAnguloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbAngulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbAngulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbSelecPonto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbSelecPonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpAnguloLayout.setVerticalGroup(
            jpAnguloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAnguloLayout.createSequentialGroup()
                .addGroup(jpAnguloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbAngulo)
                    .addComponent(jcbAngulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbSelecPonto)
                    .addComponent(jcbSelecPonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jpGerador.setBorder(javax.swing.BorderFactory.createTitledBorder("Gerador de Texto"));

        jcbGerador.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "N�o Gerar", "Estatico", "Iterativo" }));
        jcbGerador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbGeradorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpGeradorLayout = new javax.swing.GroupLayout(jpGerador);
        jpGerador.setLayout(jpGeradorLayout);
        jpGeradorLayout.setHorizontalGroup(
            jpGeradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpGeradorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcbGerador, 0, 128, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpGeradorLayout.setVerticalGroup(
            jpGeradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGeradorLayout.createSequentialGroup()
                .addComponent(jcbGerador)
                .addContainerGap())
        );

        jpBotoes.setBorder(javax.swing.BorderFactory.createTitledBorder("Bot�es"));

        jbtEditorGrafico.setText("Editor Gr�fico");
        jbtEditorGrafico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chamaEditorGrafico(evt);
            }
        });

        jbtNovaAcao.setText("Nova A��o");
        jbtNovaAcao.setToolTipText("Cria nova A��o para o Modelo.");
        jbtNovaAcao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novaAcao(evt);
            }
        });

        jbtPreExecutar.setText(">");
        jbtPreExecutar.setToolTipText("Pr�-executar a��es do Modelo");
        jbtPreExecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preExecutar(evt);
            }
        });

        javax.swing.GroupLayout jpBotoesLayout = new javax.swing.GroupLayout(jpBotoes);
        jpBotoes.setLayout(jpBotoesLayout);
        jpBotoesLayout.setHorizontalGroup(
            jpBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbtEditorGrafico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtNovaAcao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtPreExecutar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpBotoesLayout.setVerticalGroup(
            jpBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBotoesLayout.createSequentialGroup()
                .addGroup(jpBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtEditorGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(jbtPreExecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(jbtNovaAcao, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jbLinha, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                        .addComponent(lbRodape, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jpBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpAngulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jpAcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpGerador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jpGerador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpAcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpBotoes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpAngulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRodape, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                    .addComponent(jbLinha, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbAcaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbAcaoActionPerformed
        // Pega o nome da a��o selecionada
        String acaoSelecionada = (String)jcbAcao.getSelectedItem();
        // se n�o seleciona acao retorna
        if(acaoSelecionada == null)
            return;
        // se a acao selecionada anteriormente e nula inicializa ela
        if(ultimaAcao == null)
            ultimaAcao = "";
        // Se a acao selecionada for diferente da atual
        if(!ultimaAcao.equals(acaoSelecionada)){
            // Muda acao selecionada no modelo
            modelo.setNomeAcaoSelecionada((String)jcbAcao.getSelectedItem());
            // se eh metodo cria
            if(acaoSelecionada.equals(ModelMaker.CRIA)){
                // retorna posicao das pecas
                retornaPosicaoCria();
                // muda gerador para estatico
                jcbGerador.setSelectedIndex(1);
                // n�o deixa alterar gerador
                jcbGerador.setEnabled(false);
            } else
                jcbGerador.setEnabled(true);
            // atribui a ultima acao
            ultimaAcao = acaoSelecionada;
        }
    }//GEN-LAST:event_jcbAcaoActionPerformed

    private void novaAcao(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novaAcao
        if(getModelo() == null){
            Msg.erro("N�o existe Modelo!");
            return;
        }
        criaNovaAcao();
}//GEN-LAST:event_novaAcao

    private void jtaCodigoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jtaCodigoCaretUpdate
        Element root = jtaCodigo.getDocument().getDefaultRootElement();
        dot = evt.getDot();
        line = root.getElementIndex( dot );
        lineStartOffset = root.getElement( line ).getStartOffset();
        lineEndOffset = root.getElement( line ).getEndOffset();
        col = dot - lineStartOffset;
        
        jbLinha.setText((line+1) + ":" + (col+1));
    }//GEN-LAST:event_jtaCodigoCaretUpdate

    private void jcbAnguloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbAnguloActionPerformed
        modelo.getHandler().setAngulo(
                Float.parseFloat((String)jcbAngulo.getSelectedItem()));
    }//GEN-LAST:event_jcbAnguloActionPerformed

    private void jcbGeradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbGeradorActionPerformed
        switch(jcbGerador.getSelectedIndex()){
            case 0:
                modelo.getCodeMaker().setGerador(Gerador.NAO);
                break;
            case 1:
                modelo.getCodeMaker().setGerador(Gerador.ESTATICO);
                break;
            case 2:
                modelo.getCodeMaker().setGerador(Gerador.DINAMICO);
                break;
        }
}//GEN-LAST:event_jcbGeradorActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        modelo.fechar();
    }//GEN-LAST:event_formWindowClosing

    private void chamaEditorGrafico(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chamaEditorGrafico
        if(!modelo.getEditorModelos().isVisible())
            modelo.getEditorModelos().setVisible(true);
}//GEN-LAST:event_chamaEditorGrafico

    private void preExecutar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preExecutar
        // Se e metodo cria ou vida n�o executa
        if(jcbAcao.getSelectedItem().equals(ModelMaker.CRIA) ||
                jcbAcao.getSelectedItem().equals(ModelMaker.VIVA))
            return;
        final ModeloExecutavel exe = modelo.compila();
        // Se Ocorreu Algum erro na compila��o n�o faz metodo.
        if(exe == null)
            return;
        // atribui a figura
        exe.setFigura((Figura) modelo.getFiguras().get(0));
        // Inicia a execu��o
        exe.start();
        // Executa Metodo
        try{
            exe.toExecute((String)jcbAcao.getSelectedItem());
            Thread.sleep(50);
            exe.parar();
        } catch (Exception e){
            Msg.erro(e.getMessage());
        }
}//GEN-LAST:event_preExecutar

    private void atualizaSelecPonto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizaSelecPonto
        modelo.getHandler().setSelecionaPonto(
                (jcbSelecPonto.getSelectedIndex() == 0 ? false : true));
}//GEN-LAST:event_atualizaSelecPonto
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jbLinha;
    private javax.swing.JButton jbtEditorGrafico;
    private javax.swing.JButton jbtNovaAcao;
    private javax.swing.JButton jbtPreExecutar;
    private javax.swing.JComboBox jcbAcao;
    private javax.swing.JComboBox jcbAngulo;
    private javax.swing.JComboBox jcbGerador;
    private javax.swing.JComboBox jcbSelecPonto;
    private javax.swing.JLabel jlbAngulo;
    private javax.swing.JLabel jlbSelecPonto;
    private javax.swing.JPanel jpAcoes;
    private javax.swing.JPanel jpAngulo;
    private javax.swing.JPanel jpBotoes;
    private javax.swing.JPanel jpGerador;
    private javax.swing.JTextArea jtaCodigo;
    private javax.swing.JLabel lbRodape;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
    
    // Variaveis para contrelo de linhas do editor de texto
    int dot,line,col,lineStartOffset,lineEndOffset;
    
    // Ultimo Acao escolhida
    String ultimaAcao;
    
    // Modelo que este editor pertence
    private Modelo modelo;

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
