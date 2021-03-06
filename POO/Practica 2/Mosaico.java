import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.swing.*;
import java.applet.*;

public class Mosaico extends Applet implements ActionListener {
 JButton []boton=new JButton[40];
 Image []imagen=new Image[40];
 JLabel l;

public void init(){
 setLayout(new GridLayout(4,4));
 for(int i=0;i<40;i++){
 imagen[i] = (new ImageIcon("imagen" +i + ".jpg")).getImage();
 }
		
 for(int i=0;i<40;i++){
 boton[i] =new JButton(new ImageIcon("imagen" +i + ".jpg")); add(boton[i]);
 }

 for(int i=0;i<40;i++){
 boton[i].addActionListener(this);
 }
 
 l=new JLabel("____________________________"); add(l);
 }
 
public void actionPerformed(ActionEvent e){
 JButton a=(JButton)e.getSource();
 for(int i=0;i<40;i++){
 if(a==boton[i]){
 l.setIcon(a.getIcon());
    }
   }
  }
}		

