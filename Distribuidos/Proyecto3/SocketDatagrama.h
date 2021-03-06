#ifndef __SOCKET_DATAGRAMA__
#define __SOCKET_DATAGRAMA__
#include "SocketDatagrama.h"
#include "PaqueteDatagrama.h"
#include "header.h"

#include <sys/types.h>
#include <sys/socket.h>
#include <string.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <string>
#include <unistd.h>

using namespace std;

class SocketDatagrama {
public:
    SocketDatagrama(int);
    ~SocketDatagrama();
    // Recibe un paquete tipo datagrama proveniente de este socket
    int recibe(PaqueteDatagrama &p);
    // Envia un paquete tipo datagrama desde este socket
    int envia(PaqueteDatagrama &p);
private:
    struct sockaddr_in direccionLocal;    //estructura a configurar en creacion de socket
    struct sockaddr_in direccionForanea;  //estructura para recibir datos de cliente
    int s; // ID socket
};

#endif