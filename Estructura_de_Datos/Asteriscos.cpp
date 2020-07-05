#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <conio.h>

main ()
{
  int a=1000,p,x,c=1,num,op;
  while(c<=3){
      printf("Pasword:");
      scanf("%d",&p);
      system("cls");
      
      if(p==a){
         printf("Acceso Concedido");
         
          getche();
          system("cls");
          printf("Ingrese un numero:");
          scanf("%d",&num);
          printf("%d            ",num);
          for(num;num>0;num--){
           printf("*");
       }
        getch();
        exit(0);
   }
   else{
        printf("Acceso Denegado                              Intento numero %d",c);
        getch();
        c++;
    }
    system("cls");
}
}
