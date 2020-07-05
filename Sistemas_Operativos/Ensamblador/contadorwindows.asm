segment .data

	handleConsola dd 1
	numImprimir db 1
	longitudnumero dd 1
	caractEscritos dd 1
	ultimoArgumento dd 1
	saltoLinea db "",0XA ;Salto de linea

segment .bss

	cont: resb 1
	
segment .text

global _main
extern _GetStdHandle@4
extern _WriteConsoleA@20
extern _ExitProcess@4

_main: push dword -11
	call _GetStdHandle@4
	mov [handleConsola], eax 
	xor eax ,eax ;Se limpia el registro eax 
	mov eax, 0 ;Inicializa eax en 0
	mov [cont], eax ;Se le pasa el valor de eax "0" a cont
	
_ciclo: 
	cmp eax, 10 ; Compara eax con 10
	je _salir ;Si es igual a 10 se va a salir
	mov [cont], eax ;Asigna a cont el valor de eax
	add eax, 48 ;Se le suma el 48 para el caracter ascii
	mov [cont], eax ;Se copia a cont el valor del nuevo eax
	xor eax, eax ; Se limpia eax
	mov eax, 0d ; Se le asigna el parametro para el último argumento
	mov [ultimoArgumento], eax ;Se copia al ultimoArgumento el valor de eax
	push dword[ultimoArgumento] ;Pila de procesos para imprimir el numero
	push dword caractEscritos
	push dword [longitudnumero]
	push dword cont
	push dword [handleConsola]
	call _WriteConsoleA@20 ;Esta función llama a las 5 de arriba iniciando por la de hasta abajo
	push dword[ultimoArgumento] ;Pila de procesos para imprimir el salto de linea
	push dword caractEscritos
	push dword [longitudnumero]
	push dword saltoLinea
	push dword [handleConsola]
	call _WriteConsoleA@20 
	mov eax, [cont] ;Como eax tiene un valor distinto que el contador, le vuelvo asignar el valor del contador
	sub eax, 48 ; Para poder seguir comparando el numero le resto el 48 que le sumé del valor ascii
	mov [cont], eax ; Le asigno de nuevo el valor de eax al contador
	inc eax ; Incrementa eax en una unidad 
	jmp _ciclo ;Salta directamente al _ciclo
	
	
_salir: ;Manejador de la llamada al sistema _ExitProcess@4
	xor eax, eax
	mov eax, 0d 
	mov [ultimoArgumento], eax
	push dword[ultimoArgumento]
	call _ExitProcess@4
	