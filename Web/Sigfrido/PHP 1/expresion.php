<!-- Expresiones regulares --> 
<!DOCTYPE html> 
<html>  
	<head>   
		<meta charset = "utf-8">   
		<title>Expresiones regulares</title>   
		<style type = "text/css">    
			p { margin: 0; }   
		</style>  
	</head>  
	<body>   
		<?php    
			$search = "Now is the time";    
			print( "<p>Prueba de la cadena: '$search'</p>" );   
			// invoca a preg_match para encontrar el patrón 'Now' en la búsqueda    
			if( preg_match( "/Now/", $search ) )     
				print( "<p>'Now' se encontr&oacute;.</p>" );    
			// busca el patrón 'Now' en el inicio de la cadena    
			if( preg_match( "/^Now/", $search ) )     
				print( "<p>'Now' encontrado al inicio de la l&iacute;nea.</p>" );    
			// busca el patrón 'Now' al final de la cadena    
			if( !preg_match( "/Now$/", $search ) )     
				print( "<p>'Now' se encontr&oacute; al final de la l&iacute;nea. </p>" );    
			// busca cualquier palabra que termina con 'ow'    
			if ( preg_match( "/\b([a-zA-Z]*ow)\b/i", $search, $match ) )     
				print( "<p>Palabra encontrada terminando en 'ow': ".      $match[ 1 ]. "</p>" );    
			// busca cualquier palabra que inicie con 't'    
			print( "<p>Palabras encontradas que incian con 't': " );    
			while ( preg_match( "/\b(t[[:alpha:]]+)\b/", $search, $match ) ){     
				print( $match[ 1 ]. " " );     
				// elimina la primera ocurrencia de una palabra que inicia     
				// con 't' para encontrar otras instancias en la cadena 
				$search = preg_replace("/" . $match[ 1 ] . "/", "", $search);    
			} // fin del while    
			print( "</p>" );   
		?>
		<!-- fin del script PHP -->  
	</body> 
</html> 
 
 