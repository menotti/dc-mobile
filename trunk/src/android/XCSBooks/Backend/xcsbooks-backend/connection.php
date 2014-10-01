<?php
/*
	Descrição: Conexão com o banco de dados
*/
?>
<?php
	// Iniciando conexão com o servidor
	$con = mysqli_connect(); //Adicione seus próprios parâmetros
	// Usar servidor, usuario e senha local
	
	// Verificando conexão
	if (mysqli_connect_errno($con))
  		echo "Failed to connect to MySQL: " . mysqli_connect_error();
?>
