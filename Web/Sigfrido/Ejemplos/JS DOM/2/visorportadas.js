// Secuencia de comandos para demostrar los estilos dinámicos que se utilizan en animaciones.
var intervalo = null; // lleva el registro del intervalo
var velocidad = 6; // determina la velocidad de la animación
var cuenta = 0; // tamaño de la imagen durante la animación
// se llama repetidas veces para animar la portada del libro
function ejecutar(){
	cuenta += velocidad;
	// detener la animación cuando la imagen sea lo bastante grande
	if ( cuenta >= 375 ){
		window.clearInterval( intervalo );
		intervalo = null;
	} // fin de if
	var imagenGrande = document.getElementById( "imgPortada" );
	imagenGrande.setAttribute( "style", "width: " + (0.7656 * cuenta + "px;") +
		"height: " + (cuenta + "px;") );
} // fin de la función ejecutar
// inserta la imagen apropiada en el área de la imagen principal y comienza la animación
function mostrar( archimg ){
	if ( intervalo )
		return;
	var imagenGrande = document.getElementById( "imgPortada" );
	imagenGrande.setAttribute( "style", "width: 0px; height: 0px;" );
	imagenGrande.setAttribute( "src", "normal/" + archimg );
	imagenGrande.setAttributeC "alt", "Versi&oacute;n grande de " + archimg );
	cuenta = 0; // iniciar la imagen en tamaño 0
	intervalo = window.setInterval( "ejecutar()", 10 ); // animar
} // fin de la función mostrar
// registrar manejadores de eventos
function iniciar(){
	document.getElementById( "jhtp" ).addEventListener(
		"click", function() { mostrar( "jhtp.jpg" ); }, false );
	document.getElementById( "iw3htp" ).addEventListener(
		"click", function() { mostrar( "iw3htp.jpg" ); }, false );
	document.getElementById( "cpphtp" ).addEventListener(
		"click", function() { mostrar( "cpphtp.jpg" ); }, false);
	document.getElementById( "jhtplov" ).addEventListener(
		"click", function() { mostrar( "jhtplov.jpg" ); }, false );
	document.getElementById( "cpphtplov" ).addEventListener(
		"click", function() { mostrar( "cpphtplov.jpg" ); }, false );
	document.getElementById( "vcsharphtp" ).addEventListener(
		"click", function() { mostrar( "vcsharphtp.jpg" ); }, false );
} // fin de la función iniciar
window.addEventListener( "load", iniciar, false );