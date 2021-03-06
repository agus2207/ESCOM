// Programa simple de dibujo.
// función de inicialización para insertar celdas en la tabla
function crearLienzo(){
	var lado = 100;
	var tbody = document.getElementById( "cuerpotabla" );
	for ( var i = 0; i < lado; ++i ){
		var fila = document.createElement( "tr" );
		for ( var j = 0; j < lado; ++j ){
			var celda = document.createElement( "td" );
			fila.appendChild( celda );
		} // fin de for
		tbody.appendChild( fila );
	} // fin de for
	// registrar componente de escucha de mousemove para la tabla
	document.getElementById( "lienzo" ).addEventListener(
	"mousemove", procesarMouseMove, false );
} // fin de la función crearLienzo
// procesa el evento onmousemove
function procesarMouseMove( e ){
	if ( e.target.tagName.toLowerCase() == "td" ){
		// colorea la celda de azul si está presionada la tecla Ctrl
		if ( e.ctrlKey ){
			e.target.setAttribute( "class", "blue" );
		} // fin de if
		// colorea la celda de rojo si está presionada la tecla Mayús
		if ( e.shiftKey ){
			e.target.setAttribute( "class", "red" );
		} // fin de if
	} // fin de if
} // fin de la función procesarMouseMove
window.addEventListener( "load", crearLienzo, false );