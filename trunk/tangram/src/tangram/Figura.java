/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tangram;

import javax.media.opengl.GL;

import jogl.Color4f;
import jogl.intefaces.ColorModel;
import jogl.intefaces.DrawModel;
import jogl.intefaces.MirrorModel;
import jogl.intefaces.RayTracing;
import jogl.intefaces.RotateModel;
import jogl.intefaces.TranslateModel;
import jogl.util.Linha;
import jogl.util.Vetor3f;

/**
 * @author GlauKo
 * Esta classe implementa uma figura (7 peças) do jogo Tangram, possuindo sete
objetos da classe Peca.
 * Esta classe implementa mÃ©todos para detectar colisÃ£o, espelhÃ¡-la, move-la, 
 * desenhÃ¡-la, rotacionÃ¡-la, mudar sua cor e clona-la
 */
public class Figura implements RayTracing, MirrorModel, TranslateModel,
            DrawModel, RotateModel, ColorModel, Cloneable{
    
    /**
     * Vetor que contÃ©m as sete peças (Peca)
     */
    Peca[] pecas;
    
    /**
     * Armazena o nome da Figura
     */
    String name;

    /**
     * Cria uma nova figura formando o desenho de um quadrado com as sete peças.
     * @param name
     */
    public Figura(String name) {
        Peca p1 = new Peca(1,new Vetor3f(-250f,0f,-7000f));
        Peca p2 = new Peca(2,new Vetor3f(0f,-250f,-7000f));
        Peca p3 = new Peca(3,new Vetor3f(350f,350f,-7000f));
        Peca p4 = new Peca(4,new Vetor3f(375f,-250f,-7000f));
        Peca p5 = new Peca(5,new Vetor3f(0f,125f,-7000f));
        Peca p6 = new Peca(6,new Vetor3f(250f,0f,-7000f));
        Peca p7 = new Peca(7,new Vetor3f(-125f,375f,-7000f));
        pecas = new Peca[]{p1, p2, p3, p4, p5, p6, p7};
        this.name = name;
        for(Peca p : pecas){
            p.setVisible(true);
        }
    }
    
    /**
     * Cria uma nova figura passando por parametro as peças.
     * @param v1
     * @param v2
     * @param v3
     * @param v4
     * @param v5
     * @param v6
     * @param v7
     */
    public Figura(Vetor3f v1, Vetor3f v2, Vetor3f v3, Vetor3f v4, Vetor3f v5, 
            Vetor3f v6, Vetor3f v7){
        Peca p1 = null, p2 = null, p3 = null, p4 = null, p5 = null, p6 = null,
                p7 = null;
        if(v1 != null)
            p1 = new Peca(1,v1);
        if(v2 != null)
            p2 = new Peca(2,v2);
        if(v3 != null)
            p3 = new Peca(3,v3);
        if(v4 != null)
            p4 = new Peca(4,v4);
        if(v5 != null)
            p5 = new Peca(5,v5);
        if(v6 != null)
            p6 = new Peca(6,v6);
        if(v7 != null)
            p7 = new Peca(7,v7);
        pecas = new Peca[]{p1, p2, p3, p4, p5, p6, p7};
    }
    
    /**
     * Adiciona o valor do Vetor3f V em todas as peças da figura.
     * @param v
     */
    public void translate(Vetor3f v) {
        for(Peca p : pecas){
            if(p != null)
                p.translate(v);
        }
    }
    
    /**
     * Subtrai do Vetor3f V os vetor correspondente ao centro da figura e
     * adiciona o resultado em todas as peças da figura.
     * @param v
     */
    public void translateTo(Vetor3f v) {
        Vetor3f v2 = v.sub(getCenter());
        for(Peca p : pecas){
            if(p != null)
                p.translate(v2);
        }
    }
    
    /**
     * Retorna um objeto de Apontamento se houve intersecçÃ£o da Linha ray com alguma
     * das peças da figura.
     * @param ray
     * @return
     */
    public Apontamento colidiu(Linha ray){
        Apontamento colidiu = null;
        for(Peca p : pecas){
            if(p != null){
                colidiu = p.colidiu(ray);
                if(colidiu != null){
                    colidiu.setModeloColidiu(this);
                    return colidiu;
                }
            }
        }
        return null;
    }
    
    /**
     * Retorna dentre todos os pontos das peças da figura o mais prÃ³ximo da
     * Linha ray
     * @param ray
     * @return
     */
    public Vetor3f pontoProximo(Linha ray) {
        Vetor3f pontoProximo = null;
        for(Peca p : pecas){
            pontoProximo = p.pontoProximo(ray);
            if (pontoProximo != null)
                return pontoProximo;
        }
        return null;
    }
    
    /**
     * Desenha todas as peças que estÃ£o visiveis na tela.
     * @param gl
     */
    public void draw(GL gl){
        for(Peca p : pecas){
            if(p != null)
                p.draw(gl);
        }
    }
    
    /**
     * Muda o tipo de desenho de todas as peças
     * @param type
     */
    public void changeDrawType(int type){
        for(Peca p : pecas){
            if(p != null)
                p.setDrawType(type);
        }
    }

    /**
     * Espelha os pontos de todas as peças da Figura no eixo X, apartir do centro da Figura.
     */
    public void mirror() {
        mirror(getCenter());
    }
    
    /**
     * Espelha os pontos de todas as peças da Figura no eixo X, apartir de um ponto determinado.
     * 
     * @param v Ponto para efetuar o espelhamento
     */
    public void mirror(Vetor3f v) {
        for(Peca p : pecas){
            if(p != null)
                p.mirror(v);
        }
    }

    /**
     * Rotaciona toda a Figura em torno de uma origem especificada.
     * 
     * @param angulo Angulo, em graus, para rotacionar a Figura.
     * @param origem Vetor3f que representa o ponto centro de rotaçÃ£o
     */
    public void Rotate(float angulo, Vetor3f origem) {
        for(Peca p : pecas){
            if(p != null)
                p.Rotate(angulo, origem);
        }
    }

    /**
     * Rotaciona toda a Figura em torno de seu centro.
     * 
     * @param angulo Angulo, em graus, para rotacionar a Figura.
     */
    public void Rotate(float angulo) {
        Rotate(angulo, getCenter());
    }
    
    /**
     * Muda a cor de todas as peças da Figura.
     * 
     * @param color A nova cor das peças da Figura
     */
    public void setColor(Color4f color) {
        for(Peca p : pecas){
            if(p != null)
                p.setColor(color);
        }
    }

    /**
     * Este mÃ©todo compara todas as cores das peças da figura.
     * Se forem iguais ele retorna a cor. Caso alguma cor for diferente, retorna null.
     * 
     * @return A cor da modelo.
     */
    public Color4f getColor() {
        Peca pAux = null;
        for(Peca p : pecas){
            if(pAux == null)
                pAux = p;
            else if(!pAux.getColor().equals(p.getColor()))
                return null;
        }
        if (pAux != null)
            return pAux.getColor();
        else
            return null;
    }
    
    
    public void setVisible(boolean visible) {
        for(Peca p : pecas){
            if( p != null)
                p.setVisible(visible);
        }
    }

    public boolean isVisible() {
        for(Peca p : pecas){
            if( p != null)
                if(!p.isVisible())
                    return false;
        }
        return true;
    }
    
    /**
     * Retorna o centro da figura pegando os valores maximos e minimos de X e Y
     * das peças da figura.
     * @return
     */
    public Vetor3f getCenter(){
        float maxX = Float.NEGATIVE_INFINITY, minX = Float.POSITIVE_INFINITY,
                maxY = Float.NEGATIVE_INFINITY, minY = Float.POSITIVE_INFINITY;
        for(Peca p : pecas){
            if( p != null){
                maxX = Math.max(maxX, p.maxX());
                maxY = Math.max(maxY, p.maxY());
                minX = Math.min(minX, p.minX());
                minY = Math.min(minY, p.minY());
            }
        }
        Vetor3f centro = new Vetor3f(
                (maxX + minX) / 2,
                (maxY + minY) / 2, 
                getP1().getCenter().getZ());
        return centro;
    }
    
    /**
     * Retorna P1
     * @return
     */
    public Peca getP1() {
        return pecas[0];
    }

    /**
     * Retorna P2
     * @return
     */
    public Peca getP2() {
        return pecas[1];
    }

    /**
     * Retorna P3
     * @return
     */
    public Peca getP3() {
        return pecas[2];
    }

    /**
     * Retorna P4
     * @return
     */
    public Peca getP4() {
        return pecas[3];
    }

    /**
     * Retorna P5
     * @return
     */
    public Peca getP5() {
        return pecas[4];
    }

    /**
     * Retorna P6
     * @return
     */
    public Peca getP6() {
        return pecas[5];
    }

    /**
     * Retorna P7
     * @return
     */
    public Peca getP7() {
        return pecas[6];
    }

    /**
     * Atribui P1
     * @param p1
     */
    public void setP1(Peca p1) {
        pecas[0] = p1;
    }

    /**
     * Atribui P2
     * @param p2
     */
    public void setP2(Peca p2) {
        pecas[1] = p2;
    }

    /**
     * Atribui P3
     * @param p3
     */
    public void setP3(Peca p3) {
        pecas[2] = p3;
    }

    /**
     * Atribui P4
     * @param p4
     */
    public void setP4(Peca p4) {
        pecas[3] = p4;
    }

    /**
     * Atribui P5
     * @param p5
     */
    public void setP5(Peca p5) {
        pecas[4] = p5;
    }

    /**
     * Atribui P6
     * @param p6
     */
    public void setP6(Peca p6) {
        pecas[5] = p6;
    }

    /**
     * Atribui P7
     * @param p7
     */
    public void setP7(Peca p7) {
        pecas[6] = p7;
    }

    /**
     * Retorna a lista das peças
     * @return
     */
    public Peca[] getPecas() {
        return pecas;
    }

    /**
     * Atribui a lista das peças
     * @param pecaList
     */
    public void setPecas(Peca[] pecaList) {
        this.pecas = pecaList;
    }
    
    /**
     * retorna a peça pelo nÃºmero dela
     * @param num
     * @return
     */
    public Peca getPeca(int num){
        if (num > 0 && num < 8)
            return pecas[num-1];
        return null;
    } 
    
    /**
     * Atribui uma peça pelo nÃºmero dela.
     * @param p
     * @param num
     * @return
     */
    public boolean setPeca(Peca p, int num){
        switch(num){
            case 1: 
                setP1(p);
                break;
            case 2: 
                setP2(p);
                break;
            case 3: 
                setP3(p);
                break;
            case 4: 
                setP4(p);
                break;
            case 5: 
                setP5(p);
                break;
            case 6: 
                setP6(p);
                break;
            case 7: 
                setP7(p);
                break;
            default:
                return false;
        }
        return true;
    }
    
    /**
     * Cria um novo objeto de Peca para cada uma peças da figura posicionadas no
     * centro (ponto 0,0,0)
     */
    public void resetPecas(){
        pecas = new Peca[]{new Peca(1, new Vetor3f()),
                new Peca(2, new Vetor3f()),
                new Peca(3, new Vetor3f()),
                new Peca(4, new Vetor3f()),
                new Peca(5, new Vetor3f()),
                new Peca(6, new Vetor3f()),
                new Peca(7, new Vetor3f())};
    }

    /**
     * Atribui o nome da figura.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * retorna o nome da figura.
     * @return
     */
    public String getName() {
        return name;
    }
    
    /**
     * Retorna o ponto, dentro todas os pontos das peças da figura, que tenha o
     * nome passado por parametro.
     * @param name
     * @return
     */
    public Vetor3f getPoint(String name){
        for(Peca p : pecas){
            Vetor3f v = p.getPoint(name);
            if(v != null)
                return v;
        }
        return null;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException{
        Peca p1 = pecas[0], p2 = pecas[1], p3 = pecas[2], p4 = pecas[3], 
                p5 = pecas[4], p6 = pecas[5], p7 = pecas[6];
        Figura cloned = (Figura) super.clone();
        cloned.setPecas( new Peca[]{
        (p1 != null ? (Peca) p1.clone() : null ),
        (p2 != null ? (Peca) p2.clone() : null ),
        (p3 != null ? (Peca) p3.clone() : null ),
        (p4 != null ? (Peca) p4.clone() : null ),
        (p5 != null ? (Peca) p5.clone() : null ),
        (p6 != null ? (Peca) p6.clone() : null ),
        (p7 != null ? (Peca) p7.clone() : null )});
        
        return cloned;
    }
}
