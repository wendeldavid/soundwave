/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jogl.intefaces;

import javax.media.opengl.GL;

/**
 * A interface DrawModel disponibiliza metodos para o desenho de modelos.
 * 
 * @author GlauKo
 * 
 * @see javax.media.opengl.GL
 */
public interface DrawModel {
    
    /**
     * interage com a classe GL desenhado o modelo.
     * 
     * @param gl GL que será desenhado o modelo.
     * 
     * @see javax.media.opengl.GL
     */
    public void draw(GL gl);
    
    /**
     * atribui se o modelo está visivel ou não.
     * 
     * @param visible boolean que informa se o modelo está visivel
     */
    public void setVisible(boolean visible);
    
    /**
     * retorna se o modelo está visivel ou não.
     * 
     * @return true se o modelo está visivel
     */
    public boolean isVisible();
}
