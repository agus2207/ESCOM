<?php
	$dbhost = "localhost";
	$dbuser = "root";
	$dbpass = "";
	$dbname = "prueba";
	$database = mysqli_connect($dbhost, $dbuser, $dbpass);
	mysqli_select_db($database,$dbname) or die(mysqli_error());
	$edad = $_GET['edad'];
	$sexo = $_GET['sexo'];
	$anio = $_GET['anio'];
	$edad = mysqli_real_escape_string($database, $edad);
	$sexo = mysqli_real_escape_string($database, $sexo);
	$anio = mysqli_real_escape_string($database, $anio);
	$query = "SELECT * FROM ajax_example WHERE sexo = '$sexo'";
	if(is_numeric($edad))
		$query .= " AND edad <= $edad";
	if(is_numeric($anio))
		$query .= " AND anio <= $anio";
	$qry_result = mysqli_query($database, $query) or die(mysqli_error());
	$display_string = "<table>";
	$display_string .= "<tr>";
	$display_string .= "<th>Nombre</th>";
	$display_string .= "<th>Edad</th>";
	$display_string .= "<th>Sexo</th>";	
	$display_string .= "<th>Anio</th>";
	$display_string .= "</tr>";
	while($row = mysqli_fetch_array($qry_result)) {
		$display_string .= "<tr>";
		$display_string .= "<td>$row[nombre]</td>";
		$display_string .= "<td>$row[edad]</td>";
		$display_string .= "<td>$row[sexo]</td>";
		$display_string .= "<td>$row[anio]</td>";
		$display_string .= "</tr>";
	}
	echo "Consulta: " . $query . "<br />";
	$display_string .= "</table>";
	echo $display_string;
?>