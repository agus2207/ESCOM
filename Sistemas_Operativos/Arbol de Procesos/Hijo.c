#include <windows.h>
#include <stdio.h>
#include <string.h>
#include <time.h>

struct matrices
{
    int matriz1[10][10];
    int matriz2[10][10];
    int matriz3[10][10];
    char res[14];
};


HANDLE hLecturaPipeIn = NULL, hEscrituraPipeIn = NULL;
HANDLE hLecturaPipeOut = NULL, hEscrituraPipeOut = NULL;

int main (){
	srand (time(NULL));
	int h, i, j, aux;
   struct matrices *mult; 
   mult = (struct matrices*)malloc(sizeof(struct matrices));
   struct matrices *suma;
   suma = (struct matrices*)malloc(sizeof(struct matrices));
    for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			for (h=0; h<10; h++){
				suma->matriz1[i][j]=rand()%9;
			}
			for (h=0; h<10; h++){
				suma->matriz2[i][j]=rand()%9;
			}
		}
	}
   DWORD escritos, leidos; 
   HANDLE hStdIn = GetStdHandle(STD_INPUT_HANDLE); 
   HANDLE hStdOut = GetStdHandle(STD_OUTPUT_HANDLE);
   SetStdHandle(STD_INPUT_HANDLE, hStdIn); 
   SetStdHandle(STD_OUTPUT_HANDLE, hStdOut);   
   SECURITY_ATTRIBUTES pipeSeg= {sizeof(SECURITY_ATTRIBUTES), NULL, TRUE}; 
   ReadFile(hStdIn, mult, sizeof(struct matrices), &leidos, NULL); 
   CloseHandle(hStdIn);  
   for (h=0; h<10; h++){
		for (i=0; i<10; i++){
			for (j=0; j<10; j++){
				aux= aux+((mult->matriz1[h][j])*(mult->matriz2[j][i]));
				mult->matriz3[h][i]= aux;
			}
			aux=0;
		}
	}
   CreatePipe (&hLecturaPipeOut, &hEscrituraPipeOut, &pipeSeg, 0); 
   CreatePipe (&hLecturaPipeIn, &hEscrituraPipeIn, &pipeSeg, 0);
   WriteFile(hEscrituraPipeIn, suma, sizeof(struct matrices), &escritos, NULL);
   PROCESS_INFORMATION piHijo;
   STARTUPINFO siHijo;
   GetStartupInfo (&siHijo);
   siHijo.hStdError  = GetStdHandle (STD_ERROR_HANDLE);
   siHijo.hStdOutput = hEscrituraPipeOut; 
   siHijo.hStdInput  = hLecturaPipeIn;  
   siHijo.dwFlags = STARTF_USESTDHANDLES;
   CreateProcess (NULL,"Nieto.exe",NULL,NULL,TRUE,0,NULL,NULL,&siHijo,&piHijo);  
   WaitForSingleObject (piHijo.hProcess, INFINITE); 
   CloseHandle(piHijo.hThread);
   CloseHandle(piHijo.hProcess); 
   CloseHandle(hEscrituraPipeIn);  
   ReadFile(hLecturaPipeOut, suma, sizeof(struct matrices), &leidos, NULL); 
   CloseHandle(hLecturaPipeOut); 
   CloseHandle(hEscrituraPipeOut); 
   CloseHandle(hLecturaPipeIn); 
   WriteFile(hStdOut, mult, sizeof(struct matrices), &escritos, NULL); 
   WriteFile(hStdOut, suma, sizeof(struct matrices), &escritos, NULL);
   CloseHandle(hStdOut);  
   SetStdHandle(STD_INPUT_HANDLE, GetStdHandle(STD_INPUT_HANDLE));  
   SetStdHandle(STD_OUTPUT_HANDLE, GetStdHandle(STD_OUTPUT_HANDLE)); 
   return 0;
}
