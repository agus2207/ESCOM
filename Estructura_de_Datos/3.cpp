#include <stdio.h> 
#include <stdlib.h> 
#include <conio.h> 

main()
{ 
int x, matriz[x][2]; 
int i, j; 
printf("Introduzca los datos a guardar\n");
scanf("%d", &x);
for (i=0; i<x; i++)
{ 
for (j=0; j<2; j++)
{ 
printf("Escriba el valor que guardara en [%d][%d]", i,j); 
scanf("%d", &matriz[i][j]); 
} 
} 
system("CLS"); 
for(i=0;i<x;i++)
	{
		printf("\n");
		for(j=0;j<2;j++)
			printf("%d\t",matriz[i][j]);
	}
getch(); 
return 0; 
} 
