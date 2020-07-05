/*
AUTOR: Edgardo Adrián Franco Martínez (C) Febrero 2011
VERSIÓN: 1.0 

DESCRIPCIÓN: Programa que demuestra el uso de archivos en mod texto y binario
Problema: Escribe un programa en C que solicite los datos de 10 empleados y los almacene 
en un archivo plano (“empleados.txt”).  Los datos que deberán solicitarse para cada empleado, 
son los siguientes:
#empleado (entero).
Nombre del empleado (cadena de máximo 45 caracteres).
Departamento (cadena de máximo 45 caracteres).
Salario (real).
Domicilio (cadena de máximo 100 caracteres).

OBSERVACIONES: Se definio NUM_EMP como constante para hacer más compatible el programa

COMPILACIÓN: gcc problema_empleados.c -o problema_empleados
EJECUCIÓN: problema_empleados.exe (En Windows) - ./problema_empleados (En Linux)
*/
//LIBRERAS
#include<stdio.h>

//DEFINICIÓN DE ESTRUCTURAS Y CONSTANTES
//Número de empleados
#define NUM_EMP 5

//Definición de la estructura
typedef struct empleado
{
	int no_empleado;
	char nombre[45];
	char departamento[45];
	float salario;
	char direccion[100];
}empleado;


//DECLARACIÓN DE FUNCIONES
/*Procedimiento para capturar por medio de la entrada estandar los empleados 
(Recibe la referencia al arreglo de empleados)*/
void CapturaEmpleados(empleado *lista);
/*Procedimiento para mostrar en la salida estandar los empleados 
(Recibe la referencia al arreglo de empleados)*/
void ImprimeEmpleados(empleado *lista);
/*Procedimiento para guardar en un archivo de texto a los empleados 
(Recibe la referencia al arreglo de empleados y la ruta del archivo a escribir)*/
void GuardaEmpleadosTexto(empleado *lista, char* ruta_escribir);
/*Procedimiento para leer de un archivo de texto a los empleados 
(Recibe la referencia al arreglo de empleados y la ruta del archivo a leer)*/
void LeeEmpleadosTexto(empleado *lista, char* ruta_leer);
/*Procedimiento para guardar en un archivo de binario a los empleados 
(Recibe la referencia al arreglo de empleados y la ruta del archivo a escribir)*/
void GuardaEmpleadosBinario(empleado *lista, char* ruta_escribir);
/*Procedimiento para leer de un archivo binario a los empleados 
(Recibe la referencia al arreglo de empleados y la ruta del archivo a leer)*/
void LeeEmpleadosBinario(empleado *lista, char* ruta_leer);

//PROGRAMA PRINCIPAL
int main(void)
{
	//Arreglo de NUM_EMP estructuras empleado
	empleado lista[NUM_EMP];
	//Solicitar los NUM_EMP empleados
	CapturaEmpleados(lista);
	//Mostrar en salida estandar la lista de empleados
	ImprimeEmpleados(lista);
	//Guarda los empleados en un archivo de texto
	GuardaEmpleadosTexto(lista,"empleados.txt");
	//Guarda los empleados en un archivo binario
	GuardaEmpleadosBinario(lista,"empleados.dat");
	//Recupera y muestra los empleados del archivo en modo texto (*Comprobación)
	LeeEmpleadosTexto(lista,"empleados.txt");
	printf("\n\n*Empleados recuperados del archivo \"empleados.txt\"\n\n*");
	ImprimeEmpleados(lista);
	//Recupera y muestra los empleados del archivo en modo binario (*Comprobación)
	LeeEmpleadosBinario(lista,"empleados.dat");
	printf("\n\n*Empleados recuperados del archivo \"empleados.dat\"\n\n*");
	ImprimeEmpleados(lista);

	
	return 0;
}


//DEFINICIÓN DE FUNCIONES
/*
void CapturaEmpleados(empleado *lista);
Descripción: Procedimiento para capturar por medio de la entrada estandar los empleados 
Recibe: empleado *lista (Referencia al arreglo de empleados)
Devuelve:
Observaciones: Si la esctructura empleado se modifica, también se modificará esta función
*/
void CapturaEmpleados(empleado *lista)
{
	int i;
	for(i=0;i<NUM_EMP;i++)
	{
		printf("\n\n\nEmpleado %d de la lista",i+1);	
		printf("\nIntroduce el número de empleado: ");
		scanf("%d",&lista[i].no_empleado);
		printf("\nIntroduce el nombre de empleado: ");
		scanf("%s",&lista[i].nombre);
		printf("\nIntroduce el departamento del empleado: ");
		scanf("%s",&lista[i].departamento);
		printf("\nIntroduce el salario de empleado: ");
		scanf("%f",&lista[i].salario);
		printf("\nIntroduce la dirección del empleado: ");
		scanf("%s",&lista[i].direccion);
	}
}

