Server: Server.cpp mongoose.c SocketDatagrama.o PaqueteDatagrama.o
	g++ Server.cpp mongoose.c SocketDatagrama.o PaqueteDatagrama.o -std=c++11 -o Server
SocketDatagrama.o: SocketDatagrama.cpp SocketDatagrama.h PaqueteDatagrama.o
	g++ SocketDatagrama.cpp -c
PaqueteDatagrama.o: PaqueteDatagrama.cpp PaqueteDatagrama.h
	g++ PaqueteDatagrama.cpp -c