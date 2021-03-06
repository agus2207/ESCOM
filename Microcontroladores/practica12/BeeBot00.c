/*******************************************************
This program was created by the
CodeWizardAVR V2.60 Evaluation
Automatic Program Generator
� Copyright 1998-2012 Pavel Haiduc, HP InfoTech s.r.l.
http://www.hpinfotech.com

Project : 
Version : 
Date    : 6/3/2019
Author  : 
Company : 
Comments: 


Chip type               : ATmega8515
Program type            : Application
AVR Core Clock frequency: 1.000000 MHz
Memory model            : Small
External RAM size       : 0
Data Stack size         : 128
*******************************************************/

#include <mega8515.h>

#include <io.h>
#include <delay.h>
#define boton6 PINC.6
#define boton5 PINC.5
#define boton4 PINC.4
#define boton3 PINC.3
#define boton2 PINC.2
#define boton1 PINC.1
#define boton PINC.0

bit botonp;
bit botonp1;
bit botonp2;
bit botonp3;
bit botonp4;
bit botonp5;
bit botonp6;

bit botona;
bit botonb;
bit botonc;
bit botond;
bit botone;
bit botonf;
bit botong;

int control=0, control2=0, control3=0, i=0, control4=0;
int movimientos[10]={0,0,0,0,0,0,0,0,0,0};

void mover( int movimientos[] ){
   for(i=0; i<10; i++){
        if (movimientos[i]==0){
        
        delay_ms(500);
        
        }
        else if(movimientos[i]==1){  //Adelante
            PORTA=0x01;
            delay_ms(500);
        }else if( movimientos[i]==2){   //Atras
            PORTA=0x02; 
            
            delay_ms(500);
        }else if( movimientos[i]==3){   //A la derecha
            PORTA=0x04; 
            
            delay_ms(500);
        }else if( movimientos[i]==4){   //A la izquierda
            PORTA=0x08;  
           
            delay_ms(500);
        } 
         //BOTON PAUSA 
         if (boton6==0)
         botong=0;
         else
         botong=1; 
            if ((botonp6==1)&&(botong==0)) //hubo cambio de flanco de 1 a 0
            {   
            PORTA=0x00;
            
                while (control4<1)
                      {  
                         //BOTON GO
                         if (boton4==0)
                         botone=0;
                         else
                         botone=1; 
                         if ((botonp4==1)&&(botone==0)) //hubo cambio de flanco de 1 a 0
                         { 
                            control4=1;    
                         }
                         if ((botonp4==0)&&(botone==1)) //hubo cambio de flanco de 0 a 1
                         delay_ms(30);
                         botonp4=botone;
                      }  
                control4=0;  
            }
         if ((botonp6==0)&&(botong==1)) //hubo cambio de flanco de 0 a 1
         delay_ms(5);
         botonp6=botong;
   }
   PORTA=0x00;
}

