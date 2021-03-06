#ifndef ORTOEDRO_H_
#define ORTOEDRO_H_

class Ortoedro{
	private:
		Rectangulo rec1;
        Rectangulo rec2;
        Rectangulo rec3;
        Rectangulo rec4;
        Rectangulo rec5;
        Rectangulo rec6;
	public:
		//Ortoedro();
		Ortoedro(Coordenada punto1, Coordenada punto2);
		Rectangulo obtieneRec1();
        Rectangulo obtieneRec2();
        Rectangulo obtieneRec3();
        Rectangulo obtieneRec4();
        Rectangulo obtieneRec5();
        Rectangulo obtieneRec6();
		void obtenerArea();
        void obtenerVolumen();
};
#endif