/*
 * 
 * 
 */

package jogl;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import jogl.util.Linha;
import jogl.util.Vetor3f;

/**
 *
 * @author GlauKo
 *             n
 *             i
 *             h
 *             s
 */
public class UnProject {
    public static Linha unProject(GL gl, int x, int y){
        GLU glu = new GLU();
        double[] start = new double[4];
        double[] direction = new double[4];
        double[] model = new double[16];
        double[] proj = new double[16];
        int[] view = new int[4];
        gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, model,0);
        gl.glGetDoublev(gl.GL_PROJECTION_MATRIX, proj,0);
        gl.glGetIntegerv(gl.GL_VIEWPORT, view,0);

        /* note viewport[3] is height of window in pixels */
        double realY = view[3] - (int) y - 1;
                
        // desprojeta ponto inicial da linha
        glu.gluUnProject( (double) x, 
                          (double) realY, 
                          0.00001, 
                          model,0,
                          proj,0,
                          view,0,
                          start,0);
        
        // desprojeta direcao da linha
        glu.gluUnProject( (double) x, 
                          (double) realY, 
                          1.0,
                          model,0,
                          proj,0,
                          view,0,
                          direction,0);
        
        return new Linha(new Vetor3f((float)start[0],
                                     (float)start[1],
                                     (float)start[2]),
                         new Vetor3f((float)direction[0],
                                     (float)direction[1],
                                     (float)direction[2]));
    }
    
    public static Vetor3f unProject2(GL gl, int x, int y){
        GLU glu = new GLU();
        double[] start = new double[4];
        double[] model = new double[16];
        double[] proj = new double[16];
        int[] view = new int[4];
        gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, model,0);
        gl.glGetDoublev(gl.GL_PROJECTION_MATRIX, proj,0);
        gl.glGetIntegerv(gl.GL_VIEWPORT, view,0);

        /* note viewport[3] is height of window in pixels */
        double realY = view[3] - (int) y - 1;
                
        // desprojeta ponto inicial da linha
        glu.gluUnProject( (double) x, 
                          (double) realY, 
                          0.99, 
                          model,0,
                          proj,0,
                          view,0,
                          start,0);
        
        return new Vetor3f(Math.round(start[0]),
                Math.round(start[1]),
                Math.round(start[2]));
    }
    
}
