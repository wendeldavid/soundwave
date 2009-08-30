/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jogl.intefaces;

import jogl.util.Vetor3f;

/**
 * A interface TranslateModel disponibiza metodos para translacao de um modelo
 * 
 * @author GlauKo
 * 
 * @see JOGL.util.Vetor3f
 */
public interface TranslateModel {
    
    /**
     * Realiza a translacao do modelo adicionando um valor a posicao em que o 
     * modelo se encontra.
     * 
     * @param v Vetor3f que representa o valor a ser adcionado a posicao do 
     * modelo
     * 
     * @see JOGL.util.Vetor3f
     */
    public void translate(Vetor3f v);
    
    /**
     * Realiza a translacao do modelo para um ponto especifico.
     * @param v Vetor3f que representa o ponto em que o modelo será movido
     * 
     * @see JOGL.util.Vetor3f
     */
    public void translateTo(Vetor3f v);
}
