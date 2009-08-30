/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jogl;

/**
 * Esta classe armazena a cor de um modelo para o desenha na classe GL.
 * A classe armazena a quantidade de vermelho(R), verde(G), azul(A) de uma cor
 * mais o atributo alpha atraves de valores reais de zero(0) a um (1).
 * 
 * 
 * @author Glauco
 */
public class Color4f implements Cloneable {
    /**
     * Valores reais que representam a cor.
     */
    float R,G,B,alpha;
    
    /**
     * Nome da cor
     */
    String name;

    /**
     * Cria uma nova cor passando os valores de R, G, B e alfa
     * @param R
     * @param G
     * @param B
     * @param alfa
     */
    public Color4f(float R, float G, float B, float alfa) {
        this(R,G,B,alfa,"");
    }
    
    /**
     * Cria uma nova cor passando os valores de R, G, B, alfa e o nome da cor.
     * @param R
     * @param G
     * @param B
     * @param alfa
     * @param name
     */
    public Color4f(float R, float G, float B, float alfa, String name) {
        this.R = R;
        this.G = G;
        this.B = B;
        this.alpha = alfa;
        valida();
        this.name = name;
    }

    /**
     * retorna o valor de R.
     * @return
     */
    public float getR() {
        return R;
    }

    /**
     * Retorna o valor de G.
     * @return
     */
    public float getG() {
        return G;
    }
    
    /**
     * Retorna o valor de B.
     * @return
     */
    public float getB() {
        return B;
    }

    /**
     * Retorna o valor de Alpha
     * @return
     */
    public float getAlpha() {
        return alpha;
    }

    /**
     * Atribui o valor de R.
     * @param R
     */
    public void setR(float R) {
        this.R = R;
        valida();
    }

    /**
     * Atribui o valor de G.
     * @param G
     */
    public void setG(float G) {
        this.G = G;
        valida();
    }

    /**
     * Atribui o valor de B.
     * @param B
     */
    public void setB(float B) {
        this.B = B;
        valida();
    }

    /**
     * Atribui o valor de Alpha.
     * @param alfa
     */
    public void setAlpha(float alfa) {
        this.alpha = alfa;
        valida();
    }

    /**
     * Retorna o nome da cor.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Atribui o nome da cor.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Retorna uma nova cor com o valor dos atributos R, G, B invertidos 
     * conforme a equacao:
     * novaValor = 1 - valor.
     * @return
     */
    public Color4f negativo(){
        return new Color4f(1f - R, 1f - G, 1f - B, alpha);
    }
    
    /**
     * Retorna uma string com os valores dos atributos da cor.
     * @return
     */
    @Override
    public String toString() {
        return "(RGBα: " + R + ", " + G + ", " + B + ", " + alpha + " )";
    }

    /**
     * Compara se os atributos R,G,B e alpha são iguais através da funcao 
     * hashCode
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return (this.hashCode() == obj.hashCode() ? true : false);
    }

    /**
     * retorna um valor inteiro representando o hash code dos atributos R,G,B e 
     * alpha
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Float.floatToIntBits(this.R);
        hash = 19 * hash + Float.floatToIntBits(this.G);
        hash = 19 * hash + Float.floatToIntBits(this.B);
        hash = 19 * hash + Float.floatToIntBits(this.alpha);
        return hash;
    }
    
    /**
     * Verifica se os valores dos atributos da cor são maiores que um (1).
     * se for altera o valor para um (1).
     * Este método é chamado em todos os métodos de atribuição desta classe.
     */
    private void valida(){
        if(R > 1.0f)
            R = 1.0f;
        if(G > 1.0f)
            G = 1.0f;
        if(B > 1.0f)
            B = 1.0f;
        if(alpha > 1.0f)
            alpha = 1.0f;
    }

    /**
     * Clona o objeto desta classe.
     * @return
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
