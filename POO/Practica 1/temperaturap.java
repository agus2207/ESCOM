import java.awt.event.*;
import java.awt.*;
import java.applet.*;

public class temperaturap extends Applet implements ActionListener{
 
 int num=20;
 Button inc;
 Button dec;
 Label etiq;

 public void init(){
   inc=new Button("inc");
   dec=new Button("dec");
   etiq=new Label("20°");

 add(inc); add(dec); add(etiq);
 inc.addActionListener(this);
 dec.addActionListener(this);
 }

 public void actionPerformed(ActionEvent e){
   
  Button b=(Button)e.getSource();
  
 if(b==inc)
  
  num++;

 if(b==dec)

 num--;

 if(num==20 && num<=27)

etiq.setBackground(Color.green);

if(num<20)

etiq.setBackground(Color.blue);

if(num>28)

etiq.setBackground(Color.red);

 etiq.setText(" "+num);


   } 
 }
