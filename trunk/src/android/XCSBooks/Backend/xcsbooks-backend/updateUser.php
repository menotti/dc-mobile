<?php
/*
	Descrição: Atualiza dados pessoais
	INPUTs: [POST] tudo menos senha
	OUTPUTs:
		- ERROS
*/
?>
<?php
  session_start();

  if (!isset($_SESSION['id']))
    echo '{ "resposta" : -4 }';
?>
<?php
	// Conexão com o BD
	include('connection.php');
	
	// Dados pessoais
	$username = $_SESSION['username'];
	$name = $_POST['nome'];
	$email = $_POST['email'];
	$tel1 = $_POST['tel1'];
	$tel2 = $_POST['tel2'];

	// Endereço
	$street = $_POST['logradouro'];
	$number = $_POST['numero'];
	$complement = $_POST['complemento'];
	$district = $_POST['bairro'];
	$city = $_POST['cidade'];
	$state = $_POST['estado'];
	$cep = $_POST['cep'];

	// Atualizando cliente
	$update_query = "UPDATE cliente SET
		nome = '$name',
		email = '$email',
		telefone1 = '$tel1',
		telefone2 = '$tel2' WHERE username LIKE '$username'";
  	if (!mysqli_query($con,$update_query))
  		die('{ "resposta" : -1 }');
  	
  	// Pegando o código do endereço
  	$temp_query = mysqli_query($con,"SELECT codigoendereco FROM cliente WHERE username = '$username'");
  	if(!$temp_query)
  		die('{ "resposta" : -2 }');
  	$enderecoId = mysqli_fetch_row($temp_query);

  	// Atualizando endereço
  	$update_query = "UPDATE endereco SET
		logradouro = '$street',
		numero = '$number',
		complemento = '$complement',
		bairro = '$district',
		cidade = '$city',
		uf = '$state',
		cep = '$cep' WHERE codigo = $enderecoId[0]";
  	if (!mysqli_query($con,$update_query))
  		die('{ "resposta" : -3 }');

  	echo '{ "resposta" : 0 }';
?>