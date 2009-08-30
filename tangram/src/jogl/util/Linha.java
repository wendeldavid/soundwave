/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jogl.util;

/**
 * 
 * Usado Para representar um segmento de reta ou ray
 * 
 * retirado do livro:
 * Mathematics for 3D Game and computer graphics second edition
 * de Eric Lengyel
 * Capitulos 4.1
 * 
 * @author GlauKo
 * 
 * @see JOGL.util.Vetor3f
 */
public class Linha {
    /**
     * Um ponto do segmento de reta ou o ponto de origem de um ray.
     * 
     * @see JOGL.util.Vetor3f
     */
    private Vetor3f origem;
    /**
     * Outro ponto do segmento de reta ou o ponto direcao de um ray.
     * 
     * @see JOGL.util.Vetor3f
     */
    private Vetor3f direcao;
    
    /**
     * cria um segmento de reta, ou ray, com seus dois pontos.
     * @param origem Vetor3f que representa um ponto da Linha
     * @param direcao Vetor3f que representa outro ponto da Linha
     * 
     * @see JOGL.util.Vetor3f
     */
    public Linha(Vetor3f origem, Vetor3f direcao) {
        this.origem = origem;
        this.direcao = direcao;
    }
    
    /**
     * retorna o ponto direcao
     * @return Vetor3f que representa o ponto
     */
    public Vetor3f getDirecao() {
        return direcao;
    }
    
    /**
     * retorn o ponto origem
     * @return Vetor3f que representa o ponto
     */
    public Vetor3f getOrigem() {
        return origem;
    }
    
    /**
     * Atribui um ponto ao Vetor3f direcao da classe
     * @param direcao ponto que representa o Vetor3f
     */
    public void setDirecao(Vetor3f direcao) {
        this.direcao = direcao;
    }
    
    /**
     * Atribui um ponto ao Vetor3f origem da classe
     * @param origem ponto que representa o Vetor3f
     */
    public void setOrigem(Vetor3f origem) {
        this.origem = origem;
    }
    
    /**
     * Este método calcula o ponto de intersecção entre dois segmentos de reta.
     * se não existe intersecção entre as retas, retorna null.
     * é considerado apenas os eixos X e Y, o eixo Z é desconciderado.
     * 
     * @param l Linha que representa um segmento de reta.
     * 
     * @return retorna o ponto de intersecção entre as retas ou nulo se não 
     * existe intersecção.
     */
    public Vetor3f getIntersection(Linha l){
        float A = origem.x - direcao.x ;
        float B = origem.y - direcao.y ;
        float C = l.origem.x - l.direcao.x ;
        float D = l.origem.y - l.direcao.y ;
        
        
        float det = (A*D) - (B*C);
        
        if (det == 0)
            return null;
        
        float E = direcao.y - l.direcao.y;
        float F = direcao.x - l.direcao.x;
        
        float s = (A*E - B*F) / det;
        
        if(s < 0 || s > 1)
            return null;
        
        float t = (C*E - D*F) / det;
        
        if(t < 0 || t > 1)
            return null;
        
        float G = origem.x * direcao.y - origem.y * direcao.x ;
        float H = l.origem.x * l.direcao.y - l.origem.y * l.direcao.x ;        
        
        return new Vetor3f((G*C - A*H) / det,
                (G*D - B*H) / det,
                origem.z);
    }
    
    @Override
    public String toString() {
        return "O" + origem.toString() + ", D" + direcao.toString();
    }
    
    
}
