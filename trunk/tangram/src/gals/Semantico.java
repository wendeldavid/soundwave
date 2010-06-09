package gals;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import tangram.Figura;
import tangram.ModelMaker;
import tangram.comandos.Comando;
import tangram.comandos.ComandoCall;
import tangram.comandos.ComandoCallMundo;
import tangram.comandos.ComandoCor;
import tangram.comandos.ComandoCriaPeca;
import tangram.comandos.ComandoEnquantoFala;
import tangram.comandos.ComandoEspelha;
import tangram.comandos.ComandoEspera;
import tangram.comandos.ComandoFala;
import tangram.comandos.ComandoGira;
import tangram.comandos.ComandoLaco;
import tangram.comandos.ComandoMove;
import tangram.comandos.ComandoMoveParaMundo;
import tangram.comandos.ComandoViva;
import tangram.comandos.ComandoVivaMundo;
import tangram.comandos.ModeloExecutavel;
import tangram.comandos.MundoExecutavel;
import tangram.io.ReadWriteFile;

public class Semantico implements Constants{
    
    String nomeDoMundo, nomeDoModelo, nomeModeloAux, nomeDoMetodo, idDaPeca,
            idDoModelo, nomeDaCor;
    
    float x,y,z;
    
    Comando comando;
    
    Comando comandoMundo;
    
    private Pilha pilha;
    private PilhaEnquanto pilhaEnquanto;
    
    // Usado nas a��es 15,16. Representa se id do comando � nomeDoModelo ou idDaPeca
    boolean modeloSelecionado;
    
    // Usado no 14 para saber qual comando jogar para pilha
    boolean compilandoMundo;
    
    // Usado no 35 para controlar se o metodo � em paralelo ou n�o
    boolean emParalelo;
        
    // Contem os Comandos que ir�o virar um La�o
    ArrayList<Comando> comandosDoMetodo;
    ArrayList<Comando> comandosDoMundo;
    
    // Cont�m Nome do M�todo e o la�o do m�todo 
    HashMap<String,Comando> metodosDoModelo;
    
    // Metodos Usados no Modelo (pelo comando Fa�a)
    ArrayList<String> metodosUsados;
    
    boolean[] pecasCriadas;
    
    // Contem o Nome do Modelo + os Comandos dele dele
    HashMap<String,HashMap<String,Comando>> modelosCompilados;
    // Cont�m Id do Modelo + o Modelo Execut�vel dele
    HashMap<String,ModeloExecutavel> modelosDoMundo;
    // Cont�m Id do Modelo + o Status, dele os quais s�o
    // 1 - criado em espera
    // 2 - em viva
    // 3 - apagado
    HashMap<String,Integer> statusDosModelosDoMundo;

	private String jsmlURL = null;

	private boolean sobreposto = false;
    
