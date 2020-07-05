#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>

#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

int toOctal(int decimal);
int calculaPermiso(char *permisos);

int main(){
	char *dir = (char*)malloc (sizeof(char));
	char *archivo = (char*)malloc (sizeof(char));
	char *permisos = (char*)malloc(sizeof(char));
	char *permisosf = (char*)malloc(sizeof(char));
	int permiso[4], i, aux;
	permiso[0] = 0;

	printf("PROGRAMA PARA CAMBIAR LOS PERMISOS DE UN ARCHIVO\n");
	printf("Ingrese el nombre de la carpeta: ");
	scanf("%s", dir);
	if(chdir(dir) == 0){
		printf("Ingrese el nombre del archivo: ");
		scanf("%s", archivo);
		printf("r: Permiso de lectura\nw: Permiso de escritura\nx:permiso de ejecucion\n-: Sin permisos\nEscriba las letras de los prermisos que desea otorgar al");
		
		printf("\nPermisos del Propietario: ");
		scanf("%s", permisos);
		aux = calculaPermiso(permisos);
		permiso [1] = toOctal(aux);
		printf("Permiso del Propietario en octal: %d\n", permiso[1]);

		printf("Permisos de Grupo: ");
		scanf("%s", permisos);
		permiso[2] = toOctal(calculaPermiso(permisos));
		printf("Permiso de grupo en octal: %d\n", permiso[2]);

		printf("Permisos a Otros: ");
		scanf("%s", permisos);
		permiso[3] = toOctal(calculaPermiso(permisos));
		printf("Permiso de otros en octal: %d\n", permiso[3]);
/*
		for(i=0; i<4; i++)
			permisos[i] = (char) permiso[i];
*/		//printf("%s\n", permisos);

		sprintf(permisosf, "%d%d%d%d", permiso[0], permiso[1], permiso[2], permiso[3]);
		int p = strtol(permisosf, 0, 8);
		printf("Permisos dados: %s\n",permisosf);
		if(chmod(archivo, p) == 0)
			printf("Permisos cambiados correctamente\n");
		else
			printf("No se pudieron cambiar los permisos\n");
	}
	else
		printf("El directorio %s no existe\n", dir);
}

int calculaPermiso(char *permisos){			//rwx   421
	int i, p=0;
	
	for(i=0; i<strlen(permisos); i++){
		if(tolower(permisos[i]) == 'r')	
			p += 4;
		if(tolower(permisos[i]) == 'w')
			p += 2;
		if(tolower(permisos[i]) == 'x')
			p++;
	}
	return p;
}

int toOctal(int decimal){
	int i=1, octal = 0;
	while(decimal!=0){
		octal += (decimal%8)*i;
		decimal/=8;
		i*=10;
	}

	return octal;
}