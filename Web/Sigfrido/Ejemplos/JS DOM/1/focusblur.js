// Demonstración de los eventos focus y blur.
var helpArray = [ "Ingresar su nombre aqu&iacute;.",
"Ingresar su e-mail con el formato usuario@dominio.",
"Habilitar esta caja si le gusta este sitio.",
"Ingresar comentarios acerca de este sitio.",
"Este bot&oacute;n env&iacute;a el formulario al script del servidor web.",
"Este bot&oacute;n limpia los campos del formulario.", "" ];
var helpText;
// initialize helpTextDiv and register event handlers
function init(){
	helpText = document.getElementById( "helpText" );
	// register listeners
	registerListeners(document.getElementById( "name" ), 0 );
	registerListeners(document.getElementById( "email" ), 1 );
	registerListeners(document.getElementById( "like" ), 2 );
	registerListeners(document.getElementById( "comments" ), 3 );
	registerListeners(document.getElementById( "submit" ), 4 );
	registerListeners(document.getElementById( "reset" ), 5 );
} // end function init
// utility function to help register events
function registerListeners( object, messageNumber ){
	object.addEventListener( "focus", function() { helpText.innerHTML = helpArray[ messageNumber ]; }, false );
	object.addEventListener( "blur", function() { helpText.innerHTML = helpArray[ 6 ]; }, false );
} // end function registerListener
window.addEventListener( "load", init, false );