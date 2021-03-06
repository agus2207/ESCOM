<!-- Escritura de la cookie al cliente. -->
<?php
	define( "FIVE_DAYS", 60 * 60 * 24 * 5 ); // define una constante
	// escribe cada valor del campo del formulario a una cookie y asigna la
	// la fecha de expiración de la cookie
	setcookie( "nombre", $_POST["nombre"], time() + FIVE_DAYS );
	setcookie( "estatura", $_POST["estatura"], time() + FIVE_DAYS );
	setcookie( "color", $_POST["Color"], time() + FIVE_DAYS );
	$Color=$_POST["Color"];
?> 
<!-- fin del script PHP -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset = "utf-8">
		<title>Cookie Guardada</title>
		<style type = "text/css">
			p { margin: 0px; }
		</style>
	</head>
	<body>
		<p>La cookie se ha configurado con los datos siguientes:</p>
		<!-- se imprime cada valor de los campos del formulario -->
		<p>Nombre: <?php print( "$_POST[nombre]" ) ?></p>
		<p>Estatura: <?php print( "$_POST[estatura]" ) ?></p>
		<p>Color Favorito:
		<?php print( "$_POST[Color]" ) ?></span></p>
		<p>Clic <a href = "readCookies.php">aqu&iacute;</a>
			para leer la cookie guardada.</p>
	</body>
</html>