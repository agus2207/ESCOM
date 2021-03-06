<?php
	$query = "SELECT id_fruta, nombre_fruta, cantidad FROM tabla_fruta";
	function connectDB(){
		$server = "localhost";
		$user = "root";
		$pass = "";
		$bd = "fruteria";
		$conexion = mysqli_connect($server, $user, $pass, $bd);
		if($conexion){
			echo '<h4>La conexion de la base de datos se ha hecho satisfactoriamente</h4><br>';
		}else{
			echo '<h4>Ha sucedido un error inesperado en la conexion de la base de datos</h4><br>';
		}
		return $conexion;
	}
	function disconnectDB($conexion){
		$close = mysqli_close($conexion);
		if($close){
			echo '<h4>La desconexion de la base de datos se ha hecho satisfactoriamente</h4><br>';
		}else{
			echo '<h4>Ha sucedido un error inesperado en la desconexion de la base de datos</h4><br>';
		}
		return $close;
	}
	function getArraySQL($query){
		//Creamos la conexión con la función anterior
		$conexion = connectDB();
		//generamos la consulta
		mysqli_set_charset($conexion, "utf8"); //formato de datos utf8
		if(!$result = mysqli_query($conexion, $query)) die(); //si la conexión cancela programa
			$rawdata = array(); //creamos un array
			//guardamos en un array multidimensional todos los datos de la consulta
			$i=0;
			while($row = mysqli_fetch_array($result)){
			$rawdata[$i] = $row;
			$i++;
		}
		disconnectDB($conexion); //desconectamos la base de datos
		return $rawdata; //devolvemos el array
	}
	$myArray = getArraySQL($query);
	echo "<h4>De array a JSON</h4><br>";
	echo "<h4>";
	echo json_encode($myArray);
	$json= json_encode($myArray);
	echo "</h4><br>";
	$array = json_decode($json);
	echo "<h4>De JSON a array</h4><br>";
	foreach($array as $obj){
		$id_fruta = $obj->id_fruta;
		$nombre_fruta = $obj->nombre_fruta;
		$cantidad = $obj->cantidad;
		echo "<h4>".$id_fruta." ".$nombre_fruta." ".$cantidad;
		echo "</h4><br>";
	}
?>