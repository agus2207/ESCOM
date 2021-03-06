#include <iostream>
#include <vector>
#include "PoligonoIrreg.h"
#include "Coordenada.h"
using namespace std;

int main(){
	
	PoligonoIrreg p1(Coordenada(-1,0), Coordenada(1,2), Coordenada(3,4));

	p1.anadeVertice(Coordenada(4,-2));

	p1.imprimeVertices();

	int a = 1;
	int x, y;
	while( a == 1 ){

		cout << "Ingrese coordenada x : ";
		cin >> x;
		cout << "Ingrese coordenada y : ";
		cin >> y;

		p1.anadeVertice(Coordenada(x,y));
		p1.imprimeVertices();

		cout << "Desea seguir añadiendo coordenadas? (1 = SI, 0 = NO) : ";
		cin >> a;

	}
	return 0;
}