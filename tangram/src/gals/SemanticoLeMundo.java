/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gals;

import java.io.File;

import jogl.util.Vetor3f;
import tangram.Mundo;

/**
 *
 * @author GlauKo
 *             n
 *             i
 *             h
 *             s
 */
public class SemanticoLeMundo extends Semantico implements Constants{

    Mundo mundo;
    String identificador;
    File file;
    
    public SemanticoLeMundo(Mundo mundo){
        super();
        this.mundo = mundo;
    }
    @Override
    public void executeAction(int action, Token token)	throws SemanticError{
        switch(action){
            // Inicializa
            case 1:
                break;
            // Nome do mundo
            case 6:
                break;
            // Identificador para o modelo
            case 5:
                identificador = token.getLexeme();
                break;
            // Nome do modelo
            case 3:
                file = new File(token.getLexeme() + ".mod");
                break;
            // X
            case 9:
                x = Float.parseFloat(token.getLexeme());
                break;
            // Y
            case 10:
                y = Float.parseFloat(token.getLexeme());
                break;
            // Z
            case 11:
                z = Float.parseFloat(token.getLexeme());
                break;
            // insere figura
            case 29:
                mundo.addModelo(identificador, file, new Vetor3f(x, y, z));
                break;
        }
    }
}
