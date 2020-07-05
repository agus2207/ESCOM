package protocolo;
import java.io.FileWriter;

public class ACK {
    
    int q = 0, s=0;
    String a = "";
    FileWriter f, f1;
    
    public void aut(String p) throws Exception {
        
        q = 0;
        
        for (int i = 0; i < p.length(); i++){
            
            char c = p.charAt(i);
            
            if (c >= 97 && c <= 122 || c >= 65 && c <= 90 || c==164 || c==165 || c==130 || c >= 160 && c <= 163 || c==181 || c==144 || c==214 || c==224 || c==233){
                
                if (c == 'c' || c == 'C' || c == 'E' || c == 'e' || c == 'F' || c == 'f' || c == 'M' || c == 'm' || c == 'R' || c == 'r' || c == 'T' || c == 't' || c == 'V' || c == 'v'){
                    
                    q=1;
                }
                else if ((c == 'O' || c == 'o') && q==1){
                    
                    q=2;
                }
                else if ((c == 'P' || c == 'p') && q==2){
                    
                    q=3;
                }
                else if ((c == 'I' || c == 'i') && q==3){
                    
                    q=4;
                }
                else if ((c == 'A' || c == 'a') && q==4){
                    
                    q=5;
                }
                else if ((c == 'r' || c == 'R') && q==5){
                    
                    q=6;
                }
                else if ((c == 'r' || c == 'R') && q==2){
                    
                    q=7;
                }
                else if ((c == 'r' || c == 'R') && q==7){
                    
                    q=8;
                }
                else if ((c == 'u' || c == 'U') && q==8){
                    
                    q=9;
                }
                else if ((c == 'p' || c == 'P') && q==9){
                    
                    q=10;
                }
                else if ((c == 'c' || c == 'C') && q==10){
                    
                    q=11;
                }
                else if ((c == 'i' || c == 'I') && q==11){
                    
                    q=12;
                }
                else if ((c == 'o' || c == 'O') && q==12){
                    
                    q=13;
                }
                else if ((c == 'n' || c == 'N') && q==13){
                    
                    q=14;
                }
                else if ((c == 'n' || c == 'N') && q==1){
                    
                    q=15;
                }
                else if ((c == 'g' || c == 'G') && q==15){
                    
                    q=16;
                }
                else if ((c == 'a' || c == 'A') && q==16){
                    
                    q=17;
                }
                else if ((c == 'ñ' || c == 'Ñ') && q==17){
                    
                    q=18;
                }
                else if ((c == 'a' || c == 'A') && q==18){
                    
                    q=19;
                }
                else if ((c == 'r' || c == 'R') && q==19){
                    
                    q=20;
                }
                else if ((c == 'o' || c == 'O') && q==18){
                    
                    q=21;
                }
                else if ((c == 'a' || c == 'A') && q==1){
                    
                    q=22;
                }
                else if ((c == 'l' || c == 'L') && q==22){
                    
                    q=23;
                }
                else if ((c == 'S' || c == 's') && q==23){
                    
                    q=24;
                }
                else if ((c == 'i' || c == 'I') && q==24){
                    
                    q=25;
                }
                else if ((c == 'f' || c == 'F') && q==25){
                    
                    q=26;
                }
                else if ((c == 'i' || c == 'I') && q==26){
                    
                    q=27;
                }
                else if ((c == 'c' || c == 'C') && q==27){
                    
                    q=28;
                }
                else if ((c == 'a' || c == 'A') && q==28){
                    
                    q=29;
                }
                else if ((c == 'R' || c == 'r') && q==29){
                    
                    q=30;
                }
                else if ((c == 'o' || c == 'O') && q==24){
                    
                    q=31;
                }
                else if ((c == 'e' || c == 'E') && q==1){
                    
                    q=32;
                }
                else if ((c == 'n' || c == 'N') && q==32){
                    
                    q=33;
                }
                else if ((c == 't' || c == 'T') && q==33){
                    
                    q=34;
                }
                else if ((c == 'i' || c == 'I') && q==34){
                    
                    q=35;
                }
                else if ((c == 'r' || c == 'R') && q==35){
                    
                    q=36;
                }
                else if ((c == 'b' || c == 'B') && q==2){
                    
                    q=37;
                }
                else if ((c == 'a' || c == 'A') && q==37){
                    
                    q=38;
                }
                else if ((c == 'r' || c == 'R') && q==38){
                    
                    q=39;
                }
                else if ((c == 'r' || c == 'R') && q==1){
                    
                    q=40;
                }
                else if ((c == 'a' || c == 'A') && q==40){
                    
                    q=41;
                }
                else if ((c == 'f' || c == 'F') && q==41){
                    
                    q=42;
                }
                else if ((c == 'i' || c == 'I') && q==42){
                    
                    q=43;
                }
                else if ((c == 'c' || c == 'C') && q==43){
                    
                    q=44;
                }
                else if ((c == 'a' || c == 'A') && q==44){
                    
                    q=45;
                }
                else if ((c == 'r' || c == 'R') && q==45){
                    
                    q=46;
                }
                else if ((c == 'I' || c == 'i') && q==1){
                    
                    q=47;
                }
                else if ((c == 'o' || c == 'O') && q==47){
                    
                    q=48;
                }
                else if ((c == 'l' || c == 'L') && q==48){
                    
                    q=49;
                }
                else if ((c == 'e' || c == 'E') && q==49){
                    
                    q=50;
                }
                else if ((c == 'n' || c == 'N') && q==50){
                    
                    q=51;
                }
                else if ((c == 'c' || c == 'C') && q==51){
                    
                    q=52;
                }
                else if ((c == 'i' || c == 'I') && q==52){
                    
                    q=53;
                }
                else if ((c == 'a' || c == 'A') && q==53){
                    
                    q=54;
                }
                else {
                    
                    q=0;
                }
                a=a+c;

            } else {
                
                System.out.println("q" + q + "----->" + c + "----->q0");
                f.write("q" + q + "----->" + c + "----->q0");
                f.write("\r\n");
                q=0;
                a = "";
            }
            
            if (q == 6 || q == 14 || q == 20 || q == 21 || q == 30 || q == 31 || q == 36 || q == 39 || q == 46 || q == 54) {

                System.out.println(a);
                f1.write(a);
                f1.write("\r\n");
                s++;
                //System.out.println("Palabras aceptadas "+s);
                //f1.write("Palabras aceptadas "+s);
                a = "";
            }
        }
    }
}