/*
void ImprimeEmpleados(empleado *lista);
Descripción: Procedimiento para mostrar en la salida estandar los empleados 
Recibe: empleado *lista (Referencia al arreglo de empleados)
Devuelve:
Observaciones: Si la esctructura empleado se modifica, también se modificará esta función
*/
void ImprimeEmpleados(empleado *lista)
{
	int i;
	for(i=0;i<NUM_EMP;i++)
	{
		printf("\n\n\nEmpleado #%d",lista[i].no_empleado);
		printf("\nNombre: %s",lista[i].nombre);
		printf("\nDepartamento: %s",lista[i].departamento);
		printf("\nSalario: %.2f",lista[i].salario);
		printf("\nDirección: %s",lista[i].direccion);
	}
}

/*
void GuardaEmpleadosTexto(empleado *lista, char* ruta_escribir);
Descripción: Procedimiento para guardar en un archivo de texto a los empleados
Recibe: empleado *lista (Referencia al arreglo de empleados), char *ruta(Cadena con la ruta del archivo a escribir) 
Devuelve:
Observaciones: Si la esctructura empleado se modifica, también se modificará esta función, el archivo a escribir se maneja en modo texto.
*/
void GuardaEmpleadosTexto(empleado *lista, char* ruta_escribir)
{
	int i; 
	FILE *archivo;
	archivo=fopen(ruta_escribir,"w+dg");
	for(i=0;i<NUM_EMP;i++)
	{
		fprintf(archivo,"\n\nEmpleado %d de la lista",i+1);	
		fprintf(archivo,"\nNúmero de empleado: %d",lista[i].no_empleado);
		fprintf(archivo,"\nNombre de empleado: %s",lista[i].nombre);
		fprintf(archivo,"\nDepartamento del empleado: %s",lista[i].departamento);
		fprintf(archivo,"\nSalario de empleado: %f",lista[i].salario);
		fprintf(archivo,"\nDirección del empleado: %s",lista[i].direccion);	
	}
	fclose(archivo);
}
/*
void LeeEmpleadosTexto(empleado *lista, char* ruta_escribir);
Descripción: Procedimiento para leer de un archivo de texto a los empleados
Recibe: empleado *lista (Referencia al arreglo de empleados), char *ruta(Cadena con la ruta del archivo a escribir) 
Devuelve:
Observaciones: Si la esctructura empleado se modifica, o si se cambia la la manera de guardar a los empleados, 
también se modificará esta función, el archivo a escribir se maneja en modo texto.
*/
void LeeEmpleadosTexto(empleado *lista, char* ruta_escribir)
{
	int i,n; 
	FILE *archivo;
	archivo=fopen(ruta_escribir,"r+");
	for(i=0;i<NUM_EMP;i++)
	{
		fscanf(archivo,"\n\nEmpleado %d de la lista",&n);	
		fscanf(archivo,"\nNúmero de empleado: %d",&lista[i].no_empleado);
		fscanf(archivo,"\nNombre de empleado: %s",&lista[i].nombre);
		fscanf(archivo,"\nDepartamento del empleado: %s",&lista[i].departamento);
		fscanf(archivo,"\nSalario de empleado: %f",&lista[i].salario);
		fscanf(archivo,"\nDirección del empleado: %s",&lista[i].direccion);	
	}
	fclose(archivo);
}
/*
void GuardaEmpleadosBinario(empleado *lista, char* ruta_escribir);
Descripción: Procedimiento para guardar en un archivo binario a los empleados
Recibe: empleado *lista (Referencia al arreglo de empleados), char *ruta(Cadena con la ruta del archivo a escribir) 
Devuelve:
Observaciones:
*/
void GuardaEmpleadosBinario(empleado *lista, char* ruta_escribir)
{
	int i; 
	FILE *archivo;
	archivo=fopen(ruta_escribir,"wb+");
	fseek(archivo,0,SEEK_SET);	
	fwrite(lista,sizeof(empleado),NUM_EMP,archivo);	
	fclose(archivo);
}

/*
void LeeEmpleadosBinario(empleado *lista, char* ruta_escribir);
Descripción: Procedimiento para leer de un archivo binario a los empleados
Recibe: empleado *lista (Referencia al arreglo de empleados), char *ruta(Cadena con la ruta del archivo a escribir) 
Devuelve:
Observaciones: Si la esctructura empleado se modifica, también se modificará esta función, el archivo a escribir se maneja en modo binario.
*/
void LeeEmpleadosBinario(empleado *lista, char* ruta_escribir)
{
	int i,n; 
	FILE *archivo;
	archivo=fopen(ruta_escribir,"rb+");
	fread(lista,sizeof(empleado),NUM_EMP,archivo);
	fclose(archivo);
}