    public void executeAction(int action, Token token)	throws SemanticError
    {
        //System.out.println("A��o #"+action+", Token: "+token);
        switch(action){
            /* Cria novo MetodosDoModelo.
             * Zera nomeDoModelo.
             * Zera metodosUsados.
             * Zera pecasCriadas.
             * Cria pilha vazia.
             * Inclui M�todo Viva
             * Marca que esta compilando um Modelo
             */
            case 0:
                metodosDoModelo = new HashMap<String, Comando>();
                nomeDoModelo = null;
                nomeModeloAux = null;
                metodosUsados = new ArrayList<String>();
                pecasCriadas = new boolean[]
                        {false,false,false,false,false,false,false};
                pilha = new Pilha();
                metodosDoModelo.put(ModelMaker.VIVA, new ComandoViva());
                compilandoMundo = false;
                break;
            /*
             * Cria novo modelosDoMundo
             * Cria novo statusDosModelosDoMundo
             * Zera modelos Executavel
             * Zera comandos do mundo
             * Cria pilha e coloca um la�o no topo
             * Marca que esta compilando um mundo
             * Inicial o mundo executavel
             */
            case 1:
                modelosDoMundo = new HashMap<String,ModeloExecutavel>();
                statusDosModelosDoMundo = new HashMap<String, Integer>();
                modelosCompilados = new HashMap
                        <String,HashMap<String,Comando>>();
                comandosDoMundo = new ArrayList<Comando>();
                pilha = new Pilha();
                pilha.push(new ComandoLaco(comandosDoMundo,1));
                compilandoMundo = true;
                iniciaMundoExecutavel();
                break;
            /*
             * Retira o M�todo da Pilha
             * Cria um Modelo com apenas o Metodo CRIA
             */
            case 2:
                compiler.setModelo(new ModeloExecutavel(metodosDoModelo,
                        ""));
                break;
            /* 
             * Seta o nomeDoModelo se ele for nulo
             * se N�o For compara com o token
             * se for diferente dispara um erro.
             */
            case 3:
                if(nomeDoModelo == null)
                    nomeDoModelo = token.getLexeme();
                else
                    if(!nomeDoModelo.equals(token.getLexeme()))
                        throw new SemanticError("Nome do Modelo Errado",
                                token.getPosition());
                if(!compilandoMundo && nomeModeloAux == null)
                    nomeModeloAux = token.getLexeme();
                break;
            /* 
             * Grava Token na vari�vel nomeDoMetodo
             */
            case 4:
                nomeDoMetodo = token.getLexeme();
                break;
            /* 
             * Grava Token na vari�vel idDoModelo
             * Zera vari�vel nomeDoModelo
             */
            case 5:
                idDoModelo = token.getLexeme();
                nomeDoModelo = null;
                break;
            /* 
             * Grava Token na vari�vel nomeDoMundo
             */
            case 6:
                nomeDoMundo = token.getLexeme();
                break;
            /*
             * Grava Token na vari�vel idDaPeca
             */
            case 7:
                idDaPeca = token.getLexeme();
                break;  
            /*
             * Grava cor da peca na variavel nomeDaCor
             */
            case 8:
                nomeDaCor = token.getLexeme();
                break;
            /*
             * Grava valor do token na variavel x
             */
            case 9:
                x = Float.parseFloat(token.getLexeme());
                break;
            /*
             * Grava valor do token na vari�vel y
             */
            case 10:
                y = Float.parseFloat(token.getLexeme());
                break;
            /*
             * Grava valor do token na vari�vel z
             */
            case 11:
                z = Float.parseFloat(token.getLexeme());
                break;
            /* 
             * Cria Comando La�o e Coloca ele to topo da Pilha
             */
            case 12:
                comandosDoMetodo = new ArrayList<Comando>();
                pilha.push(new ComandoLaco(comandosDoMetodo,1));
                break;
            /* 
             * Verifica se o Metodo atual � Cria
             * Verifica se Peca ainda n�o foi criada
             * Cria Comando Cria Peca
             * Marca Peca como criada
             * Marca vari�vel modeloSeleciona como false
             */
            case 13:
                if(!nomeDoMetodo.equals(ModelMaker.CRIA))
                    throw new SemanticError("Comando cria deve ser " +
                            "escrito na A��o CRIA!",token.getPosition());
                int tipoDaPeca = Integer.parseInt(idDaPeca.substring(1));
                if(tipoDaPeca < 1 || tipoDaPeca > 7)
                    throw new SemanticError("O Nome da Pe�a � inv�lido",
                            token.getPosition());
                if(pecasCriadas[tipoDaPeca-1])
                    throw new SemanticError("Pe�a j� criada!",
                            token.getPosition());
                comando = new ComandoCriaPeca(tipoDaPeca, new float[]{x,y,z});
                pecasCriadas[tipoDaPeca-1] = true;
                modeloSelecionado = false;
                break;
            /*
             * Adciona Comando no La�o do Topo da Fila
             * Zera Vari�veis X Y Z
             */
            case 14:
                if (compilandoMundo){
                    if(comandoMundo == null)
                        throw new SemanticError("Comando nulo",
                                token.getPosition());
                    pilha.add(comandoMundo);
                    comandoMundo = null;
                }else{
                    if(comando == null)
                        throw new SemanticError("Comando nulo",
                                token.getPosition());
                    pilha.add(comando);
                    comando = null;
                }
                x = 0;
                y = 0;
                z = 0;
                break;
            /*
             * Marca modeloSeleciona como true
             */
            case 15:
                modeloSelecionado = true;
                break;
            /*
             * marca modeloSelecionado como false
             * verifica se pe�a foi criada
             */
            case 16:
                modeloSelecionado = false;
                verificaPecaCriada(token);
                break;
            /*
             * Cria comando Move
             */
            case 17:
                comando = new ComandoMove(getSelectedModel(), x, y, z);
                break;
            /*
             * cria comando Gira
             */
            case 18:
                comando = new ComandoGira(getSelectedModel(), x, (int)y);
                break;
            case 19:
                break;
            /* 
             * Cria comando Cor
             */
            case 20:
                comando = new ComandoCor(getSelectedModel(), nomeDaCor);
                break;
            /*
             * Cria comando Espelha
             */
            case 21:
                comando = new ComandoEspelha(getSelectedModel());
                break;
            /*
             * Cria comando Piscar
             */
            case 22:
                comando = new ComandoEspera((long)x);
                comandoMundo = comando;
                break;
            /*
             * Cria Comando La�o
             * Coloca Comando la�o no topo da pilha
             */
            case 23:
                if(compilandoMundo)
                    pilha.push(new ComandoLaco(
                            new ArrayList<Comando>(),
                            (int)x));
                else
                    pilha.push(new ComandoLaco(
                            new ArrayList<Comando>(),
                            (int)x));
                break;
            /*
             * retira da Pilha o topo e adciona no novo topo
             */
            case 24:
                if(compilandoMundo){
                    ComandoLaco cmdLaco = pilha.pop();
                    pilha.add(cmdLaco);
                    comandoMundo = null;
                }else{
                    ComandoLaco cmdLaco = pilha.pop();
                    pilha.add(cmdLaco);
                    comando = null;
                }
                x = 0;
                y = 0;
                z = 0;
                break;
            /*
             * cria comando faca
             * coloca comando faca no array de m�todos usados
             */
            case 25:
                comando = new ComandoCall(getSelectedModel(),
                        token.getLexeme(),
                        false,
                        0);
                metodosUsados.add(token.getLexeme());
                break;
            /*
             * Retira la�o do topo da pilha e adciona no hashmap de metodos do
             * modelo
             */
            case 26:
                metodosDoModelo.put(nomeDoMetodo, pilha.pop());
                break;
            /*
             * Verifica se os m�todos usados em comandos faca foram todos 
             * declarados
             * Cria comando para o metodo VIVA do Modelo.
             * Finaliza Modelo Criando objeto da classe ModeloExecutavel e 
             * devolvido-a a classe que construi esta.
             */
            case 27:
                for(String s : metodosUsados){
                    if(!metodosDoModelo.containsKey(s))
                        throw new SemanticError("Metodo: " + s + " n�o foi" +
                                "criado no modelo e foi usado em um comando " +
                                "Fa�a");
                }
                metodosDoModelo.put(ModelMaker.VIVA, new ComandoViva());
                compiler.setModelo(new ModeloExecutavel(metodosDoModelo,
                        nomeModeloAux));
                break;
            /* 
             * Verifica se id Dado ao modelo j� foi usado.
             * Verifica se o Modelo est� na lista de modelos j� usados,
             * caso n�o esteja, carrega, compila e guarda o modelo.
             * Cria um ModeloExecut�vel com uma nova Figura e os comandos do
             * modelo e adiciona na lista de modelos do mundo.
             * coloca o status do modelo como criado em espera
             * Cria comando que chama m�todo cria do modelo.
             */
            case 28:
                // Verifica se id Dado ao modelo j� foi usado
                if(modelosDoMundo.containsKey(idDoModelo))
                    throw new SemanticError(idDoModelo +
                            " j� foi usado.");
                // Verifica se o Modelo est� na lista de modelos j� usados
                if(!modelosCompilados.containsKey(nomeDoModelo))
                    try{
                        compilaModelo();
                    } catch (AnalysisError ae){
                        throw new SemanticError("Modelo " + nomeDoModelo +
                                " n�o pode ser compilado!\n" +
                                "O seguinte erro ocorreu:\n" +
                                ae.getMessage());
                    }
                // Cria um ModeloExecut�vel
                ModeloExecutavel modExe = new ModeloExecutavel(
                        modelosCompilados.get(nomeDoModelo),
                        nomeDoModelo);
                // Cria figura para o modelo
                Figura figuraDoModelo = new Figura(idDoModelo);
                // reseta pe�as
                figuraDoModelo.resetPecas();
                // Adiciona nova figura ao modelo
                modExe.setFigura(figuraDoModelo);
                // Adiciona na lista de modelos do mundo
                modelosDoMundo.put(idDoModelo, modExe);
                // Adiciona status
                statusDosModelosDoMundo.put(idDoModelo, new Integer(1));
                // Cria comando que chama m�todo cria do modelo
                comandoMundo = new ComandoCallMundo(idDoModelo,
                        ModelMaker.CRIA,
                        false);
                break;
            /* 
             * Cria comando para mover a figura para posi��o inicial apartir de
             * x, y e z.
             */
            case 29:
                comandoMundo = new ComandoMoveParaMundo(idDoModelo,
                        nomeDoModelo,
                        x,
                        y,
                        z);
                break;
            /* 
             * Verifica se id do modelo foi criado.
             * verifica se ele est� parado.
             * Cria comando Viva.
             * marca o modelo em metodo viva
             */
            case 30:
                verificaIdModelo();
                if(statusDosModelosDoMundo.get(idDoModelo).equals(2))
                    throw new SemanticError("O modelos j� est� executando " +
                            "uma a��o VIVA");
                comandoMundo = new ComandoVivaMundo(idDoModelo, nomeDoMetodo);
                statusDosModelosDoMundo.put(idDoModelo,new Integer(2));
                break;
            /* 
             * Verifica se id do modelo foi criado
             * verifica se ele esta em viva
             * cria comando termina
             */
            case 31:
                verificaIdModelo();
                if(!statusDosModelosDoMundo.get(idDoModelo).equals(2))
                    throw new SemanticError("O modelos n�o est� executando " +
                            "uma a��o VIVA");
                comandoMundo = new ComandoCallMundo(idDoModelo, 
                        ModelMaker.TERMINA,
                        true);
                statusDosModelosDoMundo.put(idDoModelo,new Integer(1));
                break;
            /* 
             * Verifica se id do modelo foi criado
             * cria comando apaga
             */
            case 32:
                verificaIdModelo();
                comandoMundo = new ComandoCallMundo(idDoModelo,
                        ModelMaker.APAGA,
                        true);
                statusDosModelosDoMundo.put(idDoModelo,new Integer(3));
                break;
            /* 
             * Limpa vari�veis x, y, z, idDoModelo e emParalelo
             */
            case 33:
                x = 0;
                y = 0;
                z = 0;
                idDoModelo = null;
                emParalelo = false;
                break;
            /* 
             * Verifica se id do modelo foi criado
             * cria comando facaMundo
             */
            case 34:
                verificaIdModelo();
                comandoMundo = new ComandoCallMundo(idDoModelo,
                        nomeDoMetodo, 
                        emParalelo);
                break;
            /* 
             * marca que o comando � em paralelo
             */
            case 35:
                emParalelo = true;
                break;
            case 36:
            	//TODO nao sei que raios essa a��o semantica faz...maldito seja!!!
            	break;
            case 37:
            	jsmlURL = token.getLexeme();
            	break;
            case 38:
            	sobreposto = true;//foda-se o lexeme, se entrar aqui � pq existe o token sobreposto
            	break;
            case 39:
            	comando = new ComandoFala(jsmlURL, sobreposto);
            	break;
            case 40:
            	jsmlURL = token.getLexeme();
            	break;
            case 41:
            	 pilhaEnquanto.push(new ComandoEnquantoFala(jsmlURL, new ArrayList<Comando>()));
            	break;
            case 42:
            	comando = pilhaEnquanto.pop();
            	//TODO verificar pq o bloco interno de anima��o s� � chamado 1 vez
//            	ComandoEnquantoFala cmdEnquanto = pilhaEnquanto.pop();
//            	pilhaEnquanto.add(cmdEnquanto);
//            	 if(compilandoMundo){
//                     comandoMundo = null;
//                 }else{
//                     comando = null;
//                 }
            	break;
        }
        	
    }
    
