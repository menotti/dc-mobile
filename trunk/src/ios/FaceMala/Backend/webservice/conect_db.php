<?php
   $link=mysql_connect("localhost", "root", "92553211Mauro")
       or die ("<h1>Cannot connect on server !</h1>".mysql_error());
   $bd=mysql_select_db("facemala", $link)
       or die ("<h1>BD does not exist !</h1><BR><BR>".mysql_error());

?>