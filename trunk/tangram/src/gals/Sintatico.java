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
            case 4: // modelo
                modelo();
                break;
            case 5: // metodo
                metodo_cria();
                semanticAnalyser.executeAction(2, previousToken);
                break;
            case 6: // mundo
                mundo();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[57], currentToken.getPosition());
        }
    }

    private void modelo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 4: // modelo
                match(4); // modelo
                semanticAnalyser.executeAction(0, previousToken);
                figura();
                Lista_de_metodos();
                match(23); // fim
                match(56); // "."
                semanticAnalyser.executeAction(27, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[58], currentToken.getPosition());
        }
    }

    private void mundo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 6: // mundo
                match(6); // mundo
                semanticAnalyser.executeAction(1, previousToken);
                nome_do_mundo();
                bloco_do_mundo();
                match(23); // fim
                match(56); // "."
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[59], currentToken.getPosition());
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
                throw new SyntaticError(PARSER_ERROR[60], currentToken.getPosition());
        }
    }

    private void Lista_de_metodos() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 5: // metodo
                metodo_cria();
                metodos_outros();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[61], currentToken.getPosition());
        }
    }

    private void bloco() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // identificador
            case 14: // repita
            case 17: // faca
            case 24: // cria
            case 27: // piscar
            case 29: // Peca1
            case 30: // Peca2
            case 31: // Peca3
            case 32: // Peca4
            case 33: // Peca5
            case 34: // Peca6
            case 35: // Peca7
                comando();
                bloco_2();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[62], currentToken.getPosition());
        }
    }

    private void bloco_2() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // identificador
            case 14: // repita
            case 17: // faca
            case 24: // cria
            case 27: // piscar
            case 29: // Peca1
            case 30: // Peca2
            case 31: // Peca3
            case 32: // Peca4
            case 33: // Peca5
            case 34: // Peca6
            case 35: // Peca7
                bloco();
                break;
            case 23: // fim
                // EPSILON
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[63], currentToken.getPosition());
        }
    }

    private void metodo_cria() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 5: // metodo
                match(5); // metodo
                match(7); // CRIA
                semanticAnalyser.executeAction(4, previousToken);
                semanticAnalyser.executeAction(12, previousToken);
                bloco();
                match(23); // fim
                match(52); // ";"
                semanticAnalyser.executeAction(26, previousToken);
                semanticAnalyser.executeAction(36, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[64], currentToken.getPosition());
        }
    }

    private void metodos_outros() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 5: // metodo
                metodo();
                metodos_outros();
                break;
            case 23: // fim
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
            case 5: // metodo
                match(5); // metodo
                nome_Metodo();
                semanticAnalyser.executeAction(12, previousToken);
                bloco();
                match(23); // fim
                match(52); // ";"
                semanticAnalyser.executeAction(26, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[66], currentToken.getPosition());
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
                throw new SyntaticError(PARSER_ERROR[67], currentToken.getPosition());
        }
    }

    private void comando() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // identificador
            case 29: // Peca1
            case 30: // Peca2
            case 31: // Peca3
            case 32: // Peca4
            case 33: // Peca5
            case 34: // Peca6
            case 35: // Peca7
                comandos_em_ids();
                break;
            case 14: // repita
                comando_repete();
                break;
            case 17: // faca
                comando_faca();
                break;
            case 24: // cria
                comando_cria();
                break;
            case 27: // piscar
                comando_piscar();
                semanticAnalyser.executeAction(22, previousToken);
                semanticAnalyser.executeAction(14, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[68], currentToken.getPosition());
        }
    }

    private void comando_cria() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 24: // cria
                match(24); // cria
                peca();
                match(53); // "("
                X();
                match(55); // ","
                Y();
                match(55); // ","
                Z();
                match(54); // ")"
                semanticAnalyser.executeAction(13, previousToken);
                semanticAnalyser.executeAction(14, previousToken);
                cria_extra();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[69], currentToken.getPosition());
        }
    }

    private void comandos_em_ids() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // identificador
            case 29: // Peca1
            case 30: // Peca2
            case 31: // Peca3
            case 32: // Peca4
            case 33: // Peca5
            case 34: // Peca6
            case 35: // Peca7
                id();
                match(56); // "."
                comando_de_id();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[70], currentToken.getPosition());
        }
    }

    private void comando_de_id() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 11: // gira
                comando_gira();
                semanticAnalyser.executeAction(14, previousToken);
                break;
            case 12: // cor
                comando_cor();
                semanticAnalyser.executeAction(14, previousToken);
                break;
            case 13: // espelha
                comando_espelha();
                semanticAnalyser.executeAction(14, previousToken);
                break;
            case 26: // move
                comando_move();
                semanticAnalyser.executeAction(14, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[71], currentToken.getPosition());
        }
    }

    private void comando_move() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 26: // move
                match(26); // move
                match(53); // "("
                X();
                match(55); // ","
                Y();
                match(55); // ","
                Z();
                match(54); // ")"
                semanticAnalyser.executeAction(17, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[72], currentToken.getPosition());
        }
    }

    private void comando_gira() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 11: // gira
                match(11); // gira
                match(53); // "("
                X();
                match(54); // ")"
                gira_extra();
                semanticAnalyser.executeAction(18, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[73], currentToken.getPosition());
        }
    }

    private void comando_piscar() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 27: // piscar
                match(27); // piscar
                match(53); // "("
                X();
                match(54); // ")"
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[74], currentToken.getPosition());
        }
    }

    private void comando_cor() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 12: // cor
                match(12); // cor
                match(53); // "("
                cor();
                match(54); // ")"
                semanticAnalyser.executeAction(20, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[75], currentToken.getPosition());
        }
    }

    private void comando_espelha() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 13: // espelha
                match(13); // espelha
                semanticAnalyser.executeAction(21, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[76], currentToken.getPosition());
        }
    }

    private void comando_repete() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 14: // repita
                match(14); // repita
                X();
                match(28); // vezes
                semanticAnalyser.executeAction(23, previousToken);
                match(22); // inicio
                bloco();
                match(23); // fim
                semanticAnalyser.executeAction(24, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[77], currentToken.getPosition());
        }
    }

    private void comando_repete_mundo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 14: // repita
                match(14); // repita
                X();
                match(28); // vezes
                semanticAnalyser.executeAction(23, previousToken);
                match(22); // inicio
                bloco_do_mundo();
                match(23); // fim
                semanticAnalyser.executeAction(24, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[78], currentToken.getPosition());
        }
    }

    private void comando_faca() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 17: // faca
                match(17); // faca
                match(2); // identificador
                semanticAnalyser.executeAction(25, previousToken);
                semanticAnalyser.executeAction(14, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[79], currentToken.getPosition());
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
            case 14: // repita
            case 17: // faca
            case 23: // fim
            case 24: // cria
            case 27: // piscar
            case 29: // Peca1
            case 30: // Peca2
            case 31: // Peca3
            case 32: // Peca4
            case 33: // Peca5
            case 34: // Peca6
            case 35: // Peca7
                // EPSILON
                break;
            case 11: // gira
            case 12: // cor
            case 13: // espelha
                extra();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[82], currentToken.getPosition());
        }
    }

    private void peca() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 29: // Peca1
                match(29); // Peca1
                semanticAnalyser.executeAction(7, previousToken);
                break;
            case 30: // Peca2
                match(30); // Peca2
                semanticAnalyser.executeAction(7, previousToken);
                break;
            case 31: // Peca3
                match(31); // Peca3
                semanticAnalyser.executeAction(7, previousToken);
                break;
            case 32: // Peca4
                match(32); // Peca4
                semanticAnalyser.executeAction(7, previousToken);
                break;
            case 33: // Peca5
                match(33); // Peca5
                semanticAnalyser.executeAction(7, previousToken);
                break;
            case 34: // Peca6
                match(34); // Peca6
                semanticAnalyser.executeAction(7, previousToken);
                break;
            case 35: // Peca7
                match(35); // Peca7
                semanticAnalyser.executeAction(7, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[83], currentToken.getPosition());
        }
    }

    private void extra() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 11: // gira
                comando_gira();
                semanticAnalyser.executeAction(14, previousToken);
                cria_extra();
                break;
            case 12: // cor
                comando_cor();
                semanticAnalyser.executeAction(14, previousToken);
                cria_extra();
                break;
            case 13: // espelha
                comando_espelha();
                semanticAnalyser.executeAction(14, previousToken);
                cria_extra();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[84], currentToken.getPosition());
        }
    }

    private void cor() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 36: // amarelo
                match(36); // amarelo
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 37: // azul
                match(37); // azul
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 38: // azulMarinho
                match(38); // azulMarinho
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 39: // azulPiscina
                match(39); // azulPiscina
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 40: // branco
                match(40); // branco
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 41: // cinza
                match(41); // cinza
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 42: // marrom
                match(42); // marrom
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 43: // oliva
                match(43); // oliva
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 44: // prata
                match(44); // prata
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 45: // preto
                match(45); // preto
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 46: // rosa
                match(46); // rosa
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 47: // verde
                match(47); // verde
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 48: // verdePiscina
                match(48); // verdePiscina
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 49: // verdeLima
                match(49); // verdeLima
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 50: // vermelho
                match(50); // vermelho
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 51: // violeta
                match(51); // violeta
                semanticAnalyser.executeAction(8, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[85], currentToken.getPosition());
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
            case 29: // Peca1
            case 30: // Peca2
            case 31: // Peca3
            case 32: // Peca4
            case 33: // Peca5
            case 34: // Peca6
            case 35: // Peca7
                peca();
                semanticAnalyser.executeAction(16, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[86], currentToken.getPosition());
        }
    }

    private void X() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 3: // numero
                match(3); // numero
                semanticAnalyser.executeAction(9, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[87], currentToken.getPosition());
        }
    }

    private void Y() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 3: // numero
                match(3); // numero
                semanticAnalyser.executeAction(10, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[88], currentToken.getPosition());
        }
    }

    private void Z() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 3: // numero
                match(3); // numero
                semanticAnalyser.executeAction(11, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[89], currentToken.getPosition());
        }
    }

    private void gira_extra() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // identificador
            case 11: // gira
            case 12: // cor
            case 13: // espelha
            case 14: // repita
            case 17: // faca
            case 23: // fim
            case 24: // cria
            case 27: // piscar
            case 29: // Peca1
            case 30: // Peca2
            case 31: // Peca3
            case 32: // Peca4
            case 33: // Peca5
            case 34: // Peca6
            case 35: // Peca7
                // EPSILON
                break;
            case 20: // no
                match(20); // no
                match(21); // ponto
                match(53); // "("
                Y();
                match(54); // ")"
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[90], currentToken.getPosition());
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
                throw new SyntaticError(PARSER_ERROR[91], currentToken.getPosition());
        }
    }

    private void bloco_do_mundo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 14: // repita
            case 17: // faca
            case 24: // cria
            case 27: // piscar
                comando_mundo();
                bloco_do_mundo_2();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[92], currentToken.getPosition());
        }
    }

    private void bloco_do_mundo_2() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 14: // repita
            case 17: // faca
            case 24: // cria
            case 27: // piscar
                bloco_do_mundo();
                break;
            case 23: // fim
                // EPSILON
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[93], currentToken.getPosition());
        }
    }

    private void comando_mundo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 14: // repita
                comando_repete_mundo();
                break;
            case 17: // faca
                semanticAnalyser.executeAction(33, previousToken);
                comando_faca_mundo();
                semanticAnalyser.executeAction(14, previousToken);
                break;
            case 24: // cria
                comando_cria_como();
                break;
            case 27: // piscar
                comando_piscar();
                semanticAnalyser.executeAction(22, previousToken);
                semanticAnalyser.executeAction(14, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[94], currentToken.getPosition());
        }
    }

    private void comando_cria_como() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 24: // cria
                match(24); // cria
                modelo_id();
                match(25); // como
                figura();
                semanticAnalyser.executeAction(28, previousToken);
                semanticAnalyser.executeAction(14, previousToken);
                match(53); // "("
                X();
                match(55); // ","
                Y();
                match(55); // ","
                Z();
                match(54); // ")"
                semanticAnalyser.executeAction(29, previousToken);
                semanticAnalyser.executeAction(14, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[95], currentToken.getPosition());
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
                throw new SyntaticError(PARSER_ERROR[96], currentToken.getPosition());
        }
    }

    private void comando_faca_mundo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 17: // faca
                match(17); // faca
                modelo_id();
                match(56); // "."
                metodo_do_id();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[97], currentToken.getPosition());
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
            case 8: // VIVA
                comando_viva();
                semanticAnalyser.executeAction(30, previousToken);
                break;
            case 9: // APAGA
                match(9); // APAGA
                semanticAnalyser.executeAction(32, previousToken);
                break;
            case 10: // TERMINA
                match(10); // TERMINA
                semanticAnalyser.executeAction(31, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[98], currentToken.getPosition());
        }
    }

    private void comando_viva() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 8: // VIVA
                match(8); // VIVA
                match(53); // "("
                nome_Metodo();
                match(54); // ")"
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[99], currentToken.getPosition());
        }
    }

}
