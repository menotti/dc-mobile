<?php
/*
	Descrição: Retorna informações de um livro
	INPUTs: [POST] isbn
	OUTPUTs:
		- Informações do livro
*/
?>
<?php
	// Conexão com o BD
	include('connection.php');

	if(isset($_GET['isbn']) && $_GET['isbn'] != "")
	{
		$isbn = $_GET['isbn'];

		$search_query = "SELECT * FROM livro WHERE isbn LIKE '$isbn'";
		$result_query = mysqli_query($con,$search_query);
		if(!$result_query)
			die('{ "resposta" : -1 }');
		$result = mysqli_fetch_row($result_query);
		echo "{ 'isbn' : '$result[0]' , 'titulo' : '".utf8_encode($result[1])."' , 'autor' : '".utf8_encode($result[2])."' , 'editora' : '".utf8_encode($result[3])."'}";
	}
	else
	{
		echo "{ 'resposta' : -2 }";
	}
?>