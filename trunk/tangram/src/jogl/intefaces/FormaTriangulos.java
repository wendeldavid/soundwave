/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jogl.intefaces;

/**
 * A interface FormaTriangulos disponibiliza metodos para criação de obsjetos da
 * classe JOGL.Util.Triangulo apartir dos atributos de um modelo.
 * 
 * @author GlauKo
 * 
 * @see JOGL.util.Triangulo
 * @see javax.media.opengl.GL
 * @see javax.media.opengl.GL#glBegin
 */
public interface FormaTriangulos {
    
    /**
     * Forma JOGL.Util.Triangulo apartir dos atributos de um modelo.
     * 
     * @see JOGL.util.Triangulo
     */
    public void formaTriangulos();
    
    /**
     * define o tipo de desenho do modelo usado pelo metodo glBegin da classe GL
     *  
     * @param type inteiro que representa um tipo de desenho utilizado pelo 
     * metodo
     * 
     * @see javax.media.opengl.GL
     * 
     * @see javax.media.opengl.GL#glBegin
     */
    public void setDrawType(int type);
    
    /**
     * retorna o valor do tipo de desenho utilizado pelo metodo glBegin da 
     * classe GL
     * 
     * @return inteiro que representa o tipo de desenho do modelo.
     * 
     * @see javax.media.opengl.GL#glBegin
     */
    public int getDrawType();
}
