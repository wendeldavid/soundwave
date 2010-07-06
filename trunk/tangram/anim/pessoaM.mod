modelo pessoaM
metodo CRIA
cria p1(-672,940,-7000) gira(90.0)
cria p2(-1708,959,-7000) cor(verde)
cria p3(-1166,42,-7000) gira(45.0) cor(rosa)
cria p4(0,0,0)
cria p5(-1151,-239,-7000) cor(rosa)
cria p6(-1170,479,-7000)
cria p7(0,0,0)
fim;
metodo piscadela
p1.gira(45)
p1.gira(45)
p2.cor(verde)
p1.cor(verde)
p2.gira(45)
p2.gira(45)
p2.move(134, -211, 0)
pisca(40)

p1.gira(45)
p1.gira(45)
p2.gira(45)
p2.gira(45)
p2.move(-134, 211, 0)
pisca(40)
fim;
metodo fechaBoca
p5.move(0, 95, 0)
pisca(40)
p5.move(0, 95, 0)
pisca(40)
fim;
metodo fala2
fala('c:/temp/anim/pessoaM_fala2.jsml')
enquanto fala inicio
faça tagarela
fim
fim;
metodo fala1
fala('c:/temp/anim/pessoaM_fala1.jsml')
enquanto fala inicio
faça tagarela
fim
fim;
metodo tagarela
faça abreBoca
faça fechaBoca
fim;
metodo abreBoca
p5.move(0, -95, 0)
pisca(40)
p5.move(0, -95, 0)
pisca(40)
fim;
fim.