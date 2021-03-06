#include "SocketDatagrama.h"

int main(int argc, char const *argv[]) {
    printf("%s\n", "CLIENTE");
    //creando objeto tipo SocketDatagrama con puerto 6666
    SocketDatagrama cliente(6666);
    printf("%s\n", "ENVIANDO PAQUETE...");
    char msg[20];
    printf("Escribe el mensaje: ");
    fgets(msg,sizeof(msg),stdin);
    
    PaqueteDatagrama paquete((char *)&msg, sizeof(msg), (char *) "127.0.0.1", 7200);

    //info: se manda paquete a la direccion y el puerto configurados
    printf("Mandando paquete a: %s:%d\n", paquete.obtieneDireccion(), paquete.obtienePuerto());

    //metodo envia(objeto PaqueteDatagrama)
    cliente.envia(paquete);
    printf("%s\n", "PAQUETE ENVIADO");
    return 0;
}
