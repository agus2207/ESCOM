<?php
	$channel = array('title' => "Hola!", 'link' => 'http://saludo.ejemplo.com/',
		'description' => 'Buenos días.');
	print htmlspecialchars('<channel>')."<br><tab>";  
	foreach ($channel as $element => $content) {
		echo "<blockquote>".htmlspecialchars('<').$element.htmlspecialchars('>');
		print htmlentities($content);
		echo htmlspecialchars('</').$element.htmlspecialchars('>')."<br></blockquote>";
	}
	echo htmlspecialchars('</channel>');
?>