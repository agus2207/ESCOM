<!-- Uso de los operadores aritméticos. --> 
<!DOCTYPE html> 
<html>  
	<head>   
		<meta charset = "utf-8">   
		<style type = "text/css">    
			p { margin: 0; }   
		</style>   
		<title>Uso de los operadores aritm&eacute;ticos</title>  
	</head>  
	<body>   
		<?php    
			$a = 5;    
			print( "<p>El valore de la variable a es $a</p>" );    
			// se define la constante VALUE    
			define( "VALUE", 5 ); 
			// agrega la constante VALUE a la variable $a    
			$a = $a + VALUE;    
			print("<p>La variable a despu&eacute;s de a&ntilde;adir la constante VALUE es $a</p>");    
			// multiplica la variable $a por 2    
			$a *= 2;    
			print( "<p>Multiplicar la variable a por 2 resulta $a</p>" );    
			// prueba si la variable $a es menor que 50    
			if ( $a < 50 )     
				print( "<p>La variable a es menor que 50</p>" );    
			// add 40 to variable $a    
			$a += 40;    
			print( "<p>La variable a despu&eacute;s de agregar 40 es $a</p>" );    
			// prueba si la variable $a es 50 o menor    
			if ( $a < 51 )     
				print( "<p>La variable a es todav&iacute;a 50 o menor</p>" );    
			elseif ( $a < 101 ) // $a >= 51 and <= 100     
				print( "<p>La variable a est&aacute; ahora entre 50 y 100,      inclusive</p>" );    
			else // $a > 100     
				print( "<p>La variable a es ahora mayor a 100</p>" );    
			// imprime una variable sin inicializar    
			print( "<p>Uso de una variable antes de inicializarla:     $nothing</p>" ); // nothing se evalúa a ""    
			// agregar la constante VALUE a una variable sin inicializar    
			$test = $num + VALUE; // num se evalúa a 0    
			print( "<p>Una variable no inicializada m&aacute;s una constant     VALUE resulta $test</p>" );    
			// agrega un string a un integer    
			$str = "3 dollars";    
			$a += $str;    
			print( "<p>Agregar un string a la variable a resulta $a</p>" );   
		?>
		<!-- fin del script PHP -->  
	</body> 
</html>