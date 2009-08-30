/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jogl.intefaces;

import jogl.util.Linha;

/**
 * A interface RayTracing disponibiliza um metodo para detectar a interseccao 
 * entre uma linha e um triangulo e outro metodo detectar pontos proximos a uma
 * linha.
 * 
 * @author GlauKo
 * 
 * @see JOGL.util.Linha
 * @see jogl.intefaces.FormaTriangulos
 */
public interface RayTracing {
    
    /**
     * Detecta a interseccao entre um segmento de reta representado pelo classe 
     * Linha e a classe que implementa esta interface.
     * 
     * @param ray Linha que representa um segmento de reta
     * @return Object resultado da da Interseccao
     * 
     * @see JOGL.util.Linha
     */
    public Object colidiu(Linha ray);
    
    /**
     * Detecta se um ponto da classe que implementa este metodo está proximo de
     * um segmento de reta.
     * 
     * @param ray Linha que representa o segmento de reta
     * @return Object resultante.
     * 
     * @see JOGL.util.Linha
     */
    public Object pontoProximo(Linha ray);
}
