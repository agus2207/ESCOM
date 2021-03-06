#ifndef RESPUESTA_H_
#define RESPUESTA_H_
#include "SocketDatagrama.h"
class Respuesta{
public:
Respuesta(int pl);
struct Mensaje *getRequest(void);
void sendReply(char *respuesta, char *ipCliente, int puertoCliente);
private:
SocketDatagrama *socketlocal;
};
#endif