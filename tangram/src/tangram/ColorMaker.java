/*
 * 
 * 
 */

package tangram;

import jogl.Color4f;

/**
 *
 * @author GlauKo
 *             n
 *             i
 *             h
 *             s
 */
public class ColorMaker {
    public static Color4f makeColor(String nome){
        nome = nome.toUpperCase();
        if(nome.equals("PRETO"))
            return PRETO;
        else if(nome.equals("MARROM"))
            return MARROM;
        else if(nome.equals("VERDE"))
            return VERDE;
        else if(nome.equals("OLIVA"))
            return OLIVA;
        else if(nome.equals("AZULMARINHO"))
            return AZUL_MARINHO;
        else if(nome.equals("VIOLETA"))
            return VIOLETA;
        else if(nome.equals("VERDEPISCINA"))
            return VERDE_PISCINA;
        else if(nome.equals("CINZA"))
            return CINZA;
        else if(nome.equals("PRATA"))
            return PRATA;
        else if(nome.equals("VERMELHO"))
            return VERMELHO;
        else if(nome.equals("VERDELIMA"))
            return VERDE_LIMA;
        else if(nome.equals("AMARELO"))
            return AMARELO;
        else if(nome.equals("AZUL"))
            return AZUL;
        else if(nome.equals("ROSA"))
            return ROSA;
        else if(nome.equals("AZULPISCINA"))
            return AZUL_PISCINA;
        else if(nome.equals("BRANCO"))
            return BRANCO;
        else
            return null;
    }
    
    public static Color4f PRETO = new Color4f(0f,0f,0f,0f,"preto");
    public static Color4f MARROM = new Color4f(.5f,.2f,.2f,0f,"marrom");
    public static Color4f VERDE = new Color4f(0f,.5f,0f,0f,"verde");
    public static Color4f OLIVA = new Color4f(.5f,.5f,0f,0f,"oliva");
    public static Color4f AZUL_MARINHO = new Color4f(0f,.25f,.5f,0f,"azulMarinho");
    public static Color4f VIOLETA = new Color4f(.5f,0f,.5f,0f,"violeta");
    public static Color4f VERDE_PISCINA = new Color4f(0f,.5f,.5f,0f,"verdePiscina");
    public static Color4f CINZA = new Color4f(.5f,.5f,.5f,0f,"cinza");
    public static Color4f PRATA = new Color4f(.75f,.75f,.75f,0f,"prata");
    public static Color4f VERMELHO = new Color4f(1f,0f,0f,0f,"vermelho");
    public static Color4f VERDE_LIMA = new Color4f(0f,1f,0f,0f,"verdeLima");
    public static Color4f AMARELO = new Color4f(1f,1f,0f,0f,"amarelo");
    public static Color4f AZUL = new Color4f(0f,0f,1f,0f,"azul");
    public static Color4f ROSA = new Color4f(1.0f,0f,.5f,0f,"rosa");
    public static Color4f AZUL_PISCINA = new Color4f(0f,1f,1f,0f,"azulPiscina");
    public static Color4f BRANCO = new Color4f(1f,1f,1f,0f,"branco");
}
