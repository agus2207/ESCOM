<!-- Uso de los operadores de comparación de cadenas. --> 
<!DOCTYPE html> 
<html>  
	<head>   
		<meta charset = "utf-8">   
		<title>Comparaci&oacute;n de Cadenas</title>   
		<style type = "text/css">    
			p { margin: 0; }   
		</style>  
	</head>  
	<body>   
		<?php    
			// se crea un arreglo de frutas    
			$fruits = array( "apio", "naranja", "banana" ); 
			// iterar sobre cada elemento del arreglo
			for ( $i = 0; $i < count( $fruits ); ++$i ){    
				// invoca a la función strcmp para comparar el elemento del arreglo    
				// con la cadena "banana"    
				if( strcmp( $fruits[ $i ], "banana" ) < 0 )     
					print( "<p>" . $fruits[ $i ] . " es menor que banana " );    
				elseif ( strcmp( $fruits[ $i ], "banana" ) > 0 )     
					print( "<p>" . $fruits[ $i ] . " es mayor que banana ");    
				else     
					print( "<p>" . $fruits[ $i ] . " es igual que banana " );        
				// uso de los operadores relacionales para comparar cada elemento    
				// con la cadena "apio"    
				if ( $fruits[ $i ] < "apio" )     
					print( "y menor que apio!</p>" );    
				elseif ( $fruits[ $i ] > "apio" )     
					print( "y mayor que apio!</p>" );    
				elseif ( $fruits[ $i ] == "apio" )     
					print( "e igual que apio!</p>" );    
			} // fin del for   
		?>
		<!-- fin del script PHP -->  
	</body> 
</html>