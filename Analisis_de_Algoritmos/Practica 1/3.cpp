#include <stdio.h>
#include <stdlib.h>
int multiplicar(int x,int y){
	if(y==0)
		return 0;
	int z=multiplicar(x,y/2);
	if(y%2==0)
		return 2*z;
	else
		return x+(2*z);	
}
int main (){
	int x,y;
	printf("Ingrese numero x:\n");
	scanf("%d", &x);
	printf("Ingrese numero y:\n");
	scanf("%d", &y);
	int resultado=multiplicar(x,y);
	printf("resultado: %d",resultado);
} 

