import java.swing.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;

public class Perros implements ActionListener {
	
	private JFrame Marco;
	private JButton Con, Ing;
	private JLabel Nomb, Edad, Gen, Raza;
	private JTextField Nw, Ew, Gw, Rw;
	private Connection conex;
	
	public Perros() {
		
		Marco = new JFrame("Perros");
		Marco.setLayout(new GridLayout(5,2));
		Con = new JButton("Conectar");
		Ing = new JButton("Ingresar");
		Nomb = new JLabel("Nombre");
		Edad = new JLabel("Edad");
		Gen = new JLabel("Genero");
		Raza = new JLabel("Raza");
		Nw = new JTextField(30);
		Ew = new JTextField(5);
		Gw = new JTextField(10);
		Rw = new JTextField(30);
		Marco.add(Con); Marco.add(Ing); Marco.add(Nomb); Marco.add(Edad); Marco.add(Gen); Marco.add(Raza);
		Marco.add(Nw); Marco.add(Ew); Marco.add(Gw); Marco.add(Rw);
		Con.addActionListener(this);
		Ing.addActionListener(this);
		Marco.setVisible(true);
	}
	
	public Connection connect() {
		
		Connection connection = null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/Perros", "root", " ");
			ResultSet rs = connection.new ResultSet();
			rs.excecute("Insert (Nombre, Edad, Genero, Raza) into Perros ('"+Nomb.getText()toString()+"', '"+Edad.getText()toString()+"', '"+Gen.getText()toString()+"', '"+Raza.getText()toString()+"')");
			
		} catch(SQLException ex){
			
		}
		
		return connection;
	}
	
	public void actionPerformed (ActionEvent event){
		
		JButton b = (JButton)event.getSources();
		if(b == Con)
			conex = connect();
		if(b == Ing){
			Nomb.setText(Nw.getText());
			Edad.setText(Ew.getText());
			Gen.setText(Gw.getText());
			Raza.setText(Rw.getText());
		}	
	}
	
	public static void main(String s[]){
		
		Perros p = new Perros();
		
	}
	
}