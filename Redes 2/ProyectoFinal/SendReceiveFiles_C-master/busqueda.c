#include <stdio.h>
#include <stdlib.h>
#include <string.h>
 
int main()
{
    FILE *archivo;
 
    int opc=0;
    int contador = 0, encontrado = 0;
    char cadena[256], buscar[100], nombre[100];
    char *a;

    printf("\n\nIngrese el nombre o ubicacion del archivo, incluyendo punto y formato: ");
    fflush(stdin);
    scanf("%[^\n]", nombre);
    archivo = fopen(nombre,"r");
    
    if (archivo != NULL)
    {      
        printf("\nIngrese la palabra/frase que desea buscar en el archivo %s: ", nombre);
        fflush(stdin);
        scanf("%s", buscar);        
        rewind(archivo);

        contador = 0;
        
        encontrado = 0;

        while (!feof(archivo))
        {
            contador++;
            
            fgets(cadena,256,archivo);
            
            a = strtok(cadena,",.- ");
            
            while (a != NULL)
            {
                if (!strcmp(buscar, a))
                {
                    encontrado++;
                    
                    if (encontrado == 1)
                    printf("\nCOINCIDENCIAS\n");
                    printf("\n%d.Se encontro la palabra/frase: %s.\nLinea en que se encontro: %d.\n", encontrado, a, contador);
                }
                a = strtok (NULL, " ");
            }
        }   
        if (encontrado <= 0)
        printf("\nLa palabra o frase no se encontro en el archivo %s.\n", nombre);
    }
    else
    {
        printf("\nHubo un error en la apertura del archivo...\n\n");
        fclose(archivo);
        return EXIT_FAILURE;
    }
    fclose(archivo);
    return EXIT_SUCCESS;
}