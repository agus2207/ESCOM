#include "Coordenada.h"
#include "PoligonoIrreg.h"
#include <iostream>
#include <vector>
using namespace std;

PoligonoIrreg::PoligonoIrreg(Coordenada c1, Coordenada c2, Coordenada c3){

	vertices.push_back(c1);
	vertices.push_back(c2);
	vertices.push_back(c3);

}
		
void PoligonoIrreg::anadeVertice(Coordenada c){

	vertices.push_back(c);

}

void PoligonoIrreg::imprimeVertices(){

	cout << "Vertices del poligono : \n";
	for(int i = 0; i < vertices.size(); i++)
		vertices[i].imprimeCoordenada();

}