<!-- Procesa la información enviada desde formulario.html. --> 
<!DOCTYPE html> 
<html>  
	<head>   
		<meta charset = "utf-8">   
		<title>Validaci&oacute;n del Formulaario</title>   
		<style type = "text/css">    
			p { margin: 0px; }    
			.error { color: red }    
			p.head { font-weight: bold; margin-top: 10px; }   
		</style>  
	</head>  
	<body>   
		<?php    
			// determina si el número telefónico es válido e imprime    
			// un mensaje de error si no lo es    
			if (!preg_match( "/^\([0-9]{3}\) [0-9]{3}-[0-9]{4}$/",     $_POST["phone"])){     
				print( "<p class = 'error'>N&uacute;mero telef&oacute;nico inv&aacute;lido</p>      
				<p>Un n&uacute;mero telef&oacute;nico v&aacute;lido debe tener la forma (555) 555-5555</p>
				<p>Clic en el bot&oacute;n Regresar, ingresar un  n&uacute;mero telef&oacute;nico v&aacute;lido y reenviar.</p>  
				<p>Gracias.</p></body></html>" );     
				die(); // terminate script execution    
			}   
		?>
		<!-- end PHP script -->   
		<p>Hi 
		<?php 
			print( $_POST["fname"] ); 
		?>
		. Thank you for    completing the survey. You have been added to the    
		<?php 
			print( $_POST["book"] ); 
		?>mailing list.</p>   
		<p class = "head">The following information has been saved in our database:</p>   
		<p>Name: <?php print( $_POST["fname"] );    
			print( $_POST["lname"] ); ?></p>   
		<p>Email: <?php print( $_POST["email"] ); ?></p>   
		<p>Phone: <?php print($_POST["phone"] ); ?></p>   
		<p>OS: <?php print( $_POST["os"] ); ?></p>   
		<p class = "head">This is only a sample form.    
		You have not been added to a mailing list.</p>  
	</body> 
</html> 