#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
#define MAX 100
#define TAM 5
int main (int argc, char **argv[]){
	srand(time(NULL));
	char *cadena= (char*)malloc(sizeof(char)*MAX);
	char *cad[]= {"I’m feeling lucky", "Si no fuera por el C, estariamos escribiendo programas en BASI, PASAL o OBOL", "Lo siento, Dave. No puedo hacer eso", "Para definir la recursividad, primero debemos definir la recursividad", "La respuesta a la vida, el universo y todo lo demas",
	 "Si le das a alguien un programa, lo frustaras un dia. Si le ensenias a programar, lo frustaras toda la vida", "La vida seria mucho mas sencilla si pudieramos echar un vistazo al codigo fuente", "No le atribuyas cualidades humanas a los ordenadores. No les gusta",
	  "Los programadores de verdad no documentan. Si fue difícil de escribir, debe ser difícil de entender", "Cuídate de los programadores que llevan destornilladores"};
	DWORD bytesWritten=0;
	LPOFSTRUCT lpReOpenBuff= OFS_MAXPATHNAME;
	UINT uStyile= OF_READ;
	LPCTSTR lpPathName;
	LPSECURITY_ATTRIBUTES lpSecurityAttributes=NULL;
	HANDLE hFile;
	LPCTSTR lpFileName;
  	DWORD dwDesiredAccess;
 	DWORD dwShareMode;
  	DWORD dwCreationDisposition;
  	DWORD dwFlagsAndAttributes;
  	HANDLE hTemplateFile;
	int i;
	printf("\t\t\tGenerador de directorios y archivos\n\n\n");
	printf("Ingresa la ruta y el nombre del directorio a generar: \n");
	gets(cadena);
	lpPathName= cadena;
	char *num= (char*)malloc(sizeof(char)*MAX);
	char *txt= (char*)malloc(sizeof(char)*MAX);
	if (CreateDirectory (lpPathName,lpSecurityAttributes)){
		printf("El directorio %s fue generado con exito\n", lpPathName);
  		dwDesiredAccess=FILE_APPEND_DATA;
 		dwShareMode=FILE_SHARE_READ;
  		lpSecurityAttributes=NULL;
  		dwCreationDisposition=OPEN_ALWAYS;
  		dwFlagsAndAttributes=FILE_ATTRIBUTE_NORMAL;
  		hTemplateFile=NULL;		
  		SetCurrentDirectory(cadena);
  		int random=0, random2=0, j;
  		for (i=0; i<10; i++){
			random= rand()%9;
		}
		
  		for(i=0; i<random; i++){
  			for (j=0; j<10; j++){
				random2= rand()%9;
			}
  			sprintf(num, "%d", i);
  			txt=".txt";
  			strcat(num, txt);
		  	lpFileName=(num);
			if(hFile= CreateFile(lpFileName, dwDesiredAccess, dwShareMode, lpSecurityAttributes, dwCreationDisposition, dwFlagsAndAttributes, hTemplateFile)){
				printf("El fichero %s se ha creado correctamente\n", num);
				WriteFile(hFile, cad[random2], strlen(cad[random2]), &bytesWritten, NULL);
			}else{
				printf("No se pudo generar el fichero\n");
			}
		}
		
		
	}else{
		printf("No se pudo generar el directorio %s\n", lpPathName);
	}
	return 0;
}
