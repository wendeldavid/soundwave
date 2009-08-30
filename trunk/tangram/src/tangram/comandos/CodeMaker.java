/*
 * 
 * 
 */

package tangram.comandos;

import java.util.ArrayList;

import tangram.Figura;
import tangram.ModelMaker;
import tangram.Peca;
import tangram.MouseEventHandler.Modo;
import tangram.gui.EditorTextual;
import tangram.gui.EditorTextualMundo;
import tangram.gui.ModoDinamico;
import tangram.gui.ModoDinamicoGira;

/**
 *
 * @author GlauKo
 *             n
 *             i
 *             h
 *             s
 */
public class CodeMaker {
    
    public enum Gerador {
        NAO,
        ESTATICO,
        DINAMICO,
        MUNDO
    }
    
    private EditorTextual editorTextual;
    private EditorTextualMundo editorTextualMundo;
    private Gerador gerador;

    public CodeMaker(EditorTextual editorTextual) {
        this.editorTextual = editorTextual;
        gerador = Gerador.ESTATICO;
    }
    
    public CodeMaker(EditorTextualMundo editorTextualMundo){
        this.editorTextualMundo = editorTextualMundo;
        gerador = Gerador.MUNDO;
    }

    public EditorTextual getEditorTextual() {
        return editorTextual;
    }

    public void setEditorTextual(EditorTextual editorTextual) {
        this.editorTextual = editorTextual;
    }

    public Gerador getGerador() {
        return gerador;
    }

    public void setGerador(Gerador gerador) {
        this.gerador = gerador;
    }
    
    public void make(Modo modo, Figura figura, Peca peca, float[] parms){
        /*
         * GERADOR ESTATICO DO MODELO
         */
        if(gerador == Gerador.ESTATICO){
        
            String NomeAcao = editorTextual.getModelo().getNomeAcaoSelecionada();

            String codigo = "";
        
            if(NomeAcao.equals(ModelMaker.CRIA)){

                /* COMANDOS CRIA PECA DO METODO CRIA */

                if(figura != null){
                    for( Peca p : figura.getPecas()){
                        codigo += "cria p" + p.getTipo() + "(" + 
                                (int)p.getCenter().getX() + "," +
                                (int)p.getCenter().getY() + "," +
                                (int)p.getCenter().getZ() + ")";
                        if(p.getAngulo() != 0)
                            codigo += " gira(" + p.getAngulo() + ")";
                        if(p.isMirrored())
                            codigo += " espelha";
                        if( ! p.getColor().equals(ModelMaker.getColor(p.getTipo())))
                            codigo += " cor(" + p.getColor().getName() + ")";
                        codigo += "\n";
                    }
                    if(codigo.length() > 0)
                        editorTextual.insereComandosCRIA(codigo);
                }
            } else{
                /* COMANDO MOVE PECA */
                if( modo == Modo.PECA_SELECIONADA){
                    if (peca != null && parms.length == 3)
                        if(parms[0] != 0 || parms[1] != 0 || parms[2] != 0)
                            codigo += "p" + peca.getTipo() + ".move(" +
                                    (int)parms[0] + ", " +
                                    (int)parms[1] + ", " +
                                    (int)parms[2] + ")";

                /* COMANDO MOVE MODELO */
                } else if ( modo == Modo.MODELO_SELECIONADO){
                    if (parms.length == 3)
                        if(parms[0] != 0 || parms[1] != 0 || parms[2] != 0)
                            codigo += editorTextual.getModelo().getNomeDoModelo().trim() +
                                    ".move(" + 
                                    (int)parms[0] + ", " +
                                    (int)parms[1] + ", " +
                                    (int)parms[2] + ")";

                /* COMANDO GIRA PECA */
                } else if ( modo == Modo.ROTACIONA_PECA){
                    if (peca != null && parms.length >=1){
                        if(parms[0] != 0)
                            codigo += "p" + peca.getTipo() + ".gira(" +
                                    (int)parms[0] + ")";
                        if (parms.length == 2)
                            codigo += " no ponto (" + (int)parms[1] + ")" ;
                    }

                /* COMANDO GIRA MODELO */
                } else if ( modo == Modo.ROTACIONA_MODELO ){
                    if (peca != null && parms.length >=1){
                        if(parms[0] != 0)
                            codigo += editorTextual.getModelo().getNomeDoModelo().trim() +
                                    ".gira(" +
                                    (int)parms[0] + ")";
                        if (parms.length == 2)
                            codigo += " no ponto (" + (int)parms[1] + ")";
                    }
                
                /* COMANDO ESPELHA */
                } else if ( modo == Modo.MIRROR){
                    if (peca == null)
                        codigo += editorTextual.getModelo().getNomeDoModelo().trim() +
                                ".espelha" ;
                    else
                        codigo += "p" + peca.getTipo() + ".espelha" ;

                /* COMANDO COR */
                } else if (modo == Modo.MUDA_COR){
                    if (peca == null){
                        if(figura.getColor() != null)
                            codigo += editorTextual.getModelo().getNomeDoModelo().trim() +
                                    ".cor(" + figura.getColor().getName() + ")";
                    } else
                        codigo += "p" + peca.getTipo() + ".cor(" + 
                                peca.getColor().getName() + ")";
                }

                if(codigo.length() > 0)
                    editorTextual.insereComando(codigo);
            }
        /*
         * GERADOR PARA O MUNDO
         */
        } else if (gerador == Gerador.MUNDO){
            String codigo = "";
            /* COMANDO CRIA */
            if (modo == Modo.CRIA_MODELO){
                String idModelo = figura.getName();
                String modelo = peca.getCenter().getName();
                codigo = "cria " + idModelo + " como " + modelo + "(" +
                        parms[0] + "," + parms[1] + "," + parms[2] + ")";
            }
            
            if(codigo.length() > 0)
                editorTextualMundo.insereComando(codigo);
        /*
         * GERADOR DINAMICO DO MODELO
         */
        } else if (gerador == Gerador.DINAMICO){
            // Carrega os metodos do modelo
            ArrayList acoes = new ArrayList();
            for( String s : editorTextual.getModelo().getAcoes().keySet()){
                if( ! s.equals(ModelMaker.CRIA))
                    acoes.add(s);
            }
            /*
             * Comando para mover
             */
            if ( modo == Modo.MODELO_SELECIONADO || 
                    modo == Modo.PECA_SELECIONADA){
                ModoDinamico md = new ModoDinamico(
                        (modo == Modo.MODELO_SELECIONADO ? figura.getName()
                                : "p"+peca.getTipo()),
                        acoes,
                        parms);
                String codigo = md.showDialog(editorTextual);
                if (codigo != null)
                    editorTextual.insereComando(codigo);
            /*
             * Comando para girar
             */
            }else if( modo == Modo.ROTACIONA_MODELO || 
                    modo == Modo.ROTACIONA_PECA){
                ModoDinamicoGira mdg = new ModoDinamicoGira(
                        (modo == Modo.ROTACIONA_MODELO ? figura.getName()
                                : "p"+peca.getTipo()),
                        acoes,
                        parms);
                String codigo = mdg.showDialog(editorTextual);
                if (codigo != null)
                    editorTextual.insereComando(codigo);
            }
        }
    }
}
