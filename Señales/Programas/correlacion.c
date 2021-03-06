#include <stdio.h>
#include <stdlib.h>

void imprimir(int a[], int b, int c){ //Funcion que imprime los arreglos
	for(int i = 0; i <b; i++){
		printf("A[%d] = %d\n", c, a[i]);
		c=c+1;
	}
}

void llenar (int a[], int b){ //Funcion que llena los arreglos
	int valor;
	for (int i = 0; i < b; i++){ 
		 scanf("%d",&valor);
		 a[i] = valor;
	}
}

int correlacion(int x[], int y[], int n, int tamx, int tamy, int negx, int negy){
	int res; //Variables que guardaran el resultado y el desplazamiento en la LIT invertida
	res=0;
	if(tamx>tamy || tamx == tamy){ //Si los tamaños de x y LIT coinciden o el tamaño de LIT es mas grande que el de x entramos a esta condicional
		int j=0; //Variable auxiliar para recorrer los arreglos
		for(int i=0; i<tamx; i++){ //Recorremos el arreglo de mayor tamaño
			if(negx == negy){ //Si ambos arreglos tienen el mismo inicio en el eje x entramos a esta condicional
				if(j>=tamy || i>=tamx) //Si j sobrepasa el tamaño de la LIT o i sobrepasa el tamaño de la LIT invertida al resultado le sumamos 0
					res = res+0;
				else //Si no al resultado le sumamso la multiplicacion del valor de la LIT invertida en la posicion i y el valor de x en la posicion j
					res = res+(x[i]*y[j]);
				negy=negy+1; //Recorremos el ultimo valor usado en el eje x de x[n]
				negx=negx+1; // Recorremos el ultimo valor usado en el eje de las x en la LIT invertida
			}
			else if (negx > negy){ //Si la posicion de x[n] no coincide con la de la LIT invertida entramos a esta condicional
				do{
					negy=negy+1; //Incrementamos la posicion de la LIT invertida hasta que coincida con la de x[n]
					j++;
				}while(negx != negy);
				if(j>=tamy || i>=tamx) //Si j sobrepasa el tamaño de la LIT o i sobrepasa el tamaño de la LIT invertida al resultado le sumamos 0
					res = res+0;
				else //Si no al resultado le sumamso la multiplicacion del valor de la LIT invertida en la posicion i y el valor de x en la posicion j
					res = res+(x[i]*y[j]);
				negy=negy+1; //Recorremos el ultimo valor usado en el eje x de x[n]
				negx=negx+1; // Recorremos el ultimo valor usado en el eje de las x en la LIT invertida 
			}
			else if (negy > negx){ //Si la posicion de la LIT invertida no coincide con la señal de entrada entramos a esta condicional
				do{
					negx=negx+1; //Incrementamos la posicion de la señal de entrada invertida hasta que coincida con la de x[n]
					i++;
				}while(negy != negx);
				if(j>=tamy || i>=tamx) //Si j sobrepasa el tamaño de la LIT o i sobrepasa el tamaño de la LIT invertida al resultado le sumamos 0
					res = res+0;
				else
					res = res+(x[i]*y[j]);
				negy=negy+1; //Recorremos el ultimo valor usado en el eje x de x[n]
				negx=negx+1;  // Recorremos el ultimo valor usado en el eje de las x en la LIT invertida
			}
			j++; //Aumentamos j que represenata la posicion de la señal de entrada
		}
	}
	else if(tamy>tamx){ //Si la señal de entrada es mayor a la LIT invertida entra a esta condicional y se repiten las operaciones de la condicional anterior
		int j=0;
		for(int i=0; i<tamy; i++){
			if(negx == negy){
				if(j>=tamx || i>=tamy)
					res = res+0;
				else
					res = res+(x[j]*y[i]);
				negy=negy+1;
				negx=negx+1;
			}
			else if (negx > negy){
				do{
					negy=negy+1;
					i++;
				}while(negx != negy);
				if(j>=tamx || i>=tamy)
					res = res+0;
				else
					res = res+(x[j]*y[i]);
				negy=negy+1;
				negx=negx+1;
			}
			else if (negy > negx){
				do{
					negx=negx+1;
					j++;
				}while(negy != negx);
				if(j>=tamx || i>=tamy)
					res = res+0;
				else
					res = res+(x[j]*y[i]);
				negy=negy+1;
				negx=negx+1;
			}
			j++;
		}
	}
	return res;
}

