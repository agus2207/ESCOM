	.include "p30F4013.inc"
	.global _gotoSleep
	
	_gotoSleep:
	    PWRSAV  #1
	RETURN


