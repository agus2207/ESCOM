#include "SocketDatagrama.h"
#include <errno.h>
#include <time.h>

SocketDatagrama::SocketDatagrama(int puerto){         //configuracion del socket
    s = socket(AF_INET, SOCK_DGRAM, 0);               //crear socket
    bzero((char *)&direccionLocal, sizeof(direccionLocal));   //limpiar estructura de info de socket
    direccionLocal.sin_family = AF_INET;              //familia: AF_INET
    direccionLocal.sin_addr.s_addr = INADDR_ANY;      //direccion: cualquiera se acepta
    direccionLocal.sin_port = htons(puerto);          //puerto: parametro
    bind(s, (struct sockaddr *)&direccionLocal, sizeof(direccionLocal));  //levantar el servicio
    //printf("SOCKET CREATED: %d\n", s);
}

SocketDatagrama::~SocketDatagrama() {     //DESTRUCTOR: cierra el socket
    close(s);
}

int SocketDatagrama::envia(PaqueteDatagrama &p){      //metodo para enviar el paquete de datagramas
    bzero((char *)&direccionForanea, sizeof(direccionForanea));   //limpiando la estructura

    //configurando la estructura, con GETTERS de PaqueteDatagrama.cpp
    direccionForanea.sin_family = AF_INET;
    direccionForanea.sin_addr.s_addr = inet_addr(p.obtieneDireccion());
    direccionForanea.sin_port = htons(p.obtienePuerto());

    //enviando datos, con GETTERS de PaqueteDatagrama.cpp
    return sendto(s, p.obtieneDatos(), p.obtieneLongitud(), 0, (struct sockaddr *)&direccionForanea, sizeof(direccionForanea));
}

int SocketDatagrama::recibe(PaqueteDatagrama &p){ //metodo para recibir un paquete de datagrama
    //printf("LOGITUD: %d\n", p.obtieneLongitud());
    char datos[p.obtieneLongitud()];      //arreglo de caracteres con longitud longi (ver PaqueteDatagrama.cpp)
    char aux[INET_ADDRSTRLEN];
    socklen_t clilen = sizeof(direccionForanea);  //longitud en terminos de socket
    int x;
    //recibiendo en arreglo "datos" con GETTER de longitud de PaqueteDatagrama.cpp
    int i = recvfrom(s, datos, p.obtieneLongitud(), 0, (struct sockaddr *) &direccionForanea, &clilen);
    //inet_ntop convierte una direccion IP a formato de texto, lo almacena en aux
    //obtiene en cadena la direccion del cliente conectado
    inet_ntop(AF_INET, &(direccionForanea.sin_addr), aux, INET_ADDRSTRLEN);
    //printf("\nCONVERT OK inet_ntop");
    //ntohs: convierte numero de red en entero short, obtiene puerto del cliente
    //inicializa puerto con dato obtenido
    p.inicializaPuerto(ntohs(direccionForanea.sin_port));
    //printf("PORT INITIAILIZED\n");
    //inicializa IP con dato obtenido por inet_ntop
    p.inicializaIp(aux);
    //printf("\nIP INITIALIZED");
    //inicializa mensaje con cadena recibida de recvfrom
    p.inicializaDatos(datos);
    //printf("\ndata INITIALIZED");
    //devuelve resultado de la funcion recvfrom
    return i;
}

int SocketDatagrama::recibeTimeOut(PaqueteDatagrama &p,  time_t segundos, suseconds_t microsegundos){
  //inicializando temporizador
  timeout.tv_sec = segundos;
  timeout.tv_usec = (microsegundos * 100000);
  int n;
  //configurando el socket
  n = setsockopt(s, SOL_SOCKET, SO_RCVTIMEO, (char *)&timeout, sizeof(timeout));
  if(n != 0){
    printf("\nERROR: setsockopt() FAILED");
    close(s);
    exit(1);
  }
  char datos[p.obtieneLongitud()];      //arreglo de caracteres con longitud longi (ver PaqueteDatagrama.cpp)
  char aux[INET_ADDRSTRLEN];
  socklen_t clilen = sizeof(direccionForanea);  //longitud en terminos de socket

  //recibiendo en arreglo "datos" con GETTER de longitud de PaqueteDatagrama.cpp
  int i = recvfrom(s, datos, p.obtieneLongitud(), 0, (struct sockaddr *) &direccionForanea, &clilen);

  //revisando el estado del temporizador
  if(i < 0){
    if(errno == EWOULDBLOCK){
        fprintf(stderr, "\nEXECUTION TIME EXCEEDED");
        i = -1;
    }
    else
      fprintf(stderr, "\nERROR: recvfrom() FAILED");
  }

  //inet_ntop convierte una direccion IP a formato de texto, lo almacena en aux
  //obtiene en cadena la direccion del cliente conectado
  inet_ntop(AF_INET, &(direccionForanea.sin_addr), aux, INET_ADDRSTRLEN);

  //ntohs: convierte numero de red en entero short, obtiene puerto del cliente
  //inicializa puerto con dato obtenido
  p.inicializaPuerto(ntohs(direccionForanea.sin_port));

  //inicializa IP con dato obtenido por inet_ntop
  p.inicializaIp(aux);

  //inicializa mensaje con cadena recibida de recvfrom
  p.inicializaDatos(datos);

  //devuelve resultado de la funcion recvfrom
  return i;

}
