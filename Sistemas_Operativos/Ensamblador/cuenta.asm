segment .data
	handleConsola 	dd 1
	longitudCadena	dd 1
	caractLeidos	dd 1
	caractEscritos	dd 1
	ultimoargumento	dd 1
	cadimprimir db 'Ingresa la cadena: '
	cadimprimir2 db 'El numero de caracteres de la cadena es: '
	
segment .bss 
	cadena resb 30
	copia resb 30
	cont resb 1
	unidades resb 1
	decenas resb 1
segment .text
	global _main
	extern _GetStdHandle@4
	extern _WriteConsoleA@20
	extern _ReadConsoleA@20
	extern _ExitProcess@4
	
_main: 	push dword -11
		call _GetStdHandle@4
		mov	[handleConsola], eax
		call _imprimir

_imprimir: 
		xor eax, eax ;/////////////////////////////Escribe "Ingresa la cadena: "
		mov eax, 19d
		mov [longitudCadena], eax
		xor eax, eax
		mov eax, 0d
		mov [ultimoargumento], eax
		push dword[ultimoargumento]
		push dword caractEscritos
		push dword [longitudCadena]
		push dword cadimprimir
		push dword [handleConsola]
		call _WriteConsoleA@20
		call _ingrcad
		
_ingrcad: 	push dword -10 ;/////////////////////////////////////// Ingresa la cadena
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
			push dword cadena
			push dword [handleConsola]
			call _ReadConsoleA@20
			call _inicial
			
_inicial:
xor eax, eax
push dword -11
call _GetStdHandle@4
mov [handleConsola], eax 
	
		xor eax, eax
		mov eax, 0
		mov [cont], eax
		mov esi, cadena
		mov edi, copia
		mov ebx, [esi]
		call _ciclo
		
_ciclo: cmp ebx, 0x00
		je _exit
		mov [cont], eax
		mov ebx, [esi]
		mov [edi], ebx
		inc esi
		inc edi
		inc eax
		jmp _ciclo
		
		
_exit: ;Manejador de la llamada al sistema _ExitProcess@4
	xor eax, eax
	push dword -11
	call _GetStdHandle@4
	mov [handleConsola], eax 
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;En esta parte le resto 2 caracteres a la cadena
	xor eax, eax
	mov eax, [cont]
	sub eax, 2
	mov [cont], eax
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	mov al, [cont] ; Agregamos el numero de caracteres de la cadena al registro al
	aam ;ajusta el valor en AL por: AH=XX Y AL=X
	mov [unidades], al ;Muevo el registro al a las unidades
	mov al, ah ;Ahora muevo lo que hay en AH dentro de AL
	aam ;Vuelvo a serparar el valor, pero ahora dentro de aam ya esta AH
	mov [decenas], al ;Muevo el registro al que contiene las decenas 
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;Le sumo los 48 digitos del código ascii para poder imprimirlos
	xor eax, eax
	mov eax, [unidades]
	add eax, 48
	mov [unidades], eax
	
	xor eax, eax
	mov eax, [decenas]
	add eax, 48
	mov [decenas], eax
	
	xor eax, eax
	mov eax, 41d
	mov [longitudCadena], eax
	xor eax, eax
	mov eax, 0d
	mov [ultimoargumento], eax
	push dword[ultimoargumento]
	push dword caractEscritos
	push dword [longitudCadena]
	push dword cadimprimir2
	push dword [handleConsola]
	call _WriteConsoleA@20
	
	xor eax, eax
	mov eax, 1d
	mov [longitudCadena], eax
	xor eax, eax
	mov eax, 0d
	mov [ultimoargumento], eax
	push dword[ultimoargumento]
	push dword caractEscritos
	push dword [longitudCadena]
	push dword decenas
	push dword [handleConsola]
	call _WriteConsoleA@20
	
	xor eax, eax
	mov eax, 1d
	mov [longitudCadena], eax
	xor eax, eax
	mov eax, 0d
	mov [ultimoargumento], eax
	push dword[ultimoargumento]
	push dword caractEscritos
	push dword [longitudCadena]
	push dword unidades
	push dword [handleConsola]
	call _WriteConsoleA@20

	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

	xor eax, eax
	mov eax, 0d 
	mov [ultimoargumento], eax
	push dword[ultimoargumento]
	call _ExitProcess@4