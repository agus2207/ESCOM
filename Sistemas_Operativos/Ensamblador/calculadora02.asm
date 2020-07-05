segment .data
  menu db 'Ingresa una opcion:', 0x0A, '1. Multiplicacion', 0x0A, '2. Division', 0x0A, '3. Suma', 0x0A, '4. Resta', 0x0A ;
  longitudMenu       equ $-menu
	msgOpcion          db "Digite la opcion: " ,0xA,0xD
	longitud1          equ $-msgOpcion
	msgNumero1         db "Digita el numero 1: ",0xA
	longitud2          equ $-msgNumero1
	msgNumero2         db "Digita el numero 2: ",0xA
	longitud3          equ $-msgNumero2
	msgOpcionInvalida  db "Error de opcion",0xA
	longitud4          equ $-msgOpcionInvalida
	msgResultado       db 0xD,"El resultado es: ",0xA
	longitud5          equ $-msgResultado
  msgDivError        db 0xD,"No esposible dividir entre 0",0xA
	longitud6         equ $-msgDivError
  handleEntrada      dd 0  
  handleSalida       dd 0           
  longitudCadena     dd 0       
  caracEscritos      dd 0          
  caracLeidosop      dd 0             
  caracLeidosnum1    dd 0            
  caracLeidosnum2    dd 0         
  ultimoArgumento    dd 0     

segment .bss
  opc                  resb 10 
  num1                 resb 10 			
  num2                 resb 10 			
  resultadoStr         resb 10 	
  opcion               resd 1
  n1                   resd 1
  n2                   resd 1
  resultado            resd 1
  longitudResultadoStr resd 1

segment .text

  global _main
  extern _GetStdHandle@4   
  extern _WriteConsoleA@20 
  extern _ExitProcess@4   
  extern _ReadConsoleA@20           

_main:
      push dword -10             
      call _GetStdHandle@4        
      mov [handleEntrada],eax

      push dword -11            
      call _GetStdHandle@4    
      mov [handleSalida],eax         

      push 0                     
      push dword caracEscritos    
      push dword longitudMenu      
      push dword menu              
      push dword [handleSalida]
      call _WriteConsoleA@20    

      push 0                     
      push dword caracEscritos   
      push dword longitud1    
      push dword msgOpcion        
      push dword [handleSalida]   
      call _WriteConsoleA@20 

      push 0                         
      push dword caracLeidosop         
      push dword 10                   
      push dword opc                   
      push dword [handleEntrada] 
      call _ReadConsoleA@20           

      push 0                       
      push dword caracEscritos
      push dword longitud2     
      push dword msgNumero1   
      push dword [handleSalida]    
      call _WriteConsoleA@20       

      push 0                  
      push dword caracLeidosnum1   
      push dword 10  
      push dword num1           
      push dword [handleEntrada]   
      call _ReadConsoleA@20


      push 0                       
      push dword caracEscritos      
      push dword longitud3          
      push dword msgNumero2         
      push dword [handleSalida]    
      call _WriteConsoleA@20       

      push 0                    
      push dword caracLeidosnum2    
      push dword 10                
      push dword num2               
      push dword [handleEntrada]   
      call _ReadConsoleA@20

      mov esi, opc
      call cadAint
      mov [opcion], eax

      mov esi, num1
      call cadAint
      mov [n1], eax

      mov esi, num2
      call cadAint
      mov [n2], eax
      mov eax,[opcion]	
    	cmp al,1
    	je  multiplicar
    	cmp al,2
    	je dividir
    	cmp al,3
    	je suma
    	cmp al,4
    	je resta
      cmp al,5
    	je salida
      push 0                        
      push dword caracEscritos    
      push dword longitud4      
      push dword msgOpcionInvalida  
      push dword [handleSalida]    
      call _WriteConsoleA@20 
      jmp salida


    multiplicar:
      xor eax, eax
      xor edx, edx
      mov eax,[n1]
      mul dword [n2]
      mov [resultado],eax
      jmp impResultado

    dividir:
      xor eax, eax
      xor edx, edx
      mov eax,[n1]
      div dword [n2]
      mov [resultado],eax
      jmp impResultado

    suma:
      xor eax, eax
    	mov eax, [n1]
    	add eax,[n2]
    	mov [resultado],eax
      jmp impResultado

    resta:
      xor eax, eax
      mov eax, [n1]
      sub eax,[n2]
      mov [resultado],eax
      jmp impResultado

    impResultado:
      mov eax, [resultado]
      mov edi, resultadoStr
      call intAcad
      mov [longitudResultadoStr], eax
      push 0                       
      push dword caracEscritos    
      push dword longitud5          
      push dword msgResultado       
      push dword [handleSalida]    
      call _WriteConsoleA@20        
      push 0                      
      push dword caracEscritos      
      push dword [longitudResultadoStr]
      push dword resultadoStr      
      push dword [handleSalida]     
      call _WriteConsoleA@20        
      jmp salida

    cadAint:
      xor eax, eax    
      xor edx, edx  

      .loop:
        mov dl, byte [esi] 
        cmp dl, 10          
        je .fin
        cmp dl, 13
        je .fin
        cmp dl, 0
        je .fin
        lea eax, [eax*5]
        add eax, eax
        add esi, 1
        and dl, 0x0F
        add eax, edx
        jmp .loop

      .fin:
        ret

    intAcad:
      xor ecx, ecx
      xor ebx, ebx
      mov ebx, 10
      .apilar:
        xor edx, edx
        div ebx
        push dx
        inc cl
        test eax, eax
        jnz .apilar

      mov ebx, ecx

      .desapilar:
        pop ax
        or al, 0x30
        mov byte[edi], al
        inc edi
        sub cl, 1
        jnz .desapilar

      mov byte[edi], 0
      mov eax, ebx
      ret

    salida:
      ;Salida
      xor eax,eax                     ;Limpieza del registro eax (eax=0)
      push 0                          ;Argumento de _ExitProcess() pasado por pila
      call _ExitProcess@4             ;Invocación de _ExitProcess