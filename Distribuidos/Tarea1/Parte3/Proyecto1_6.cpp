#include <iostream>
#include "Coordenada.h"
#include "Rectangulo.h"
#include "Ortoedro.h"
#include <math.h>

using namespace std;

int main( ){
    Ortoedro ortoedro1(Coordenada(1,2,3), Coordenada(5,7,3));
    cout << "Calculando el area de un Ortoedro:\n";     
    ortoedro1.obtenerArea();
    cout << "Calculando el volumen de un Ortoedro:\n";     
    ortoedro1.obtenerVolumen();
    return 0;
}