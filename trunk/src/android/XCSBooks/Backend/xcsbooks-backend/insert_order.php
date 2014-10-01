<?php
/*
	Descrição: Inserta a compra no BD (pedidos e produtopedido)
	INPUTs: [POST] username, pedido ([codigolivro]x[quantidade]/[codigolivro]x[quantidade])	// Username do cliente + pedido dele (sem os colchetes))
	OUTPUTs:
		- 0 	// Inserção bem sucedida
		- -1 	// Falha na inserção do pedido
		- -2	// Nenhum parâmetro
		- -3 	// Falha na inserção dos produtos
		- -4	// Falha no envio de e-mail
		- -5	// Quantidade acima do estoque
		- -6	// Se não está logado
*/
?>
<?php
  session_start();

  if (!isset($_SESSION['email']))
    die('{ "resposta" : -6 }');

	include("connection.php");

	if(isset($_POST['username']) && isset($_POST['pedido']))
	{
		$username = $_POST['username'];
		$order = explode("/",$_POST['pedido']);

		$order_query = "INSERT INTO pedido VALUES ('', now(), '$username', '".utf8_decode("Aguardando confirmação")."')";
		if (!mysqli_query($con,$order_query))
  			die('{ "resposta" : -1 }');

  		for($i=0;$i<count($order);$i++)
  		{
  			$product = explode("x",$order[$i]);

  			// Verificando se o número do estoque é menor que o do pedido
  			$productData = mysqli_fetch_row(mysqli_query($con,"SELECT quantidade, preco FROM produto WHERE codigo = $product[0]"));
  			if($productData[0] < $product[1])
  				die('{ "resposta" : -5 }');

  			// Inserindo produto
  			$product_query = "INSERT INTO produtopedido VALUES (LAST_INSERT_ID(), '$product[0]', '$product[1]', '$productData[1]')";
  			if (!mysqli_query($con,$product_query))
  				die('{ "resposta" : -3 }');

  			// Atualizando estoque
  			$qtdAtual = $productData[0]-$product[1];
  			$product_query = "UPDATE produto SET quantidade = '$qtdAtual' WHERE codigo = $product[0]";
  			if (!mysqli_query($con,$product_query))
  				die('{ "resposta" : -3 }');

  			$del_query = mysqli_query($con,"SELECT u.codigo FROM livrousado u INNER JOIN produto p ON u.codigo = p.codigo");
  			while($del_livro = mysqli_fetch_row($del_query))
  			{
  				
  			}
  		}

  		// Criando pedido do cliente
  		$temp_query = mysqli_query($con,"SELECT id FROM pedido WHERE cliente = '$username' ORDER BY id DESC LIMIT 1");
  		if(!$temp_query)
  			die('{ "resposta" : -1 }'.mysqli_error());
  		$orderId = mysqli_fetch_row($temp_query);
  		$search_query = "SELECT c.nome, c.username, c.email, ped.datahora, ped.estado, ped.id FROM pedido ped INNER JOIN cliente c ON ped.cliente = c.username WHERE ped.id = $orderId[0]";
		$result_query = mysqli_query($con,$search_query);

		// Escrevendo pedido
		$result = mysqli_fetch_row($result_query);
		$order = utf8_encode($result[0])."/".utf8_encode($result[1])."/".utf8_encode($result[2])."/".$result[3]."/".utf8_encode($result[4])."/";

		// Livros solicitados
		// Livros novos
		$search_query2 = "SELECT n.isbn, l.titulo, prodped.quantidade, prodped.preco FROM produtopedido prodped INNER JOIN livronovo n ON prodped.codigo = n.codigo INNER JOIN livro l ON n.isbn = l.isbn WHERE prodped.pedido = $orderId[0]";
		$result_query2 = mysqli_query($con,$search_query2);
		if($result_query2)
		{
			while($result2 = mysqli_fetch_row($result_query2))
			{
				$qtdxpreco = $result2[2]*$result2[3];
				$order .= $result2[0]."/".utf8_encode($result2[1])."/".$result2[2]."/".$result2[3]."/".$qtdxpreco."/Não/";
				$total_price += $qtdxpreco;
			}
		}

		// Livros velhos
		$search_query2 = "SELECT u.isbn, l.titulo, prodped.quantidade, prodped.preco FROM produtopedido prodped INNER JOIN livrousado u ON prodped.codigo = u.codigo INNER JOIN livro l ON u.isbn = l.isbn WHERE prodped.pedido = $orderId[0]";
		$result_query2 = mysqli_query($con,$search_query2);
		if($result_query2)
		{
			while($result2 = mysqli_fetch_row($result_query2))
			{
				$qtdxpreco = $result2[2]*$result2[3];
				$order .= $result2[0]."/".utf8_encode($result2[1])."/".$result2[2]."/".$result2[3]."/".$qtdxpreco."/Sim/";
				$total_price += $qtdxpreco;
			}
		}

		$order .= $total_price;
		$order = urlencode($order);

 		$mail_to = $_SESSION['email'];
  		header("Location: http://nihongogakkou.site90.net/send_mail.php?address=$mail_to&orderid=$orderId[0]&order=$order");

  		echo '{ "resposta" : 0 }';	// Deu certo (inútil porque a resposta retorna na página de e-mail)
	}
	else
	{
		echo '{ "resposta" : -2 }';	// Nenhum parâmetro
	}
?>