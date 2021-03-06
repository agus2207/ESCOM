#ifndef SOCKETDATAGRAMA_H
#define SOCKETDATAGRAMA_H
#include "PaqueteDatagrama.h"

#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
class SocketDatagrama{

public:
	SocketDatagrama( int );
	~SocketDatagrama();
	//Recibe un paquete tipo datagrama proveniente de este socket
	int recibe(PaqueteDatagrama & p);
	//Envía un paquete tipo datagrama desde este socket
	int envia(PaqueteDatagrama & p);
	int recibeTimeout(PaqueteDatagrama & p, time_t segundos, suseconds_t microsegundos);
	void setBroadcast();
	void unsetBroadcast();
private:
	struct sockaddr_in direccionLocal;
	struct sockaddr_in direccionForanea;
	int s; //ID socket
	struct timeval timeout;
};

#endif