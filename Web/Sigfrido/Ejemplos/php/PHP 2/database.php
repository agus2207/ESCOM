<!-- Consulta de una base de datos y muestra de los resultados. -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset = "utf-8">
		<title>Resultados de la b&uacute;squeda</title>
		<style type = "text/css">
			body { font-family: sans-serif;
			background-color: lightyellow; }
			table { background-color: lightblue;
			border-collapse: collapse;
			border: 1px solid gray; }
			td { padding: 5px; }
			tr:nth-child(odd) {
				background-color: white; }
		</style>
	</head>
	<body>
		<?php
			$select = $_POST["select"]; // crea la variable $select
			// construy la consulta SELECT
			$query = "SELECT " . $select . " FROM books";
			// Connect to MySQL
			if ( !( $database = mysqli_connect( "localhost","root", "" ) ) ) 
				die( "No se pudo conectar a la base de datos </body></html>" );
			// abre la base de datos Products
			if ( !mysqli_select_db( $database, "products" ) )
				die( "No se pudo conectar a la base de datos </body></html>" );
			// consulta a la base de datos Products
			if ( !( $result = mysqli_query( $database, $query ) ) ){
				print( "<p>No se pude ejecutar la consulta!</p>" );
				die( mysqli_error() . "</body></html>" );
			} // end if
			mysqli_close( $database );
		?>
		<!-- end PHP script -->
		<table>
			<caption>Resultdos de "SELECT <?php print( "$select" ) ?>
			FROM books"</caption>	
			<?php
				// busca cada registro en conjunto de results
				while( $row = mysqli_fetch_row( $result ) ){
					// construye la tabla para mostrar los resultados
					print( "<tr>" );
					foreach ( $row as $key => $value )
						print( "<td>$value</td>" );
					print( "</tr>" );
				} // end while
			?>
			<!-- end PHP script -->
		</table>
		<p>Su b&uacute;squeda termin&oacute;
		<?php print( mysqli_num_rows( $result ) ) ?> results.</p>
		<p>Por env&iacute;e sus comentarios a <a href = "mailto:deitel@deitel.com">
		Deitel and Associates, Inc.</a></p>
	</body>
</html>