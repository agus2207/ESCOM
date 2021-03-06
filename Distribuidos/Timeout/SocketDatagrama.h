#ifndef SOCKETDDAT_H_
#define SOCKETDDAT_H_
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <sys/time.h>
#include <arpa/inet.h>
#include "PaqueteDatagrama.h"
using namespace std;
class SocketDatagrama{
public:
  SocketDatagrama(int);
  ~SocketDatagrama();
  //Recibe un paquete tipo datagrama proveniente de este socket
  int recibe(PaqueteDatagrama &p);
  //Envía un paquete tipo datagrama desde este socket
  int envia(PaqueteDatagrama &p);
  int recibeTimeout(PaqueteDatagrama & p, time_t segundos, suseconds_t microsegundos);
private:
  struct sockaddr_in direccionLocal;
  struct sockaddr_in direccionForanea;
  struct timeval timeout;
  
  int s; //ID socket
};
#endif
