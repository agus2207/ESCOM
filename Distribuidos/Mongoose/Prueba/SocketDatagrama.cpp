#include <iostream>
#include <string.h>
#include <stdio.h>
#include <sys/types.h>          
#include <sys/socket.h>
#include <sys/time.h>
#include <errno.h>
#include "SocketDatagrama.h"

using namespace std;

SocketDatagrama::SocketDatagrama(int puerto){
	s = socket(AF_INET, SOCK_DGRAM, 0);
	bzero((char *)&direccionLocal, sizeof(direccionLocal));
  direccionLocal.sin_family = AF_INET;
  direccionLocal.sin_addr.s_addr = INADDR_ANY;
  direccionLocal.sin_port = htons(puerto);
  bind(s, (struct sockaddr *)&direccionLocal,sizeof(direccionLocal));
}

int SocketDatagrama::recibe(PaqueteDatagrama &p){
  int recv;
  socklen_t len;
  char ip_address[16], datos[p.obtieneLongitud()];
  len = sizeof(direccionForanea);
  recv = recvfrom(s, (char *)datos , p.obtieneLongitud() * sizeof(char), 0, (struct sockaddr *)&direccionForanea, &len);  
  p.inicializaPuerto(htons(direccionForanea.sin_port)); 
  memcpy(ip_address,inet_ntoa(direccionForanea.sin_addr),16);
  p.inicializaIp(ip_address);
  p.inicializaDatos(datos);
  cout<< "Ip: "<< p.obtieneDireccion() << " Puerto: " << p.obtienePuerto() <<endl;
  return recv;
}


int SocketDatagrama::recibeTimeout(PaqueteDatagrama & p, time_t segundos, suseconds_t microsegundos){
  int recv;
  timeout.tv_sec = segundos;
  timeout.tv_usec = microsegundos;
  socklen_t len;
  char ip_address[16], datos[p.obtieneLongitud()];
	len = sizeof(direccionForanea);
  setsockopt(s, SOL_SOCKET, SO_RCVTIMEO, (char *)&timeout, sizeof(timeout));
	recv = recvfrom(s, (char *)datos , p.obtieneLongitud() * sizeof(char), 0, (struct sockaddr *)&direccionForanea, &len); 
  
  p.inicializaPuerto(htons(direccionForanea.sin_port)); 
  memcpy(ip_address,inet_ntoa(direccionForanea.sin_addr),16);
  p.inicializaIp(ip_address);
  p.inicializaDatos(datos);
  
  if (recv < 0) {
    if (errno == EWOULDBLOCK){
      fprintf(stderr, "Tiempo para recepción transcurrido\n");
      recv = -1;
    }
    else
      fprintf(stderr, "Error en recvfrom\n");
  }

  
  //cout<< "Ip: "<< p.obtieneDireccion() << " Puerto: " << p.obtienePuerto() <<endl;
  return recv;
}

void SocketDatagrama::setBroadcast(){
  int yes = 1;
  setsockopt(s,SOL_SOCKET,SO_BROADCAST,&yes,sizeof(int));
}
void SocketDatagrama::unsetBroadcast(){
  int yes = 0;
  setsockopt(s,SOL_SOCKET,SO_BROADCAST,&yes,sizeof(int));
}


int SocketDatagrama::envia(PaqueteDatagrama &p){
  int snd; 
  bzero((char *)&direccionForanea, sizeof(direccionForanea));
  direccionForanea.sin_family = AF_INET;
  direccionForanea.sin_addr.s_addr = inet_addr(p.obtieneDireccion());
  direccionForanea.sin_port = htons(p.obtienePuerto());
  snd = sendto(s, (char *)p.obtieneDatos(), p.obtieneLongitud() * sizeof(char), 0, (struct sockaddr *) &direccionForanea, sizeof(direccionForanea));
  return snd;
}

SocketDatagrama::~SocketDatagrama(){
	
}