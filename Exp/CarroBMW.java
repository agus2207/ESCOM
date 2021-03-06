
public class CarroBMW extends Carro{

	public CarroBMW(){

		this.modelo="BMW";
	}

	public void turbo(){

		System.out.println("Acabas de viajar a 100 millas por hora");
	}

	public static void main (String[] args){

		CarroBMW c = new CarroBMW();
		c.apagar();
		c.encender();
		c.estado();
		c.turbo();
		
	}
	
}
