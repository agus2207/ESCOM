<?php
// En versiones de PHP anteriores a la 4.1.0, debería utilizarse $HTTP_POST_FILES en lugar
// de $_FILES.
	/*if ($_FILES['archivo']["error"] > 0)
	 {
	  echo "Error: " . $_FILES['archivo']['error'] . "<br>";
	 }
	
	else
	{
	  echo "Nombre: " . $_FILES['archivo']['name'] . "<br>";
	  echo "Tipo: " . $_FILES['archivo']['type'] . "<br>";
	  echo "Tamaño: " . ($_FILES["archivo"]["size"] / 1024) . " kB<br>";
	  echo "Carpeta temporal: " . $_FILES['archivo']['tmp_name'];
	}

	$dir_subida = 'Archivos/';
	$fichero_subido = $dir_subida . basename($_FILES['archivo']['name']);

	echo '<pre>';
	if (move_uploaded_file($_FILES['archivo']['tmp_name'], $fichero_subido)) {
	    echo "El fichero es válido y se subió con éxito.\n";
	} else {
	    echo "¡Posible ataque de subida de ficheros!\n";
	}*/
	$clave = ini_get("session.upload_progress.prefix") . $_POST[ini_get("session.upload_progress.name")];
	var_dump($_SESSION[$clave]);
	print_r($_SESSION[$clave]);

	foreach ($_FILES["archivo"]["error"] as $clave => $error) {
    if ($error == UPLOAD_ERR_OK) {
        $nombre_tmp = $_FILES["archivo"]["tmp_name"][$clave];
        // basename() puede evitar ataques de denegació del sistema de ficheros;
        // podría ser apropiado más validación/saneamiento del nombre de fichero
        $nombre = basename($_FILES["archivo"]["name"][$clave]);
        move_uploaded_file($nombre_tmp, "Archivos/$nombre");
    	}
	}



	echo 'Más información de depuración:';
	//print_r($_FILES);

	print "</pre>";

?>