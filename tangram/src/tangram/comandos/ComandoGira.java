/*
 * 
 * 
 */

package tangram.comandos;

/**
 *
 * @author GlauKo
 *             n
 *             i
 *             h
 *             s
 */
public class ComandoGira implements Comando{
    String model;
    float angulo;
    int ponto;

    public ComandoGira(String model, float angulo, int ponto) {
        this.model = model;
        this.angulo = angulo;
        this.ponto = ponto;
    }

    public void faca(Executor executor)throws ComandException {
        if (ponto == 0)
            executor.getRotateModel(model).Rotate(angulo);
        else
            executor.getRotateModel(model).Rotate(angulo,
                    executor.getPoint(String.valueOf(ponto)));
        //System.out.println("Comando Gira: " + angulo + "-" + ponto);
    }
    
    
}
