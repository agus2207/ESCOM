	.include "p30F4013.inc"
        .global _activarOSC
	.global _parOSC
	
	_parOSC:
	MOV	#0x8000,    W0
	MOV	W0,	    PR1
	NOP
	CLR TMR1
	NOP
	MOV	#0x0002,    W0
	MOV	W0,	    T1CON
	NOP
	return
	
	_activarOSC:
	BSET    T1CON,	#TON
	NOP
	BSET    T1CON,	#TCS
	NOP
	BCLR    T1CON,	#TGATE
	NOP
	BCLR    T1CON,	#TSYNC
	NOP
	MOV	#0x46,	W0
	MOV	#0x57,	W1
	MOV	#OSCCONL,	W2
	MOV.B	W0,	[W2]
	MOV.B	W1,	[W2]
	BSET    OSCCONL ,	#LPOSCEN
	return


