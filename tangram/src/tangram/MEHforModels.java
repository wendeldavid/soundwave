/*
 * 
 * 
 */
package tangram;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.media.opengl.GL;

import jogl.Camera;
import jogl.Color4f;
import jogl.UnProject;
import jogl.intefaces.ColorModel;
import jogl.util.Linha;
import jogl.util.Vetor3f;
import tangram.comandos.CodeMaker;
import tangram.gui.EscolheCor;

import com.sun.opengl.util.GLUT;

/**
 *
 * @author GlauKo
 *             n
 *             i
 *             h
 *             s
 */
public class MEHforModels implements MouseEventHandler{
    
    private Modo modo;
    private Modo oldModo;
    private boolean selecionaPonto;
    private GL gl ;
    private ArrayList <Figura> figuras;
    private Camera camera; 
    private Apontamento colidiu;   
    private Vetor3f  pontoAnterior;
    private float angulo;
    private CodeMaker codeMaker;
    private Color4f corFundo;
    private Color4f mudaCorFunco;
    
    private Peca pecaSelecionada;
    private Peca pecaOriginal;
    private Figura figuraSelecionada;
    private Figura figuraOriginal;
    private Vetor3f pontoSelecionado;
    
    
    public MEHforModels(ArrayList <Figura> figuras, Camera camera){
        this.figuras = figuras;
        this.camera = camera;
        modo = Modo.SELECIONA_PECA;
        colidiu = null;
        pontoAnterior = null;
        this.angulo = 45;
        pecaSelecionada = null;
        figuraSelecionada = null;
        pontoSelecionado = null;
    }

    public ArrayList<Figura> getModelos() {
        return figuras;
    }

    public void setModelos(ArrayList<Figura> modelos) {
        this.figuras = modelos;
    }
    
    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
    
    public Modo getModo() {
        return modo;
    }
    
    public void setModo(Modo modo) {
        if(modo == Modo.PAN || modo == Modo.MIRROR ||
                modo == Modo.SELECIONA_PECA ||
                modo == Modo.SELECIONA_MODELO ||
                modo == Modo.MUDA_COR)
            this.modo = modo;
    }

    public boolean isSelecionaPonto() {
        return selecionaPonto;
    }
    
    public boolean getSelecionaPonto(){
        return selecionaPonto;
    }

    public void setSelecionaPonto(boolean selecionaPonto) {
        this.selecionaPonto = selecionaPonto;
    }
    
    public float getAngulo() {
        return angulo;
    }

