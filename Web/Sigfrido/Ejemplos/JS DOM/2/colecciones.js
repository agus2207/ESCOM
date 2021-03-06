// Secuencia de comandos para demostrar el uso de la colección links.
function procesarVinculos(){
	var listaVinculos = document.links; // obtiene los vínculos del documento
	var contenido = "<ul>";
	// concatenar cada vinculo a contenido
	for ( var i = 0; i < listaVinculos.length; ++i ){
		var vinculoActual = listaVinculos[ i ];
		contenido += "<li><a href='" + vinculoActual.href + "'>" +
		vinculoActual.innerHTML + "</li>";
	} // fin de for
	contenido += "</ul>";
	document.getElementById( "vinculos" ).innerHTML = contenido;
} // fin de la función procesarVinculos
window.addEventListener( "load", procesarVinculos, false );