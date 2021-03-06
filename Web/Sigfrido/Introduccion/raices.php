<?php
	$a=$_POST['a'];
	$b=$_POST['b'];
	$c=$_POST['c'];
	$resta=(pow($b, 2))-(4*$a*$c);
	echo "<h3>Las raices de a= ".$a.", b= ".$b.", c=".$c." son: </h3>";
	if($resta < 0){
		$resta=$resta*(-1);
		$raiz = sqrt($resta);
		$x1= -$b/(2*$a);
		$x2 = $raiz/(2*$a);
		echo "<h3>x1= ".$x1."+".$x2."i"."<br></h3>";
		echo "<h3>x2= ".$x1."-".$x2."i</h3>";
	}
	else if($resta == 0){
		$x1=(-$b)/(2*$a);
		$x2=(-$b)/(2*$a);
		echo "<h3>x1= ".$x1."<br></h3>";
		echo "<h3>x2= ".$x2."</h3>";
	}
	else{
		$raiz = sqrt($resta);
		$x1=(-$b+$raiz)/(2*$a);
		$x2=(-$b-$raiz)/(2*$a);
		echo "<h3>x1= ".$x1."<br></h3>";
		echo "<h3>x2= ".$x2."</h3>";
	}
?>