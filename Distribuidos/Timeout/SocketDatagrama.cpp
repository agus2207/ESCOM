#include <iostream>
#include "SocketDatagrama.h"
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <errno.h>
#include <arpa/inet.h>
#include "PaqueteDatagrama.h"
using namespace std;

SocketDatagrama::SocketDatagrama(int puerto){
  s = socket(AF_INET,SOCK_DGRAM,0);
  bzero((char*)&direccionLocal,sizeof(direccionLocal));
  direccionLocal.sin_family = AF_INET;
  direccionLocal.sin_addr.s_addr=INADDR_ANY;
  direccionLocal.sin_port=htons(puerto);
  bind(s,(struct sockaddr *)&direccionLocal,sizeof(direccionLocal));
}
SocketDatagrama::~SocketDatagrama(){
  close(s);
}
//Recibe un paquete tipo datagrama proveniente de este socket
int SocketDatagrama::recibe(PaqueteDatagrama &p){

  socklen_t d = sizeof (direccionForanea);
  printf("ESPERANDO RECIBIR...\n" );
  int x = recvfrom(s,p.obtieneDatos(),p.obtieneLongitud(),0,(struct sockaddr *)&direccionForanea, &d);
  printf("Recibimos mensaje de la Ip: %s\n", inet_ntoa(direccionForanea.sin_addr));

  p.inicializaIp(inet_ntoa(direccionForanea.sin_addr));
  p.inicializaPuerto(ntohs(direccionForanea.sin_port));

  return x;
}
//Envía un paquete tipo datagrama desde este socket
int SocketDatagrama::envia(PaqueteDatagrama &p){

  bzero((char*)&direccionForanea,sizeof(direccionForanea));
  direccionForanea.sin_family = AF_INET;
  direccionForanea.sin_addr.s_addr=inet_addr(p.obtieneDireccion());
  direccionForanea.sin_port=htons(p.obtienePuerto());
  int x = sendto(s,p.obtieneDatos(),p.obtieneLongitud(),0,(struct sockaddr *)&direccionForanea,sizeof(direccionForanea));
return x;
}

int SocketDatagrama::recibeTimeout(PaqueteDatagrama & p, time_t segundos, suseconds_t microsegundos) {
  timeout.tv_sec = segundos;
  timeout.tv_usec = microsegundos;

  setsockopt(s, SOL_SOCKET, SO_RCVTIMEO, (char *)&timeout, sizeof(timeout));

  socklen_t d = sizeof (direccionForanea);
  printf("ESPERANDO RECIBIR...\n" );
  int x = recvfrom(s,p.obtieneDatos(),p.obtieneLongitud(),0,(struct sockaddr *)&direccionForanea, &d);

  if(x < 0) {
    if (errno == EWOULDBLOCK){
      fprintf(stderr, "Tiempo para recepción transcurrido\n");
      return -1;
    }
    else
      fprintf(stderr, "Error en recvfrom\n");
  }
  printf("Recibimos mensaje de la Ip: %s\n", inet_ntoa(direccionForanea.sin_addr));

  p.inicializaIp(inet_ntoa(direccionForanea.sin_addr));
  p.inicializaPuerto(ntohs(direccionForanea.sin_port));

  return x;
}
