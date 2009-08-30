/*
 * 
 * 
 */

package tangram;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.media.opengl.GL;

import jogl.Camera;

/**
 *
 * @author GlauKo
 *             n
 *             i
 *             h
 *             s
 */
public class MEHforMundoExecutavel implements MouseEventHandler{

    ArrayList modelos;
    Camera camera;

    public MEHforMundoExecutavel(ArrayList modelos, Camera camera) {
        this.modelos = modelos;
        this.camera = camera;
    }
    
    public void action(GL gl, ArrayList<MouseEvent> me) {
        
    }

    public void extraDraws(GL gl) {
        
    }

    public void setModelos(ArrayList<Figura> modelos) {
        
    }

    public ArrayList getModelos() {
        return modelos;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        
    }

    public void setModo(Modo modo) {
        
    }

}
