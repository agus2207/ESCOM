// Cancelación del burbujeo de eventos.
function clicDocumento(){
	alert( "Hizo clic en el documento." );
} // fin de la función clicDocumento
function burbujeo( e ){
	alert( "Esto va a burbujear." );
	e.cancelBubble = false;
} // fin de la función burbujeo
function sinBurbujeo( e ){
	alert( "Esto no va a burbujear." );
	e.cancelBubble = true;
} // fin de la función sinBurbujeo
function registrarEventos(){
	document.addEventListener( "click", clicDocumento, false );
	document.getElementById( "burbujeo" ).addEventListener(
		"click", burbujeo, false );
	document.getElementById( "sinBurbujeo" ).addEventListener(
		"click", sinBurbujeo, false );
} // fin de la función registrarEventos
window.addEventListener( "load", registrarEventos, false );