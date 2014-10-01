<?php
/*
	Descrição: Autenticação de administradores
	INPUTs: [POST] username 	/	[POST] senha
	OUTPUTs:
		- [echo JSON] { login : { sessionID : _ID_ }}	// ID da sessão
		- -1 	// Informações erradas
		- -2 	// Parâmetros vazios
*/
?>
<?php
	include('connection.php');
	
	if(isset($_POST['username']) && isset($_POST['senha']) && $_POST['username'] != "" && $_POST['senha'] != "")	// Se ambas as variáveis estão setadas
	{
		$user = strtolower($_POST['username']);
		$password = $_POST['senha'];
		//$password = md5($password); // Necessário para senha em hash

		// Consulta banco de dados
		$consult = "SELECT username FROM administrador WHERE username = '".$user."' AND senha = '".$password."'";
		$result = mysqli_fetch_array(mysqli_query($con,$consult)); // Connection + consulta	

		// Verifica se encontrou a pessoa (success=true) ou não (success=false)
		($result[0] == $user) ? $success = true : $success = false;

		// Redirecionamento ou erro
		if ($success)
		{
			// Iniciando sessão
			session_id($user);
			if(isset($_SESSION['id']))
				session_destroy();	// Se tinha sessão aberta, destroi ela.
			session_start();
			$_SESSION['id'] = session_id($user);

			header("location: admin/index.php");
		}
		else {
			echo "<h3>Informa&ccedil;&atilde;es incorretas.</h3><a href='admin/login.php'>Volte e tente novamente!</a>";	// Informações erradas
		}
	}
	else
	{
		echo -2;	// Parâmetros vazios.
	}
?>