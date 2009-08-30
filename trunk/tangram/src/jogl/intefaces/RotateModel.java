/*
 * 
 * 
 */

package jogl.intefaces;

import jogl.util.Vetor3f;

/**
 * A interface RotateModel disponibilza metodos para rotacao de modelos.
 * 
 * @author GlauKo
 */
public interface RotateModel {
    
    /**
     * Rotaciona o modelo em sentido anti-horario em torno de um ponto
     * 
     * @param angulo float que representa o angulo de rotacao do modelo
     * @param origem Vetor3f que representa o centro de rotacao
     */
    public void Rotate(float angulo, Vetor3f origem);
    
    /**
     * Rotaciona o modelo em sentido anti-horario em torno de seu centro
     * 
     * @param angulo float que representa o angulo de rotacao do modelo
     */
    public void Rotate(float angulo);
}
