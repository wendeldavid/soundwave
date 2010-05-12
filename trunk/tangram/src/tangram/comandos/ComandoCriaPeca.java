/*
 * 
 * 
 */

package tangram.comandos;

import jogl.intefaces.DrawModel;
import jogl.intefaces.TranslateModel;
import jogl.util.Vetor3f;

/**
 *
 * @author GlauKo
 *             n
 *             i
 *             h
 *             s
 */
public class ComandoCriaPeca implements Comando{

    int tipoDaPeca;
    float[] parms;

    public ComandoCriaPeca(int tipoDaPeca, float[] parms) {
        this.tipoDaPeca = tipoDaPeca;
        this.parms = parms;
    }
    
    public void faca(Executor executor) throws ComandException {
        try{
            String model = "p" + tipoDaPeca;
            TranslateModel tm = executor.getTranslateModel(model);
            tm.translateTo(new Vetor3f(parms[0], parms[1], parms[2]));
            DrawModel dm = executor.getDrawModel(model);
            dm.setVisible(true);
            //System.out.println("Comando Cria Peca: " + tipoDaPeca + "-" + 
            //        parms[0] + "-" + parms[1] + "-" + parms[2]);
        }catch(Exception e){
            e.printStackTrace();
            //throw new ComandException("Peça Inválida");
        }
    }

}
