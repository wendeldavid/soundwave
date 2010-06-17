package gals;

public class Sintatico implements Constants
{
    private Token currentToken;
    private Token previousToken;
    private Lexico scanner;
    private Semantico semanticAnalyser;

    public void parse(Lexico scanner, Semantico semanticAnalyser) throws AnalysisError
    {
        this.scanner = scanner;
        this.semanticAnalyser = semanticAnalyser;

        currentToken = scanner.nextToken();
        if (currentToken == null)
            currentToken = new Token(DOLLAR, "$", 0);

        codigo();

        if (currentToken.getId() != DOLLAR)
            throw new SyntaticError(PARSER_ERROR[DOLLAR], currentToken.getPosition());
    }

    private void match(int token) throws AnalysisError
    {
        if (currentToken.getId() == token)
        {
            previousToken = currentToken;
            currentToken = scanner.nextToken();
            if (currentToken == null)
            {
                int pos = 0;
                if (previousToken != null)
                    pos = previousToken.getPosition()+previousToken.getLexeme().length();

                currentToken = new Token(DOLLAR, "$", pos);
            }
        }
        else
            throw new SyntaticError(PARSER_ERROR[token], currentToken.getPosition());
    }

    private void codigo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 6: // modelo
                modelo();
                break;
            case 7: // metodo
                metodo_cria();
                semanticAnalyser.executeAction(2, previousToken);
                break;
            case 8: // mundo
                mundo();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[62], currentToken.getPosition());
        }
    }

    private void modelo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 6: // modelo
                match(6); // modelo
                semanticAnalyser.executeAction(0, previousToken);
                figura();
                Lista_de_metodos();
                match(25); // fim
                match(61); // "."
                semanticAnalyser.executeAction(27, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[63], currentToken.getPosition());
        }
    }

    private void mundo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 8: // mundo
                match(8); // mundo
                semanticAnalyser.executeAction(1, previousToken);
                nome_do_mundo();
                bloco_do_mundo();
                match(25); // fim
                match(61); // "."
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[94], currentToken.getPosition());
        }
    }

    private void figura() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // identificador
                match(2); // identificador
                semanticAnalyser.executeAction(3, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[85], currentToken.getPosition());
        }
    }

    private void Lista_de_metodos() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 7: // metodo
                metodo_cria();
                metodos_outros();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[64], currentToken.getPosition());
        }
    }

    private void bloco() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // identificador
            case 16: // repita
            case 19: // faca
            case 26: // cria
            case 29: // piscar
            case 31: // Peca1
            case 32: // Peca2
            case 33: // Peca3
            case 34: // Peca4
            case 35: // Peca5
            case 36: // Peca6
            case 37: // Peca7
            case 54: // fala
            case 55: // enquanto
            case 56: // espera
                comando();
                bloco_2();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[68], currentToken.getPosition());
        }
    }

    private void bloco_2() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // identificador
            case 16: // repita
            case 19: // faca
            case 26: // cria
            case 29: // piscar
            case 31: // Peca1
            case 32: // Peca2
            case 33: // Peca3
            case 34: // Peca4
            case 35: // Peca5
            case 36: // Peca6
            case 37: // Peca7
            case 54: // fala
            case 55: // enquanto
            case 56: // espera
                bloco();
                break;
            case 25: // fim
                // EPSILON
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[69], currentToken.getPosition());
        }
    }

    private void metodo_cria() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 7: // metodo
                match(7); // metodo
                match(9); // CRIA
                semanticAnalyser.executeAction(4, previousToken);
                semanticAnalyser.executeAction(12, previousToken);
                bloco();
                match(25); // fim
                match(57); // ";"
                semanticAnalyser.executeAction(26, previousToken);
                semanticAnalyser.executeAction(36, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[66], currentToken.getPosition());
        }
    }

    private void metodos_outros() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 7: // metodo
                metodo();
                metodos_outros();
                break;
            case 25: // fim
                // EPSILON
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[65], currentToken.getPosition());
        }
    }

    private void metodo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 7: // metodo
                match(7); // metodo
                nome_Metodo();
                semanticAnalyser.executeAction(12, previousToken);
                bloco();
                match(25); // fim
                match(57); // ";"
                semanticAnalyser.executeAction(26, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[67], currentToken.getPosition());
        }
    }

    private void nome_Metodo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // identificador
                match(2); // identificador
                semanticAnalyser.executeAction(4, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[86], currentToken.getPosition());
        }
    }

    private void comando() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // identificador
            case 31: // Peca1
            case 32: // Peca2
            case 33: // Peca3
            case 34: // Peca4
            case 35: // Peca5
            case 36: // Peca6
            case 37: // Peca7
                comandos_em_ids();
                break;
            case 16: // repita
                comando_repete();
                break;
            case 19: // faca
                comando_faca();
                break;
            case 26: // cria
                comando_cria();
                break;
            case 29: // piscar
                comando_piscar();
                semanticAnalyser.executeAction(22, previousToken);
                semanticAnalyser.executeAction(14, previousToken);
                break;
            case 54: // fala
                comando_fala();
                break;
            case 55: // enquanto
                comando_enquanto_fala();
                break;
            case 56: // espera
                comando_espera_fala();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[70], currentToken.getPosition());
        }
    }

    private void comando_cria() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 26: // cria
                match(26); // cria
                peca();
                match(58); // "("
                X();
                match(60); // ","
                Y();
                match(60); // ","
                Z();
                match(59); // ")"
                semanticAnalyser.executeAction(13, previousToken);
                semanticAnalyser.executeAction(14, previousToken);
                cria_extra();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[73], currentToken.getPosition());
        }
    }

    private void comandos_em_ids() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // identificador
            case 31: // Peca1
            case 32: // Peca2
            case 33: // Peca3
            case 34: // Peca4
            case 35: // Peca5
            case 36: // Peca6
            case 37: // Peca7
                id();
                match(61); // "."
                comando_de_id();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[71], currentToken.getPosition());
        }
    }

    private void comando_de_id() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 13: // gira
                comando_gira();
                semanticAnalyser.executeAction(14, previousToken);
                break;
            case 14: // cor
                comando_cor();
                semanticAnalyser.executeAction(14, previousToken);
                break;
            case 15: // espelha
                comando_espelha();
                semanticAnalyser.executeAction(14, previousToken);
                break;
            case 28: // move
                comando_move();
                semanticAnalyser.executeAction(14, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[72], currentToken.getPosition());
        }
    }

    private void comando_move() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 28: // move
                match(28); // move
                match(58); // "("
                X();
                match(60); // ","
                Y();
                match(60); // ","
                Z();
                match(59); // ")"
                semanticAnalyser.executeAction(17, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[77], currentToken.getPosition());
        }
    }

    private void comando_gira() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 13: // gira
                match(13); // gira
                match(58); // "("
                X();
                match(59); // ")"
                gira_extra();
                semanticAnalyser.executeAction(18, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[78], currentToken.getPosition());
        }
    }

    private void comando_piscar() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 29: // piscar
                match(29); // piscar
                match(58); // "("
                X();
                match(59); // ")"
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[82], currentToken.getPosition());
        }
    }

    private void comando_cor() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 14: // cor
                match(14); // cor
                match(58); // "("
                cor();
                match(59); // ")"
                semanticAnalyser.executeAction(20, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[80], currentToken.getPosition());
        }
    }

    private void comando_espelha() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 15: // espelha
                match(15); // espelha
                semanticAnalyser.executeAction(21, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[81], currentToken.getPosition());
        }
    }

    private void comando_repete() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 16: // repita
                match(16); // repita
                X();
                match(30); // vezes
                semanticAnalyser.executeAction(23, previousToken);
                match(24); // inicio
                bloco();
                match(25); // fim
                semanticAnalyser.executeAction(24, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[83], currentToken.getPosition());
        }
    }

    private void comando_repete_mundo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 16: // repita
                match(16); // repita
                X();
                match(30); // vezes
                semanticAnalyser.executeAction(23, previousToken);
                match(24); // inicio
                bloco_do_mundo();
                match(25); // fim
                semanticAnalyser.executeAction(24, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[99], currentToken.getPosition());
        }
    }

    private void comando_faca() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 19: // faca
                match(19); // faca
                match(2); // identificador
                semanticAnalyser.executeAction(25, previousToken);
                semanticAnalyser.executeAction(14, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[84], currentToken.getPosition());
        }
    }

    private void em_paralelo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 14: // repita
            case 17: // faca
            case 23: // fim
            case 24: // cria
            case 27: // piscar
                // EPSILON
                break;
            case 15: // em
                match(15); // em
                match(16); // paralelo
                semanticAnalyser.executeAction(35, previousToken);
                em_paralelo_extra();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[80], currentToken.getPosition());
        }
    }

    private void em_paralelo_extra() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 14: // repita
            case 17: // faca
            case 23: // fim
            case 24: // cria
            case 27: // piscar
                // EPSILON
                break;
            case 18: // depois
                match(18); // depois
                match(19); // de
                comando_piscar();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[81], currentToken.getPosition());
        }
    }

    private void cria_extra() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // identificador
            case 16: // repita
            case 19: // faca
            case 25: // fim
            case 26: // cria
            case 29: // piscar
            case 31: // Peca1
            case 32: // Peca2
            case 33: // Peca3
            case 34: // Peca4
            case 35: // Peca5
            case 36: // Peca6
            case 37: // Peca7
            case 54: // fala
            case 55: // enquanto
            case 56: // espera
                // EPSILON
                break;
            case 13: // gira
            case 14: // cor
            case 15: // espelha
                extra();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[74], currentToken.getPosition());
        }
    }

    private void peca() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 31: // Peca1
                match(31); // Peca1
                semanticAnalyser.executeAction(7, previousToken);
                break;
            case 32: // Peca2
                match(32); // Peca2
                semanticAnalyser.executeAction(7, previousToken);
                break;
            case 33: // Peca3
                match(33); // Peca3
                semanticAnalyser.executeAction(7, previousToken);
                break;
            case 34: // Peca4
                match(34); // Peca4
                semanticAnalyser.executeAction(7, previousToken);
                break;
            case 35: // Peca5
                match(35); // Peca5
                semanticAnalyser.executeAction(7, previousToken);
                break;
            case 36: // Peca6
                match(36); // Peca6
                semanticAnalyser.executeAction(7, previousToken);
                break;
            case 37: // Peca7
                match(37); // Peca7
                semanticAnalyser.executeAction(7, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[89], currentToken.getPosition());
        }
    }

    private void extra() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 13: // gira
                comando_gira();
                semanticAnalyser.executeAction(14, previousToken);
                cria_extra();
                break;
            case 14: // cor
                comando_cor();
                semanticAnalyser.executeAction(14, previousToken);
                cria_extra();
                break;
            case 15: // espelha
                comando_espelha();
                semanticAnalyser.executeAction(14, previousToken);
                cria_extra();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[75], currentToken.getPosition());
        }
    }

    private void cor() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 38: // amarelo
                match(38); // amarelo
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 39: // azul
                match(39); // azul
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 40: // azulMarinho
                match(40); // azulMarinho
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 41: // azulPiscina
                match(41); // azulPiscina
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 42: // branco
                match(42); // branco
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 43: // cinza
                match(43); // cinza
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 44: // marrom
                match(44); // marrom
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 45: // oliva
                match(45); // oliva
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 46: // prata
                match(46); // prata
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 47: // preto
                match(47); // preto
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 48: // rosa
                match(48); // rosa
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 49: // verde
                match(49); // verde
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 50: // verdePiscina
                match(50); // verdePiscina
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 51: // verdeLima
                match(51); // verdeLima
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 52: // vermelho
                match(52); // vermelho
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 53: // violeta
                match(53); // violeta
                semanticAnalyser.executeAction(8, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[90], currentToken.getPosition());
        }
    }

    private void id() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // identificador
                figura();
                semanticAnalyser.executeAction(15, previousToken);
                break;
            case 31: // Peca1
            case 32: // Peca2
            case 33: // Peca3
            case 34: // Peca4
            case 35: // Peca5
            case 36: // Peca6
            case 37: // Peca7
                peca();
                semanticAnalyser.executeAction(16, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[76], currentToken.getPosition());
        }
    }

    private void X() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 4: // numero
                match(4); // numero
                semanticAnalyser.executeAction(9, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[91], currentToken.getPosition());
        }
    }

    private void Y() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 4: // numero
                match(4); // numero
                semanticAnalyser.executeAction(10, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[92], currentToken.getPosition());
        }
    }

    private void Z() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 4: // numero
                match(4); // numero
                semanticAnalyser.executeAction(11, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[93], currentToken.getPosition());
        }
    }

    private void gira_extra() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // identificador
            case 13: // gira
            case 14: // cor
            case 15: // espelha
            case 16: // repita
            case 19: // faca
            case 25: // fim
            case 26: // cria
            case 29: // piscar
            case 31: // Peca1
            case 32: // Peca2
            case 33: // Peca3
            case 34: // Peca4
            case 35: // Peca5
            case 36: // Peca6
            case 37: // Peca7
            case 54: // fala
            case 55: // enquanto
            case 56: // espera
                // EPSILON
                break;
            case 22: // no
                match(22); // no
                match(23); // ponto
                match(58); // "("
                Y();
                match(59); // ")"
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[79], currentToken.getPosition());
        }
    }

    private void nome_do_mundo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // identificador
                match(2); // identificador
                semanticAnalyser.executeAction(6, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[88], currentToken.getPosition());
        }
    }

    private void bloco_do_mundo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 16: // repita
            case 19: // faca
            case 26: // cria
            case 29: // piscar
                comando_mundo();
                bloco_do_mundo_2();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[95], currentToken.getPosition());
        }
    }

    private void bloco_do_mundo_2() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 16: // repita
            case 19: // faca
            case 26: // cria
            case 29: // piscar
                bloco_do_mundo();
                break;
            case 25: // fim
                // EPSILON
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[96], currentToken.getPosition());
        }
    }

    private void comando_mundo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 16: // repita
                comando_repete_mundo();
                break;
            case 19: // faca
                semanticAnalyser.executeAction(33, previousToken);
                comando_faca_mundo();
                semanticAnalyser.executeAction(14, previousToken);
                break;
            case 26: // cria
                comando_cria_como();
                break;
            case 29: // piscar
                comando_piscar();
                semanticAnalyser.executeAction(22, previousToken);
                semanticAnalyser.executeAction(14, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[97], currentToken.getPosition());
        }
    }

    private void comando_cria_como() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 26: // cria
                match(26); // cria
                modelo_id();
                match(27); // como
                figura();
                semanticAnalyser.executeAction(28, previousToken);
                semanticAnalyser.executeAction(14, previousToken);
                match(58); // "("
                X();
                match(60); // ","
                Y();
                match(60); // ","
                Z();
                match(59); // ")"
                semanticAnalyser.executeAction(29, previousToken);
                semanticAnalyser.executeAction(14, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[98], currentToken.getPosition());
        }
    }

    private void modelo_id() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // identificador
                match(2); // identificador
                semanticAnalyser.executeAction(5, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[87], currentToken.getPosition());
        }
    }

    private void comando_faca_mundo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 19: // faca
                match(19); // faca
                modelo_id();
                match(61); // "."
                metodo_do_id();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[100], currentToken.getPosition());
        }
    }

    private void metodo_do_id() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // identificador
                nome_Metodo();
                em_paralelo();
                semanticAnalyser.executeAction(34, previousToken);
                break;
            case 10: // VIVA
                comando_viva();
                semanticAnalyser.executeAction(30, previousToken);
                break;
            case 11: // APAGA
                match(11); // APAGA
                semanticAnalyser.executeAction(32, previousToken);
                break;
            case 12: // TERMINA
                match(12); // TERMINA
                semanticAnalyser.executeAction(31, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[101], currentToken.getPosition());
        }
    }

    private void comando_viva() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 10: // VIVA
                match(10); // VIVA
                match(58); // "("
                nome_Metodo();
                match(59); // ")"
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[102], currentToken.getPosition());
        }
    }
    private void comando_fala() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 54: // fala
                match(54); // fala
                match(58); // "("
                match(3); // jsml
                semanticAnalyser.executeAction(37, previousToken);
                comando_fala_sobreposto();
                match(59); // ")"
                semanticAnalyser.executeAction(39, previousToken);
                semanticAnalyser.executeAction(14, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[104], currentToken.getPosition());
        }
    }

    private void comando_fala_sobreposto() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 5: // sobreposto
                match(5); // sobreposto
                semanticAnalyser.executeAction(38, previousToken);
                break;
            case 59: // ")"
                // EPSILON
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[105], currentToken.getPosition());
        }
    }

    private void comando_enquanto_fala() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 55: // enquanto
                match(55); // enquanto
                match(54); // fala
                semanticAnalyser.executeAction(40, previousToken);
                match(24); // inicio
                bloco();
                match(25); // fim
                semanticAnalyser.executeAction(41, previousToken);
                semanticAnalyser.executeAction(14, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[106], currentToken.getPosition());
        }
    }
    
        private void comando_espera_fala() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 56: // espera
                match(56); // espera
                match(54); // fala
                semanticAnalyser.executeAction(42, previousToken);
                semanticAnalyser.executeAction(14, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[107], currentToken.getPosition());
        }
    }
}
