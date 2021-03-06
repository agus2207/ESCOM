#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#include <time.h>

#define MAX_NUM 3500
#define prueba 10

int mandar[MAX_NUM];

struct hilo_envia{
    int rango1;
    int rango2;
    int id;
};

void merge(int arr[], int l, int m, int r) 
{ 
    int i, j, k; 
    int n1 = m - l + 1; 
    int n2 =  r - m; 
  
    /* create temp arrays */
    int L[n1], R[n2]; 
  
    /* Copy data to temp arrays L[] and R[] */
    for (i = 0; i < n1; i++) 
        L[i] = arr[l + i]; 
    for (j = 0; j < n2; j++) 
        R[j] = arr[m + 1+ j]; 
  
    /* Merge the temp arrays back into arr[l..r]*/
    i = 0; // Initial index of first subarray 
    j = 0; // Initial index of second subarray 
    k = l; // Initial index of merged subarray 
    while (i < n1 && j < n2) 
    { 
        if (L[i] <= R[j]) 
        { 
            arr[k] = L[i]; 
            i++; 
        } 
        else
        { 
            arr[k] = R[j]; 
            j++; 
        } 
        k++; 
    } 
  
    /* Copy the remaining elements of L[], if there 
       are any */
    while (i < n1) 
    { 
        arr[k] = L[i]; 
        i++; 
        k++; 
    } 
  
    /* Copy the remaining elements of R[], if there 
       are any */
    while (j < n2) 
    { 
        arr[k] = R[j]; 
        j++; 
        k++; 
    } 
} 
  
/* l is for left index and r is right index of the 
   sub-array of arr to be sorted */
void mergeSort(int arr[], int l, int r) 
{ 
    if (l < r) 
    { 
        // Same as (l+r)/2, but avoids overflow for 
        // large l and h 
        int m = l+(r-l)/2; 
  
        // Sort first and second halves 
        mergeSort(arr, l, m); 
        mergeSort(arr, m+1, r); 
  
        merge(arr, l, m, r); 
    } 
} 

void *final(void *count){
    struct hilo_envia *p2 = count;
    printf("Estoy en la cubeta: %d\n", (p2->id)+1);
    mergeSort(mandar, p2->rango1, (p2->rango2)-1);
    pthread_exit(&p2->id);
}

int main(){
    int cubetas, numeros[3500], random, rangoinf, rangosup, count = 0;
    printf("Ingrese las cubetas deseadas\n");
    scanf("%d", &cubetas);

    while(cubetas > 999){
        printf("Numero incorrecto\nIgrese las cubetas deseadas\n");
        scanf("%d", &cubetas);
    }

    printf("\nARREGLO DESORDENADO\n");

    srand(time(NULL));
    for(int i = 0; i < MAX_NUM; i++){
        random = 1+rand()%(999);
        numeros[i] = random;
        printf("%d ", numeros[i]);
    }

    printf("\n");
    int rangos[cubetas];
    pthread_t sort[cubetas];

    for(int i = 0; i < cubetas; i++){
        rangoinf = rangosup+1;
        rangosup = (i+1)*999/cubetas;
        printf("Rango inf = %d, Rango sup = %d\n", rangoinf, rangosup);
        for(int j = 0; j < MAX_NUM; j++){
            if(numeros[j] >= rangoinf && numeros[j] <= rangosup){
                mandar[count] = numeros[j];
                count++;
            }
        }
        rangos[i] = count;
    }

    struct hilo_envia hilos[cubetas];

    for(int i = 0; i < cubetas; i++){
        if(i-1 < 0){
            hilos[i].rango1 = 0;
            hilos[i].rango2 = rangos[0];
            hilos[i].id = i;
            pthread_create(&sort[i], NULL, final, &hilos[i]);
        }
        else{
            hilos[i].rango1 = rangos[i-1];
            hilos[i].rango2 = rangos[i];
            hilos[i].id = i;
            pthread_create(&sort[i], NULL, final, &hilos[i]);
        }
    }   

    for(int i = 0; i < cubetas; i++){
        int *salida;
        pthread_join(sort[i], (void**)&salida);
        printf("Termine de acomodar la cubeta: %d\n", *salida+1);
    }

    printf("\nARREGLO ORDENADO\n");

    for(int i = 0; i < MAX_NUM; i++){
        printf("%d ", mandar[i]);
    }
}
