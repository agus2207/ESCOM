#ifndef HEADER_H_
#define HEADER_H_

//Definiciones necesarias para los clientes y servidores
#define MAX_PATH 255    //longitud maxima del nombre de un archivo
#define BUF_SIZE 1024   //cantidad de datos que se pueden transferir de una sola vez
#define FILE_SERVER 243 //Direccion en la red del servidor de archivos

//Definicion de operaciones permitidas
#define CREATE 1    //Crea un archivo
#define READ 2  //Lee una parte del archivo y la regresa
#define WRITE 3 //Escribe una parte del archivo
#define DELETE 4    //Elimina un archivo existente

//Codigos de error
#define OK 0    //Operacion desarrollada en forma correcta
#define E_BAD_OPCODE -1 //Solicitud de una operacion desconocida
#define E_BAD_PARAM -2  //Error en un parametro
#define E_IO -3 //Error en disco u otro error en E/S

//Definicion del formato del mensaje
struct mensaje{
    long source; //identidad del emisor
    long dest;   //identidad del receptor
    long opcode; //operacion
    long count;  //numero de bits por transferir
    long offset; //Lugar del archivo donde comienza la lectura o la escritura

    long extra1;   //Campo adicioal
    long extra2;   //Campo adicional
    long result;    //Resultado de la operacio que se informa
    char name[MAX_PATH];     //Nombre del archivo en el cual se opera
    //char destiny[MAX_PATH];
    char data[BUF_SIZE];    //Datos por leer o escribir
};

#endif