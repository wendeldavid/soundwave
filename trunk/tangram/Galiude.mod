modelo Galiude
metodo CRIA
cria p1(-62,-150,-7000) gira(270.0) espelha
cria p2(-709,353,-7000) espelha
cria p3(790,980,-7000) gira(45.0) espelha cor(vermelho)
cria p4(285,-414,-7000) espelha cor(marrom)
cria p5(-394,-395,-7000) gira(90.0) espelha cor(marrom)
cria p6(588,644,-7000) gira(45.0) espelha cor(azul)
cria p7(415,279,-7000) gira(45.0)
fim;
metodo bicaChao
p7.gira(45)
p7.gira(45)
p7.move(-190, -165, 0)
p6.move(340, -355, 0)
p3.gira(45)
p3.gira(45)
p3.move(-140, -1200, 0)
fim;
metodo Vira
Galiude.espelha
fim;
metodo teste
repita 5 vezes inicio
  repita 5 vezes inicio
    Galiude.move(-100, 0, 0)
  fim
  Galiude.move(90, 0, 0)
fim
fim;
metodo LevantaBico
p6.move(-340, 355, 0)
p7.gira(45)
p7.gira(45)
p7.move(190, 165, 0)
p3.gira(45)
p3.gira(45)
p3.gira(45)
p3.gira(45)
p3.gira(45)
p3.gira(45)
p3.move(140, 1200, 0)
fim;
metodo Direita
repita 2 vezes inicio
  repita 10 vezes inicio
    Galiude.move(30, 0, 0)
    pisca(4)
  fim
  faça MexePe11
  repita 10 vezes inicio
    Galiude.move(30, 0, 0)
    pisca(4)
  fim
  faça MexePe21
  repita 10 vezes inicio
    Galiude.move(30, 0, 0)
    pisca(4)
  fim
  faça MexePe12
  repita 10 vezes inicio
    Galiude.move(30, 0, 0)
    pisca(4)
  fim
  faça MexePe22
fim
fim;
metodo MexePe11
p4.gira(45) no ponto (19)
fim;
metodo MexePe12
p4.gira(-45) no ponto (19)
fim;
metodo MexePe21
p5.gira(-45) no ponto (22)
fim;
metodo MexePe22
p5.gira(45) no ponto (22)
fim;
metodo Esquerda
repita 2 vezes inicio
  repita 10 vezes inicio
    Galiude.move(-30, 0, 0)
    pisca(4)
  fim
  faça MexePe22
  repita 10 vezes inicio
    Galiude.move(-30, 0,0)
    pisca(4)
  fim
  faça MexePe12
  repita 10 vezes inicio
    Galiude.move(-30, 0, 0)
    pisca(4)
  fim
  faça MexePe21
  repita 10 vezes inicio
    Galiude.move(-30, 0, 0)
    pisca(4)
  fim
  faça MexePe11
fim
fim;
metodo VaiEVolta
repita 10 vezes inicio
    faça Direita
    faça Vira
    pisca(25)
    faça Esquerda
    repita 5 vezes inicio
        faça bicaChao
        pisca(100)
        faça LevantaBico
        pisca(100)
    fim
    faça Esquerda
    faça Vira
    pisca(25)
    faça Direita
fim
fim;
fim.