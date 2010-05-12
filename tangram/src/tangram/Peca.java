/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tangram;

import jogl.Color4f;
import jogl.intefaces.MirrorModel;
import jogl.intefaces.RayTracing;
import jogl.intefaces.RotateModel;
import jogl.intefaces.TranslateModel;
import jogl.util.Linha;
import jogl.util.Triangulo;
import jogl.util.Vetor3f;

/**
 * Esta classe implementa uma peça do jogo Tangram.
 * Esta classe implementa mÃ©todos para detectar colisÃ£o, espelhá-la, move-la, 
 * desenhá-la, rotacioná-la, mudar sua cor e clona-la
 * @author GlauKo
 */
public class Peca extends jogl.BasicModel implements TranslateModel,
        RotateModel, MirrorModel, RayTracing, Cloneable {
    
    /**
     * Armazena se a peça foi movida.
     */
    boolean moveuPeca;
    
    /**
     * Armazena o tipo da peça, o qual Ã© o respectivo nÃºmero do nome das peças
     * no tangra, P1, P2, P3, P4, P5, P6, P7.
     */
    int tipo;
    
    /**
     * Armazena o angulo que a peça foi rotacionada.
     */
    float angulo;
    
    /**
     * Armazema se a peça foi espelhada
     */
    boolean mirrored;
    
    /**
     * Cria um Peça de um Modelo (Figura) do Tangram
     * @param tipo numero de 1 - 7 que representa uma peça do tangram
     * @param centro ponto central da peça
     */
    public Peca(int tipo, Vetor3f centro){
        super(centro, 
              ModelMaker.getPontos(tipo, centro),
              ModelMaker.getDrawType(tipo));
        moveuPeca = true;
        this.tipo = tipo;
        setColor(ModelMaker.getColor(tipo));
        angulo = 0;
        mirrored = false;
    }

    /**
     * Adciona o Vetor3f V a todos os pontos da peça
     * @param v
     */
    protected void movePeca(Vetor3f v){
        center = center.add(v);
        for(Vetor3f pts : points){
            pts.addOnThis(v);
        }
        moveuPeca = true;
    }
    
    public void translate(Vetor3f v) {
        movePeca(v);
    }

    public void translateTo(Vetor3f v) {
        movePeca(v.sub(center));
    }
    
    public Apontamento colidiu(Linha ray){
        if(moveuPeca){
            formaTriangulos();
            moveuPeca = false;
        }
        
        for( Triangulo t : triangles){
            Vetor3f pontoColidiu = t.getIntersectionPoint(ray);
            if(pontoColidiu != null)
                return new Apontamento(this,pontoColidiu,ray,t.getPlano());
        }
        return null;
    }
    
    public Vetor3f pontoProximo(Linha ray){
        if(moveuPeca){
            formaTriangulos();
            moveuPeca = false;
        }
        
        for( Triangulo t : triangles){
            Vetor3f pontoColidiu = t.getPlano().getIntersection(ray);
            if(pontoColidiu != null){
                try{
                    float minDistance = Float.POSITIVE_INFINITY;
                    Vetor3f pontoProximo = null;
                    for(Vetor3f v : points){
                        float vLenght = v.sub(pontoColidiu).getLength();
                        float distance;
                        if(vLenght >= 0)
                            distance = vLenght;
                        else
                            distance = - vLenght;
                        if(distance < minDistance){
                            pontoProximo = v;
                            minDistance = distance;
                        }
                    }
                    if(minDistance < 100)
                        return pontoProximo;
                } catch (Exception e) {}
            }
        }
        return null;
    }
    
    /**
     * retorna a String do metodo toString() do Vetor3f que representa o centro
     * da peça.
     * @return
     */
    @Override
    public String toString(){
        return center.toString();
    }
    
    public int getTipo(){
        return tipo;
    }

    public void Rotate(float angulo, Vetor3f origem) {
        double radius = Math.PI/(180/angulo);
        for(Vetor3f pts : points){
            pts.rotateXYOnThis(radius, origem);
        }
        center.rotateXYOnThis(radius, origem);
        addAngulo(angulo);
    }

    public void Rotate(float angulo) {
        Rotate(angulo,center);
    }

    public void mirror() {
        mirror(center);
    }

    public void mirror(Vetor3f v) {
        for(Vetor3f pts : points){
            pts.mirrorXOnThis(v.getX());
        }
        center.mirrorXOnThis(v.getX());
        this.mirrored = ( mirrored ? false : true);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Retorna se a peça foi espelhada
     * @return
     */
    public boolean isMirrored() {
        return mirrored;
    }

    /**
     * Retorna o angulo em que a peça foi rotacionada
     * @return
     */
    public float getAngulo() {
        return angulo;
    }
    
    /**
     * Adciona o valor do angulo rotaciona a variavel que armazena o angulo 
     * rotaciona da peça.
     */ 
    public void addAngulo(float adicional){
        angulo += adicional;
        if (angulo >= 360)
            angulo -= 360;
    }
    
    /**
     * Altera a cor da Peça
     * @param color
     */
    @Override
    public void setColor(Color4f color) {
        super.setColor(color);
    }
}
