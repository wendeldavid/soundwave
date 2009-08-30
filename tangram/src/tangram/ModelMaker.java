/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tangram;

import javax.media.opengl.GL;

import jogl.Color4f;
import jogl.util.Vetor3f;

/**
 *
 * @author GlauKo
 */
public final class ModelMaker {
    
    public static Vetor3f[] getPontos(int tipo, Vetor3f centro){
        Vetor3f ponto1,ponto2,ponto3,ponto4;
        // pega valores do ponto central
        float x = centro.getX();
        float y = centro.getY();
        float z = centro.getZ();
        // se tipo for > 5 eh quadrador
        if (tipo > 5){
            // calcula os pontos x e y para cada ponto
            switch(tipo){
                case 6:
                    ponto1 = new Vetor3f(x - 250f,y,z);
                    ponto1.setName("1");
                    ponto2 = new Vetor3f(x,y - 250f,z);
                    ponto2.setName("2");
                    ponto3 = new Vetor3f(x + 250f,y,z);
                    ponto3.setName("3");
                    ponto4 = new Vetor3f(x,y + 250f,z);
                    ponto4.setName("4");
                    break;
                case 7:
                    ponto1 = new Vetor3f(x - 375f,y + 125f,z);
                    ponto1.setName("5");
                    ponto2 = new Vetor3f(x - 125f,y - 125f,z);
                    ponto2.setName("6");
                    ponto3 = new Vetor3f(x + 375f,y - 125f,z);
                    ponto3.setName("7");
                    ponto4 = new Vetor3f(x + 125f,y + 125f,z);
                    ponto4.setName("8");
                    break;
                default:
                    ponto1 = new Vetor3f(x,y,z);
                    ponto2 = new Vetor3f(x,y,z);
                    ponto3 = new Vetor3f(x,y,z);
                    ponto4 = new Vetor3f(x,y,z);
                    break;
            }
            return new Vetor3f[]{ponto1,ponto2,ponto3,ponto4};
        } else {
            // calcula os pontos x e y para cada ponto
            switch(tipo){
                case 1:
                    ponto1 = new Vetor3f(x - 250f,y - 500f,z);
                    ponto1.setName("9");
                    ponto2 = new Vetor3f(x - 250f,y + 500f,z);
                    ponto2.setName("10");
                    ponto3 = new Vetor3f(x + 250f,y,z);
                    ponto3.setName("11");
                    break;
                case 2:
                    ponto1 = new Vetor3f(x + 500f,y - 250f,z);
                    ponto1.setName("12");
                    ponto2 = new Vetor3f(x - 500f,y - 250f,z);
                    ponto2.setName("13");
                    ponto3 = new Vetor3f(x,y + 250f,z);
                    ponto3.setName("14");
                    break;
                case 3:
                    ponto1 = new Vetor3f(x + 150f,y - 350f,z);
                    ponto1.setName("15");
                    ponto2 = new Vetor3f(x - 350f,y + 150f,z);
                    ponto2.setName("16");
                    ponto3 = new Vetor3f(x + 150f,y + 150f,z);
                    ponto3.setName("17");
                    break;
                case 4:
                    ponto1 = new Vetor3f(x + 125f,y - 250f,z);
                    ponto1.setName("18");
                    ponto2 = new Vetor3f(x + 125f,y + 250f,z);
                    ponto2.setName("19");
                    ponto3 = new Vetor3f(x - 125f,y,z);
                    ponto3.setName("20");
                    break;
                case 5:
                    ponto1 = new Vetor3f(x - 250f,y + 125f,z);
                    ponto1.setName("21");
                    ponto2 = new Vetor3f(x + 250f,y + 125f,z);
                    ponto2.setName("22");
                    ponto3 = new Vetor3f(x,y - 125f,z);
                    ponto3.setName("23");
                    break;
                default:
                    ponto1 = new Vetor3f(x,y,z);
                    ponto2 = new Vetor3f(x,y,z);
                    ponto3 = new Vetor3f(x,y,z);
                    break;
            }
            return new Vetor3f[]{ponto1,ponto2,ponto3};
        }
    };
    
    public static int getDrawType(int tipo){
        // se tipo for > 5 eh quadrador senão é triangulo
        return (tipo > 5 ? GL.GL_QUADS : GL.GL_TRIANGLES);
    }
    
    public static Color4f getColor(int tipo){
        Color4f cor;
        switch(tipo){
            case 1:
                cor = ColorMaker.VERDE;
                break;
            case 2:
                cor = ColorMaker.VERDE_PISCINA;
                break;
            case 3:
                cor = ColorMaker.OLIVA;
                break;
            case 4:
                cor = ColorMaker.AZUL_PISCINA;
                break;
            case 5:
                cor = ColorMaker.VIOLETA;
                break;
            case 6:
                cor = ColorMaker.VERMELHO;
                break;
            case 7:
                cor = ColorMaker.AZUL;
                break;
            default:
                cor = ColorMaker.BRANCO;
                break;
        }
        return cor;
    }
    
    public static final String CRIA = "CRIA";
    public static final String VIVA = "VIVA";
    public static final String MUNDO = "MUNDO";
    public static final String TERMINA = "TERMINA";
    public static final String APAGA = "APAGA";
    
    public static void createModel(Modelo model){
        model.addAcao(CRIA);
        model.setNomeAcaoSelecionada(CRIA);
        model.setCodigoAcaoSelecionada(codigoDaPosicaoInicial());
    }
    
    public static boolean isNomeAcaoValido(String nomeAcao){
        nomeAcao = nomeAcao.trim();
        if(nomeAcao.length() == 0)
            return false;
        if(nomeAcao.equalsIgnoreCase(CRIA))
            return false;
        if(nomeAcao.equalsIgnoreCase(VIVA))
            return false;
        if(nomeAcao.equalsIgnoreCase(MUNDO))
            return false;
        if(nomeAcao.equalsIgnoreCase(TERMINA))
            return false;
        if(nomeAcao.equalsIgnoreCase(APAGA))
            return false;
        if(nomeAcao.equalsIgnoreCase("metodo"))
            return false;
        if(nomeAcao.equalsIgnoreCase("mundo"))
            return false;
        if(nomeAcao.equalsIgnoreCase("fim"))
            return false;
        for(char c : nomeAcao.toCharArray()){
            if(!(
                    (c >= 'a' && c <= 'z') ||
                    (c >= 'A' && c <= 'Z') ||
                    (c >= '0' && c <= '9') ||
                    (c == '�') ||
                    (c == '_')))
                
                return false;
        }
        return true;
    }
    
    public static String codigoDaPosicaoInicial(){
        return
                "cria p1(-250,0,-7000)" + "\n" +
                "cria p2(0,-250,-7000)" + "\n" +
                "cria p3(350,350,-7000)" + "\n" +
                "cria p4(375,-250,-7000)" + "\n" +
                "cria p5(0,125,-7000)" + "\n" +
                "cria p6(250,0,-7000)" + "\n" +
                "cria p7(-125,375,-7000)" + "\n";
    }
}
