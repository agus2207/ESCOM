<?php
	class MiObjeto {
		var $nombre;
		var $origen;
		var $miCadena;
		var $array1=array();
		var $vector;
		public function __construct($nom, $ori, $cad){
			$this->nombre=$nom;
			$this->origen=$ori;
			$this->miCadena=$cad;
			array_push($this->array1, "adios");
			array_push($this->array1, 10);
			$this->vector=array();
			array_push($this->vector, "Elemento 1");
			array_push($this->vector, NULL);
			array_push($this->vector, "Elemento 3");
			array_push($this->vector, "Elemento 4");
		}
		
	}
	$cadena1 = new MiObjeto('Juan', 'Madrid', NULL);
	$myJSON = json_encode($cadena1);
	echo "<h3>";
	echo "JSON said: ".$myJSON."</h3>";
?>
