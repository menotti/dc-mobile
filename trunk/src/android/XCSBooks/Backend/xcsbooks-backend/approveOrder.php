<?php
/*
	Descrição: Aprova pedidos e muda status
	INPUTs: [GET] codigo, estado
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

	if(isset($_GET['codigo']) && isset($_GET['estado']))
	{
		$codigo = $_GET['codigo'];
		$status = utf8_decode($_GET['estado']);

		$order_query = "UPDATE pedido SET estado = '$status' WHERE id = $codigo";
  			if (!mysqli_query($con,$order_query))
  				die('Falha no update');

  		header("location: admin/relatorio.php");
	}
	else
	{
		echo 'Nenhum parâmetro';	// Nenhum parâmetro
	}
?>