void main(void)
{
// Declare your local variables here

// Input/Output Ports initialization
// Port A initialization
// Function: Bit7=In Bit6=In Bit5=In Bit4=In Bit3=Out Bit2=Out Bit1=Out Bit0=Out 
DDRA=(0<<DDA7) | (0<<DDA6) | (0<<DDA5) | (0<<DDA4) | (1<<DDA3) | (1<<DDA2) | (1<<DDA1) | (1<<DDA0);
// State: Bit7=T Bit6=T Bit5=T Bit4=T Bit3=0 Bit2=0 Bit1=0 Bit0=0 
PORTA=(0<<PORTA7) | (0<<PORTA6) | (0<<PORTA5) | (0<<PORTA4) | (0<<PORTA3) | (0<<PORTA2) | (0<<PORTA1) | (0<<PORTA0);

// Port B initialization
// Function: Bit7=In Bit6=In Bit5=In Bit4=In Bit3=In Bit2=In Bit1=In Bit0=In 
DDRB=(0<<DDB7) | (0<<DDB6) | (0<<DDB5) | (0<<DDB4) | (0<<DDB3) | (0<<DDB2) | (0<<DDB1) | (0<<DDB0);
// State: Bit7=T Bit6=T Bit5=T Bit4=T Bit3=T Bit2=T Bit1=T Bit0=T 
PORTB=(0<<PORTB7) | (0<<PORTB6) | (0<<PORTB5) | (0<<PORTB4) | (0<<PORTB3) | (0<<PORTB2) | (0<<PORTB1) | (0<<PORTB0);

// Port C initialization
// Function: Bit7=In Bit6=In Bit5=In Bit4=In Bit3=In Bit2=In Bit1=In Bit0=In 
DDRC=(0<<DDC7) | (0<<DDC6) | (0<<DDC5) | (0<<DDC4) | (0<<DDC3) | (0<<DDC2) | (0<<DDC1) | (0<<DDC0);
// State: Bit7=P Bit6=P Bit5=P Bit4=P Bit3=P Bit2=P Bit1=P Bit0=P 
PORTC=(1<<PORTC7) | (1<<PORTC6) | (1<<PORTC5) | (1<<PORTC4) | (1<<PORTC3) | (1<<PORTC2) | (1<<PORTC1) | (1<<PORTC0);

// Port D initialization
// Function: Bit7=In Bit6=In Bit5=In Bit4=In Bit3=In Bit2=In Bit1=In Bit0=In 
DDRD=(0<<DDD7) | (0<<DDD6) | (0<<DDD5) | (0<<DDD4) | (0<<DDD3) | (0<<DDD2) | (0<<DDD1) | (0<<DDD0);
// State: Bit7=T Bit6=T Bit5=T Bit4=T Bit3=T Bit2=T Bit1=T Bit0=T 
PORTD=(0<<PORTD7) | (0<<PORTD6) | (0<<PORTD5) | (0<<PORTD4) | (0<<PORTD3) | (0<<PORTD2) | (0<<PORTD1) | (0<<PORTD0);

// Port E initialization
// Function: Bit2=In Bit1=In Bit0=In 
DDRE=(0<<DDE2) | (0<<DDE1) | (0<<DDE0);
// State: Bit2=T Bit1=T Bit0=T 
PORTE=(0<<PORTE2) | (0<<PORTE1) | (0<<PORTE0);

// Timer/Counter 0 initialization
// Clock source: System Clock
// Clock value: Timer 0 Stopped
// Mode: Normal top=0xFF
// OC0 output: Disconnected
TCCR0=(0<<PWM0) | (0<<COM01) | (0<<COM00) | (0<<CTC0) | (0<<CS02) | (0<<CS01) | (0<<CS00);
TCNT0=0x00;
OCR0=0x00;

// Timer/Counter 1 initialization
// Clock source: System Clock
// Clock value: Timer1 Stopped
// Mode: Normal top=0xFFFF
// OC1A output: Disconnected
// OC1B output: Disconnected
// Noise Canceler: Off
// Input Capture on Falling Edge
// Timer1 Overflow Interrupt: Off
// Input Capture Interrupt: Off
// Compare A Match Interrupt: Off
// Compare B Match Interrupt: Off
TCCR1A=(0<<COM1A1) | (0<<COM1A0) | (0<<COM1B1) | (0<<COM1B0) | (0<<WGM11) | (0<<WGM10);
TCCR1B=(0<<ICNC1) | (0<<ICES1) | (0<<WGM13) | (0<<WGM12) | (0<<CS12) | (0<<CS11) | (0<<CS10);
TCNT1H=0x00;
TCNT1L=0x00;
ICR1H=0x00;
ICR1L=0x00;
OCR1AH=0x00;
OCR1AL=0x00;
OCR1BH=0x00;
OCR1BL=0x00;

// Timer(s)/Counter(s) Interrupt(s) initialization
TIMSK=(0<<TOIE1) | (0<<OCIE1A) | (0<<OCIE1B) | (0<<TICIE1) | (0<<TOIE0) | (0<<OCIE0);

// External Interrupt(s) initialization
// INT0: Off
// INT1: Off
// INT2: Off
MCUCR=(0<<SRE) | (0<<SRW10) | (0<<ISC11) | (0<<ISC10) | (0<<ISC01) | (0<<ISC00);
EMCUCR=(0<<SRL2) | (0<<SRL1) | (0<<SRL0) | (0<<SRW01) | (0<<SRW00) | (0<<SRW11) | (0<<ISC2);

// USART initialization
// USART disabled
UCSRB=(0<<RXCIE) | (0<<TXCIE) | (0<<UDRIE) | (0<<RXEN) | (0<<TXEN) | (0<<UCSZ2) | (0<<RXB8) | (0<<TXB8);

// Analog Comparator initialization
// Analog Comparator: Off
ACSR=(1<<ACD) | (0<<ACBG) | (0<<ACO) | (0<<ACI) | (0<<ACIE) | (0<<ACIC) | (0<<ACIS1) | (0<<ACIS0);

// SPI initialization
// SPI disabled
SPCR=(0<<SPIE) | (0<<SPE) | (0<<DORD) | (0<<MSTR) | (0<<CPOL) | (0<<CPHA) | (0<<SPR1) | (0<<SPR0);


while (1)
      {

         //BOTON GO
         if (boton4==0)
         botone=0;
         else
         botone=1; 
         if ((botonp4==1)&&(botone==0)) //hubo cambio de flanco de 1 a 0
         { 
            control=3;    
         }
         if ((botonp4==0)&&(botone==1)) //hubo cambio de flanco de 0 a 1
         delay_ms(30);
         botonp4=botone;
         
         //BOTON CLEAR
         if (boton5==0)
         botonf=0;
         else
         botonf=1; 
            if ((botonp5==1)&&(botonf==0)) //hubo cambio de flanco de 1 a 0
            { 
            control=1;    
            }
         if ((botonp5==0)&&(botonf==1)) //hubo cambio de flanco de 0 a 1
         delay_ms(30);
         botonp5=botonf; 
        
            if(control==0)
            {  
                while(control2<1){
                    //Avanza adelante 
                    if (boton==0)
                    botona=0;
                    else
                    botona=1; 
                        if ((botonp==1)&&(botona==0)) //hubo cambio de flanco de 1 a 0
                        {
                    
                         movimientos[control]=1;
                         control++;   
                        }
                    if ((botonp==0)&&(botona==1)) //hubo cambio de flanco de 0 a 1
                    delay_ms(30);
                    botonp=botona;
                     //Boton atras  
                    if (boton1==0)
                    botonb=0;
                    else
                    botonb=1; 
                        if ((botonp1==1)&&(botonb==0)) //hubo cambio de flanco de 1 a 0
                        {
                       
                        movimientos[control]=2;   
                        control++;  
                        }
                    if ((botonp1==0)&&(botonb==1)) //hubo cambio de flanco de 0 a 1
                    delay_ms(30);
                    botonp1=botonb;   
                    
                    //Avanza derecha
                    if (boton2==0)
                    botonc=0;
                    else
                    botonc=1; 
                        if ((botonp2==1)&&(botonc==0)) //hubo cambio de flanco de 1 a 0
                        {
                       
                        movimientos[control]=3; 
                        control++;    
                        }
                    if ((botonp2==0)&&(botonc==1)) //hubo cambio de flanco de 0 a 1
                    delay_ms(30);
                    botonp2=botonc; 
                    
                    //Avanza izquierda
                    if (boton3==0)
                    botond=0;
                    else
                    botond=1; 
                        if ((botonp3==1)&&(botond==0)) //hubo cambio de flanco de 1 a 0
                        {
                       
                         movimientos[control]=4; 
                        control++;    
                        }
                    if ((botonp3==0)&&(botond==1)) //hubo cambio de flanco de 0 a 1
                    delay_ms(30);
                    botonp3=botond;
                    
                    if(control==10){
                       control2=1;
                       control=10;
                    }
                }   
            control2=0;
            }else if(control==1){ 
                
                while(control3<8){
                
                     movimientos[control]=0;
                    control3++;
                } 
                control3=0;
                control=0;
            }else if(control==3){
                mover(movimientos);
                control=10;
            }


      }
}
