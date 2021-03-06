<!-- Carrito de compras-->
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Carrito de compras</title>
		<link rel="stylesheet" href="master.css" type="text/css">
	</head>
	<body link="#0000FF" vlink="#800080" class="body">
        <script>
             function clearBasket() {
	         	index = document.cookie.indexOf("TheBasket");
		        document.cookie="TheBasket=.";
		        top.location = "estado.html";
		}
        </script>
		<center>
			<?php
				$nombre = $_POST['nombre'];
				$apellido = $_POST['apellido'];
				$telefono = $_POST['telefono'];
				$email = $_POST['email'];
                                $lista = $_POST['Lista'];
                                for($i=1; $i<=$lista; $i++){
                                       ${"producto".$i}=$_POST["Producto".$i];
                                       ${"cantidad".$i}=$_POST["Cantidad".$i];
                                       ${"ptotal".$i}=$_POST["ProductoTotal".$i];
                                }
                                $total= $_POST['Total'];
				$con = mysqli_connect("localhost", "carrito4_agus22", "asgard22") or die ("No se ha podido conectar al servidor de Base de datos");
				mysqli_select_db($con, "carrito4_carrito") or die ( "Upps! Pues va a ser que no se ha podido conectar a la base de datos" );
				mysqli_query($con, "Insert into usuarios (nombre, apellido, telefono, email, total) values ('$nombre', '$apellido', '$telefono', '$email', '$total')");
                                for($i=1; $i<=$lista; $i++){
                                        mysqli_query($con, "Insert into productos (nombre, producto, cantidad, total, cuentatotal) values ('$nombre', '${"producto".$i}', '${"cantidad".$i}', '${"ptotal".$i}', '$total')");
                                }
			?>
                       <h4>Seguir Comprando</h4>
                       <a href="index.html" onClick="clearBasket()"><img src="tienda1.png" width="150" height="200" border="0"></a>
		</center>
		<div id="content">
			<div id="mainContent">
				<section>
					<article class="blogPost">
						<header>
							<h2>Bienvenido al carrito de compras</h2>
							<p>Enviado el <time datetime="2016-06-29T23:31+01:00">15 de Septiembre de 2018</time> por <a href="#">Agustin Galindo Reyes</a> - <a href="#comments">3 comentarios</a></p>
						</header>
					</article>
				</section>
				<section id="comments">
					<h3>Comentarios</h3>
					<article>
						<header>
							<a href="#">Antonio Estrella</a> el <time datetime="2016-06-29T23:35:20+01:00">1 de Octubre de 2018</time>
						</header>
						<p>Posiblemente la mejor tienda de comics en linea. Tienen un staff muy servicial y todo muy bien organizado.</p>
					</article>
					<article>
						<header>
							<a href="#">Pedro Parque</a> on <time datetime="2016-06-29T23:40:09+01:00">29 de Septiembre de 2018</time>
						</header>
						<p>Un lugar muy surtido de comics en ingles y algunos volumenes en latino ,en la cual encontraran cualquier tipo de novela grafica o comics con accesibilidad en todos los aspectos</p>
					</article>
					<article>
						<header>
							<a href="#">Bruno Diaz</a> on <time datetime="2016-06-29T23:59:00+01:00">24 de Septiembre de 2018</time>
						</header>
						<p>Mi hijo disfruta mucho visitar este lugar, pero hay publicaciones que no infantiles y estan a su alcance, por lo que es importante que los padres no dejen solos a sus hijos, o que pongan esas publicaciones en un lugar mas limitado en su acceso.</p>
					</article>
				</section>
				<form action="#" method="post">
					<h3>Enviar un comentario</h3>
					<p>
						<label for="name">Nombre</label>
						<input name="name" id="name" type="text" required />
					</p>
					<p>
						<label for="email">E-mail</label>
						<input name="email" id="email" type="email" required />
					</p>
					<p>
						<label for="website">Website</label>
						<input name="website" id="website" type="url" />
					</p>
					<p>
						<label for="comment">Comentario</label>
						<textarea name="comment" id="comment" required></textarea>
					</p>
					<p><input type="submit" value="Post comment" /></p>
				</form>
			</div>
			<aside>
				<section>
					<header>
						<h3>Archivos</h3>
					</header>
					<ul>
						<li><a href="carrito.html">Diciembre 2017</a></li>
						<li><a href="carrito.html">Enero 2018</a></li>
						<li><a href="carrito.html">Febrero 2018</a></li>
						<li><a href="carrito.html">Marzo 2018</a></li>
						<li><a href="carrito.html">Abril 2018</a></li>
						<li><a href="carrito.html">Mayo 2018</a></li>
						<li><a href="carrito.html">Junio 2018</a></li>
					</ul>
				</section>
			</aside>
		</div>
		<footer>
			<div>
				<section id="about">
					<header>
						<h3>Acerca</h3>
					</header>
					<p>
						MISION<br>

						Ofrecer una gran variedad de productos geek a un buen precio, para satisfacer todas las necesidades <br>
						de los coleccionistas mexicanos. Contamos con los mejores productos con certificado de autenticidad <br>
					</p>
				</section>
				<section id="Follow">
					<header>
						<h3>Siguenos en</h3>
					</header>
					<ul>
						<li><a href="https://www.facebook.com/">Facebook</a></li>
						<li><a href="https://www.twitter.com/">Twitter</a></li>
						<li><a href="https://www.instagram.com/">Instagram</a></li>
						<li><a href="https://www.snapchat.com/">Snapchat</a></li>
						<li><a href="https://www.periscope.com/">Periscope</a></li>
					</ul>
				</section>
				<section id="popular">
					<header>
						<h3>Popular</h3>
					</header>
					<ul>
						<li><a href="https://www.comic-con.org/">San Diego Comic-Con 2018</a></li>
						<li><a href="https://www.lamole.com.mx/">La mole 2018</a></li>
						<li><a href="http://expocoleccionistas.com.mx/">Expo Coleccionistas Mexico</a></li>
						<li><a href="https://www.comic-con.org/">Expo TNT</a></li>
						<li><a href="https://www.frikiplaza.com/">Frikiplaza</a></li>
						<li><a href="https://www.funko.com/">Funko Pop</a></li>
						<li><a href="https://disneyland.disney.go.com/destinations/disneyland/">Disneyland Park</a></li>
						<li><a href="www.hottoys.com.hk/">Hot Toys</a></li>
						<li><a href="https://shop.lego.com/">Lego</a></li>
					</ul>
				</section>
			</div>
		</footer>
	</body>
</html>
