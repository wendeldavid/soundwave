/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jogl.util;

/**
 * Esta classe representa um triangulo e seu pontos são reprentetados 
 * pela classe Vetor3f
 * 
 * @author GlauKo
 * 
 * @see JOGL.util.Vetor3f
 * @see JOGL.util.Plano
 */
public class Triangulo {
    /**
     * Vetor3f que representam os pontos do triangulo
     * 
     * @see JOGL.util.Vetor3f
     */
    private Vetor3f a, b, c;
    
    /**
     * Plano em que está contido o triangulo
     * 
     * @see JOGL.util.Plano
     */
    private Plano plano;

    /**
     * Cria um Tringulo apartir dos seus pontos
     * @param a Vetor3f que representam um pontos do triangulo
     * @param b Vetor3f que representam um pontos do triangulo
     * @param c Vetor3f que representam um pontos do triangulo
     * 
     * @see JOGL.util.Vetor3f
     */    
    public Triangulo(Vetor3f a, Vetor3f b, Vetor3f c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.plano = new Plano(this);
    }

    /**
     * retorna o ponto A do tringulo
     * @return Vetor3f que representa o ponto A
     */
    public Vetor3f getA() {
        return a;
    }

    /**
     * retorna o ponto B do tringulo
     * @return Vetor3f que representa o ponto B
     */
    public Vetor3f getB() {
        return b;
    }

    /**
     * retorna o ponto C do tringulo
     * @return Vetor3f que representa o ponto C
     */
    public Vetor3f getC() {
        return c;
    }
    
    /**
     * retorna o plano que esta contido o triangulo
     * @return Plano em que esta contido o triangulo
     */
    public Plano getPlano(){
        return plano; 
    }

    @Override
    public String toString() {
        return "A" + a.toString() + ", B" + b.toString() + ", C" + c.toString();
    }
    
    /**
     * Detecta a interseccao de um ray com o triangulo.
     * @param ray linha representando um ray.
     * @return retorna o ponto aonde o ray intersepta o triangulo, caso
     *         o ray não intersepta o triangulo retorna null.
     */
    public Vetor3f getIntersectionPoint(Linha ray){
        // P é ponto onde o ray intersepta o plano do triangulo
        Vetor3f P = plano.getIntersection(ray);
        if(P == null)
            return null;
        
        // calcula as coordenadas baricentricas
        Vetor3f baryCoord = getBarycentricCoordinates(P);
        
        // se todos os valores forem positivos o ponto colidiu
        return ((baryCoord.getX() >= 0 &&
                 baryCoord.getY() >= 0 &&
                 baryCoord.getZ() >= 0)
                ? P : null);
    }
    
    /**
     * 
     * Calcula as coordenadas baricêntricas.
     * considerando os vetores A, B e C deste triangulo
     * formasse apartir de P outros três triangulos:
     * PAB, PAC, PBC.
     * Considerasse a soma da área de cada um desses triangulo
     * igual a área do do triangulo ABC.
     * 
     * f1 = área de PAB,
     * f2 = área de PAC,
     * f3 = área de PBC.
     * 
     * f1 + f2 + f3 = 1 (unidade de área ABC)
     * 
     * f3 = 1 - f2 - f3
     * 
     * Sendo assim, calcula-se f1 e f2 e utiliza-se 1 - f1 - f2
     * para calcular f3.
     * 
     * Se o ponto P está dentro do triangulo, f1, f2 e f3 
     * não podem ser negativos.
     * 
     * @param p ponto para calcular as coordenadas junto ao triangulo
     * @return um Vetor3f com f1, f2, f3.
     */
    public Vetor3f getBarycentricCoordinates(Vetor3f p){
        float f1, f2, f3, A,B,C,D,E,F,G,H,I;
        /* formula retirada do wikipedia
         * 
         * http://en.wikipedia.org/wiki/Barycentric_coordinates_%28mathematics%29
        */
        A = a.getX() - c.getX();
        B = b.getX() - c.getX();
        C = c.getX() - p.getX();
        D = a.getY() - c.getY();
        E = b.getY() - c.getY();
        F = c.getY() - p.getY();
        G = a.getZ() - c.getZ();
        H = b.getZ() - c.getZ();
        I = c.getZ() - p.getZ();
        
        f1 = (B*(F+I) - C*(E+H)) / (A*(E+H) - B*(D+G));
        f2 = (A*(F+I) - C*(D+G)) / (B*(D+G) - A*(E+H));
        f3 = 1 - f1 - f2;
        
        return new Vetor3f(f1,f2,f3);
        
        /*
        f1 = (
                (b.getX() - c.getX()) * ( (c.getY() - p.getY()) + (c.getZ() - p.getZ()) )
                -(c.getX() - p.getX()) * ( (b.getY() - c.getY()) + (b.getZ() - c.getZ()) )
             ) / (
                (a.getX() - c.getX()) * ( (b.getY() - c.getY()) + (b.getZ() - c.getZ()) )
                -(b.getX() - c.getX()) * ( (a.getY() - c.getY()) + (a.getZ() - c.getZ()) )
        );
        f2 = (
                (a.getX() - c.getX()) * ( (c.getY() - p.getY()) + (c.getZ() - p.getZ()) )
                - (c.getX() - p.getX()) * ( (a.getY() - c.getY()) + (a.getZ() - c.getZ()) )
             ) / (
                (b.getX() - c.getX()) * ( (a.getY() - c.getY()) + (a.getZ() - c.getZ()) )
                -(a.getX() - c.getX()) * ( (b.getY() - c.getY()) + (b.getZ() - c.getZ()) )
        );
        */
        
        
    }
}