    private Compiler compiler;

    public Semantico(Compiler compiler){
        this.compiler = compiler;
        /* 
         * estes comandos abaixo foram inclu�dos aqui para que se po�a compilar
         * um m�todo sem alterar as a��es semanticas no GALS
         */
        pilha = new Pilha();
        pilhaEnquanto = new PilhaEnquanto();
        pecasCriadas = new boolean[]
                        {false,false,false,false,false,false,false};
        metodosDoModelo = new HashMap<String, Comando>();
    }
    
    public Semantico(){
        
    }
    
    private String getSelectedModel(){
        return (modeloSelecionado ? nomeDoModelo : idDaPeca);
    }
    
    private void verificaPecaCriada(Token token) throws SemanticError{
        if(!pecasCriadas[Integer.parseInt(idDaPeca.substring(1))-1])
            throw new SemanticError("Pe�a n�o foi criada!",
                token.getPosition());
    }
    
    private void compilaModelo() throws AnalysisError{
        String codigoDoModelo = ReadWriteFile.read(new File(nomeDoModelo+".mod"));
        Compiler comp = new Compiler(codigoDoModelo);

        modelosCompilados.put(nomeDoModelo,comp.compileModel().getMetodos());
    }
    
    private void verificaIdModelo()throws SemanticError{
        if(!statusDosModelosDoMundo.containsKey(idDoModelo))
            throw new SemanticError("Modelo n�o criado.");
        if(statusDosModelosDoMundo.get(idDoModelo).equals(3))
            throw new SemanticError("O modelos j� foi apagado");
    }
    
