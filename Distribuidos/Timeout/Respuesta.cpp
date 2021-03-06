#include <iostream>
#include <cstring>
#include "Respuesta.h"
#include "Mensaje.h"
#include "PaqueteDatagrama.h"
#include "SocketDatagrama.h"

using namespace std;

Respuesta::Respuesta(int pl){
    socketlocal = new SocketDatagrama(pl);
}
struct Mensaje *Respuesta::getRequest(void){
  Mensaje *m,mel;
  m = &mel;
  PaqueteDatagrama b(sizeof(Mensaje));
  socketlocal->recibe(b);
  m = (Mensaje *)b.obtieneDatos();
  m->puerto = b.obtienePuerto();
  strcpy(m->IP,b.obtieneDireccion());
  return m;
}

void Respuesta::sendReply(char *respuesta, char *ipCliente, int puertoCliente){
  Mensaje *m,mel;
  m = &mel;
  m->messageType = 1;
  m->requestId = 20;
  strcpy(m->IP,ipCliente);
  m->puerto = puertoCliente;
  strcpy(m->arguments,respuesta);
  PaqueteDatagrama a((char*)m,sizeof(Mensaje),ipCliente,puertoCliente);
  socketlocal->envia(a);
}
