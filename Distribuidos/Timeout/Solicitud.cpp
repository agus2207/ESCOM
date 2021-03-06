#include <iostream>
#include "PaqueteDatagrama.h"
#include "SocketDatagrama.h"
#include <string.h>
#include <sys/time.h>
#include "Mensaje.h"
#include "Solicitud.h"
using namespace std;

Solicitud::Solicitud(){
    socketlocal = new SocketDatagrama(0);
}

int Solicitud::iden = 0;

char * Solicitud::doOperation(char *IP, int puerto, int operationId, char *arguments){

  int cont = 0;
  int val;
  iden++;

    Mensaje *m,mel;
    m = &mel;
    PaqueteDatagrama b(sizeof(Mensaje));
    m->messageType = 0;
    m->requestId = iden;
    strcpy(m->IP,IP);
    m->puerto = puerto;
    m->operationId = operationId;
    strcpy(m->arguments,arguments);
    PaqueteDatagrama a((char*)m,sizeof(Mensaje),IP,puerto);

    for(int i = 0; i<=7;i++){
      if(i == 7){
        cout << "Servidor no disponible" <<endl;
        exit(0);//return NULL;
      }
      cout << "ID: " << m->requestId << '\n';
      socketlocal->envia(a);
      val = socketlocal->recibeTimeout(b, 2, 500000);
      if(val != -1){
        m = (Mensaje*)b.obtieneDatos();
        return m->arguments;
      }
    }

}
