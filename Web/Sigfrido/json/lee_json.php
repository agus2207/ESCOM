<?php 
	$data = file_get_contents("datos.json");
	$products = json_decode($data, true);
	foreach ($products as $product) {	
		print_r($products);
		echo "<br>";
	}
?>