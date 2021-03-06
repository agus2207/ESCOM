#ifndef RESPUESTA_H_
#define RESPUESTA_H_
#include "SocketDatagrama.h"

class Respuesta{
  public:
    Respuesta(int pl);
    struct mensaje *getRequest(int ID);
    void sendReply(char *respuesta, char *ipCliente, int puertoCliente, int op);
  private:
    SocketDatagrama *socketlocal;
};

#endif
