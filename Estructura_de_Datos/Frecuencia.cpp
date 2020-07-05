#include<stdio.h>
#include<stdlib.h>
#include<math.h>
#define PI 3.1416
#define G 9.81
float periodo(float lon);
float frecuencia(float perio);
float velocidadm(float elo, float lon);
main ()
{
	float e,l,p,f,vm;
	printf("Introduzca el valor de la elongacion:\n\n\n");
	scanf("%f", &e);
	printf("Introduzca el valor de la longitud de la cuerda:\n\n\n");
	scanf("%f", &l);
	p=periodo(l);
	printf("El valor del periodo es:%f\n\n\n",p);
	f=frecuencia(p);
	printf("El valor de la frecuencia es:%f\n\n\n",f);
	vm=velocidadm(l,e);
	printf("El valor de la velocidad maxima es:%f\n\n\n",vm);
	system("PAUSE");
}

float periodo(float lon)
{
	float per;
	per=2*PI*sqrt(lon/G);
	return per;
}

float frecuencia(float perio)
{
	float fre;
	fre=1/perio;
	return fre; 
}

float velocidadm(float elo, float lon)
{
	float vel;
	vel=elo*sqrt(G/lon);
	return vel;
}
