<!-- Un programa sencillo en PHP. --> 
<!DOCTYPE html> 
<html>  
	<?php   
		$name = "Beatles"; // declaration and initialization  
	?>
	<!-- end PHP script -->  
	<head>   
		<meta charset = "utf-8">   
		<title>Simple PHP document</title>  
	</head>  
	<body>   
		<!-- print variable name’s value -->   
		<h1>
			<?php 
				print( "Bienvenido a PHP, $name!" ); 
			?>
		</h1>  
	</body> 
</html>