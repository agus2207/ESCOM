	.include "p30F4013.inc"
	.global __T3Interrupt
	.global __ADCInterrupt
	
	__T3Interrupt:
	    BTG	    LATD,   #RD0
	    BCLR    IFS0,   #T3IF
	RETFIE
	
	__ADCInterrupt:
	    MOV	    ADCBUF0,	W0
	    MOV	    #0X003F,	W3
	    AND	    W0,	W3, W1
	    LSR		W0, #6, W2
	    BSET    W1,	    #7
	    MOV		W1, U1TXREG
	    MOV		W2, U1TXREG
	    BCLR    IFS0    ,	#ADIF
	RETFIE
	
	
	    


