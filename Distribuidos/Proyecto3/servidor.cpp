#include "SocketDatagrama.h"
#include "PaqueteDatagrama.h"
#include "header.h"
#include "File.h"

int main(int argc, char const *argv[]) {
    printf("%s\n", "SERVIDOR");
    //creando objetos SocketDatagrama en puerto 7200 y PaqueteDatagrama para recibir
    SocketDatagrama cliente(7200);
    PaqueteDatagrama paquete(sizeof(struct mensaje));
    int estado;

    for(;;){
        printf("%s\n", "ESPERANDO...");
        cliente.recibe(paquete);
        //cuando el cliente se conecte (ver SocketDatagrama.cpp)
        //printf("Recibidos = %d\n", cliente.recibe(paquete));  //funcion recibe(objeto PaqueteDatagrama)
        struct mensaje msg;
        msg = paquete.obtieneDatos();
        //printf("Recibi esta opcion: %li\n", paquete.obtieneDatos().opcode);
        //printf("Recibi este nombre %s\n", msg.name);
        if(msg.opcode == READ){
            File archivo(msg.name, (char *)"");
            estado = archivo.buscar();
            msg.result = estado;
            printf("ENVINDO RESULTADO\n");
            PaqueteDatagrama respuesta(msg, sizeof(msg), paquete.obtieneDireccion(), paquete.obtienePuerto());
            cliente.envia(respuesta);
        }
        //printf("Estado: %d\n", estado);
        if(msg.opcode == WRITE){
            File archivo(msg.name, msg.data);
            estado = archivo.escribir();
            msg.result = estado;
            printf("ENVIANDO RESULTADO\n");
            PaqueteDatagrama respuesta(msg, sizeof(msg), paquete.obtieneDireccion(), paquete.obtienePuerto());
            cliente.envia(respuesta);
        }
    }
    return 0;
}