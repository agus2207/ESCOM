<!-- Conversión de tipos de datos. --> 
<!DOCTYPE html> 
<html>  
	<head>   
		<meta charset = "utf-8">   
		<title>Conversión de tipos de datos</title>   
		<style type = "text/css">    
			p { margin: 0; }    
			.head { margin-top: 10px; font-weight: bold; }    
			.space { margin-top: 10px; }   
		</style>  
	</head>  
	<body>   
		<?php    
			// declarar un string, double and integer    
			$testString = "3.5 seconds";    
			$testDouble = 79.2;    
			$testInteger = 12;   
		?>
		<!-- fin del script PHP -->   
		<!-- imprime cada valor y tipo de la variable -->   
		<p class = "head">Valores originales:</p>   
		<?php    
			print( "<p>$testString is a(n) ".gettype( $testString )     ."</p>" );    
			print( "<p>$testDouble is a(n) ".gettype( $testDouble )     ."</p>" );    
			print( "<p>$testInteger is a(n) ".gettype( $testInteger )     ."</p>" );   
		?>
		<!-- fin del script PHP -->   
		<p class = "head">Conversi&oacute;n a otros tipos de datos:</p> 
		<?php    
			// llama a la función settype para convertir la variable    
			// testString a diferentes tipos de datos    
			print( "<p>$testString " );    
			settype( $testString, "double" );    
			print( " as a double is $testString</p>" );    
			print( "<p>$testString " );    
			settype( $testString, "integer" );    
			print( " as an integer is $testString</p>" );    
			settype( $testString, "string" );    
			print( "<p class = 'space'>Conversi&oacute;n inversa a string resulta en     $testString</p>" );   
			// usar el tipo casting para convertir las variables a tipos diferentes    
			$data = "98.6 degrees";    
			print( "<p class = 'space'>Antes de la conversi&oacute;n: $data es un ".     gettype( $data )."</p>" );    
			print( "<p class = 'space'>Usando el tipo casting:</p>     
			<p>as a double: ".(double) $data."</p>".     "<p>as an integer: ".(integer) $data."</p>");    
			print( "<p class = 'space'>Despu&eacute;s de la conversi&oacute;n: $data is a ".    gettype( $data )."</p>" );   
		?>
		<!-- end PHP script -->  
	</body> 
</html> 