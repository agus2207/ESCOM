public class Ticket implements Runnable{
   
 private int sleepTime, howMany, i;
 private String s,s2="";
 Thread t;
 
public Ticket(String string, int count, int sleep){
   s=string;
   count=howMany;         
   sleepTime=sleep;
   t = new Thread(this);
   t.start();
 }

public void run(){

   char imprimir;
   int tam;
   tam=s.length();
   System.out.println(s);

   while(true){
   
   for(i=0; i<s.length(); i++){
   s2+=s.charAt((i+1) % s.length());
   }

   System.out.println(s2);
   s=s2;
   s2= "";


   try{

   Thread.sleep(500);
   }

   catch(Exception e){
  return;
  }
   howMany--;
 }
}

public static void main(String s[]){
   new Ticket("PING PONG",5,500); 
}

interface Runneable{
void run();
}
}
