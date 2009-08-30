/*
 * 
 * 
 */

package jogl.intefaces;

import jogl.Color4f;

/**
 * A interface ColorModel disponibiliza metodos para manipulacao das cores de
 * um modelo.
 * 
 * @author GlauKo
 * 
 * @see jogl.Color4f
 */
public interface ColorModel {
    
    /**
     * Atribui a cor para o modelo.
     * 
     * @param color Color4f que representa a nova cor para o modelo.
     * 
     * @see jogl.Color4f
     */
    public void setColor(Color4f color);
    
    /**
     * retorna a cor do modelo.
     * 
     * @return Color4f que representa a cor do modelo.
     * 
     * @see jogl.Color4f
     */
    public Color4f getColor();
}
