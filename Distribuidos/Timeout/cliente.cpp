#include <iostream>
#include <stdlib.h>
#include "Solicitud.h"
using namespace std;


int main(int argc, char *argv[]){
int cuenta = 0,ndbID,ndb,opc,cant,idCli = 0,idSer;
char* envio;
    Solicitud cliente1;
 if(argv[1]==NULL){
		cout << "Siga el formato ./cli ip_servidor puerto_servidor cantidad" <<endl;
		exit(0);
	}

  do{
    cout << "Seleccione Lectura(1) o Escritura (2), Salir(3)."  << '\n';
    cin >> opc;
    switch(opc){
      case 1:
      idCli++;
      sprintf(envio,"%d",idCli);
      ndbID = atoi(cliente1.doOperation (argv[1], atoi(argv[2]),1, envio));
       ndb = ndbID-idCli;
       idSer = ndbID - ndb;
       if(idSer == idCli){
           cout <<"Saldo $" << ndb << endl;
         }
        break;

      case 2:
      cout << "Ingrese cantidad $";
      cin >> cant;
      for(int i=0;i<atoi(argv[3]);i++){
        idCli++;
        sprintf(envio,"%d",idCli);
         ndbID = atoi(cliente1.doOperation (argv[1], atoi(argv[2]),2, envio));
           ndb = ndbID-idCli;
           idSer = ndbID - ndb;
           if(idSer == idCli){
              cuenta++;
              cout << "idSer:" << idSer  << " idCli:" << idCli << " ndb:" << ndb << "cuenta:" << cuenta << endl;
              cout <<"Saldo $" << ndb << endl;
              /*if(cuenta == ndb){
              cout <<"Saldo $" << ndb << endl;
              }else{
              cout << "Saldo inconsistente" << '\n';
             // exit(0);
              //break;
                }*/
           }else{
             cout << "idSer:" << idSer  << " idCli:" << idCli << " ndb:" << ndb << "cuenta:" << cuenta << endl;
             cout <<"Saldo $" << ndb << endl;

           }
          }
}
}while(opc!=3);

    //cout << cliente1.doOperation("127.0.0.1",6666,15,"HOLA A TODOS") << endl;
    return 0;
}
