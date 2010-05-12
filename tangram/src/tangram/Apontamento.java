/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tangram;

import jogl.util.Linha;
import jogl.util.Plano;
import jogl.util.Vetor3f;

/**
 * Esta Classe guarda informações sobre a intersecção de um segmento de reta com
 * um objeto da classe Figura.
 * @author GlauKo
 */
public class Apontamento{
    /**
     * Linha que gerou a colisão.
     */
    Linha ray;
    
    /**
     * Plano em que a Figura esta contida.
     */
    Plano plano;
    
    /**
     * Peca em que a Linha colidiu.
     */
    Peca pecaColidiu;
    
    /**
     * Vetor3f que representa o ponto em que a Linha colidiu
     */
    Vetor3f pontoColidiu;
    
    /**
     * Figura em que a Linha colidiu.
     */
    Figura modeloColidiu;
    
    /**
     * Cria nova instância de Apontamento passando a Peca, o ponto, a linha e o 
     * plano em que ouve a colisão.
     * @param pecaColidiu
     * @param pontoColidiu
     * @param ray
     * @param p
     */
    public Apontamento(Peca pecaColidiu, Vetor3f pontoColidiu, Linha ray, Plano p){
            this.pecaColidiu = pecaColidiu;
            this.pontoColidiu = pontoColidiu;
            this.ray = ray;
            this.plano = p;
    }

    /**
     * Retorna a Figura em que houve a colisão
     * @return
     */
    public Figura getModeloColidiu() {
        return modeloColidiu;
    }

    /**
     * Retorna a Peca em que houve a colisão
     * @return
     */
    public Peca getPecaColidiu() {
        return pecaColidiu;
    }

    /**
     * Retorna o Vetor3f em que houve a colisão
     * @return
     */
    public Vetor3f getPontoColidiu() {
        return pontoColidiu;
    }

    /**
     * Retorna o Plano em que houve a colisão
     * @return
     */
    public Plano getPlano() {
        return plano;
    }

    /**
     * Retorna a Linha que gerou a colisão
     * @return
     */
    public Linha getRay() {
        return ray;
    }
    
    /**
     * atribui a Figura em que houve a colisão
     * @param modeloColidiu
     */
    public void setModeloColidiu(Figura modeloColidiu) {
            this.modeloColidiu = modeloColidiu;
    }
    
    @Override
    public String toString(){
        return "Tipo de Pe�a: " + pecaColidiu.getTipo() +
               " - Ponto Colidiu: " + pontoColidiu.toString();
    }
    
}
