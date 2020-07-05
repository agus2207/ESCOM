/*
AUTOR: Agustin Galindo Reyes       Agosto 2016

DESCRIPCION: Programa solucion al ejercicio 2: "programa capaz de generar y recibir dos matrices dinámicas de tamaño m por n, donde m y
n ingresan por la entrada estándar, además de los valores de las matrices, el programa realiza una operación de suma y resta de matrices
que se muestra por la salida estándar". El programa se base en el diagrama de flujo que nos otorgo el profesor.
 
OBSERVACIONES:
El programa recibe m y n que son el numero de filas y columnas, y recibe los valores de cada coordenada, el programa solo reconoce 
numeros enteros, el meter otro tipo de dato causara un error.

COMPILACION: gcc programa.cpp
EJECUCION: Programa.exe (En Windows)
*/

//LIBRERIAS
#include<stdio.h> //Incluye las funciones estandar de entrada y salida
#include<stdlib.h> //Incluye las funciones de busqueda y ordenamiento de datos

//DECLARACION DE FUNCIONES
//Aparta el espacio de memoria para la matriz 1
int **Aparta_Matriz(int m,int n);
//Aparta el espacio de memoria para la matriz 2
int **Aparta_Matriz2(int m,int n); 
//Aparta el espacio de memoria para la matriz Resultante 
int **Aparta_Matriz3(int m,int n); 
//Capturar la matriz 1
void Capturar_Matriz(int **matriz,int m,int n);
//Capturar la matriz 2
void Capturar_Matriz2(int **matriz2,int m,int n);
//Suma la matriz 1 con la matriz 2
void Suma_Matriz(int **matriz3,int **matriz,int **matriz2,int m,int n);
//Resta la matriz 1 con la matriz 2
void Resta_Matriz(int **matriz3,int **matriz,int **matriz2,int m,int n);
//Imprime la matriz 1
void Imprime_Matriz(int **matriz,int m,int n);
//Imprime la matriz 2
void Imprime_Matriz2(int **matriz2,int m,int n);
//Imprime la matriz resultante
void Imprime_Matriz3(int **matriz3,int m,int n);

//PROGRAMA PRINCIPAL
int main(void)
{
	int m,n,**matriz,**matriz2,**matriz3;
	int i,j;
	//Leer m y n
	printf("\nIntroduce numero de filas: \n");
	scanf("%d",&m);
	printf("\n");
	printf("\nIntroduce numero de columnas: \n");
	scanf("%d",&n);
	printf("\n");
	//Apartar espacio para la matriz 1
	matriz=Aparta_Matriz(m,n);
	//Apartar espacio para la matriz 2
	matriz2=Aparta_Matriz2(m,n);
	//Apartar espacio para la matriz 3
	matriz3=Aparta_Matriz3(m,n);
	//Llenar la matriz 1 de datos
	Capturar_Matriz(matriz,m,n);
	//Llenar la matriz 2 de datos
	Capturar_Matriz2(matriz2,m,n);
	//Mostrar la matriz	1
	printf("\n***Matriz 1***");
	Imprime_Matriz(matriz,m,n);
	//Mostrar la matriz 2	
	printf("\n***Matriz 2***");
	Imprime_Matriz2(matriz2,m,n);
	//Suma la matriz 1 con la matriz 2
	printf("\n***Matriz Sumada***");
	Suma_Matriz(matriz3,matriz,matriz2,m,n);
	Imprime_Matriz3(matriz3,m,n); //Mostrar la matriz	Resultante
    //Resta la matriz 1 con la matriz 2
	printf("\n***Matriz Restada***");
	Resta_Matriz(matriz3,matriz,matriz2,m,n);
	Imprime_Matriz3(matriz3,m,n); //Mostrar la matriz	Resultante
	void free(void **matriz); //Libera espacio no utilizado para otros procesos
	void free(void **matriz2); //Libera espacio no utilizado para otros procesos
	void free(void **matriz3); //Libera espacio no utilizado para otros procesos
	system("pause");
}

