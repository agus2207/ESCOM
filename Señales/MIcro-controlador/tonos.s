	.include "p30F4013.inc"
	.global _tonoDO
	.global _tonoRE
	.global _tonoMI
	.global _tonoFA
	.global _tonoSOL
	.global _tonoLA
	.global _tonoSI
	
	
	_tonoDO:
	    MOV	#55,    W0
	    MOV	W0,	    PR1
	    NOP
	    CLR TMR1
	    NOP
	    MOV	#0x8020,    W0
	    MOV	W0,	    T1CON
	    NOP
	    CALL    _busyFlag
	    MOV	    #0X85,        W0
	    CALL    _comandoLCD
	    CALL    _busyFlag
	    MOV	    #68,        W0
	    CALL    _datoLCD
	    CALL    _busyFlag
	    MOV	    #79,        W0
	    CALL    _datoLCD
	    CALL    _busyFlag
	    MOV	    #32,        W0
	    CALL    _datoLCD
	RETURN
	
	_tonoRE:
	    MOV	#49,    W0
	    MOV	W0,	    PR1
	    NOP
	    CLR TMR1
	    NOP
	    MOV	#0x8020,    W0
	    MOV	W0,	    T1CON
	    NOP
	    CALL    _busyFlag
	    MOV	    #0X85,        W0
	    CALL    _comandoLCD
	    CALL    _busyFlag
	    MOV	    #82,        W0
	    CALL    _datoLCD
	    CALL    _busyFlag
	    MOV	    #69,        W0
	    CALL    _datoLCD
	    CALL    _busyFlag
	    MOV	    #32,        W0
	    CALL    _datoLCD
	RETURN
	
	_tonoMI:
	    MOV	#11,    W0
	    MOV	W0,	PR1
	    NOP
	    CLR TMR1
	    NOP
	    MOV	#0x8030,    W0
	    MOV	W0,	    T1CON
	    NOP
	    CALL    _busyFlag
	    MOV	    #0X85,        W0
	    CALL    _comandoLCD
	    CALL    _busyFlag
	    MOV	    #77,        W0
	    CALL    _datoLCD
	    CALL    _busyFlag
	    MOV	    #73,        W0
	    CALL    _datoLCD
	    CALL    _busyFlag
	    MOV	    #32,        W0
	    CALL    _datoLCD
	RETURN
	
	_tonoFA:
	    MOV	#2639,    W0
	    MOV	W0,	    PR1
	    NOP
	    CLR TMR1
	    NOP
	    MOV	#0x8000,    W0
	    MOV	W0,	    T1CON
	    NOP
	    CALL    _busyFlag
	    MOV	    #0X85,        W0
	    CALL    _comandoLCD
	    CALL    _busyFlag
	    MOV	    #70,        W0
	    CALL    _datoLCD
	    CALL    _busyFlag
	    MOV	    #65,        W0
	    CALL    _datoLCD
	    CALL    _busyFlag
	    MOV	    #32,        W0
	    CALL    _datoLCD
	RETURN
	
	_tonoSOL:
	    MOV	#2351,    W0
	    MOV	W0,	    PR1
	    NOP
	    CLR TMR1
	    NOP
	    MOV	#0x8000,    W0
	    MOV	W0,	    T1CON
	    NOP
	    CALL    _busyFlag
	    MOV	    #0X85,        W0
	    CALL    _comandoLCD
	    CALL    _busyFlag
	    MOV	    #83,        W0
	    CALL    _datoLCD
	    CALL    _busyFlag
	    MOV	    #79,        W0
	    CALL    _datoLCD
	    CALL    _busyFlag
	    MOV	    #76,        W0
	    CALL    _datoLCD
	RETURN
	
	_tonoLA:
	    MOV	#8,    W0
	    MOV	W0,	    PR1
	    NOP
	    CLR TMR1
	    NOP
	    MOV	#0x8030,    W0
	    MOV	W0,	    T1CON
	    NOP
	    CALL    _busyFlag
	    MOV	    #0X85,        W0
	    CALL    _comandoLCD
	    CALL    _busyFlag
	    MOV	    #76,        W0
	    CALL    _datoLCD
	    CALL    _busyFlag
	    MOV	    #65,        W0
	    CALL    _datoLCD
	    CALL    _busyFlag
	    MOV	    #32,        W0
	    CALL    _datoLCD
	RETURN
	
	_tonoSI:
	    MOV	#1866,    W0
	    MOV	W0,	    PR1
	    NOP
	    CLR TMR1
	    NOP
	    MOV	#0x8000,    W0
	    MOV	W0,	    T1CON
	    NOP
	    CALL    _busyFlag
	    MOV	    #0X85,        W0
	    CALL    _comandoLCD
	    CALL    _busyFlag
	    MOV	    #83,        W0
	    CALL    _datoLCD
	    CALL    _busyFlag
	    MOV	    #73,        W0
	    CALL    _datoLCD
	    CALL    _busyFlag
	    MOV	    #32,        W0
	    CALL    _datoLCD
	RETURN


