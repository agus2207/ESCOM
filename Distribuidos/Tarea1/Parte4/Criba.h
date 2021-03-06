#ifndef CRIBAH_
#define CRIBA_H_
#include "Primos.h"
#include <vector>

using namespace std;

class Criba{
	private:
		vector<Primos> primos;
        int numero;
	public:
		Criba(int);
		void iniciarcriba();
		void imprimePrimos();
};

#endif