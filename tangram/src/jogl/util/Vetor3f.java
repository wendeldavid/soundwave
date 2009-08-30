/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jogl.util;

/**
 *
 * Esta classe contém as coordenadas tridimencionais de um ponto,
 * e várias operações matemáticas retiradas do livro
 * Mathematics for 3D Game and computer graphics second edition
 * de Eric Lengyel
 * 
 * @author GlauKo
 */
public class Vetor3f implements Cloneable {
    
    /**
     * Valores das coordenadas 3D de um ponto
     */ 
    float x,y,z;
    
    /**
     * Nome do ponto
     */
    String name;
    
    /**
     * Contrutor. Inicializa os pontos com zero.
     */
    public Vetor3f(){
        x = 0;
        y = 0;
        z = 0;
    }
    
    /**
     * Construtor. Inicializa os pontos com os valores recebidos por parâmetros
     * 
     * @param x valor do eixo X do ponto
     * @param y valor do eixo Y do ponto
     * @param z valor do eixo Y do ponto
     */
    public Vetor3f(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    /**
     * compare está classe com o obj.
     * se o obj for null retorna false.
     * se a classe obj não for a mesma desta retorna false.
     * se este hashCode é diferente do hashCode do obj retorna null.
     * @param obj Object que será comparado com este.
     * @return true se x, y e z do obj forem iguais a estes x, y e z.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vetor3f other = (Vetor3f) obj;
        if (hashCode() != other.hashCode()) {
            return false;
        }
        return true;
    }

    /**
     * faz um código hash para os valores x, y e z da classe.
     * @return um inteiro representando o código hash.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Float.floatToIntBits(this.x);
        hash = 59 * hash + Float.floatToIntBits(this.y);
        hash = 59 * hash + Float.floatToIntBits(this.z);
        return hash;
    }
    
    /**
     * transforma os valores da classe em um String;
     * @return uma String com name : ( x , y , z ).
     */
    @Override
    public String toString() {
        return name + ": (" + x + ", " + y + ", " + z + ")"; 
    }

    /**
     * retorna o valor de x
     * @return float com o valor de x
     */
    public float getX() {
        return x;
    }

    /**
     * retorna o valor de y
     * @return float com o valor de y
     */
    public float getY() {
        return y;
    }

    /**
     * retorna o valor de z
     * @return float com o valor de z
     */
    public float getZ() {
        return z;
    }

    /**
     * atribui o valor de #name
     * @param name String com o valor de #name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * retorna o valor de name
     * @return String com o valor de name
     */
    public String getName() {
        return name;
    }
    
    
    /**
     * Retorna o vetor resultante da Soma de um Vetor a este;
     * 
     * @param v vetor que Somará a este vetor
     * @return um novo vetor sendo o resultado da Soma
     */
    public Vetor3f add(Vetor3f v){
        return new Vetor3f(x + v.getX(),
                           y + v.getY(),
                           z + v.getZ());
    }
    
    
    /**
     * Adciona o valor de um vetor a este.
     * 
     * @param v Vetor que somará a este.
     */
    public void addOnThis(Vetor3f v){
        x += v.getX();
        y += v.getY();
        z += v.getZ();
    }
    
    
    /**
     * Retorna o vetor resultante da Subtração de um Vetor a este;
     * 
     * @param v vetor que subtrairá este vetor
     * @return um novo vetor sendo o resultado da subtração
     */
    public Vetor3f sub(Vetor3f v){
        return new Vetor3f(x - v.getX(),
                           y - v.getY(),
                           z - v.getZ());
    }
    
    
    /**
     * Subtrai o valor de um vetor a este.
     * 
     * @param v vetor que subtrairá este vetor
     */
    public void subOnThis(Vetor3f v){
        x -= v.getX();
        y -= v.getY();
        z -= v.getZ();
    }
    
    
    /**
     * Retorna o vetor resultante da Multiplicação de um numero por este vetor
     * 
     * @param value valor que multiplicará este vetor
     * @return um novo vetor sendo resultado da multiplicação
     */
    public Vetor3f dot(float value){
        return new Vetor3f( x * value,
                            y * value,
                            z * value);
    }
    
    
    /**
     * Multiplica um numero a este vetor
     * 
     * @param value valor que multiplicará este vetor
     */
    public void dotOnThis(float value){
        x *= value;
        y *= value;
        z *= value;
    }
    
    
    /**
     * Retorna o vetor resultante da divisao de um numero por este vetor
     * 
     * @param value valor que dividirá este vetor
     * @return um novo vetor sendo resultado da divisão
     * @throws java.lang.Exception
     */
    public Vetor3f div(float value) throws Exception{
        if(value == 0)
            throw new Exception("Divison by zero");
        
        return new Vetor3f( x / value,
                            y / value,
                            z / value);
    }
    
    /**
     * divide de um numero por este vetor
     * 
     * @param value valor que dividirá este vetor
     * @throws java.lang.Exception
     */
    public void divOnThis(float value) throws Exception{
        if(value == 0)
            throw new Exception("Divison by zero");
        
        x /= value;
        y /= value;
        z /= value;
    }
    
    /**
     * Retorna o vetor resultante da soma de numero por este vetor
     * 
     * @param value valor que somará a este vetor
     * @return um novo vetor sendo resultado da soma
     */
    public Vetor3f sum(float value){
        return new Vetor3f( x + value,
                            y + value,
                            z + value);
    }
    
    /**
     * Soma um numero a este vetor
     * @param value valor que somará a este vetor
     */
    public void sumOnThis(float value){
        x += value;
        y += value;
        z += value;
    }
    
    /**
     * Calcula o comprimento de um vetor
     * formula do livro:
     * (1.6) pg. 13-14
     * @return float que representa o comprimento de um vetor
     */
    public float getLength(){
        return (float) Math.sqrt( x*x + y*y + z*z );
    }
    
    /**
     * calcula o produto escalar entre dois vetores
     * formula do livro:
     * (1.10) pg. 15
     * 
     * @param v Vetor3f que será multiplicado por este
     * 
     * @return float que representa a multiplicação escalar entre dois vetores
     */
    public float getScalarProduct(Vetor3f v){
        return (float) x * v.getX() + y * v.getY() + z * v.getZ();
    }
    
    /**
     * Rotaciona este ponto apartir de um ponto de origem.
     * Rotaciona apenas os pontos nas axis X e Y
     * @param angle Angulo em Radiandos
     * @param center Vetor3f que representa o ponto do Centor de Rotação
     */
    public void rotateXYOnThis(double angle, Vetor3f center){
        float xi = this.x;
        float yi = this.y;
        float xc = center.getX();
        float yc = center.getY();
        x = (float) ( ((xi - xc) * Math.cos(angle)) -
                      ((yi - yc) * Math.sin(angle)) + xc);
        y = (float) ( ((xi - xc) * Math.sin(angle)) +
                      ((yi - yc) * Math.cos(angle)) + yc);
    }
    
    /**
     * Faz o espelhamento do ponto na axis X apartir do ponto central
     * @param xc valor da axis x do ponto central
     */
    public void mirrorXOnThis(float xc){
        if (x != xc)
            x = 2 * xc - x;
    }
    
    /**
     * Calcula o Produto entre dois vetores
     * formula do livro:
     * (1.21) pg. 20
     * @param v Vetor3f a ser multiplicado por este
     * @return Vetor3f do Produto entre this e v
     */
    public Vetor3f vectorProduct(Vetor3f v){
        return new Vetor3f( y * v.getZ() - z * v.getY(),
                            z * v.getX() - x * v.getZ(), 
                            x * v.getY() - y * v.getX());
    }
    
    /**
     * Calcula a Normal deste vetor
     * @return Vetor3f que representa a Normal deste vetor
     * @throws java.lang.Exception
     */
    public Vetor3f normalization() throws Exception{
        
        float length = getLength();
        
        if(length == 0)
            throw new Exception("Division by Zero");
        
        return new Vetor3f( x / length,
                            y / length,
                            z / length);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
}
