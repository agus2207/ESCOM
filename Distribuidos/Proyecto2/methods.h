#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// :v

struct arr_ind {
	int dato;
	struct arr_ind * siguiente;
};

void imprime(struct arr_ind * p){
	struct arr_ind * cpy = p;	
	printf("El arreglo esta compuesto por \n[ ");
	while(cpy != NULL){
		printf("%d ",cpy -> dato);
		cpy = cpy -> siguiente;
	}
	printf("]\n");
}

struct arr_ind * agregar_valor(int valor,struct arr_ind * p){
	struct arr_ind * nueva = (struct arr_ind*) malloc(sizeof(struct arr_ind));

	nueva -> dato = valor;
	nueva -> siguiente = p;	

	p = nueva;

	return p;
}

int encontrar(int cmp,struct arr_ind * p){
	struct arr_ind * cpy = p;
	while(cpy != NULL){
		if(cmp == cpy -> dato){
			return 1;
		} else{
			cpy = cpy -> siguiente;
		}
	}
	return 0;
}

int maximo(struct arr_ind * p){
	int max = 0;
	struct arr_ind * cpy = p;
	while(cpy != NULL){
		if(max < cpy -> dato){
			max = cpy -> dato;
			cpy = cpy -> siguiente;
		}else{ /*No pasa nada */ cpy = cpy -> siguiente; }
	}
	return max;
}

// Cuales no llegaron y porcentaje de exito :)
void faltantes(struct arr_ind * p, char * c){
	strcat(c, "Lista de paquetes perdidos ");	
	char buffer[3];	
	struct arr_ind * cpy = p;
	int max, i , faltantes;
	faltantes = 0; 
	max = maximo(p);	
	for(i = 1;i < max;i++){
		if(encontrar(i,p)){}
		else{ //Concatena al mensaje
			snprintf(buffer,sizeof(buffer),"%d",i); 
			strcat(c, buffer);
			strcat(c, " ");
			faltantes ++;
		}			
	}
	float percent = ((float)faltantes/(float)max) * 100;
	int res = (int)percent;
	strcat(c,"\nPorcentaje de no enviados ");
	snprintf(buffer,sizeof(buffer),"%d",res);
	strcat(c, buffer);
	return;
}