#include "SocketDatagrama.h"

int main(int argc, char const *argv[]) {
    printf("%s\n", "SERVIDOR");
    //creando objetos SocketDatagrama en puerto 7200 y PaqueteDatagrama para recibir
    SocketDatagrama cliente(7200);
    PaqueteDatagrama paquete(2*sizeof(double));

    while(1) {
        printf("%s\n", "ESPERANDO...");
        //cuando el cliente se conecte (ver SocketDatagrama.cpp)
        printf("Recibidos = %d\n", cliente.recibe(paquete));  //funcion recibe(objeto PaqueteDatagrama)
        //info: muestra la direccion y el puerto del cliente
        printf("Mensaje de: %s:%d\n", paquete.obtieneDireccion(), paquete.obtienePuerto());
        //memcpy(prueba, paquete.obtieneDatos(), cliente.recibe(paquete));
        printf("He recibido: %s\n", paquete.obtieneDatos());
        printf("%s\n", "RECIBIDO");
    }
    return 0;
}