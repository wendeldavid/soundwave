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
 * Esta Interface disponibiliza métodos para o tratamento das interações do 
 * usuário com a inteface gráfica, a qual é desenhada através de um GLCanvas.
 * A interação do usuário, utilizando o mouse, com a interface gráfica gera
 * objetos da classe MouseEvent, os quais são tratados gerando ações sobre os
 * objetos das Classes Figura e Peca.
 * Esta Interface disponibiliza métodos atribui ou retorna as figuras que são
 * desenhadas no GLCanvas, além de um método para desenhas outras informações no
 * GLCanvas. Disponibiliza também o objeto da classe Camera.
 * @author GlauKo
 */
public interface MouseEventHandler {
    
    /**
     * Trata os eventos do mouse gerados pelo GLCanvas.
     * @param gl
     * @param me
     */
    public void action(GL gl, ArrayList<MouseEvent> me);
    
    /**
     * Desenha informações extras na tela.
     * @param gl
     */
    public void extraDraws(GL gl);
    
    /**
     * Atribui os modelos que devem ser desenhados na tela
     * @param modelos
     */
    public void setModelos(ArrayList<Figura> modelos);
    
    /**
     * Retona os modelos que devem ser desenhados na tela
     * @return
     */
    public ArrayList getModelos();
    
    /**
     * retorna a Camera
     * @return
     */
    public Camera getCamera();
    
    /**
     * Atribui a Camera
     * @param camera
     */
    public void setCamera(Camera camera);
    
    /**
     * Atribui o modo de tratamento dos eventos do mouse.
     * @param modo
     */
    public void setModo(Modo modo);
    
    /**
     * Enumeração que contem os possiveis modes de tratamento de eventos do 
     * mouse.
     */
    public enum Modo {
        /**
         * Modo para movimentar a camera
         */
        PAN,
        /**
         * Modo para espelhar
         */
        MIRROR,
        /**
         * Modo para rotação de peças
         */
        ROTACIONA_PECA,
        /**
         * Modo para rotação de figuras
         */
        ROTACIONA_MODELO,
        /**
         * Modo para seleção de pontos das peças
         */
        SELECIONA_PONTO,
        /**
         * Modo para seleção de peças
         */
        SELECIONA_PECA,
        /**
         * Modo para seleção de figuras
         */
        SELECIONA_MODELO,
        /**
         * Modo para quando uma peça foi selecionada
         */
        PECA_SELECIONADA,
        /**
         * Modo para quando uma figura foi selecionada
         */
        MODELO_SELECIONADO,
        /**
         * Modo para mudar a cor
         */
        MUDA_COR,
        /**
         * Modo para criar um modelo (Figura).
         */
        CRIA_MODELO
    };
}
