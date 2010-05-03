/*
 * 
 * 
 */

package gals;

import tangram.comandos.ModeloExecutavel;
import tangram.comandos.MundoExecutavel;

/**
 *
 * @author GlauKo
 *             n
 *             i
 *             h
 *             s
 */
public class Compiler {
    
    String code;
    
    ModeloExecutavel modelo;
    
    MundoExecutavel mundo;

    public Compiler(String code) {
        this.code = code;
        modelo = null;
        mundo = null;
    }

    public ModeloExecutavel compileModel() throws AnalysisError{
        Lexico lexico = new Lexico(code);
        Semantico semantico = new Semantico(this);
        Sintatico sintatico = new Sintatico();
        sintatico.parse(lexico, semantico);
        if(modelo == null)
            throw new AnalysisError("Código compilado não é um Modelo");
        return modelo;
    }
    
    public MundoExecutavel compileWorld() throws AnalysisError{
        Lexico lexico = new Lexico(code);
        Semantico semantico = new Semantico(this);
        Sintatico sintatico = new Sintatico();
        sintatico.parse(lexico, semantico);
        if(mundo == null)
            throw new AnalysisError("Código compilado não é um Mundo");
        return mundo;
    }

    public void setModelo(ModeloExecutavel modelo) {
        this.modelo = modelo;
    }

    public void setMundo(MundoExecutavel mundo) {
        this.mundo = mundo;
    }

    public ModeloExecutavel getModelo() {
        return modelo;
    }

    public MundoExecutavel getMundo() {
        return mundo;
    }
}
