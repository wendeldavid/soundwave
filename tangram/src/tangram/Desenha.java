/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tangram;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import jogl.Camera;
import jogl.intefaces.DrawModel;

/**
 * Esta classe implementa metodos para o desenho em um GLCanvas, tratamento
 * de eventos do mouse e tratamento de eventos do teclado.
 * @author GlauKo
 */
public class Desenha implements GLEventListener, MouseMotionListener,
        MouseListener, KeyListener{
    
    /**
     * Armazena o GLU
     */
    GLU glu;
    
    /**
     * Armazena o largura dividida pela altura da tela
     */
    float wide;
    
    /**
     * Armazena a Camera
     */
    Camera camera;
    
    /**
     * Armazena os modelos a serem desenhados
     */
    ArrayList <DrawModel> modelos;
    
    /**
     * Armazena o tratador de eventos do mouse.
     */
    MouseEventHandler handler;
    
    /**
     * Armazena os Eventos do mouse que ainda nao foram tratados.
     */
    ArrayList <MouseEvent> me;
    
    /**
     * Armazena os Eventos do teclado que ainda não foram tratados.
     */
    ArrayList <KeyEvent> ke;
    
    /**
     * Cria nova instâcia de Desenha passando o Tratados de Eventos.
     * @param handler
     */
    public Desenha(MouseEventHandler handler){
        super();
        this.modelos = handler.getModelos();
        this.camera = handler.getCamera();
        this.handler = handler;
    }
    
    public void init(GLAutoDrawable glad) {
        GL gl = glad.getGL(); 
        
        glu = new GLU();
        
        me = new ArrayList<MouseEvent>();
        ke = new ArrayList<KeyEvent>();
        
        // cor de fundo padrão
        gl.glClearColor(1.0f, 1.0f, 0.7f, 0.5f);
        
        /* ShadeModel: 
         *     GL_FLAT (constante),
         *     GL_SMOOTH (interpolacao linear entre os vertices)
         */
        gl.glShadeModel(GL.GL_SMOOTH);

        // Depth Buffer Setup 
        gl.glClearDepth(1.0f);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LEQUAL);
        gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
               
    	// setar o tamanho do ponto em pixels
    	//gl.glPointSize(5.0f);
        
    	// setar a largura da linha em pixels
    	//gl.glLineWidth(3.0f);
        
    	/**
         * setar o modo de renderizacao das faces. Ha tres alternativas:
    	 *     GL_POINT (somente vertices), 
    	 *      GL_LINE (somente linhas),
         *      GL_FILL (faces preenchidas) 
         */
    	//gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_LINE);
        
    	/**
         * setar a convencao de orientacao. Ha duas alternativas:
         *      GL_CCW (anti-horario),
         *      GL_CW (horario)
         */
    	//gl.glFrontFace (GL.GL_CCW);
        
        
    	// Habilitar face culling
    	//gl.glEnable (GL.GL_CULL_FACE);
        
    	/** 
         * definir as faces a serem discardadas. Ha tres alternativas: 
         *     GL_FRONT,
    	 *     GL_BACK ,
         *     GL_FRONT_AND_BACK 
         */
    	//gl.glCullFace (GL.GL_BACK);
    	
        
        //gl.glEnable(gl.GL_LIGHTING);
        //gl.glEnable(gl.GL_LIGHT1);
        //gl.glLightfv(gl.GL_LIGHT1, gl.GL_AMBIENT, new float[]{20.9f,20.9f,20.9f}, 0);
        //gl.glLightfv(gl.GL_LIGHT1, gl.GL_POSITION, new float[]{.0f,.0f,-30f},0);

        //gl.glEnable(gl.GL_BLEND);
        //gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_ONE_MINUS_SRC_ALPHA);
        //gl.glBlendFunc(gl.GL_SRC_ALPHA_SATURATE, gl.GL_ONE);
         
        //gl.glEnable(gl.GL_LINE_SMOOTH);
        //gl.glHint(gl.GL_LINE_SMOOTH_HINT, GL.GL_NICEST);
        //gl.glEnable(gl.GL_POINT_SMOOTH);
        //gl.glHint(gl.GL_POINT_SMOOTH_HINT, GL.GL_NICEST);
        gl.glEnable(gl.GL_POLYGON_SMOOTH);
        gl.glHint(gl.GL_POLYGON_SMOOTH_HINT, GL.GL_NICEST);
        
        //gl.glEnable(gl.GL_FOG);
        //gl.glHint(gl.GL_FOG_HINT, GL.GL_NICEST);
        
        // eventos do mouse e teclado
        glad.addMouseListener(this);
        glad.addMouseMotionListener(this);
        glad.addKeyListener(this);
    }

    public void display(GLAutoDrawable glad) {
        GL gl = glad.getGL();
        
        gl.glMatrixMode(gl.GL_MODELVIEW);
        //gl.glLoadIdentity();
        
        // trata eventos
        if( ! me.isEmpty() ){
            ArrayList <MouseEvent> events = me;
            me = new ArrayList<MouseEvent>();
            handler.action(gl, events);
        }
        
        //gl.glMatrixMode(gl.GL_MODELVIEW);
        //gl.glLoadIdentity();
        //gl.glMatrixMode(GL.GL_PROJECTION);
        //gl.glLoadIdentity();
        //glu.gluPerspective(60.0f, wide, 100f, 100000.0f);
        
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        //gl.glClear (GL.GL_COLOR_BUFFER_BIT);
        
        this.modelos = handler.getModelos();
        for(DrawModel dm : modelos){
            dm.draw(gl);
        }
        
        handler.extraDraws(gl);
        
        camera.action(gl);
                
        glad.swapBuffers();
    }
    
    public void reshape(GLAutoDrawable glad, int x, int y, int w, int h) {
        //System.out.println("reshape" + "-" + x + "-" + y + "-" + w + "-" + h);
        
        GL gl = glad.getGL();
        
        if (h <= 0)
            h = 1;
        wide = (float) w / h;
        gl.glViewport(x, y, w, h);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(60.0f, wide, 100f, 100000.0f);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        //gl.glLoadIdentity();
        /*
        camera.setCameraType(Camera.lookAt);
        camera.start = new Vetor3f(0f,0f,0f);
        camera.direction = new Vetor3f(0f,0f,-10000f);
        camera.up = new Vetor3f(0f, 10000f, -10000f);
        */
    }

    public void displayChanged(GLAutoDrawable glad, 
                               boolean modeChanged,
                               boolean deviceChanged){
        // Não faz nada.
    }

    public void mouseDragged(MouseEvent e) {
        me.add(e);
    }

    public void mouseMoved(MouseEvent e) {
        me.add(e);
    }

    public void mouseClicked(MouseEvent e) {
        me.add(e);
    }

    public void mousePressed(MouseEvent e) {
        me.add(e);
    }

    public void mouseReleased(MouseEvent e) {
        me.add(e);
    }

    public void mouseEntered(MouseEvent e) {
        me.add(e);
    }

    public void mouseExited(MouseEvent e) {
        me.add(e);
    }

    public void keyTyped(KeyEvent e) {
        ke.add(e);
    }

    public void keyPressed(KeyEvent e) {
        ke.add(e);
    }

    public void keyReleased(KeyEvent e) {
        ke.add(e);
    }
    
}
