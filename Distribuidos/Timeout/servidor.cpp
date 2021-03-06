#include <iostream>
#include "Respuesta.h"
#include "Mensaje.h"

using namespace std;

int main(){
    int ndb = 0,idR=0,idR2=0;
    char * cndb = (char*)malloc(10);
    Respuesta a(6666);
    Mensaje *m;
    while(1){
      m = (Mensaje*)a.getRequest();
      idR = m->requestId;
      if(idR!=idR2){
      if(m->operationId == 1){
        cout << "****Lectura*****" << '\n';//" ID:" << m->requestId << '\n';
        sprintf(cndb,"%d",ndb+atoi(m->arguments));
      a.sendReply(cndb,m->IP,m->puerto);
      idR2 = atoi(m->arguments);
    }else if (m->operationId == 2){
      cout << "****Escritura*****" << '\n';//" ID:" << m->requestId << '\n';
      ndb++;
      //cout << "Llego del ID: " << m->arguments << endl;
        sprintf(cndb,"%d",ndb+atoi(m->arguments));
      a.sendReply(cndb,m->IP,m->puerto);
      cout << "Envio: " << ndb << '\n';
        idR2 = atoi(m->arguments);
        }

      }else{
        cout<<"ID duplicado"<<endl;
	     sprintf(cndb,"%d",ndb+atoi(m->arguments));
       cout << "Envio: " << ndb << '\n';
        a.sendReply(cndb,m->IP,m->puerto);
      }

    }
    return 0;
}
