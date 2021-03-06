/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voz_señales;

/**
 *
 * @author esc97
 */
import java.io.IOException;
import java.io.InputStream;

import gnu.io.SerialPort;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Process2 extends Thread{
	private boolean stop;
	private javax.swing.JLabel jTextField1;
	private long millis=2000;
	private InputStream In0;
	FileWriter w;
         BufferedWriter bw;
        PrintWriter wr;  
	public Process2(javax.swing.JLabel Source,InputStream S0) {
		stop=false;
		jTextField1=Source;
		In0=S0;
	}
       @Override
	public void run() {
		int  s=0;
                File f;
                f = new File("Muestra2.txt");
		 float Value=0;
		while(stop==false) {
			try {
				s=In0.read();
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