/*
int **Aparta_Matriz(int m,int n);
Descripcion: Aparta el espacio de memoria para la matriz 1
Recibe: int m (Numero de filas), int n (Numero de columnas)
Devuelve: int **matriz (Referencia a la matriz apartada), 
Observaciones: m y n deberan ser mayor a 0
*/
int **Aparta_Matriz(int m,int n)
{
	int i, **matriz;
	//Apartar memoria para la matriz
	matriz=(int**)malloc(m*sizeof(int*)); //m apuntadores simples a enteros
	for(i=0;i<m;i++)
		matriz[i]=(int*)malloc(n*sizeof(int*)); //El apuntador simple matriz[i] apunta a n enteros 
	return matriz;	//Regresa la referencia al apuntador principal a la matriz 1
}

/*
int **Aparta_Matriz2(int m,int n);
Descripcion: Aparta el espacio de memoria para la matriz 2
Recibe: int m (Numero de filas), int n (Numero de columnas)
Devuelve: int **matriz2 (Referencia a la matriz apartada), 
Observaciones: m y n deberan ser mayor a 0
*/
int **Aparta_Matriz2(int m,int n)
{
	int i, **matriz2;
	//Apartar memoria para la matriz 2
	matriz2=(int**)malloc(m*sizeof(int*)); //m apuntadores simples a enteros
	for(i=0;i<m;i++)
		matriz2[i]=(int*)malloc(n*sizeof(int*)); //El apuntador simple matriz2[i] apunta a n enteros 
	return matriz2;	//Regresa la referencia al apuntador principal a la matriz 2	
}

/*
int **Aparta_Matriz3(int m,int n);
Descripcion: Aparta el espacio de memoria para la matriz resultante
Recibe: int m (Numero de filas), int n (Numero de columnas)
Devuelve: int **matriz3 (Referencia a la matriz apartada), 
Observaciones: m y n dependeran de las 2 matrices anteriores
*/
int **Aparta_Matriz3(int m,int n)
{
	int i, **matriz3;
	//Apartar memoria para la matriz resultante
	matriz3=(int**)malloc(m*sizeof(int*)); //m apuntadores simples a enteros
	for(i=0;i<m;i++)
		matriz3[i]=(int*)malloc(n*sizeof(int*)); //El apuntador simple matriz3[i] apunta a n enteros 
	return matriz3;	//Regresa la referencia al apuntador principal a la matriz resultante	
}

/*
void Capturar_Matriz(int **matriz,int m,int n);
Descripcion: Capturar la matriz 1
Recibe: int **matriz (Referencia a la matriz 1), int m (Numero de filas), int n (Numero de columnas)
Observaciones: La matriz 1 fue reservada previamente de tamaño m por n
*/
void Capturar_Matriz(int **matriz,int m,int n)
{
	int i,j;
	printf("Matriz 1");
	for(i=0;i<m;i++)
	{
	printf("\n");
		for(j=0;j<n;j++)
		{
			//Leer dato por dato de la matriz 1
			printf("\nIntroduce el elemento[%d][%d]: ",i,j);
			scanf("%d",&matriz[i][j]);	
		}
    }
    printf("\n\n");
}

/*
void Capturar_Matriz2(int **matriz2,int m,int n);
Descripcion: Capturar la matriz 2
Recibe: int **matriz2 (Referencia a la matriz 2), int m (Numero de filas), int n (Numero de columnas)
Observaciones: La matriz 2 fue reservada previamente de tamaño m por n
*/
void Capturar_Matriz2(int **matriz2,int m,int n)
{
	int i,j;
	printf("Matriz 2");
	for(i=0;i<m;i++)
	{
	printf("\n");
		for(j=0;j<n;j++)
		{
			//Leer dato por dato de la matriz 2
			printf("\nIntroduce el elemento[%d][%d]: ",i,j);
			scanf("%d",&matriz2[i][j]);	
		}
    }
}


