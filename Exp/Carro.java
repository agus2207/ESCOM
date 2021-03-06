import java.awt.*;

public class Carro {

	double peso;
	double altura;
	double ancho;
	double largo;
	int nP;
	boolean enc=false;
	String modelo;

	public Carro(){

		this.peso=1000;
		this.altura=1.90;
		this.ancho=4;

	}

	public double obtenerPeso(){

		return this.peso;
	}

	public void encender(){

		this.enc=true;
		System.out.println("El carro esta encendido");

	}


	public void apagar(){

		this.enc=false;
		System.out.println("El carro esta apagado");
		
	}

	public void estado(){

		if(this.enc==true)
			System.out.println("El carro esta encendido");
		else
			System.out.println("El carro esta apagado");
	}
}

