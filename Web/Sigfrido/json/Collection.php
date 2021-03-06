<?php
	$array=array();
	array_push($array, "hello");
	array_push($array, 5);
	$myJSON = json_encode($array);
	echo "<h3>";
	echo "JSON said ".$myJSON."</h3>";
?>