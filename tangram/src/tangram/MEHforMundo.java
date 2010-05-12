/*
 * 
 * 
 */

package tangram;

import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import jogl.Camera;
import jogl.Color4f;
import jogl.UnProject;
import jogl.util.Linha;
import jogl.util.Vetor3f;
import tangram.comandos.CodeMaker;
import tangram.gui.EscolheCor;
import tangram.io.AbrirArquivo;

import com.sun.opengl.util.GLUT;

/**
 *
 * @author GlauKo
 *             n
 *             i
 *             h
 *             s
 */
public class MEHforMundo implements MouseEventHandler {
    
    private Camera camera;
    private ArrayList<Figura> modelos;
    private Mundo mundo;
    private Color4f corFundo;
    private Color4f mudaCorFunco;
    private CodeMaker codeMaker;
    private Modo modo;
    private GL gl;

    private Figura figuraSelecionada;
    private Vetor3f pontoAnterior;
    private Apontamento colidiu;
    
    public MEHforMundo(Camera camera, ArrayList<Figura> modelos, Mundo mundo) {
        this.camera = camera;
        this.modelos = modelos;
        this.mundo = mundo;
        corFundo = new Color4f(1.0f, 1.0f, 0.7f, 0.5f);
        mudaCorFunco = corFundo;
    }

    public void action(GL gl, ArrayList<MouseEvent> me) {
        this.gl = gl;
        for (MouseEvent e : me) {
            switch (e.getID()) {
                case MouseEvent.MOUSE_PRESSED:
                    mousePressed(e);
                    break;
                case MouseEvent.MOUSE_RELEASED:
                    mouseRelessed(e);
                    break;
                case MouseEvent.MOUSE_DRAGGED:
                    mouseDragged(e);
                    break;
                case MouseEvent.MOUSE_WHEEL:
                    break;
            }
        }
        if(mudaCorFunco != null){
            // cor de fundo padrão
            gl.glClearColor(mudaCorFunco.getR(),
                    mudaCorFunco.getG(),
                    mudaCorFunco.getB(),
                    mudaCorFunco.getAlpha());
            corFundo = mudaCorFunco;
            mudaCorFunco = null;
        }
    }

    public void extraDraws(GL gl) {
        GLUT glut = new GLUT();
        for(Figura f : modelos){
            Color4f c = corFundo.negativo();
            gl.glColor3f(c.getR(),c.getG(),c.getB());
            Vetor3f v = f.getCenter();
            gl.glRasterPos3f(v.getX()-60,v.getY(),v.getZ());
            glut.glutBitmapString(GLUT.BITMAP_HELVETICA_12, f.getName());
        }
    }

    public void setModelos(ArrayList<Figura> modelos) {
        this.modelos = modelos;
    }

    public ArrayList getModelos() {
        return modelos;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setModo(Modo modo) {
        if (modo == Modo.SELECIONA_MODELO || modo == Modo.CRIA_MODELO || 
                modo == Modo.MODELO_SELECIONADO || modo == Modo.MUDA_COR)
            this.modo = modo;
    }
    
    public CodeMaker getCodeMaker() {
        return codeMaker;
    }

    public void setCodeMaker(CodeMaker codeMaker) {
        this.codeMaker = codeMaker;
    }
    
    public void mudaCorFundo(Color4f cor){
        mudaCorFunco = cor;
    }
    
    private String entrada(String texto){
        return JOptionPane.showInputDialog(null,
                texto, 
                "Tangram 3.0", 
                JOptionPane.PLAIN_MESSAGE);
    }

    private void mousePressed(MouseEvent e) {
        if(modo == Modo.CRIA_MODELO){
            Vetor3f ponto = UnProject.unProject2(gl,e.getX(),e.getY());
            novoModelo(ponto);
        }else if (modo == Modo.SELECIONA_MODELO){
            setColidiu(e.getX(), e.getY());
            if (colidiu != null){
                modo = Modo.MODELO_SELECIONADO;
                pontoAnterior = colidiu.getPontoColidiu();
            }
        }
    }

    private void mouseRelessed(MouseEvent e) {
        if (modo == Modo.MODELO_SELECIONADO)
            modo = Modo.SELECIONA_MODELO;
        else if (modo == Modo.MUDA_COR)
            mudaCorFundo();
        colidiu = null;
        figuraSelecionada = null;
        pontoAnterior = null;
    }
    
    private void mouseDragged(MouseEvent e) {
        if(modo == Modo.MODELO_SELECIONADO)
            translateEvent(e);
    }
    
    private void setColidiu(int x, int y) {
        colidiu = null;
        Linha ray = UnProject.unProject(gl, x, y);
        for (Figura m : modelos) {
            colidiu = m.colidiu(ray);
            if (colidiu != null){
                figuraSelecionada = colidiu.modeloColidiu;
                informaFiguraSelecionaAoEditorTextual();
                break;
            }
        }
    }
    
    private void translateEvent(MouseEvent e) {
        Linha ray = UnProject.unProject(gl, e.getX(), e.getY());
        Vetor3f novoPonto = colidiu.getPlano().getIntersection(ray);
        colidiu.getModeloColidiu().translate(novoPonto.sub(pontoAnterior));
        pontoAnterior = novoPonto;
    }
    
    private void novoModelo(Vetor3f ponto){
        // Seleciona Arquivo
        File f = new AbrirArquivo(true,false).showDialog(
               new JFrame(mundo.getEditorGrafico().getGraphicsConfiguration()));
        // Verifica se selecionou arquivo
        if(f == null || !f.exists())
            return;
        // Pede Nome para o modelo
        String idDoModelo = entrada("Nome do Modelo");
        // se não deu nome ao modelo retorna
        if (idDoModelo == null)
            return;
        // cria modelo
        if(mundo.addModelo(idDoModelo, f, ponto)){
            ponto.setName(f.getName().replaceAll(".mod", ""));
            codeMaker.make(modo,
                    mundo.getFigura(idDoModelo),
                    new Peca(1,ponto),
                    new float[]{ponto.getX(), ponto.getY(), ponto.getZ()});
        }
    }
    
    private void informaFiguraSelecionaAoEditorTextual(){
        mundo.setSelectedModel(figuraSelecionada.getName(),figuraSelecionada);
    }
    
    private boolean mudaCorFundo(){
        // Cria Tela para escolher a cor
        EscolheCor ec = new EscolheCor("","Seleciona Cor do Fundo");
        // Pega a cor da tela
        String novaCor = ec.showDialog(null);
        // Se selecionou alguma cor
        if(novaCor != null && novaCor.length() > 0)
            // altera a cor da pe�a na tela
            mudaCorFunco = ColorMaker.makeColor(novaCor);
        else
            return false;
        return true;
    }
}