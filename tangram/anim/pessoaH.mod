modelo pessoaH
metodo CRIA
cria p1(-902,38,-7000) gira(270.0) espelha cor(marrom)
cria p2(-921,556,-7000) cor(marrom)
cria p3(0,0,0)
cria p4(-508,1439,-7000) gira(90.0) cor(azul)
cria p5(-1343,1430,-7000) cor(azul)
cria p6(-921,1075,-7000)
cria p7(0,0,0)
fim;
metodo fechaBoca
p1.move(-19, 95, 0)
pisca(40)
p1.move(-19, 95, 0)
pisca(40)
fim;
metodo fala3
fala('c:/temp/anim/pessoa1_fala3.jsml')
enquanto fala inicio
fa�a tagarela
fim
fim;
metodo fala1
fala('c:/temp/anim/pessoa1_fala1.jsml')
enquanto fala inicio
fa�a tagarela
fim
fim;
metodo tagarela
fa�a abreBoca
fa�a fechaBoca
fim;
metodo abreBoca
p1.move(19, -95, 0)
pisca(40)
p1.move(19, -95, 0)
pisca(40)
fim;
fim.