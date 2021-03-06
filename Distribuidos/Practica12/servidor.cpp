#include "SocketDatagrama.h"
#include "mensaje.h"
#include "Respuesta.h"
#include <iostream>
using namespace std;

int main(int argc, char const *argv[]) {
    mensaje *info;
    int ID = 0;
    //char address[16];
    //int client_port;
    std::cout << "SERVER" << '\n';
    Respuesta reply(7201);

    while(1){
      cout << "WAITING FOR REQUESTS..." << "\n";
      info = reply.getRequest(ID);
      ID = ID + 1;
      //std::cout << "OP: " << info->messageType << '\n';
      reply.sendReply((info->arguments), (info->IP), (info->puerto), (info->messageType));
      //cout << "ANSWER FROM CLIENT " << info->IP << ":" << info->puerto << "\n";
      }

    std::cout << "" << '\n';
    return 0;
}
