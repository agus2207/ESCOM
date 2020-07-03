#include <AES.h>
#include "./printf.h"
#include "sha1.h"
#include <LiquidCrystal.h>
 
AES aes ;

String entrada;
String myString;
boolean newData = false;

byte llave[16];

//byte *key = (unsigned char*)"01234567890123456789012345678901"; // encryption key
 
unsigned long long int myIv = 3675356236753562;

int edo_aes = 0, edo_sha = 0;

const int rs = 12, en = 11, d4 = 5, d5 = 4, d6 = 3, d7 = 2;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);

void printHash(uint8_t* hash) {
    int i;
    String resultado;
    for (i=0; i<20; i++) {
      Serial.print("0123456789abcdef"[hash[i]>>4]);
      Serial.print("0123456789abcdef"[hash[i]&0xf]);
      resultado = resultado + "0123456789abcdef"[hash[i]>>4];
      resultado = resultado + "0123456789abcdef"[hash[i]&0xf];
    }
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("Hashing");
    lcd.setCursor(0, 1);
    lcd.print(resultado);
    delay(5000);
    Serial.println();
}

void setup() {
    // put your setup code here, to run once:
    Serial.begin(9600);
    Serial.println("<Arduino is ready>");
    printf_begin();
    lcd.begin(16, 2);
    lcd.print("Arduino is ready");
}

void loop() {
    // put your main code here, to run repeatedly:
    recvWithEndMarker();
    showNewData();
}

void recvWithEndMarker() {
  
    char endMarker = '\n';
    char rc;
   
    while (Serial.available() > 0 && newData == false){
        rc = Serial.read();

        if (rc != endMarker) {

          entrada += rc;  
        }
        else {
            entrada += '\0'; // terminate the string
            newData = true;
        }
    }
}

void showNewData() {
    if (newData == true) {
        
        if(entrada == "1"){
            Serial.println("Voy a probar aes");
            lcd.clear();
            lcd.setCursor(0, 0);
            lcd.print("Voy a probar AES");
            delay(5000);
            edo_aes = 1;
            edo_sha = 0;
            entrada = "";
            newData = false;
            lcd.clear();
        }

        else if(entrada == "2"){
            Serial.println("Voy a probar SHA-1");
            lcd.clear();
            lcd.setCursor(0, 0);
            lcd.print("Voy a probar SHA-1");
            delay(5000);
            edo_aes = 0;
            edo_sha = 1;
            entrada = "";
            newData = false;
            lcd.clear();
        }

        else if(edo_aes == 1){
            Serial.println("Recibi una llave");
            lcd.clear();
            lcd.setCursor(0, 0);
            lcd.print("Recibi una llave");
            lcd.setCursor(0, 1);
            lcd.print(entrada);
            delay(5000);
            edo_aes = 2;
            entrada.getBytes(llave, 16);
            entrada = "";
            newData = false;
        }

        else if(edo_aes == 2){
            Serial.println("Cifrando");
            lcd.clear();
            lcd.setCursor(0, 0);
            lcd.print("Cifrando...");
            lcd.setCursor(0, 1);
            lcd.print(entrada);
            delay(5000);
            
            edo_aes = 0;
            edo_sha = 0;

            byte plain[entrada.length()];
            entrada.getBytes(plain, entrada.length());

            aes.iv_inc();
            byte iv [N_BLOCK] ;
            int plainPaddedLength = sizeof(plain) + (N_BLOCK - ((sizeof(plain)-1) % 16)); // length of padded plaintext [B]
         
            byte cipher [plainPaddedLength]; // ciphertext (encrypted plaintext)
            byte check [plainPaddedLength]; // ciphertext (encrypted plaintext)

            entrada = "";
            
            aes.set_IV(myIv);
            aes.get_IV(iv);
            
            lcd.clear();
            lcd.setCursor(0, 0);
            lcd.print("Tiempo cifrado");
            unsigned long ms = micros ();
            aes.do_aes_encrypt(plain,sizeof(plain),cipher,llave,128,iv);
            lcd.setCursor(0, 1);
            lcd.print(micros()-ms);
            delay(5000);

            myString = String((char *)cipher);
            lcd.clear();
            //lcd.setCursor(0, 0);
            //lcd.print("Cifrado...");
            lcd.setCursor(0, 1);
            lcd.print(myString);
            delay(5000);

            //Serial.print("- Cifrado:  ");
            //aes.printArray(cipher,(bool)false); //print cipher with padding

            aes.set_IV(myIv);
            aes.get_IV(iv);

            lcd.clear();
            lcd.setCursor(0, 0);
            lcd.print("Tiempo descifrado");
            ms = micros ();
            aes.do_aes_decrypt(cipher,aes.get_size(),check,llave,128,iv);
            lcd.setCursor(0, 1);
            lcd.print(micros()-ms);
            delay(5000); 

            myString = String((char *)check);
            lcd.clear();
            lcd.setCursor(0, 0);
            lcd.print("Descifrado...");
            lcd.setCursor(0, 1);
            lcd.print(myString);
            delay(5000);

            lcd.clear();
            lcd.setCursor(0, 0);
            lcd.print("Arduino is ready");
            delay(5000);

            //Serial.print("- Descifrado:   ");
            //aes.printArray(check,(bool)true); //print decrypted plain with no padding

            /*Serial.print("- A cifrar:   ");
            Serial.println(entrada); //print plain with no padding
       
            Serial.print("- Cifrado:  ");
            aes.printArray(cipher,(bool)false); //print cipher with padding            

            Serial.print("- Descifrado:   ");
            aes.printArray(check,(bool)true); //print decrypted plain with no padding*/

            entrada = "";
            newData = false;
        }
        
        else if(edo_sha == 1){

            Serial.println("Hashing");
            lcd.clear();
            lcd.setCursor(0, 0);
            lcd.print("Hashing...");
            lcd.setCursor(0, 1);
            lcd.print(entrada);
            delay(5000);
            
            edo_aes = 0;
            edo_sha = 0;

            Sha1.init();
            Sha1.print(entrada);
            printHash(Sha1.result());
            Serial.println(); 

            lcd.clear();
            lcd.setCursor(0, 0);
            lcd.print("Arduino is ready");
            delay(5000);
            
            entrada = "";
            newData = false;
            
        }
    }
}
