<?php
/*
	Descrição: Autenticação de clientes
	INPUTs: [POST] username 	/	[POST] senha
	OUTPUTs:
		- [echo JSON] { login : { sessionID : _ID_ }}	// ID da sessão
		- -1 	// Informações erradas
		- -2 	// Parâmetros vazios
*/
?>
<?php
	include('connection.php');
	header('Content-Type: text/plain; charset=utf-8');
	
	if(isset($_POST['username']) && isset($_POST['senha']))	// Se ambas as variáveis estão setadas
	{
		$user = $_POST['username'];
		$password = $_POST['senha'];
		//$password = md5($password); // Necessário para senha em hash

		// Consulta banco de dados
		$consult = "SELECT username, nome, cpf, email, telefone1, telefone2, codigoendereco FROM cliente WHERE username = '".$user."' AND senha = '".$password."'";
		$result = mysqli_fetch_array(mysqli_query($con,$consult)); // Connection + consulta	

		// Verifica se encontrou a pessoa (success=true) ou não (success=false)
		($result[0] == $user) ? $success = true : $success = false;

		// Redirecionamento ou erro
		if ($success)
		{
			$consult2 = "SELECT logradouro, numero, complemento, bairro, cidade, uf, cep FROM endereco WHERE codigo = '".$result['codigoendereco']."'";
			$result2 = mysqli_fetch_array(mysqli_query($con,$consult2));

			// Iniciando sessão
			$for_session = md5($user.date('YndGis').rand(1,1000));
			session_id($for_session);
			if(isset($_SESSION['id']))
				session_destroy();	// Se tinha sessão aberta, destroi ela.
			session_start();
			$_SESSION['id'] = session_id($for_session);
			$_SESSION['username'] = $user;
			$_SESSION['name'] = $result['nome'];
			$_SESSION['cpf'] = $result['cpf'];
			$_SESSION['email'] = $result['email'];
			$_SESSION['telefone1'] = $result['telefone1'];
			$_SESSION['telefone2'] = $result['telefone2'];
			$_SESSION['logradouro'] = $result2['logradouro'];
			$_SESSION['numero'] = $result2['numero'];
			$_SESSION['complemento'] = $result2['complemento'];
			$_SESSION['bairro'] = $result2['bairro'];
			$_SESSION['cidade'] = $result2['cidade'];
			$_SESSION['uf'] = $result2['uf'];
			$_SESSION['cep'] = $result2['cep'];

			// Resposta em JSON
			header('Content-Type: application/json');
			echo "{ \"resposta\": 0, \"login\" : {
					\"sessionID\" : \"".$for_session."\" ,
					\"nome\" : \"".$result['nome']."\" ,
					\"cpf\" : \"".$result['cpf']."\" ,
					\"email\" : \"".$result['email']."\" ,
					\"telefone1\" : \"".$result['telefone1']."\" ,
					\"telefone2\" : \"".$result['telefone2']."\" ,
					\"endereco\" : {
						\"logradouro\" : \"".$result2['logradouro']."\" ,
						\"numero\" : \"".$result2['numero']."\" ,
						\"complemento\" : \"".$result2['complemento']."\" ,
						\"bairro\" : \"".$result2['bairro']."\" ,
						\"cidade\" : \"".$result2['cidade']."\" ,
						\"uf\" : \"".$result2['uf']."\" ,
						\"cep\" : \"".$result2['cep']."\"
					}
				}}";
		}
		else {
			die('{ "resposta" : -1 }');	// Informações erradas
		}
	}
	else
	{	
		die('{ "resposta" : -2 }');	// Parâmetros vazios
	}
?>
