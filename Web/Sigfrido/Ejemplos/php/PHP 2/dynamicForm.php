<!-- Formulario Dinámico. -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset = "utf-8">
		<title>Ejemplo de Formulario de Registro</title>
		<style type = "text/css">
			p { margin: 0px; }
			.error { color: red }
			p.head { font-weight: bold; margin-top: 10px; }
			label { width: 5em; float: left; }
		</style>
	</head>
	<body>
		<?php
			// variables usadas en el script
			$fname = isset($_POST[ "fname" ]) ? $_POST[ "fname" ] : "";
			$lname = isset($_POST[ "lname" ]) ? $_POST[ "lname" ] : "";
			$email = isset($_POST[ "email" ]) ? $_POST[ "email" ] : "";
			$phone = isset($_POST[ "phone" ]) ? $_POST[ "phone" ] : "";
			$book = isset($_POST[ "book" ]) ? $_POST[ "book" ] : "";
			$os = isset($_POST[ "os" ]) ? $_POST[ "os" ] : "";
			$iserror = false;
			$formerrors = array( "fnameerror" => false, "lnameerror" => false, "emailerror" => false, "phoneerror" => false );
			// arreglo de títulos de libros
			$booklist = array( "C&oacute;mo programar en Internet ", "C&oactue;mo programa en C++", "C&oacute;mo programa en Java", "C&oacute;mo programar Visual Basic" );
			// arreglo de los posibles sistemas operativos
			$systemlist = array( "Windows", "Mac OS X", "Linux", "Other" );
			// arreglo de los name-values para los campos de entrada de texto
			$inputlist = array( "fname" => "Nombre", "lname" => "Apellido", "email" => "Email", "phone" => "Tel&eacute;fono" );
			// asegura que todos los campos se llenaron correctamente
			if ( isset( $_POST["submit"] ) ){
				if ( $fname == "" ){
					$formerrors[ "fnameerror" ] = true;
					$iserror = true;
				} // end if
				if ( $lname == "" ){
					$formerrors[ "lnameerror" ] = true;
					$iserror = true;
				} // end if
				if ( $email == "" ){
					$formerrors[ "emailerror" ] = true;
					$iserror = true;
				} // end if
				if ( !preg_match( "/^\([0-9]{3}\) [0-9]{3}-[0-9]{4}$/", $phone ) ){
					$formerrors[ "phoneerror" ] = true;
					$iserror = true;
				} // end if
				if ( !$iserror ){
					// se construye la consulta INSERT
					$query = "INSERT INTO contacts " .
						"( LastName, FirstName, Email, Phone, Book, OS ) " .
						"VALUES ( '$lname', '$fname', '$email', '$phone', '$book', '$os' )";
					// Se conecta a MySQL
					if ( !( $database = mysqli_connect( "localhost", "root", "" ) ) )
						die( "<p>No se pudo conectar a la base de datos</p>" );
					// abre la base de datos MailingList
					if ( !mysqli_select_db( $database, "MailingList" ) )
						die( "<p>No se pudo abrir la base de datos MailingList</p>" );
					// ejecuta la consulta en la base de datos MailingList
					if ( !( $result = mysqli_query( $database, $query ) ) ){
						print( "<p>No se pudo ejecutar la consulta!</p>" );
						die( mysqli_error() );
					} // end if
					mysqli_close( $database );
					print( "<p>Hi $fname. Gracias por completar la encuesta.
						Se ha agregado a la lista de correo $book.</p>
						<p class = 'head'>La siguiente informaci&oacute;n se ha
						guardado en nuestra base de datos:</p>
						<p>Nombre: $fname $lname</p>
						<p>Email: $email</p>
						<p>Tel&eacute;fono: $phone</p>
						<p>OS: $os</p>
						<p><a href = 'formDatabase.php'>Clic aqu&iacute; para mostrar
						la base de datos completa.</a></p>
						<p class = 'head'>Este es solamente un ejemplo de formulario.
						Usted no ha sido agregado a una lista de correo.</p>
						</body></html>" );
					die(); // fin de la página
				} // fin de if
			} // fin de if
			print( "<h1>Ejemplo de Formulario de Registro</h1>
				<p>Por favor, llene todos los campos y clic en Registro.</p>" );
			if ( $iserror ){
				print( "<p class = 'error'>Los campos con * necesitan llenarse
				apropiadamente.</p>" );
			} // end if
			print( "<!-- post form data to dynamicForm.php -->
			<form method = 'post' action = 'dynamicForm.php'>
				<h2>Informaci&oacute;n del Usuario</h2>
				<!-- create four text boxes for user input -->" );
				foreach ( $inputlist as $inputname => $inputalt ){
					print( "<div><label>$inputalt:</label><input type = 'text'
						name = '$inputname' value = '" . $$inputname . "'>" );
					if ( $formerrors[ ( $inputname )."error" ] == true )
						print( "<span class = 'error'>*</span>" );
					print( "</div>" );
				} // end foreach
				if ( $formerrors[ "phoneerror" ] )
					print( "<p class = 'error'>Debe estar en la forma
						(555)555-5555" );
				print( "<h2>Publicaciones</h2>
				<p>De cu&aacute;l libro le interesar&iacute;a tener
					informaci&iacute;n?</p>
				<!-- crea lista desplegable conteniendo nombres de libros -->
				<select name = 'book'>" );
					foreach ( $booklist as $currbook ){
						print( "<option" .
							($currbook == $book ? " selected>" : ">") .
							$currbook . "</option>" );
				}// fin de foreach
				print( "</select>
				<h2>Sistema Operativo</h2>
				<p>Cu&aacute,l sistema operativo utiliza?</p>
				<!-- crea cinco radio buttons -->" );
				$counter = 0;
				foreach ( $systemlist as $currsystem ){
					print( "<input type = 'radio' name = 'os'
						value = '$currsystem' " );
					if ( ( !$os && $counter == 0 ) || ( $currsystem == $os ) )
						print( "checked" );	
					print( ">$currsystem" );
					++$counter;
				} // fin de foreach
				print( "<!-- create a submit button -->
				<p class = 'head'><input type = 'submit' name = 'submit'
				value = 'Registro'></p></form></body></html>" );
		?>
		<!-- end PHP script -->