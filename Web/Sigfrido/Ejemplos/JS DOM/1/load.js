// Secuencia de comandos para demostrar el evento load.
var segundos = 0;
// se invoca al cargarse la página para iniciar el temporizador
function iniciarTemporizador(){
	window.setInterval( "actualizarTiempo()", 1000 );
} // fin de la función iniciarTemporizador
// se invoca cada 1000 ms para actualizar el temporizador
function actualizarTiempo(){
	++segundos;
	document.getElementById( "hastaAhora" ).innerHTML = segundos;
} // fin de la función actualizarTiempo
window.addEventListener( "load", iniciarTemporizador, false );