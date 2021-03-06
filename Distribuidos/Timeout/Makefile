run: servidor cliente
	
servidor: servidor.o PaqueteDatagrama.o SocketDatagrama.o Respuesta.o
	g++ SocketDatagrama.o PaqueteDatagrama.o servidor.o Respuesta.o -o servidor
cliente: cliente.o PaqueteDatagrama.o SocketDatagrama.o Solicitud.o
	g++ SocketDatagrama.o PaqueteDatagrama.o cliente.o Solicitud.o -o cliente
servidor.o: servidor.cpp
	g++ -Wall -c servidor.cpp
cliente.o: cliente.cpp
	g++ -Wall -c cliente.cpp
Solicitud.o: Solicitud.cpp
	g++ -Wall -c Solicitud.cpp
Respuesta.o: Respuesta.cpp
	g++ -Wall -c Respuesta.cpp
PaqueteDatagrama.o: PaqueteDatagrama.cpp
	g++ -Wall -c PaqueteDatagrama.cpp
SocketDatagrama.o:SocketDatagrama.cpp 
	g++ -Wall -c SocketDatagrama.cpp
clean:
	rm *.o *.out ./cliente ./servidor
