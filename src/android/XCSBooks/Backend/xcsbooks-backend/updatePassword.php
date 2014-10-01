<?php
/*
	Descrição: Atualiza senha
	INPUTs: [POST] user, senha
	OUTPUTs:
		- ERROS
*/
?>
<?php
  session_start();

  if (!isset($_SESSION['id']))
    echo '{ "resposta" : -2 }';
?>
<?php
	// Conexão com o BD
	include('connection.php');
	if(!isset($_POST['prevsenha']) || $_POST['prevsenha'] == "" || !isset($_POST['senha']) || $_POST['senha'] == ""){		die('{ "resposta" : -3 }');	}
	// Dados pessoais
	$username = $_SESSION['username'];		$passwordAnterior = $_POST['prevsenha'];
	$password = $_POST['senha'];	

	// Atualizando cliente
	$update_query = "UPDATE cliente SET senha = '$password' WHERE username = '$username' AND senha = '$passwordAnterior'";
  		if (!mysqli_query($con,$update_query))
  		die('{ "resposta" : -1 }');			if(mysqli_affected_rows($con) < 1){		die('{ "resposta" : -2 }');	}
  	echo '{ "resposta" : 0 }';
?>