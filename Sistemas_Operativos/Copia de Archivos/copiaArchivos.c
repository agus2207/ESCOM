#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <dirent.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <string.h>
#include <fcntl.h>

void *hilo(char *arg);
pthread_t id_hilo;
char *destino;

int main(void){
	char *origen = (char*)malloc(sizeof(char));
	destino = (char*)malloc(sizeof(char));
	printf("Ingrese la ruta de origen: ");
	scanf("%s", origen);
	printf("Ingrese la ruta de destino: ");
	scanf("%s", destino);
	
	/*for(i=2; i<n; i++){
		pthread_create(&id_hilo, NULL, (void*)hilo, NULL);
		pthread_join(id_hilo, NULL);
		printf("%s\n", namelist[i]->d_name);
        free(namelist[i]);
	}
	free(namelist);*/
	
	pthread_create(&id_hilo, NULL, (void*)hilo, origen);	
	pthread_join(id_hilo, NULL);
	
	return 0;
}
 void *hilo(char *arg){
 	printf("Hilo creado\n");
 	DIR *directorio;
 	struct stat estru, estruA;
 	struct dirent **namelist;
 	struct dirent *d;
 	int n, dir, archivoO, archivoD;
 	mode_t modo;
 	char *texto = (char*)malloc(sizeof(char));
 	//if((directorio=opendir(arg)) != NULL){
	 	/*if(chdir(destino)==0){
	 		printf("Carpeta creada\n");
	 		dir = mkdir(destino, 0777);
	 	}*/
	 	//if(chdir(arg)==0){
		 	n = scandir(arg, &namelist, NULL, alphasort);
		 	printf("%d\n", n);
		 	while(n--){
		 	//printf("Copiando: %s\n", namelist[n]->d_name);
		 		if(strcmp(namelist[n]->d_name, ".")!=0 && (strcmp(namelist[n]->d_name, "..")!=0)){
		 			stat(namelist[n]->d_name, &estru);
		 			if(S_ISDIR(estru.st_mode)){	//si es directorio
		 				printf("Directorio: %s\n", namelist[n]->d_name);
		 				pthread_create(&id_hilo, NULL, (void*)hilo, namelist[n]->d_name);	
		 				pthread_join(id_hilo, NULL);
					}
					else{
		 				printf("Archivo: %s \n", namelist[n]->d_name);
		 				if(chdir(arg)==0){
		 					printf("Intentando Copiar\n");
			 				archivoO = open(namelist[n]->d_name, O_RDWR);
			 				if(archivoO==-1){
			 					printf("No se pudo copiar archivo origen\n");
			 				}
			 				else{
				 				if(read(archivoO, texto, 20) == -1){
				 					printf("Error al copiar archivo\n");
				 				}
				 				else{
				 					printf("Copiando %s\n", texto);
				 					stat(namelist[n]->d_name,&estruA);
				 					modo = estruA.st_mode;
				 				
					 				if(chdir(destino)==0){
					 					printf("Copiando a %s: \n", destino);
					 					archivoD = creat(namelist[n]->d_name, modo);
						 				if(archivoD == -1){
						 					printf("No se puede crear el archivo copia\n");
						 				}	
						 				else{
						 					archivoD = open(namelist[n]->d_name, O_RDWR);
						 					if(archivoD != -1)	
						 						if(write(archivoD, texto, strlen(texto))==0)
						 							printf("No se copio el archivo\n");
						 						else
						 							printf("Archivo copado\n");
						 				}
					 				}
					 				close(archivoD);
					 				close(archivoO);
					 				chmod(namelist[n]->d_name, modo);
					 			}
				 			}
				 		}
				 		else
				 			printf("No se puede cambiar a la carpeta %s\n", arg);
				 		//chdir(arg);
					}
				//	free(namelist[n]);
		 		}
		 		//free(namelist);
		 		
		 	}
		 	//chdir("..");
		 //}
	/*}
		
	 else
	 	printf("Error\n");	
 	closedir(directorio);*/
 }