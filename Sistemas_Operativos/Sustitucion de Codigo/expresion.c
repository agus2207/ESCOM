#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

//variables y constantes
typedef int bool; 
#define true 1
#define false 0
int tope;
int operacionPila[25]; 
int posicion = -1; 

//estructuras
typedef struct nodoCola{
    char car;
    struct nodoCola *sig;
}nodoCola;

typedef struct nodo{
   int dato;
   struct nodo *sig;
}nodo;
nodo *primero = NULL;

typedef struct cola{
    nodoCola *fin, *inicio;
}cola;

typedef struct pila{
    nodoCola *primero;
}pila;



//definicion de funciones
void push(char);
char pop();
nodo *final();
nodoCola* crearNodo(int caracter);
cola *creacola();
void encola(cola *q, char c);
void pushInt(int valor);
char popInt();
char *descolar(cola *q);

int evaluacion(char *expresion);
bool esOperador(char c);
int precedenciaOperadores(char c);

int main()
{
	cola *q = creacola();
	char expresion[50]="", aux[50];
	int i;
	char cadenafinal[30];
	printf("Ingrese una expresion donde cada dato tenga un digito: ");
	scanf("%s", expresion);
	strcpy (aux,expresion);
     strcat(expresion,"z");
	for(i=0;i<strlen(expresion);i++){
		if(expresion[i]=='z')
			break;
		if(isdigit(expresion[i])){
			encola(q, expresion[i]);
		}
		else if(esOperador(expresion[i]) || expresion[i]=='\0' && expresion[i]!='('){
			while(tope!=0 && precedenciaOperadores(final()->dato) >= precedenciaOperadores(expresion[i])){
				encola(q,pop());		
			}
			push(expresion[i]);		
		}
		else if(expresion[i]=='('){
			push(expresion[i]);
		}
		else if(expresion[i]==')'){
			while(tope!=0 && final()->dato!='('){
				if(final()->dato!='(')
				encola(q,pop());	
			}
			pop();
		}
		if(expresion[i+1]=='z'){
			while(tope!=0){
				encola(q,pop());
			}
		}
	}	
	strcpy(cadenafinal, descolar(q));
	printf("\n%s = %d\n\n",aux, evaluacion(cadenafinal));
	return 0;	
}

//funciones de extructuras
void push(char caracter){
   nodo *nuevo = (nodo*)malloc(sizeof(nodo));
   nuevo -> dato = caracter;
   if(primero == NULL)
      nuevo -> sig = NULL;
   else
      nuevo -> sig = primero;
   primero = nuevo;
   tope++;
}


char pop(){
	char p;
   if(primero == NULL)
      return '\0';
   else{
      nodo *temp = primero;
      primero = temp->sig;
      tope--;
      p=temp->dato;
      free(temp);
	  return p; 
   }
}


nodo *final(){
	if(primero != NULL)
		return primero;
	return NULL;
}

nodoCola* crearNodo(int caracter){
    nodoCola *temp = (nodoCola*)malloc(sizeof(nodoCola));
    temp->car = caracter;
    temp->sig = NULL;
    return temp; 
}

cola *creacola(){
    cola *q = (cola*)malloc(sizeof(cola));
    q->fin = q->inicio = NULL;
    return q;
}

void encola(cola *q, char c){
	if(c!='('){
    nodoCola *temp = crearNodo(c);
    if (q->inicio == NULL){
       q->fin = q->inicio = temp;
       return;
    }
    q->inicio->sig = temp;
    q->inicio = temp;
    }
	else{
    	printf("parentesis balenceados\n");
	}
}


void pushInt(int valor) {
   operacionPila[++posicion] = valor; 
} 

char popInt() {
   return operacionPila[posicion--]; 
} 

char* descolar(cola *q){
	char nn[2]="";
	char *cadena = (char *)malloc(30);
	if(q->fin==NULL){
		return "";
	}
	nodoCola *temp = q->fin;
	while(q->fin->sig!=NULL)
	{
		nn[0]=q->fin->car;
		nn[1]='\0';
		strcat(cadena,nn);
		q->fin=q->fin->sig;
	}
	nn[0]=q->fin->car;
	nn[1]='\0';
	strcat(cadena,nn);
	return cadena;
}

//funciones auxiliares
bool esOperador(char c){
	if(c>41 && c<48){
		return true;
	}
	return false;
}

int precedenciaOperadores(char c){
	int precedencia;
	char as;
	if(c=='+'){
		return precedencia=2;
	}
	if(c=='-'){
		return precedencia=2;
	}

	if(c=='*'){
		return precedencia=3;
	}

	if(c=='/'){
		return precedencia=3;
	}
}

int evaluacion(char *expresion){
 	char ch;
 	int i = 0,op1,op2;
	while((ch = expresion[i++])!='\0'){
		if(isdigit(ch)) {
			pushInt(ch-'0');
		}
		else {
         	op2 = popInt();
         	op1 = popInt();	
			switch(ch) {
            	case '+':
            	pushInt(op1+op2);
            	break;
            case '-':
            	pushInt(op1-op2);
            	break;
            case '*':
            	pushInt(op1*op2);
            	break;
            case '/':
            	pushInt(op1	/op2);
            	break;
         	}
		}
	}
   return operacionPila[posicion];
}

