/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jogl.intefaces;

import jogl.util.Vetor3f;

/**
 * A interface MirrorModel disponibiliza metodos para espelhar um modelo.
 * 
 * @author Glauco
 */
public interface MirrorModel {
    /**
     * Espelha o modelo, no eixo X, a partir do centro dele.
     */
    public void mirror();
    
    /**
     * Espelha o modelo, no eixo X, a partir do ponto representado pelo Vetor3f 
     * v
     * 
     * @param v Vetor3f que representa o ponto em que será espelhado o modelo
     * 
     * @see JOGL.util.Vetor3f
     */
    public void mirror(Vetor3f v);
}
