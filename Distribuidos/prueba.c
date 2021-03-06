#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void){
    char direccion[13] = "10.100.74.";
    for(int i = 0; i < 255; i++){
        //char numero[3] = (char*)&i;
        strcat(direccion, (char*)&i);
        puts(direccion);
    }
}