segment .data

	handleConsola 	dd 1
	longitudCadena	dd 1
	caractLeidos	dd 1
	caractEscritos	dd 1
	ultimoargumento	dd 1
	imprimir1		db 'Ingresa la primer cadena: '
	imprimir2		db 'Ingresa la segunda cadena: '
	imprimir3		db 'Ingresa la tercer cadena: '
	
segment .bss

	cadena1 resb 30
	cadena2 resb 30
	cadena3 resb 30
	copia 	resb 30
	cont: resb 1
	
segment .text

global _main
extern _GetStdHandle@4
extern _WriteConsoleA@20
extern _ReadConsoleA@20
extern _ExitProcess@4

_main: 	push dword -11
		call _GetStdHandle@4
		mov	[handleConsola], eax
		call _impr1

_impr1:	xor eax, eax ;/////////////////////////////Escribe "Ingresa la primer cadena: "
		mov eax, 26d
		mov [longitudCadena], eax
		xor eax, eax
		mov eax, 0d
		mov [ultimoargumento], eax
		push dword[ultimoargumento]
		push dword caractEscritos
		push dword [longitudCadena]
		push dword imprimir1
		push dword [handleConsola]
		call _WriteConsoleA@20
		call _ingrcad1			
		
_ingrcad1: 	push dword -10 ;/////////////////////////////////////// Ingresa la primer cadena
			call _GetStdHandle@4
			mov [handleConsola], eax
			xor eax, eax
			mov eax, 30d
			mov [longitudCadena], eax
			xor eax, eax
			mov eax, 0d
			mov [ultimoargumento], eax
			push dword [ultimoargumento]
			push dword caractLeidos
			push dword [longitudCadena]
			push dword cadena1
			push dword [handleConsola]
			call _ReadConsoleA@20
			call _impr2
			;///////////////////////////////////

_impr2:	push dword -11
		call _GetStdHandle@4
		mov	[handleConsola], eax
		xor eax, eax ;/////////////////////////////Escribe "Ingresa la segunda cadena: "
		mov eax, 27d
		mov [longitudCadena], eax
		xor eax, eax
		mov eax, 0d
		mov [ultimoargumento], eax
		push dword[ultimoargumento]
		push dword caractEscritos
		push dword [longitudCadena]
		push dword imprimir2
		push dword [handleConsola]
		call _WriteConsoleA@20
		call _ingrcad2		

_ingrcad2: 	push dword -10 ;/////////////////////////////////////// Ingresa la segunda cadena
			call _GetStdHandle@4
			mov [handleConsola], eax
			xor eax, eax
			mov eax, 30d
			mov [longitudCadena], eax
			xor eax, eax
			mov eax, 0d
			mov [ultimoargumento], eax
			push dword [ultimoargumento]
			push dword caractLeidos
			push dword [longitudCadena]
			push dword cadena2
			push dword [handleConsola]
			call _ReadConsoleA@20
			call _impr3
			;///////////////////////////////////
			
_impr3:	push dword -11
		call _GetStdHandle@4
		mov	[handleConsola], eax
		xor eax, eax ;/////////////////////////////Escribe "Ingresa la tercer cadena: "
		mov eax, 26d
		mov [longitudCadena], eax
		xor eax, eax
		mov eax, 0d
		mov [ultimoargumento], eax
		push dword[ultimoargumento]
		push dword caractEscritos
		push dword [longitudCadena]
		push dword imprimir3
		push dword [handleConsola]
		call _WriteConsoleA@20
		call _ingrcad3
		;///////////////////////////////////////////////////////////////////////	

_ingrcad3: 	push dword -10 ;/////////////////////////////////////// Ingresa la segunda cadena
			call _GetStdHandle@4
			mov [handleConsola], eax
			xor eax, eax
			mov eax, 30d
			mov [longitudCadena], eax
			xor eax, eax
			mov eax, 0d
			mov [ultimoargumento], eax
			push dword [ultimoargumento]
			push dword caractLeidos
			push dword [longitudCadena]
			push dword cadena3
			push dword [handleConsola]
			call _ReadConsoleA@20
			mov edi, copia
			xor eax, eax
			mov eax, 0
			mov [cont], eax
			call _ciclo	
			;///////////////////////////////////
		
_ciclo:
		cmp eax, 30
		je _exit
		mov [cont], eax
		mov esi, cadena1
		add esi, eax
		mov ebx, [esi]
		mov [edi], ebx
		inc edi
		xor ebx, ebx
		mov esi, cadena2
		add esi, eax
		mov ebx, [esi]
		mov [edi], ebx
		inc edi
		xor ebx, ebx
		mov esi, cadena3
		add esi, eax
		mov ebx, [esi]
		mov [edi], ebx
		inc edi
		inc eax
		xor ebx, ebx
		jmp _ciclo
		
_exit:	
		push dword -11
		call _GetStdHandle@4
		mov [handleConsola], eax 
		xor eax, eax 
		mov eax, 0d
		mov [ultimoargumento], eax 
		xor eax,eax
		mov eax, 30
		mov [longitudCadena], eax
		push dword[ultimoargumento] 
		push dword caractEscritos
		push dword [longitudCadena]
		push dword copia
		push dword [handleConsola]
		call _WriteConsoleA@20
		xor eax, eax
		mov eax, 0d
		mov [ultimoargumento], eax
		push dword [ultimoargumento]
		call _ExitProcess@4			