    public void setAngulo(float angulo) {
        this.angulo = angulo;
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
            // cor de fundo padrÃ£o
            gl.glClearColor(mudaCorFunco.getR(),
                    mudaCorFunco.getG(),
                    mudaCorFunco.getB(),
                    mudaCorFunco.getAlpha());
            corFundo = mudaCorFunco;
            mudaCorFunco = null;
        }
    }
    
    public void extraDraws(GL gl){
        Vetor3f v0 = null, v1 = null;
        if(pecaSelecionada != null){
            if(modo == Modo.PECA_SELECIONADA){
                pecaSelecionada.draw(gl);
                v0 = pecaSelecionada.getCenter();
                v1 = pecaOriginal.getCenter();
            } else if (modo == Modo.MODELO_SELECIONADO){
                figuraSelecionada.draw(gl);
                v0 = figuraSelecionada.getCenter();
                v1 = figuraOriginal.getCenter();
            } else if (modo == Modo.SELECIONA_PONTO){
                gl.glPointSize(8);
                pecaSelecionada.draw(gl);
                gl.glPointSize(1);
            }
        }
        if(v0 != null && v1 != null){
            gl.glBegin(GL.GL_LINES);
            gl.glVertex3f( v0.getX(), v0.getY(), v0.getZ());
            gl.glVertex3f( v1.getX(), v1.getY(), v1.getZ());
            gl.glEnd();
        }
        //if(modo == Modo.SELECIONA_PECA){
            GLUT glut = new GLUT();
            for(Figura f : figuras){
                for(Peca p : f.getPecas()){
                    Vetor3f v = p.getCenter();
                    String s = "p" + p.tipo;
                    Color4f c = p.getColor().negativo();
                    gl.glColor3f(c.getR(),c.getG(),c.getB());
                    gl.glRasterPos3f(v.getX()-60,v.getY(),v.getZ()+1);
                    glut.glutBitmapString(GLUT.BITMAP_HELVETICA_12, s);
                }
            }
        //}
            
    }

    private void setColidiu(int x, int y) {
        colidiu = null;
        Linha ray = UnProject.unProject(gl, x, y);
        for (Figura m : figuras) {
            colidiu = m.colidiu(ray);
            if (colidiu != null) {
                try {
                    pecaOriginal = colidiu.getPecaColidiu();
                    pecaSelecionada = (Peca) colidiu.getPecaColidiu().clone();
                    figuraOriginal = colidiu.getModeloColidiu();
                    figuraSelecionada = (Figura) 
                            colidiu.getModeloColidiu().clone();
                    pecaSelecionada.setDrawType(GL.GL_LINE_LOOP);
                    figuraSelecionada.changeDrawType(GL.GL_LINE_LOOP);
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }
                break;
            }
        }
    }

    private void setPontoProximo(int x, int y){
        pontoSelecionado = null;
        Linha ray = UnProject.unProject(gl, x, y);
        pontoSelecionado = pecaSelecionada.pontoProximo(ray);
    }
    
    private float[] getParmMovePeca(){
        float[] parm = new float[3];
        if (pecaSelecionada != null && pecaOriginal != null){
            Vetor3f diferenca = pecaOriginal.getCenter().sub(
                    pecaSelecionada.getCenter());
            parm[0] = diferenca.getX();
            parm[1] = diferenca.getY();
            parm[2] = diferenca.getZ();
        }
        return parm;
    }
    
    private float[] getParmMoveModelo(){
        float[] parm = new float[3];
        if (figuraSelecionada != null && figuraOriginal != null){
            Vetor3f diferenca = figuraOriginal.getCenter().sub(
                    figuraSelecionada.getCenter());
            parm[0] = diferenca.getX();
            parm[1] = diferenca.getY();
            parm[2] = diferenca.getZ();
        }
        return parm;
    }
    
    private float[] getParmGira(){
        return (pontoSelecionado == null ? new float[]{angulo} : 
            new float[]{angulo,Float.parseFloat(pontoSelecionado.getName())});
    }
    
    private void mousePressed(MouseEvent e) {
        if(modo == Modo.SELECIONA_PONTO){
            setPontoProximo(e.getX(), e.getY());
            modo = oldModo;
            if(modo == Modo.ROTACIONA_PECA)
                rotacionaPeca();
            else
                rotacionaModelo();
        }else{
            setColidiu(e.getX(), e.getY());
            switch (e.getButton()) {
                /* BOTAO ESQUEDO */
                case MouseEvent.BUTTON1:
                    /* SELECIONA PECA */
                    if (modo == Modo.SELECIONA_PECA) {
                        if (colidiu != null){
                            modo = Modo.PECA_SELECIONADA;
                            pontoAnterior = colidiu.getPontoColidiu();
                        }

                    /* SELECIONA MODELO */
                    } else if (modo == Modo.SELECIONA_MODELO) {
                        if (colidiu != null){
                            modo = Modo.MODELO_SELECIONADO;
                            pontoAnterior = colidiu.getPontoColidiu();
                        }

                    /* ESPELHA */
                    } else if (modo == Modo.MIRROR) {
                        if (colidiu != null)
                            espelhaPeca();

                    /* PAN */
                    } else if (modo == Modo.PAN) {
                        panEvent(e);
                    }
                    break;
                /* BOTAO DIREITO */
                case MouseEvent.BUTTON3:
                    /* SELECIONA PECA */
                    if (modo == Modo.SELECIONA_PECA) {
                        if(colidiu != null){
                            modo = Modo.ROTACIONA_PECA;
                            if(selecionaPonto){
                                oldModo = modo;
                                modo = Modo.SELECIONA_PONTO;
                                pecaSelecionada.setDrawType(GL.GL_POINTS);
                                pecaSelecionada.setColor(
                                        pecaSelecionada.getColor().negativo());
                                //pecaSelecionada.translate(
                                //        new Vetor3f(0,0,0.001f));
                            } else
                                rotacionaPeca();
                        }

                    /* SELECIONA MODELO */
                    } else if (modo == Modo.SELECIONA_MODELO){
                        if (colidiu != null){
                            modo = Modo.ROTACIONA_MODELO;
                            if(selecionaPonto){
                                oldModo = modo;
                                modo = Modo.SELECIONA_PONTO;
                                pecaSelecionada.setDrawType(GL.GL_POINTS);
                                pecaSelecionada.setColor(
                                        pecaSelecionada.getColor().negativo());
                                //pecaSelecionada.translate(
                                //        new Vetor3f(0,0,0.001f));
                            }else
                                rotacionaModelo();
                        }
                    /* ESPELHA */
                    }else if (modo == Modo.MIRROR) {
                        if(colidiu != null)
                            espelhaModelo();
                    }
                    break;
            }
        }
    }

    private void rotacionaPeca() {
        if(colidiu != null)
            if(pontoSelecionado == null)
                colidiu.pecaColidiu.Rotate(angulo);
            else
                colidiu.pecaColidiu.Rotate(angulo,pontoSelecionado);
    }

    private void rotacionaModelo() {
        if(colidiu != null)
            if(pontoSelecionado == null)
                colidiu.modeloColidiu.Rotate(angulo);
            else
                colidiu.modeloColidiu.Rotate(angulo,pontoSelecionado);
    }
    
    private void espelhaPeca() {
        if(colidiu != null)
            colidiu.pecaColidiu.mirror();
    }

    private void espelhaModelo() {
        if(colidiu != null)
            colidiu.modeloColidiu.mirror();
    }

    private void mouseRelessed(MouseEvent e) {
        if (colidiu != null){
            /* PECA SELECIONADA */
            if (modo == Modo.PECA_SELECIONADA) {
                codeMaker.make(modo,
                        figuraOriginal,
                        pecaOriginal,
                        getParmMovePeca());
                modo = Modo.SELECIONA_PECA;

            /* MODELO SELECIONADO */
            } else if (modo == Modo.MODELO_SELECIONADO ) {
                codeMaker.make(modo,
                        figuraOriginal,
                        pecaOriginal,
                        getParmMoveModelo());
                modo = modo.SELECIONA_MODELO;

            /* ROTACIONA PECA */
            } else if (modo == Modo.ROTACIONA_PECA) {
                codeMaker.make(modo,
                        figuraOriginal,
                        pecaOriginal,
                        getParmGira());
                modo = Modo.SELECIONA_PECA;
            
            /* ROTACIONA MODELO */
            } else if(modo == Modo.ROTACIONA_MODELO){
                codeMaker.make(modo,
                        figuraOriginal,
                        pecaOriginal,
                        getParmGira());
                modo = modo.SELECIONA_MODELO;

            /* ESPELHA */
            } else if(modo == Modo.MIRROR){
                if(e.getButton() == MouseEvent.BUTTON3){
                    codeMaker.make(modo, figuraOriginal, null, null);
                } else if(e.getButton() == MouseEvent.BUTTON1){
                    codeMaker.make(modo, figuraOriginal, pecaOriginal, null);
                }
            
            /* MUDA COR */
            } else if(modo == Modo.MUDA_COR){
                if(e.getButton() == MouseEvent.BUTTON3){
                    if(mudaCorEvent(figuraOriginal,"",figuraOriginal.getName()))
                        codeMaker.make(modo, figuraOriginal, null,null);
                } else if(e.getButton() == MouseEvent.BUTTON1){
                    if (mudaCorEvent(pecaOriginal,
                            pecaOriginal.getColor().getName(),
                            String.valueOf(pecaOriginal.getTipo())));
                        codeMaker.make(modo, figuraOriginal, pecaOriginal,null);
                }
            }
        } else if (modo == Modo.MUDA_COR)
            mudaCorFundo();
        
        if(modo != Modo.SELECIONA_PONTO){
            colidiu = null;
            pontoAnterior = null;
            pecaOriginal = null;
            pecaSelecionada = null;
            figuraOriginal = null;
            figuraSelecionada = null;
            pontoSelecionado =  null;
        } else {
            
        }
    }

    private void mouseDragged(MouseEvent e) {
        if (modo == Modo.PAN) {
            panEvent(e);
        } else if ((modo == Modo.PECA_SELECIONADA ||
                    modo == Modo.MODELO_SELECIONADO) &&
                   (colidiu != null))
            translateEvent(e);
    }

    private void panEvent(MouseEvent e) {
        if (pontoAnterior == null) {
            pontoAnterior = new Vetor3f(e.getX(),-e.getY(),camera.start.getZ());
        } else {
            Vetor3f novoPan = new Vetor3f((float) e.getX(),
                                          (float) -e.getY(),
                                          pontoAnterior.getZ());
            Vetor3f dif= novoPan.sub(pontoAnterior);
            camera.setStart(camera.start.add(dif));
            //camera.setDirection(camera.direction.add(dif));
            //camera.setDirection(camera.direction.add(dif));
            pontoAnterior = novoPan;
        }
    }

    private void translateEvent(MouseEvent e) {
        Linha ray = UnProject.unProject(gl, e.getX(), e.getY());
        Vetor3f novoPonto = colidiu.getPlano().getIntersection(ray);
        if (modo == Modo.PECA_SELECIONADA)
            colidiu.getPecaColidiu().movePeca(
                    novoPonto.sub(pontoAnterior));
        else
            colidiu.getModeloColidiu().translate(
                    novoPonto.sub(pontoAnterior));
        pontoAnterior = novoPonto;
    }
    
    /**
     * 
     * Chama tela para escolha da cor da Peça ou do Modelo.
     * 
     * @param cm ColorModel que deve mudar a cor.
     * @param corAtual a Cor atual do ColorModel.
     * @param titulo nome da peça ou do modelo que serÃ¡ usado no titulo da tela.
     * @return retorna true se escolheu alguma cor, ou cor diferente.
     */
    private boolean mudaCorEvent(ColorModel cm, String corAtual, String titulo){
        // Monta Tilulo
        try{
            Integer.parseInt(titulo);
            titulo = "Selecione a cor da Peca " + titulo;
        } catch(Exception e){
            titulo = "Selecione a cor do Modelo " + titulo;
        }
        // Cria Tela para escolher a cor
        EscolheCor ec = new EscolheCor(corAtual,titulo);
        // Pega a cor da tela
        String novaCor = ec.showDialog(null);
        // Se selecionou alguma cor
        if(novaCor != null && novaCor.length() > 0)
            // altera a cor da peça na tela
            cm.setColor(ColorMaker.makeColor(novaCor));
        else
            return false;
        return true;
    }
    
    private boolean mudaCorFundo(){
        // Cria Tela para escolher a cor
        EscolheCor ec = new EscolheCor("","Seleciona Cor do Fundo");
        // Pega a cor da tela
        String novaCor = ec.showDialog(null);
        // Se selecionou alguma cor
        if(novaCor != null && novaCor.length() > 0)
            // altera a cor da peça na tela
            mudaCorFunco = ColorMaker.makeColor(novaCor);
        else
            return false;
        return true;
    }
}
