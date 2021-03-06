Proyecto1_6: Proyecto1_6.cpp Ortoedro.o
	g++ Proyecto1_6.cpp Ortoedro.o Rectangulo.o Coordenada.o -o 1_6
Ortoedro.o: Ortoedro.cpp Rectangulo.o Ortoedro.h
	g++ Ortoedro.cpp -c
Rectangulo.o: Rectangulo.cpp Coordenada.o Rectangulo.h 
	g++ Rectangulo.cpp -c
Coordenada.o: Coordenada.cpp Coordenada.h
	g++ Coordenada.cpp -c