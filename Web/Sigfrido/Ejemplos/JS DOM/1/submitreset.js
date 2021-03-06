// Demostración de los eventos focus y blur.
var arregloAyuda = [ "Escriba su nombre en este cuadro de entrada.",
"Escriba su direcci&oacute;n de e-mail en el formato usuario@dominio.",
"Marque esta casilla si le gust&oacute; nuestro sitio.",
"Escriba aqu&iacute; los comentarlos para que nosotros los leamos.",
"Este bot&oacute;n env&iacute;a el formulario a la secuencia de comandos del lado delservidor.",
"Este bot&oacute;n borra el formulario.",
"" ];
var textoAyuda;
// inicializar textoAyudaDiv y registrar los manejadores de eventos
function inic(){
textoAyuda = document.getElementById( "helpText" );
// registrar componentes de escucha
registrarEscuchas(document.getElementById( "name" ), 0 );
registrarEscuchas(document.getElementById( "email" ), 1 );
registrarEscuchas(document.getElementById( "like" ), 2 );
registrarEscuchas(document.getElementById( "comments" ), 3 );
registrarEscuchas(document.getElementById( "submit" ), 4 );
registrarEscuchas(document.getElementById( "reset" ), 5 );
var miFormulario = document.getElementById( "myForm" );
miFormulario.addEventListener( "submit",
function(){
return confirm("¿Seguro que desea enviar el formulario?");
}, // fin de la función anónima
false );
miFormulario.addEventListener( "reset",
function(){
return confirm("¿Seguro que desea reiniciar el formulario?");
}, // fin de la función anónima
false );
} // fin de la función inic
// función utilitaria para ayudar a registrar eventos
function registrarEscuchas( objeto, numeroMensaje ){
objeto.addEventListener( "focus",
function() { textoAyuda.innerHTML = arregloAyuda[ numeroMensaje ]; },
false);
objeto.addEventListener( "blur",
function(){ textoAyuda.innerHTML = arregloAyuda[ 6 ]; }, false );
} // fin de la función registrarEscuchas
window.addEventListener( "load", inic, false );