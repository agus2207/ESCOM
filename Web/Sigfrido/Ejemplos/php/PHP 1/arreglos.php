<!-- Manejo de Arreglos. --> 
<!DOCTYPE html> 
<html>  
	<head>   
		<meta charset = "utf-8">   
		<title>Manejo de Arreglos</title>   
		<style type = "text/css">    
			p { margin: 0; }    
			.head { margin-top: 10px; font-weight: bold; }   
		</style>  
	</head>  
	<body>   
		<?php    
			// se crea el primer arreglo    
			print( "<p class = 'head'>Creando el primer arreglo</p>" );    
			$first[ 0 ] = "cero";    
			$first[ 1 ] = "uno";    
			$first[ 2 ] = "dos";    
			$first[] = "tres";    
			// imprime el índice y valor de cada elemento    
			for ( $i = 0; $i < count( $first ); ++$i )     
				print( "Elemento $i es $first[$i]</p>" );    
			print( "<p class = 'head'>Creando el segundo arreglo</p>" );    
			// se invoca a la función array para crear el segundo arreglo    
			$second = array( "cero", "uno", "dos", "tres" );        
			for ( $i = 0; $i < count( $second ); ++$i )     
				print( "Elemento $i es $second[$i]</p>" ); 
			print( "<p class = 'head'>Creando el tercer arreglo</p>" );    
			// asigna valores a la entradas usando índices no numéricos    
			$third[ "Paty" ] = 21;    
			$third[ "Diana" ] = 18;    
			$third[ "Ale" ] = 23; 	
			// itera en los elementos del arreglo e imprime cada    
			// nombre y valor del elemento    
			for ( reset( $third ); 
				$element = key( $third ); next( $third ) )    
			print( "<p>$element es $third[$element]</p>" ); 
			print( "<p class = 'head'>Creando el cuarto arreglo</p>" ); 
			// invoca a la función array para crear el cuarto arreglo usando    
			// índices string    
			$fourth = array(     
				"Enero" => "primer", "Febrero" => "segundo",     
				"Marzo" => "tercer", "Abril" => "cuarto",     
				"Mayo" => "quinto", "Junio" => "sexto",     
				"Julio" => "s&eacute;ptimo", "Agosto" => "octavo",     
				"Septiembre" => "noveno", "Octubre" => "d&eacute;cimo",     
				"Noviembre" => "onceavo","Diciembre" => "doceavo" );    
			// imprime el nombre y valor de cada    
			foreach ( $fourth as $element => $value )     
				print( "<p>$element es el $value mes</p>" );   
		?>
		<!-- fin del script PHP -->  
	</body> 
</html>