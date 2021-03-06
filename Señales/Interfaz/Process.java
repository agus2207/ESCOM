package voz_señales;



import java.io.IOException;
import java.io.InputStream;
import java.io.*;
import gnu.io.SerialPort;
import java.util.Random;

public class Process extends Thread{
	private boolean stop;
	private javax.swing.JLabel jTextField1;
	private long millis=2000;
	private InputStream In0;
	FileWriter w;
         BufferedWriter bw;
        PrintWriter wr;  
	public Process(javax.swing.JLabel Source,InputStream S0) {
		stop=false;
		jTextField1=Source;
		In0=S0;
	}
       @Override
	public void run() {
		double  s=0;
                File f;
                f = new File("Muestra1.txt");
		 float Value=0;
                 Random r=new Random();
		while(stop==false) {
			try {
				s=1 +( 499 ) * r.nextDouble();
				Value=(float) ((float)s*1.00);
                                 w = new FileWriter(f);
                                bw = new BufferedWriter(w);
                                wr = new PrintWriter(bw);  
                                //wr.write("Esta es una linea de codigo");//escribimos en el archiv
                                wr.print(Value);
                                wr.append("\n");
				 
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			jTextField1.setText(Float.toString(Value));
			/*try {
				Thread.sleep(millis);
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
	}
		}
	public void Detener() throws IOException {
		stop=true;
		jTextField1.setText("");
                wr.close();
                bw.close();
	}

}