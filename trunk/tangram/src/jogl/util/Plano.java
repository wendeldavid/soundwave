/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jogl.util;

/**
 * Plano de um triangulo.
 * 
 * Usado para calcular a cruzamento de uma Linha com um plano.
 * retirado do livro:
 * Mathematics for 3D Game and computer graphics second edition
 * de Eric Lengyel.
 * Capitulos 4.1, 4.2.1, 5.2  e 5.2.1
 * 
 * @author GlauKo
 * 
 * @see JOGL.util.Linha
 * @see JOGL.util.Vetor3f
 * @see JOGL.util.Triangulo
 */
public class Plano {
    /**
     * Vetor3f que representa a <I>Normal</I> de um plano
     */ 
    private Vetor3f N;
    /**
     * float que representa a <I>Distacia</I> de um plano
     */
    private float D;

    /**
     * Cria um plano recebendo a <I>Normal</I> e <I>Distacia</I> de um plano
     * 
     * @param N Vetor3f que representa a <I>Normal</I> de um plano
     * @param D float que representa a <I>Distacia</I> de um plano
     * 
     * @see JOGL.util.Vetor3f
     */
    public Plano(Vetor3f N, float D) {
        this.N = N;
        this.D = D;
    }
    
    /**
     * Cria um plano a partir dos pontos de um triangulo
     * formulas do livro:
     * (5.52) pg. 144
     * 
     * @param t tringulo
     * 
     * @see JOGL.util.Triangulo
     */
    public Plano(Triangulo t) {
        try{
            N = t.getB().sub(t.getA()).
                    vectorProduct(
                    t.getC().sub(t.getA()));
            D = - N.getScalarProduct(t.getA());
        } catch(Exception e) {
            System.out.println("Não Existe Plano!");
        }
    }
    
    /**
     * formulas do livro:
     * (4.16) pg. 107
     * (5.51) pg. 143
     * 
     * @param ray Linha que cruza o plano
     * 
     * @return retorna o ponto de cruzamento com o plano se a Linha cruza o 
     * Plano, caso não cruze, retorna nulo.
     * 
     * @see JOGL.util.Linha
     */
    public Vetor3f getIntersection(Linha ray){
        // se o produto escalar entre a normal do plano e
        // o ponto final do segmento de reta for zero
        // não há intersecção
        if(N.getScalarProduct(ray.getDirecao()) == 0 )
            return null;
        
        // aplicando (4.16)
        float t = ( ( - ray.getOrigem().getScalarProduct(N)) - D ) /
                        ray.getDirecao().getScalarProduct(N);
        
        // aplicando propriedade: t deve ser maior ou igual a zero
        // pg. 102
        if( t>=0 )
            // aplicando (5.51)
            return ray.getOrigem().add(ray.getDirecao().dot(t));
        
        return null;
    }
    
}
