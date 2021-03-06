#ifndef __SOCKET_DATAGRAMA__
#define __SOCKET_DATAGRAMA__
#include "PaqueteDatagrama.h"

#include <sys/types.h>
#include <sys/socket.h>
#include <string.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <string>
#include <unistd.h>
#include <sys/time.h>

using namespace std;

class SocketDatagrama {
public:
    SocketDatagrama(int);
    ~SocketDatagrama();
    // Recibe un paquete tipo datagrama proveniente de este socket
    int recibe(PaqueteDatagrama &p);
    // Envia un paquete tipo datagrama desde este socket
    int envia(PaqueteDatagrama &p);
    //Recibe un paquete datagrama pero tiene funcion de timeout
    int recibeTimeOut(PaqueteDatagrama &p,  time_t segundos, suseconds_t microsegundos);
    int s; // ID socket
private:
    struct sockaddr_in direccionLocal;    //estructura a configurar en creacion de socket
    struct sockaddr_in direccionForanea;  //estructura para recibir datos de cliente
    struct timeval timeout;
};
#endif
