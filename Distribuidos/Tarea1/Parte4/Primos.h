#ifndef PRIMOS_H_
#define PRIMOS_H_

class Primos{
	private:
		int x;
		bool b;
	public:
		Primos(int = 0, bool = true);
		double obtenerx();
		double obtenerb();
		void imprimir();
		void cambiarbool(bool);
};

#endif