#include <stdio.h>
#include <iostream>
#include <stdlib.h>
#include "Coordenada.h"
#include "Rectangulo.h"
#include "Ortoedro.h"
#include <math.h>

using namespace std;

Ortoedro::Ortoedro(Coordenada superior1, Coordenada inferior2):rec1(superior1, Coordenada(6,8,2)), rec2(superior1, Coordenada(1,2,2)), 
            rec3(Coordenada(6,8,2), Coordenada(3,1,5)), rec4(Coordenada(4,3,9), inferior2), rec5(superior1, Coordenada(6,5,2)), rec6(Coordenada(2,5,9), inferior2)
{}

void Ortoedro::obtenerArea(){
    double area = rec1.obtenerArea() + rec2.obtenerArea() + rec3.obtenerArea() + rec4.obtenerArea() + rec5.obtenerArea() + rec6.obtenerArea();
    cout << "El area del ortoedro es igual a " << area << endl;
}

void Ortoedro::obtenerVolumen(){
    double area = rec4.obtenerArea();
    double x = rec6.obtieneInfDer().obtenerX()-rec1.obtieneInfDer().obtenerX();
    double y = rec6.obtieneInfDer().obtenerY()-rec1.obtieneInfDer().obtenerY();
    double z = rec6.obtieneInfDer().obtenerZ()-rec1.obtieneInfDer().obtenerZ();
    double profundidad = sqrt ((x*x)+(y*y)+(z*z));
    double volumen = profundidad*area;
    cout << "El volumen del Ortoedro es igual a " << volumen << endl;
}

Rectangulo Ortoedro::obtieneRec1(){
    return rec1;
}

Rectangulo Ortoedro::obtieneRec2(){
    return rec2;
}

Rectangulo Ortoedro::obtieneRec3(){
    return rec3;
}

Rectangulo Ortoedro::obtieneRec4(){
    return rec4;
}

Rectangulo Ortoedro::obtieneRec5(){
    return rec5;
}

Rectangulo Ortoedro::obtieneRec6(){
    return rec6;
}