modelo Moinho
metodo CRIA
cria p1(-613,1165,-7000) gira(90.0) cor(prata)
cria p2(382,686,-7000) gira(180.0) cor(prata)
cria p3(-602,-270,-7000) gira(270.0) cor(marrom)
cria p4(-341,64,-7000) gira(270.0) cor(vermelho)
cria p5(-229,182,-7000) gira(270.0) cor(branco)
cria p6(-271,-248,-7000) gira(45.0) cor(marrom)
cria p7(-239,566,-7000) gira(90.0) cor(branco)
fim;
metodo anima_fala
fala('C:/Documents and Settings/wendel/workspace/soundwave-mbrola-test/resources/jsml_parallel_1.xml')
enquanto fala inicio
    p1.gira(-1) no ponto(9)
    p2.gira(-1) no ponto(12)
    pisca(10)
fim
fim;
metodo alterna_fala
fala('C:/Documents and Settings/wendel/workspace/soundwave-mbrola-test/resources/jsml_parallel_1.xml')
espera fala
repita 45 vezes inicio
    p1.gira(-1) no ponto(9)
    p2.gira(-1) no ponto(12)
    pisca(10)
fim
fim;
metodo vai
repita 45 vezes inicio
    p1.gira(-1) no ponto(9)
    p2.gira(-1) no ponto(12)
    pisca(10)
fim
fim;
fim.