void inicio(){
	int n, size1, size2, desx, desy, neg1, neg2, opcion, aux1, aux2;
	printf("Ingrese la cantidad de muestras: \n"); //Se pide la cantidad de muestras
	scanf("%d", &n);
	printf("Ingrese el tamaño de la señal x(n) \n"); //Se pide el tamaño de la LIT
	scanf("%d", &size1);
	int x[size1];
	printf("Ingrese los valores de x(n): \n"); //Se piden los datos de la LIT
	llenar(x, size1);
	printf("Ingrese el tamaño de la señal de y(n): \n"); //Se pide el tamaño de la señal de entrada
	scanf("%d", &size2);
	int y[size2];
	printf("Ingrese los datos de y(n): \n"); //Se piden los datos de la señal de entrada
	llenar(y, size2);
	printf("Ingrese el menor valor de x(n) en el eje x: \n"); //Donde inicia en el eje de las x la LIT
	scanf("%d", &neg1);
	printf("Ingrese el menor valor de y(n) en el eje x: \n"); //Donde inicia en el eje de las x la señal de entrada
	scanf("%d", &neg2);
	int res[n];
	printf("x(n): \n"); //Se imprime LIT en pantalla
	imprimir(x, size1, neg1);
	printf("y(n): \n"); //Se imprime la señal de entrada en pantalla
	imprimir(y, size2, neg2);
	for(int i=0; i<n; i++){
		printf("Ingrese a que senal le desea hacer un corrimiento: \n");
		printf("1.x(n) \n");
		printf("2.y(n) \n");
		printf("3.Ambos \n");
		printf("4.Ninguno \n");
		scanf("%d", &opcion);
		switch(opcion){
			case 1:
			printf("Ingrese el desplazamiento en x(n): \n");
			scanf("%d", &desx);
			aux1=desx+neg1;
			res[i] = correlacion(x, y, n, size1, size2, aux1, neg2);
			printf("X[%d] = %d \n", desx, correlacion(x, y, n, size1, size2, aux1, neg2));
			break;

			case 2:
			printf("Ingrese el desplazamiento en y(n): \n");
			scanf("%d", &desy);
			aux2 = desy+neg2;
			res[i] = correlacion(x, y, n, size1, size2, neg1, aux2);
			printf("X[%d] = %d \n", desy, correlacion(x, y, n, size1, size2, neg1, aux2));
			break;

			case 3:
			printf("Ingrese el desplazamiento en x(n): \n");
			scanf("%d", &desx);
			printf("Ingrese el desplazamiento en y(n): \n");
			scanf("%d", &desy);
			aux1 = desx+neg1;
			aux2 = desy+neg2;
			res[i] = correlacion(x, y, n, size1, size2, aux1, aux2);
			printf("X[%d] = %d \n", desx, correlacion(x, y, n, size1, size2, aux1, aux2));
			break;

			case 4:
			res[i] = correlacion(x, y, n, size1, size2, neg1, neg2);
			printf("X[0] = %d \n", correlacion(x, y, n, size1, size2, neg1, neg2));
			break;

			default:
			printf("Seleccione una opcion correcta \n");
		}

	}
}

int main(){
	printf("BIENVENIDO A LA CORRELACION CRUZADA \n\n");
	char opcion;
	do{ //do while para que el programa finalice cuando el usuario precione una tecla diferente a S
		inicio();
		printf("\nDesea Continuar \n");
		printf("Si ------> S \n");
		printf("No ------> N \n");
		fflush(stdin);
		scanf("%c", &opcion);
		printf("\n");
	}while(opcion == 'S' || opcion == 's');
}