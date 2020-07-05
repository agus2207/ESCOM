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

void sumarMatrices(struct matrices *m);

int main ()
{
   struct matrices *suma; 
   suma = (struct matrices*)malloc(sizeof(struct matrices));
   DWORD escritos, leidos;  
   HANDLE hStdIn = GetStdHandle(STD_INPUT_HANDLE);  
   HANDLE hStdOut = GetStdHandle(STD_OUTPUT_HANDLE);
   SetStdHandle(STD_INPUT_HANDLE, hStdIn);  
   SetStdHandle(STD_OUTPUT_HANDLE, hStdOut); 
   ReadFile(hStdIn, suma, sizeof(struct matrices), &leidos, NULL);  
   CloseHandle(hStdIn); 
   sumarMatrices(suma); 
   strcpy(suma->res,"SUMA");
   WriteFile(hStdOut, suma, sizeof(struct matrices), &escritos, NULL);  
   CloseHandle(hStdOut);
   SetStdHandle(STD_INPUT_HANDLE, GetStdHandle(STD_INPUT_HANDLE));
   SetStdHandle(STD_OUTPUT_HANDLE, GetStdHandle(STD_OUTPUT_HANDLE));
   return 0;
}

void sumarMatrices(struct matrices *m)
{
   int i, j;
    for (i = 0;   i < 10 ;i++)
    {
        for (j = 0;   j < 10 ;j++)
        {
            m->matriz3[i][j] = m->matriz1[i][j] + m->matriz2[i][j];
        }
    }
}
