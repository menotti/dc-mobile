<?php
/*
	Descrição: Registro de novo cliente no BD
	INPUTs: [POST] username, senha, nome, cpf, email, tel1, tel2, logradouro, numero, complemento, bairro, cidade, estado, cep
	OUTPUTs:
		- [mysqli_error]	// Fracasso
		- 1					// Sucesso
*/
?>
<?php
	// Conexão com o BD
	include('connection.php');
	
	if(isset($_POST['username']) && isset($_POST['senha']) && isset($_POST['nome']) && isset($_POST['cpf']) && isset($_POST['email']) && isset($_POST['tel1']) && isset($_POST['tel2'])){
	
		if(isset($_POST['logradouro']) && isset($_POST['numero']) && isset($_POST['complemento']) && isset($_POST['bairro']) && isset($_POST['cidade']) && isset($_POST['estado']) && isset($_POST['cep'])){
				
			if($_POST['username'] == "")
				die('{ "resposta" : -20 }');
			if($_POST['senha'] == "")
				die('{ "resposta" : -21 }');
			if($_POST['nome'] == "" || $_POST['cpf'] == "")
				die('{ "resposta" : -22 }');
			if($_POST['email'] == "")
				die('{ "resposta" : -23 }');
				
			// Dados pessoais
			$username = $_POST['username'];
			$password = $_POST['senha'];
			$name = $_POST['nome'];
			$cpf = $_POST['cpf'];
			$email = $_POST['email'];
			$tel1 = $_POST['tel1'];
			$tel2 = $_POST['tel2'];

			if($_POST['logradouro'] == "" || $_POST['numero'] == "" || $_POST['cidade'] == "" || $_POST['estado'] == "" || $_POST['cep'] == "")
				die('{ "resposta" : -30 }');
			
			// Endereço
			$street = $_POST['logradouro'];
			$number = $_POST['numero'];
			$complement = $_POST['complemento'];
			$district = $_POST['bairro'];
			$city = $_POST['cidade'];
			$state = $_POST['estado'];
			$cep = $_POST['cep'];
			
			mysqli_query($con,"INSERT INTO endereco VALUES ('', '$street', '$number', '$complement', '$district', '$city', '$state', '$cep')") or die ('{ "resposta" : -1 }');
			$endereco_id = mysqli_insert_id($con);
			mysqli_query($con,"INSERT INTO cliente VALUES ('$username', '$password', '$name', '$cpf', '$email', '$tel1', '$tel2', '$endereco_id')") or die ('{ "resposta" : -3 }');
			
			die('{"resposta": 0}');
		}
		die('{ "resposta" : -10 }');
	}
	echo '{ "resposta" : -11 }';
?>