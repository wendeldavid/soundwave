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
            case 5: // modelo
                modelo();
                break;
            case 6: // metodo
                metodo_cria();
                semanticAnalyser.executeAction(2, previousToken);
                break;
            case 7: // mundo
                mundo();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[60], currentToken.getPosition());
        }
    }

    private void modelo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 5: // modelo
                match(5); // modelo
                semanticAnalyser.executeAction(0, previousToken);
                figura();
                Lista_de_metodos();
                match(24); // fim
                match(59); // "."
                semanticAnalyser.executeAction(27, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[61], currentToken.getPosition());
        }
    }

    private void Lista_de_metodos() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 6: // metodo
                metodo_cria();
                metodos_outros();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[62], currentToken.getPosition());
        }
    }

    private void metodos_outros() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 6: // metodo
                metodo();
                metodos_outros();
                break;
            case 24: // fim
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
            case 6: // metodo
                match(6); // metodo
                match(8); // CRIA
                semanticAnalyser.executeAction(4, previousToken);
                semanticAnalyser.executeAction(12, previousToken);
                bloco();
                match(24); // fim
                match(55); // ";"
                semanticAnalyser.executeAction(26, previousToken);
                semanticAnalyser.executeAction(36, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[64], currentToken.getPosition());
        }
    }

    private void metodo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 6: // metodo
                match(6); // metodo
                nome_Metodo();
                semanticAnalyser.executeAction(12, previousToken);
                bloco();
                match(24); // fim
                match(55); // ";"
                semanticAnalyser.executeAction(26, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[65], currentToken.getPosition());
        }
    }

    private void bloco() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // identificador
            case 15: // repita
            case 18: // faca
            case 25: // cria
            case 28: // piscar
            case 30: // Peca1
            case 31: // Peca2
            case 32: // Peca3
            case 33: // Peca4
            case 34: // Peca5
            case 35: // Peca6
            case 36: // Peca7
            case 53: // fala
            case 54: // enquanto
                comando();
                bloco_2();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[66], currentToken.getPosition());
        }
    }

    private void bloco_2() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // identificador
            case 15: // repita
            case 18: // faca
            case 25: // cria
            case 28: // piscar
            case 30: // Peca1
            case 31: // Peca2
            case 32: // Peca3
            case 33: // Peca4
            case 34: // Peca5
            case 35: // Peca6
            case 36: // Peca7
            case 53: // fala
            case 54: // enquanto
                bloco();
                break;
            case 24: // fim
                // EPSILON
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
            case 30: // Peca1
            case 31: // Peca2
            case 32: // Peca3
            case 33: // Peca4
            case 34: // Peca5
            case 35: // Peca6
            case 36: // Peca7
                comandos_em_ids();
                break;
            case 15: // repita
                comando_repete();
                break;
            case 18: // faca
                comando_faca();
                break;
            case 25: // cria
                comando_cria();
                break;
            case 28: // piscar
                comando_piscar();
                semanticAnalyser.executeAction(22, previousToken);
                semanticAnalyser.executeAction(14, previousToken);
                break;
            case 53: // fala
                comando_fala();
                break;
            case 54: // enquanto
                comando_enquanto_fala();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[68], currentToken.getPosition());
        }
    }

    private void comandos_em_ids() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // identificador
            case 30: // Peca1
            case 31: // Peca2
            case 32: // Peca3
            case 33: // Peca4
            case 34: // Peca5
            case 35: // Peca6
            case 36: // Peca7
                id();
                match(59); // "."
                comando_de_id();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[69], currentToken.getPosition());
        }
    }

    private void comando_de_id() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 12: // gira
                comando_gira();
                semanticAnalyser.executeAction(14, previousToken);
                break;
            case 13: // cor
                comando_cor();
                semanticAnalyser.executeAction(14, previousToken);
                break;
            case 14: // espelha
                comando_espelha();
                semanticAnalyser.executeAction(14, previousToken);
                break;
            case 27: // move
                comando_move();
                semanticAnalyser.executeAction(14, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[70], currentToken.getPosition());
        }
    }

    private void comando_cria() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 25: // cria
                match(25); // cria
                peca();
                match(56); // "("
                X();
                match(58); // ","
                Y();
                match(58); // ","
                Z();
                match(57); // ")"
                semanticAnalyser.executeAction(13, previousToken);
                semanticAnalyser.executeAction(14, previousToken);
                cria_extra();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[71], currentToken.getPosition());
        }
    }

    private void cria_extra() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // identificador
            case 15: // repita
            case 18: // faca
            case 24: // fim
            case 25: // cria
            case 28: // piscar
            case 30: // Peca1
            case 31: // Peca2
            case 32: // Peca3
            case 33: // Peca4
            case 34: // Peca5
            case 35: // Peca6
            case 36: // Peca7
            case 53: // fala
            case 54: // enquanto
                // EPSILON
                break;
            case 12: // gira
            case 13: // cor
            case 14: // espelha
                extra();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[72], currentToken.getPosition());
        }
    }

    private void extra() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 12: // gira
                comando_gira();
                semanticAnalyser.executeAction(14, previousToken);
                cria_extra();
                break;
            case 13: // cor
                comando_cor();
                semanticAnalyser.executeAction(14, previousToken);
                cria_extra();
                break;
            case 14: // espelha
                comando_espelha();
                semanticAnalyser.executeAction(14, previousToken);
                cria_extra();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[73], currentToken.getPosition());
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
            case 30: // Peca1
            case 31: // Peca2
            case 32: // Peca3
            case 33: // Peca4
            case 34: // Peca5
            case 35: // Peca6
            case 36: // Peca7
                peca();
                semanticAnalyser.executeAction(16, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[74], currentToken.getPosition());
        }
    }

    private void comando_move() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 27: // move
                match(27); // move
                match(56); // "("
                X();
                match(58); // ","
                Y();
                match(58); // ","
                Z();
                match(57); // ")"
                semanticAnalyser.executeAction(17, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[75], currentToken.getPosition());
        }
    }

    private void comando_gira() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 12: // gira
                match(12); // gira
                match(56); // "("
                X();
                match(57); // ")"
                gira_extra();
                semanticAnalyser.executeAction(18, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[76], currentToken.getPosition());
        }
    }

    private void gira_extra() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 2: // identificador
            case 12: // gira
            case 13: // cor
            case 14: // espelha
            case 15: // repita
            case 18: // faca
            case 24: // fim
            case 25: // cria
            case 28: // piscar
            case 30: // Peca1
            case 31: // Peca2
            case 32: // Peca3
            case 33: // Peca4
            case 34: // Peca5
            case 35: // Peca6
            case 36: // Peca7
            case 53: // fala
            case 54: // enquanto
                // EPSILON
                break;
            case 21: // no
                match(21); // no
                match(22); // ponto
                match(56); // "("
                Y();
                match(57); // ")"
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[77], currentToken.getPosition());
        }
    }

    private void comando_cor() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 13: // cor
                match(13); // cor
                match(56); // "("
                cor();
                match(57); // ")"
                semanticAnalyser.executeAction(20, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[78], currentToken.getPosition());
        }
    }

    private void comando_espelha() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 14: // espelha
                match(14); // espelha
                semanticAnalyser.executeAction(21, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[79], currentToken.getPosition());
        }
    }

    private void comando_piscar() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 28: // piscar
                match(28); // piscar
                match(56); // "("
                X();
                match(57); // ")"
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[80], currentToken.getPosition());
        }
    }

    private void comando_repete() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 15: // repita
                match(15); // repita
                X();
                match(29); // vezes
                semanticAnalyser.executeAction(23, previousToken);
                match(23); // inicio
                bloco();
                match(24); // fim
                semanticAnalyser.executeAction(24, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[81], currentToken.getPosition());
        }
    }

    private void comando_faca() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 18: // faca
                match(18); // faca
                match(2); // identificador
                semanticAnalyser.executeAction(25, previousToken);
                semanticAnalyser.executeAction(14, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[82], currentToken.getPosition());
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
                throw new SyntaticError(PARSER_ERROR[83], currentToken.getPosition());
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
                throw new SyntaticError(PARSER_ERROR[84], currentToken.getPosition());
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
                throw new SyntaticError(PARSER_ERROR[85], currentToken.getPosition());
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
                throw new SyntaticError(PARSER_ERROR[86], currentToken.getPosition());
        }
    }

    private void peca() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 30: // Peca1
                match(30); // Peca1
                semanticAnalyser.executeAction(7, previousToken);
                break;
            case 31: // Peca2
                match(31); // Peca2
                semanticAnalyser.executeAction(7, previousToken);
                break;
            case 32: // Peca3
                match(32); // Peca3
                semanticAnalyser.executeAction(7, previousToken);
                break;
            case 33: // Peca4
                match(33); // Peca4
                semanticAnalyser.executeAction(7, previousToken);
                break;
            case 34: // Peca5
                match(34); // Peca5
                semanticAnalyser.executeAction(7, previousToken);
                break;
            case 35: // Peca6
                match(35); // Peca6
                semanticAnalyser.executeAction(7, previousToken);
                break;
            case 36: // Peca7
                match(36); // Peca7
                semanticAnalyser.executeAction(7, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[87], currentToken.getPosition());
        }
    }

    private void cor() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 37: // amarelo
                match(37); // amarelo
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 38: // azul
                match(38); // azul
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 39: // azulMarinho
                match(39); // azulMarinho
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 40: // azulPiscina
                match(40); // azulPiscina
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 41: // branco
                match(41); // branco
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 42: // cinza
                match(42); // cinza
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 43: // marrom
                match(43); // marrom
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 44: // oliva
                match(44); // oliva
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 45: // prata
                match(45); // prata
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 46: // preto
                match(46); // preto
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 47: // rosa
                match(47); // rosa
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 48: // verde
                match(48); // verde
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 49: // verdePiscina
                match(49); // verdePiscina
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 50: // verdeLima
                match(50); // verdeLima
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 51: // vermelho
                match(51); // vermelho
                semanticAnalyser.executeAction(8, previousToken);
                break;
            case 52: // violeta
                match(52); // violeta
                semanticAnalyser.executeAction(8, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[88], currentToken.getPosition());
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
                throw new SyntaticError(PARSER_ERROR[89], currentToken.getPosition());
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
                throw new SyntaticError(PARSER_ERROR[90], currentToken.getPosition());
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
                throw new SyntaticError(PARSER_ERROR[91], currentToken.getPosition());
        }
    }

    private void mundo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 7: // mundo
                match(7); // mundo
                semanticAnalyser.executeAction(1, previousToken);
                nome_do_mundo();
                bloco_do_mundo();
                match(24); // fim
                match(59); // "."
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[92], currentToken.getPosition());
        }
    }

    private void bloco_do_mundo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 15: // repita
            case 18: // faca
            case 25: // cria
            case 28: // piscar
                comando_mundo();
                bloco_do_mundo_2();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[93], currentToken.getPosition());
        }
    }

    private void bloco_do_mundo_2() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 15: // repita
            case 18: // faca
            case 25: // cria
            case 28: // piscar
                bloco_do_mundo();
                break;
            case 24: // fim
                // EPSILON
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[94], currentToken.getPosition());
        }
    }

    private void comando_mundo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 15: // repita
                comando_repete_mundo();
                break;
            case 18: // faca
                semanticAnalyser.executeAction(33, previousToken);
                comando_faca_mundo();
                semanticAnalyser.executeAction(14, previousToken);
                break;
            case 25: // cria
                comando_cria_como();
                break;
            case 28: // piscar
                comando_piscar();
                semanticAnalyser.executeAction(22, previousToken);
                semanticAnalyser.executeAction(14, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[95], currentToken.getPosition());
        }
    }

    private void comando_cria_como() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 25: // cria
                match(25); // cria
                modelo_id();
                match(26); // como
                figura();
                semanticAnalyser.executeAction(28, previousToken);
                semanticAnalyser.executeAction(14, previousToken);
                match(56); // "("
                X();
                match(58); // ","
                Y();
                match(58); // ","
                Z();
                match(57); // ")"
                semanticAnalyser.executeAction(29, previousToken);
                semanticAnalyser.executeAction(14, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[96], currentToken.getPosition());
        }
    }

    private void comando_repete_mundo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 15: // repita
                match(15); // repita
                X();
                match(29); // vezes
                semanticAnalyser.executeAction(23, previousToken);
                match(23); // inicio
                bloco_do_mundo();
                match(24); // fim
                semanticAnalyser.executeAction(24, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[97], currentToken.getPosition());
        }
    }

    private void comando_faca_mundo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 18: // faca
                match(18); // faca
                modelo_id();
                match(59); // "."
                metodo_do_id();
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[98], currentToken.getPosition());
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
            case 9: // VIVA
                comando_viva();
                semanticAnalyser.executeAction(30, previousToken);
                break;
            case 10: // APAGA
                match(10); // APAGA
                semanticAnalyser.executeAction(32, previousToken);
                break;
            case 11: // TERMINA
                match(11); // TERMINA
                semanticAnalyser.executeAction(31, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[99], currentToken.getPosition());
        }
    }

    private void comando_viva() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 9: // VIVA
                match(9); // VIVA
                match(56); // "("
                nome_Metodo();
                match(57); // ")"
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[100], currentToken.getPosition());
        }
    }

    private void em_paralelo() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 16: // em
                match(16); // em
                match(17); // paralelo
                semanticAnalyser.executeAction(35, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[101], currentToken.getPosition());
        }
    }

    private void comando_fala() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 53: // fala
                match(53); // fala
                match(56); // "("
                match(3); // url
                match(57); // ")"
                semanticAnalyser.executeAction(36, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[102], currentToken.getPosition());
        }
    }

    private void comando_enquanto_fala() throws AnalysisError
    {
        switch (currentToken.getId())
        {
            case 54: // enquanto
                match(54); // enquanto
                comando_fala();
                semanticAnalyser.executeAction(37, previousToken);
                match(23); // inicio
                bloco();
                match(24); // fim
                semanticAnalyser.executeAction(38, previousToken);
                break;
            default:
                throw new SyntaticError(PARSER_ERROR[103], currentToken.getPosition());
        }
    }

}
