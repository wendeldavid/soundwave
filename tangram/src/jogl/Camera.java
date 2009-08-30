/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jogl;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import jogl.util.Vetor3f;

/**
 * Esta classe implementa o posicionamento da camera no espaço tridimencional.
 * Estao disponiveis duas formas para controlar a camera, as quais sao:
 * glTranslatef e gluLookAt.
 * 
 * @author GlauKo
 * 
 * @see javax.media.opengl.GL
 * @see javax.media.opengl.glu.GLU
 */
public class Camera implements jogl.intefaces.Camera{
    /**
     * armazena o tipo de controle da camera.
     */
    int cameraType;
    
    /**
     * Pontos para controlar a posicao da camera
     */
    public Vetor3f start, direction, up;
    
    /**
     * Cria Camera passando o ponto para controle da camera atraves do metodo
     * glTranslatef
     * 
     * @param start
     */
    public Camera(Vetor3f start) {
        cameraType = translate;
        this.start = start;
    }
    
    /**
     * Cria Camera passando os trez pontos necessario para o controle da camera
     * atraves do metodo gluLookAt
     * 
     * @param start
     * @param direction
     * @param up
     */
    public Camera(Vetor3f start, Vetor3f direction, Vetor3f up) {
        this.cameraType = lookAt;
        this.start = start;
        this.direction = direction;
        this.up = up;
    }
    
    public void setCameraType(int cameraType) {
        this.cameraType = cameraType;
    }

    public void setStart(Vetor3f start) {
        this.start = start;
    }

    public void setDirection(Vetor3f direction) {
        this.direction = direction;
    }

    public void setUp(Vetor3f up) {
        this.up = up;
    }


    public void action(GL gl) {
        switch(cameraType){
            case translate :
                gl.glTranslatef(start.getX(),start.getY(),start.getZ());
                start = new Vetor3f();
                break;
            case lookAt :
                GLU glu = new GLU();
                glu.gluLookAt(start.getX(), start.getY(), start.getZ(), 
                        direction.getX(), direction.getY(), direction.getZ(),
                        up.getX(), up.getY(), up.getZ());
        }
    }

}
