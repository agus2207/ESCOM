#include <stdio.h>  
#include <stdlib.h>
#include <dirent.h>
#include <Windows.h>
#include <time.h>  
#include <sys/types.h>  
#include <sys/stat.h>  
#include <errno.h> 
#define MAX 100

int main (int argc, char **argv[]){
	DIR *dirp;
 	struct dirent *direntp;
	struct stat buf;  
	int cont=0, i=0, result=0;
	char timebuf[26];  
	char *filename= (char*)malloc(sizeof(char)*MAX);
	char *direccion= (char*)malloc(sizeof(char)*MAX);
	char *txt= (char*)malloc(sizeof(char)*MAX);
	//errno_t err;
	printf("\t\t\tVisualizador de datos de un archivo\n\n");
	printf("Ingresa la direcci%cn de la carpeta: ", 162);
	gets(direccion);
	printf("\n");
	dirp= opendir(direccion);
	if(dirp==NULL){
		printf("Ha ocurrido un error\n");
	}
 	while ((direntp = readdir(dirp)) != NULL) {
		printf("%d.- %s\n", cont+1, direntp->d_name);
		cont++;
 	}
 	closedir(dirp);
	SetCurrentDirectory(direccion);
	printf("\n\n");
	for (i=0; i<cont-2; i++){
			sprintf(filename, "%d", i);
			txt=".txt";
  			strcat(filename, txt);
  			printf("%s\n", filename);
  			result= stat(filename, &buf);
			if (result!=0){
				perror("Ha ocurrido un error");
				switch (errno){
					case ENOENT:{
						printf("No se ha encontrado la ruta %s\n", filename); 
						break;
					}
					case EINVAL:{
						printf("Parametro invalido\n");
						break;
					}
					default:{
						printf("Error inesperado\n");
						break;
					}
				}
			}else{
				printf("Tamanio del dispositivo en : %ld bytes\n", buf.st_size);
      			printf( "Tiempo de modificaci%cn: %s\n", 162, ctime(&buf.st_mtime));
			}
		}
	return 0;
}
