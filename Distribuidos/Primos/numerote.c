#include <stdio.h>
#include<stdlib.h>

int main(){
    unsigned long int primo = 4294967291, i;
    int respuesta = 1;
    printf("%lu\n", primo);
    for(i = 2; i < primo; i++){
        if(primo%i == 0){
            respuesta = 0;
        }
    }
    printf("La respuesta es: %d\n", respuesta);
}