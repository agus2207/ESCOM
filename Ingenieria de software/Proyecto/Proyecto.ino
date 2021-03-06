int leds[]={8, 9, 10, 11, 12, 13}; //Arreglo de leds

int pot; //Valor del Potenciometro
int n=0;

const int Trigger = 6; //Trigger del Sensor
const int Echo = 7; //Echo del sensor

char VarChar = '0';

void setup(){
    Serial.begin(9600);
    pinMode(Trigger, OUTPUT); //pin como salida
    pinMode(Echo, INPUT);  //pin como entrada
    digitalWrite(Trigger, LOW);//Inicializamos el pin con 0
    
    for(n = 0; n < 6; n++) {
        pinMode(leds[n],OUTPUT);
        digitalWrite(leds[n], LOW);
    }
}

void loop(){

    //Control de luces
    pot = analogRead(A0);// variable pot entrada analógica en A0
    //Serial.print("Valor del potenciometro");
    //Serial.println(pot);
    if (VarChar == '1'){
        for(n = 0; n < 6; n++) {
            digitalWrite(leds[n], LOW);
        } 
    }
    else if(pot > 0 && pot <= 165){
        for(n = 0; n < 6; n++) {
            digitalWrite(leds[n], LOW);
        }
        digitalWrite(leds[0], HIGH);
    }
    else if(pot >= 165 && pot <= 330){
        for(n = 0; n < 6; n++) {
            digitalWrite(leds[n], LOW);
        }
        digitalWrite(leds[1], HIGH);
    }
    else if(pot >= 330 && pot <= 495){
        for(n = 0; n < 6; n++) {
            digitalWrite(leds[n], LOW);
        }
        digitalWrite(leds[2], HIGH);
    }
    else if(pot >= 495 && pot <= 660){
        for(n = 0; n < 6; n++) {
            digitalWrite(leds[n], LOW);
        }  
        digitalWrite(leds[3], HIGH);
    }
    else if(pot >= 660 && pot <= 825){
        for(n = 0; n < 6; n++) {
            digitalWrite(leds[n], LOW);
        }
        digitalWrite(leds[4], HIGH);
    }
    else if(pot >= 825 && pot <= 1000){
        for(n = 0; n < 6; n++) {
            digitalWrite(leds[n], LOW);
        }
        digitalWrite(leds[5], HIGH);
    }
    else if(pot >= 1000){
        for(n = 0; n < 6; n++) {
            digitalWrite(leds[n], LOW);
        }    
    }

    //Distancia
    long t; //timepo que demora en llegar el eco
    long d; //distancia en centimetros
  
    digitalWrite(Trigger, HIGH);
    delayMicroseconds(10);          //Enviamos un pulso de 10us
    digitalWrite(Trigger, LOW);
    
    t = pulseIn(Echo, HIGH); //obtenemos el ancho del pulso
    d = t/59;             //escalamos el tiempo a una distancia en cm
    
    delay(100);          //Hacemos una pausa de 100ms
    
    //Comunicacion Bluetooth
    if(Serial.available() > 0){
        int bandera = 0;
        Serial.println("Modulo Conectado");
        Serial.println("#");
        delay(1000);
        
        //Verifica si alguna luz esta prendida
        for(n = 0; n < 6; n++){
            int estado = digitalRead(leds[n]);
            if(estado == HIGH && d >= 50){
                bandera = 1;
            }
        }

        if(bandera == 1){
            Serial.println("Luz encendida");
            //Serial.println("#");
            Serial.println("Usuario no detectado");
            Serial.print("Distancia: ");
            Serial.print(d);      //Enviamos serialmente el valor de la distancia
            Serial.print("cm");
            Serial.println("#");
            delay(1000);
            VarChar = Serial.read();
            if(VarChar == '1'){
                Serial.print("Luces apagadas");
                Serial.println("#");
            }
        }

        else{
            Serial.println("Usuario cerca detectado");
            Serial.print("¿Desea apagar sus luces?");
            Serial.println("#");
            delay(1000);
            VarChar = Serial.read();
            if(VarChar == '1'){
                Serial.print("Luces apagadas");
                Serial.println("#");
            }
        }
    }
}
