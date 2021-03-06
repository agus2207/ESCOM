run: servidor cliente

cliente: cliente.o PaqueteDatagrama.o SocketDatagrama.o File.o
	g++ SocketDatagrama.o PaqueteDatagrama.o cliente.o File.o -o cliente
servidor: servidor.o PaqueteDatagrama.o SocketDatagrama.o File.o
	g++ SocketDatagrama.o PaqueteDatagrama.o servidor.o File.o -o servidor
servidor.o: servidor.cpp
	g++ -Wall -c servidor.cpp
cliente.o: cliente.cpp
	g++ -Wall -c cliente.cpp
File.o: File.cpp
	g++ -Wall -c File.cpp
PaqueteDatagrama.o: PaqueteDatagrama.cpp
	g++ -Wall -c PaqueteDatagrama.cpp
SocketDatagrama.o:SocketDatagrama.cpp 
	g++ -Wall -c SocketDatagrama.cpp
clean:
	rm *.o *.out ./cliente ./servidor