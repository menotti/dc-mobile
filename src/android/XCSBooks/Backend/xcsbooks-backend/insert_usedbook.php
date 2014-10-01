<?php
/*
	Descrição: Cadastra o livro usado para ser vendido
	INPUT:  [POST]	isbn -> isbn do livro
					titulo -> titulo do livro
					autor -> autor do livro
					editora -> editoa do livro
					preco -> preço do livro
					cliente -> username de quem está vendendo o livro

	OUTPUT: [Objeto JSON]
		{ resposta:  0 } // Cadastro realizado com sucesso
		{ resposta: -1 } // Erro na inserção
		{ resposta: -2 } // Faltando parâmetros
		{ resposta: -3 } // Busca de informações do livro falha
		{ resposta: -4 } // Não está logado
*/
session_start();

if (!isset($_SESSION['email']))
	die('{ "resposta" : -4 }');

include("connection.php");

if(isset($_POST['isbn']) && $_POST['isbn'] != "" &&
	isset($_POST['titulo']) && $_POST['titulo'] != "" &&
	isset($_POST['autor']) && $_POST['autor'] != "" &&
	isset($_POST['editora']) && $_POST['editora'] != "" &&
	isset($_POST['preco']) && $_POST['preco'] != "")/* &&
	isset($_POST['cliente']) && $_POST['cliente'] != "")*/ {
		
	$isbn = $_POST['isbn'];
	$titulo = $_POST['titulo'];
	$autor = $_POST['autor'];
	$editora = $_POST['editora'];
	$preco = $_POST['preco'];
	$cliente = $_SESSION['username'];
	
	//Insere novo produto
	$query = "INSERT INTO produto (quantidade, preco) VALUES ('1', '".$preco."')";
	$result = mysqli_query($con, $query);
	
	if(!$result){
		die('{ "resposta" : -1, "erro": "'.mysqli_error($con).'"}');
	}
	
	$codigo = mysqli_insert_id($con);
	//echo "Codigo: ".$codigo."<br>";

	//Verifica se livro já está cadastrado
	$query = "SELECT isbn FROM livro WHERE isbn = '".$isbn."' LIMIT 1";
	$result = mysqli_query($con, $query);
	
	$existe = false;
	$temp = mysqli_fetch_array($result);
	
	if($temp[0] == $isbn){
		$existe = true;	
	}
	//echo "Livro cadastrado: ".$existe."<br>";
	
	if(!$existe){	
		//Insere livro 
		$query = "INSERT INTO livro VALUES ('$isbn', '$titulo', '$autor', '$editora')";
		$result = mysqli_query($con, $query);
		if(!$result){
			die('{ "resposta" : -1, "erro" : "'.mysqli_error($con).'"}');
		}
	}

	//Insere livro usado
	$query = "INSERT INTO livrousado VALUES('$codigo', '$isbn', 'Aguardando', '10', CURDATE(), CURTIME(), '$cliente')"; 
	$result = mysqli_query($con, $query);
	if(!$result){
		die('{ "resposta" : -1, "erro" : "'.mysqli_error($con).'"}');
	}
	//echo "Livro usado inserido!<br>";

	// Busca informações do livro
	$usedBook_query = mysqli_query($con,"SELECT l.isbn, l.titulo, l.autor, l.editora, u.datacadastro, u.horacadastro FROM livro l INNER JOIN livrousado u ON l.isbn = u.isbn WHERE u.codigo = $codigo");
	if($usedBook_query)
	{
		$bookInfo = mysqli_fetch_row($usedBook_query);
		$dados = $bookInfo[0]."/".utf8_encode($bookInfo[1])."/".utf8_encode($bookInfo[2])."/".utf8_encode($bookInfo[3])."/".$bookInfo[4]."/".$bookInfo[5];
		$dados = urlencode($dados);
	}
	else
		die('{ "resposta" : -3 }');
	
	$mail_to = $_SESSION['email'];
	//echo "Enviando e-mail a".$mail_to."<br>";
  	header("Location: http://nihongogakkou.site90.net/send_mail_used_book.php?address=$mail_to&codigo=$codigo&dados=$dados");

  	echo '{ "resposta" : 0 }';	// Deu certo (inútil porque a resposta retorna na página de e-mail)
} 
die('{ "resposta" : -2 }');
?>