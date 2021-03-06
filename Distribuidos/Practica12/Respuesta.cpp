#include "Respuesta.h"
#include "mensaje.h"

#include <iostream>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
using namespace std;

Respuesta::Respuesta(int pl){
  socketlocal = new SocketDatagrama(pl);
  //printf("\nSOCKET CREATED");
}

struct mensaje *Respuesta::getRequest(int ID){
  //recibe informacion del cliente

  struct mensaje client_info;
  bzero((char *)&client_info, sizeof(client_info));
  int status;
  PaqueteDatagrama packageReply(100);

  //printf("\nPACKAGEREPLY CREATED");
  //getting info from client
  status = socketlocal->recibe(packageReply);
  if(status < 0){
    perror("SEE: ");
    cout << "ERROR: getRequest() FAILED" << endl;
    exit(EXIT_FAILURE);
  }

  std::cout << "OPERATION: "  << packageReply.obtieneOp() << '\n';
  cout << "REQUEST FROM: " << packageReply.obtieneDireccion() << ":" << packageReply.obtienePuerto() << "\n";
  std::cout << "FILLING STRUCT MENSAJE" << '\n';


  client_info.messageType = packageReply.obtieneOp();
  client_info.requestId = ID;
  memcpy(client_info.IP, packageReply.obtieneDireccion(), sizeof(client_info.IP));
  client_info.puerto = packageReply.obtienePuerto();
  memcpy(client_info.arguments, packageReply.obtieneDatos(), sizeof(client_info.arguments));

  std::cout << "STRUCT MENSAJE: OK" << '\n';
  struct mensaje *cliente1 = &client_info;
  return cliente1;
}

void Respuesta::sendReply(char *respuesta, char *ipCliente, int puertoCliente, int op){
  int status;
  time_t start, end;
  double elapsed;
  start = time(NULL);
  int terminate = 1;
  //ALGORITHM TO INVERT RESPUESTA
  cout << "RESPUESTA DIRECTA: " << respuesta << "\n";

  PaqueteDatagrama replyPackage((char *) respuesta, 100, (char *) ipCliente, puertoCliente);
  if(op == 0)
    std::cout << "INVERT WORD" << '\n';
  else if(op == 1)
    std::cout << "OP 1 BANK" << '\n';
  else if(op == 2)
    std::cout << "OP 2 BANK" << '\n';
  else
    std::cout << "UNKNOWN OP" << '\n';
  std::cout << "\nWAIT 3 SECONDS..." << '\n';

  //WHILE FOR 3 SECONDS
  while(terminate){
    end = time(NULL);
    elapsed = difftime(end, start);
    if(elapsed >= 10)
      terminate = 0;
    else
      usleep(50000);
  }

  cout << "SENDING PACKAGE TO:" << replyPackage.obtieneDireccion() << ":" << replyPackage.obtienePuerto() << endl;
	status = socketlocal->envia(replyPackage);
  if(status < 0){
    cout << "ERROR: sendto() FAILED" << "\n";
    exit(EXIT_FAILURE);
  }
	cout << "PACKAGE DELIVERED" << endl;
  //status = sendto(socketlocal->s, respuesta, sizeof(respuesta), 0, (struct sockaddr *)&foreignAddr, sizeof(foreignAddr));

  return;
}
