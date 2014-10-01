<?php
/*
	Descrição: Aprova livros usados
	INPUTs: [POST] codigo, estado
	OUTPUTs:
		- 0 	// Inserção bem sucedida
		- -1 	// Falha na inserção do pedido
		- -2	// Nenhum parâmetro
*/
?>
<?php
  session_start();

  if (!isset($_SESSION['id']))
    echo "Sem sessão";
?>
<?php
	include("connection.php");

	if(isset($_POST['codigo']) && isset($_POST['estado']))
	{
		$codigo = $_POST['codigo'];
		$status = utf8_decode($_POST['estado']);

		$usedBook_query = "UPDATE livrousado SET estado = '$status' WHERE codigo = $codigo";
  			if (!mysqli_query($con,$usedBook_query))
  				die('Falha no update');

  		header("location: admin/pedidos.php");
	}
	else
	{
		echo 'Nenhum parâmetro';	// Nenhum parâmetro
	}
?>