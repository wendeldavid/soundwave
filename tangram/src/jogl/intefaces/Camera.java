/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jogl.intefaces;

import javax.media.opengl.GL;

import jogl.util.Vetor3f;

/**
 * A interface Camera disponibiliza métodos para manipulação da camera em mundo
 * tridimencional.
 * 
 * @author GlauKo
 * 
 * @see javax.media.opengl.GL#glTranslatef
 * @see javax.media.opengl.GL
 */
public interface Camera {
    
    /**
     * tipo da camera: lookAt
     */
    public int lookAt = 1;
    
    /**
     * tipo da camera: translate
     * 
     * @see javax.media.opengl.GL#glTranslatef
     */
    public int translate = 2;
    
    /**
     * Atribui o tipo de implementação da camera.
     * @param cameraType numero do tipo de camera.
     */
    public void setCameraType(int cameraType);
    
    /**
     * Atribui o ponto Start da camera.
     * @param start Vetor3f que representa o ponto Start da camera
     */
    public void setStart(Vetor3f start);
    
    /**
     * Atribui o ponto Direction da camera.
     * @param direction Vetor3f que representa o ponto Direction da camera
     */
    public void setDirection(Vetor3f direction);
    
    /**
     * Atribui o ponto Up da camera.
     * @param up Vetor3f que representa o ponto Up da camera
     */
    public void setUp(Vetor3f up);
    
    /**
     * interage com a classe GL alterando a posição da camera.
     * @param gl GL que será alterada a posição da camera.
     * @see javax.media.opengl.GL
     */
    public void action(GL gl);
}
