#include "Solicitud.h"
#include <iostream>
#include <stdlib.h>
#include <string>
using namespace std;

int main(int argc, char *argv[]) {
    cout << "CLIENT" << "\n";
    Solicitud cliente1;
    int ordenaCadena = 0, flag = 1;
    string cadena;
    while(flag){
      cout << "inserte la cadena: " << endl;
      getline (cin, cadena);
      cout << "seleccione la operacion: " << endl;
      std::cin >> ordenaCadena;
      if(ordenaCadena < 0 || ordenaCadena > 2){
        std::cout << "ERROR: INVALID OPERATION" << '\n';
      }
      else
        flag = 0;
    }
    char *cadenota;
    cadenota = (char *)cadena.c_str();

    cout << "CONNECTING..." << endl;
    //args = cliente1.doOperation(argv[1], atoi(argv[2]), ordenaCadena, cadenota);
    cout << "SERVER HAS REPLIED!" << "\n";
    printf("La respuesta del servidor es <%s>\n",cliente1.doOperation ((char*)"127.0.0.1", 7201, ordenaCadena, cadenota));
    ///cout << "DATA: " << args << endl;

    return 0;
}
