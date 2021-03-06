<!-- Muestra el contenido de la cookie. -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset = "utf-8">
		<title>Lectura de Cookies</title>
		<style type = "text/css">
			p { margin: 0px; }
		</style>
	</head>
	<body>
		<p>Los siguientes datos están guardados en una cookie en su computadora.</p>
		<?php	
			// itera en el arreglo $_COOKIE e imprime
			// el nombre y valor de cada cookie
			foreach ($_COOKIE as $key => $value )
				print( "<p>$key: $value</p>" );
		?>
		<!-- fin del script PHP -->
	</body>
</html>