    private void iniciaMundoExecutavel(){
        MundoExecutavel me = new MundoExecutavel();
        me.setModelosDoMundo(modelosDoMundo);
        me.setComandosDoMundo(comandosDoMundo);
        compiler.setMundo(me);
    }
    
    private class Pilha {
        
        private Nodo topo;

        public Pilha() {
            topo = null;
        }
        
        public void push(ComandoLaco laco){
            topo = new Nodo(laco,topo);
        }
        
        public ComandoLaco pop(){
            Nodo aux = topo;
            topo = topo.getAnterior();
            return aux.getLaco();
        }
        
        public void add(Comando c){
            topo.add(c);
        }
                
        private class Nodo{
            private ComandoLaco laco;
            private Nodo anterior;
            
            public Nodo(ComandoLaco laco, Nodo anterior){
                this.laco = laco;
                this.anterior = anterior;
            }

            public Semantico.Pilha.Nodo getAnterior() {
                return anterior;
            }

            public ComandoLaco getLaco() {
                return laco;
            }
            
            public void add(Comando c){
                laco.add(c);
            }
        }
    }
    
    private class PilhaEnquanto {
        
        private Nodo topo;

        public PilhaEnquanto() {
            topo = null;
        }
        
        public void push(ComandoEnquantoFala enquanto){
            topo = new Nodo(enquanto,topo);
        }
        
        public ComandoEnquantoFala pop(){
            Nodo aux = topo;
            topo = topo.getAnterior();
            return aux.getEnquanto();
        }
        
        public void add(Comando c){
            topo.add(c);
        }
                
        private class Nodo{
            private ComandoEnquantoFala enquanto;
            private Nodo anterior;
            
            public Nodo(ComandoEnquantoFala enquanto, Nodo anterior){
                this.enquanto = enquanto;
                this.anterior = anterior;
            }

            public Semantico.PilhaEnquanto.Nodo getAnterior() {
                return anterior;
            }

            public ComandoEnquantoFala getEnquanto() {
                return enquanto;
            }
            
            public void add(Comando c){
                enquanto.add(c);
            }
        }
    }
}
