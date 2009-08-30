/*
 * 
 * 
 */

package tangram.comandos;

import jogl.Color4f;
import tangram.ColorMaker;

/**
 *
 * @author GlauKo
 *             n
 *             i
 *             h
 *             s
 */
public class ComandoCor implements Comando{
    String model;
    Color4f color;

    public ComandoCor(String model, String cor) {
        this.model = model;
        color = ColorMaker.makeColor(cor);
    }

    public void faca(Executor executor)throws ComandException {
        executor.getColorModel(model).setColor(color);
        //System.out.println("Comando Cor: " + color);
    }
}
