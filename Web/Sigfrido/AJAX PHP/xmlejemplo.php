<!DOCTYPE html>
<html>
	<body>
		<?php
			$note=<<<XML
<?xml version='1.0' standalone='yes'?>
<note>
<to>Gopal K Verma</to>
<from>Sairamkrishna</from>
<heading>Project submission</heading>
<body>
Lea cuidadosamente.
</body>
</note>
XML;
			$xml=simplexml_load_string($note);
			print_r($xml);
		?>
	</body>
</html>