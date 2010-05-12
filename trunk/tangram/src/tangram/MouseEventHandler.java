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
 * Esta Interface disponibiliza m�todos para o tratamento das intera��es do 
 * usu�rio com a inteface gr�fica, a qual � desenhada atrav�s de um GLCanvas.
 * A intera��o do usu�rio, utilizando o mouse, com a interface gr�fica gera
 * objetos da classe MouseEvent, os quais s�o tratados gerando a��es sobre os
 * objetos das Classes Figura e Peca.
 * Esta Interface disponibiliza m�todos atribui ou retorna as figuras que s�o
 * desenhadas no GLCanvas, al�m de um m�todo para desenhas outras informa��es no
 * GLCanvas. Disponibiliza tamb�m o objeto da classe Camera.
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
     * Desenha informa��es extras na tela.
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
     * Enumera��o que contem os possiveis modes de tratamento de eventos do 
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
         * Modo para rota��o de pe�as
         */
        ROTACIONA_PECA,
        /**
         * Modo para rota��o de figuras
         */
        ROTACIONA_MODELO,
        /**
         * Modo para sele��o de pontos das pe�as
         */
        SELECIONA_PONTO,
        /**
         * Modo para sele��o de pe�as
         */
        SELECIONA_PECA,
        /**
         * Modo para sele��o de figuras
         */
        SELECIONA_MODELO,
        /**
         * Modo para quando uma pe�a foi selecionada
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
