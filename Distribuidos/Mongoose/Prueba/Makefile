Servidor: Servidor.cpp SocketDatagrama.o
	g++ Servidor.cpp SocketDatagrama.o PaqueteDatagrama.o -o Servidor
SocketDatagrama.o: SocketDatagrama.cpp PaqueteDatagrama.o SocketDatagrama.h
	g++ SocketDatagrama.cpp -c
PaqueteDatagrama.o: PaqueteDatagrama.cpp PaqueteDatagrama.h
	g++ PaqueteDatagrama.cpp -c