/*
void Imprime_Matriz(int **matriz,int m,int n);
Descripcion: Procedimiento para imprimir la matriz 1
Recibe: int **matriz (Referencia a la matriz 1), int m (Numero de filas), int n (Numero de columnas)
Devuelve: La visualizacion en la pantalla de la matriz 1 
Observaciones: La matriz fue reservada previamente de tamaño m por n
*/
void Imprime_Matriz(int **matriz,int m,int n)
{
	int i,j;
	for(i=0;i<m;i++)
	{
		printf("\n");
		for(j=0;j<n;j++)
			printf("%d\t",matriz[i][j]);
	}
}

/*
void Imprime_Matriz2(int **matriz2,int m,int n);
Descripcion: Procedimiento para imprimir la matriz 2
Recibe: int **matriz (Referencia a la matriz 2), int m (Numero de filas), int n (Numero de columnas)
Devuelve: La visualizacion en la pantalla de la matriz 2
Observaciones: La matriz 2 fue reservada previamente de tamaño m por n
*/
void Imprime_Matriz2(int **matriz2,int m,int n)
{
	int i,j;
	for(i=0;i<m;i++)
	{
		printf("\n");
		for(j=0;j<n;j++)
			printf("%d\t",matriz2[i][j]);
	}
}

/*
void Imprime_Matriz3(int **matriz3,int m,int n);
Descripcion: Procedimiento para imprimir la matriz resultante
Recibe: int **matriz3 (Referencia a la matriz 3), int m (Numero de filas), int n (Numero de columnas)
Devuelve: La visualizacion en la pantalla de la matriz resultante
Observaciones: La matriz 3 fue reservada previamente de tamaño m por n
*/
void Imprime_Matriz3(int **matriz3,int m,int n)
{
	int i,j;
	for(i=0;i<m;i++)
	{
		printf("\n");
		for(j=0;j<n;j++)
			printf("%d\t",matriz3[i][j]);
	}
}

/*
void Suma_Matriz(int **matriz3,int **matriz, int **matriz2,int m,int n)
Descripcion: Procedimiento para sumar la matriz 1 con la 2
Recibe: int **matriz3 (Referencia a la matriz 3), int **matriz (Referencia a la matriz 1), int **matriz2 (Referencia a la matriz 2),
int m (Numero de filas), int n (Numero de columnas)  
Devuelve: La suma de las matrices 1 y 2
Observaciones: La matriz 3 fue reservada previamente de tamaño m por n
*/
void Suma_Matriz(int **matriz3,int **matriz, int **matriz2,int m,int n)
{
    int i,j;
	for(i=0;i<m;i++)
       {
                       
          for(j=0;j<n;j++)
          {             
             matriz3[i][j]=(matriz[i][j] + matriz2[i][j]); //Realiza la operacion de suma de matrices
          }
       } 
}

/*
void Resta_Matriz(int **matriz3,int **matriz, int **matriz2,int m,int n)
Descripcion: Procedimiento para restar la matriz 1 con la 2
Recibe: int **matriz3 (Referencia a la matriz 3), int **matriz (Referencia a la matriz 1), int **matriz2 (Referencia a la matriz 2),
int m (Numero de filas), int n (Numero de columnas)  
Devuelve: La resta de las matrices 1 y 2
Observaciones: La matriz 3 fue reservada previamente de tamaño m por n
*/
void Resta_Matriz(int **matriz3,int **matriz, int **matriz2,int m,int n)
{
    int i,j;
	for(i=0;i<m;i++)
       {
                       
          for(j=0;j<n;j++)
          {             
             matriz3[i][j]=(matriz[i][j]-matriz2[i][j]); //Realiza la operacion de resta de matrices
          }
       } 
}


