#include <stdlib.h>
#include <fcntl.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include "File.h"

using namespace std;

File::File(char* original, char* copia){
    memcpy(origen, original, sizeof(origen));
    memcpy(destino, copia, sizeof(destino));
}

int File::buscar(){
    int partida;

    if((partida = open(origen, O_RDONLY)) == -1)
        perror("No se encontro el archivo de origen");
    
    printf("ARCHIVO ENCONTRADO\n");

    return partida;
}

int File::escribir(){
    int nbytes, partida, llegada;

    if((partida = open(origen, O_RDONLY)) == -1)
        perror(origen);

    if((llegada = open(destino, O_WRONLY|O_TRUNC|O_CREAT, 0666)) == -1)
        perror(destino);

    printf("ESCRIBIENDO ARCHIVO\n");

    while((nbytes = read(partida, buffer, sizeof buffer)) > 0)
        write(llegada, buffer, nbytes);

    printf("ARCHIVO LISTO\n");
        
    close(llegada);
    close(partida);
    
    return llegada;
}
