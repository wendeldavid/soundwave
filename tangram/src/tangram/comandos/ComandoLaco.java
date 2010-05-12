/*
 * 
 * 
 */

package tangram.comandos;

import java.util.ArrayList;

/**
 *
 * @author GlauKo
 *             n
 *             i
 *             h
 *             s
 */
public class ComandoLaco implements Comando{
    // Numero de Repetições
    int numeroDeRepeticoes;
    // Lista de Comandos
    ArrayList<Comando> comandos;

    public ComandoLaco(ArrayList<Comando> al, int parm) {
        comandos = al;
        numeroDeRepeticoes = parm;
    }
    
    public void add(Comando c){
        comandos.add(c);
    }
    
    public void faca(Executor executor)throws ComandException {
        // para cada repeticao
        for(int i = 0; i < numeroDeRepeticoes; i++){
            // para cada comando da lista
            for(Comando c : comandos){
                // executa o comando
                c.faca(executor);
            }
        }
    }
}
