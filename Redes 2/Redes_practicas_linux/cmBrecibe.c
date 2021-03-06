#include <sys/types.h> //Definicion de tipos
#include <sys/socket.h> //Llamadas API sockets
#include <netinet/in.h> //Estructura de datos
#include <arpa/inet.h> //Para sockaddr_in
#include <stdio.h> //Para printf()
#include <stdlib.h> //Para atoi()
#include <string.h> //Para stdlen()
#include <unistd.h> //Para close()

#define MAX_LEN 1024
#define MIN_PORT 1024
#define MAX_PORT 65535

int main (int argc, char *argv[]){
    int sock; //descriptor del socket 
    int flag_on = 1; //Banderas de opcion
    struct sockaddr_in mc_addr; //Direccion
    char recv_str[MAX_LEN +1]; //Buffer de lectura
    int recv_len; //longitud de cadena recibida
    struct ip_mreq mac_reg; //Estructura solicitud MC
    char *mc_addr_str; //Direccion IP multicast
    unsigned short mc_port; //Puerto multicast
    struct sockaddr_in from_addr; //Paquete origen
    unsigned int from_len; //longitud direccion origen
    /*Validacion de parametros*/
    if(argc != 3){
        fprintf(stderr, "Uso %s <Multicast IP><Multicast puerto>\n", argv[0]);
        exit(1);
    }
    mc_addr_str = argv[1]; //arg1: direccion multicast
    mc_port = atoi(argv[2]); //arg2: puerto multicast
    /*Validamos el numero de puertos*/
    if((mc_port < MIN_PORT)||(mc_port > MAX_PORT)){
        fprintf(stderr, "Numero de puerto invalido %d\n", mc_port);
        exit(1);
    }
    /*Se crea un socket para un canal Multicast*/
    if((sock = socket(PF_INET, SOCK_DGRAM, IPPROTO_UDP)) < 0){
        perror("Error en creacion del socket ()");
        exit(1);
    }
    /*Permite la reutilizacion del socket*/
    if((setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &flag_on, sizeof(flag_on))) < 0){
        perror("Error en setsockopt() ");
        exit(1);
    }
    /*Se construye la estructura sockaddr */
    memset(&mc_addr, 0, sizeof(mc_addr));
    mc_addr.sin_family = AF_INET;
    mc_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    mc_addr.sin_port = htons(mc_port);
    /*liga la direccion con el socket*/
    if((bind(sock, (struct sockaddr*)&mc_addr, sizeof(mc_addr))) < 0){
        perror("Error en bind()");
        exit(1);
    }
    /*Estructura para unirse al grupo de multicast*/
    mac_reg.imr_multiaddr.s_addr = inet_addr(mc_addr_str);
    mac_reg.imr_interface.s_addr = htonl(INADDR_ANY);
    /*Se une al grupo mediante setsockopt */
    if((setsockopt(sock, IPPROTO_IP, IP_ADD_MEMBERSHIP, (void*)&mac_reg, sizeof(mac_reg))) < 0){
        perror("Error en setsockopt(), membership");
        exit(1);
    }
    for(;;){ //Lazo infinito
        //Se limpia el buffer y la estructura de lectura
        memset(recv_str, 0, sizeof(recv_str));
        from_len = sizeof(from_addr);
        memset(&from_addr, 0, from_len);
        /*Bloqueo para la recepcion de paquetes*/
        if((recv_len = recvfrom(sock, recv_str, MAX_LEN, 0, (struct sockaddr *)&from_addr, &from_len)) < 0){
            perror("Error al recibir paquete");
            exit(1);
        }
        /*Imprimimos lo que se recibio*/
        printf("\nSe recibieron %d bytes desde %s: ", recv_len, inet_ntoa(from_addr.sin_addr));
        printf("\n %s\n ", recv_str);
    } //for
    /*En caso de modificar el lazo infinito para poder salir*/
    /*Modificamos la opcion del socket para salir del grupo*/
    if((setsockopt(sock, IPPROTO_IP, IP_DROP_MEMBERSHIP, (void*)&mac_reg, sizeof(mac_reg))) < 0){
        perror("Error en setsockopt drop membership");
        exit(1);
    }
    close(sock);
}