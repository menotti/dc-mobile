<?php
/*
	Descrição: Inserta novos produtos
	INPUTs: [POST] existe, 
	OUTPUTs:
		- 1 	// Inserção bem sucedida
		- 0 	// Falha na inserção do pedido
		- -1 	// Falha na inserção dos produtos
		- -2	// Nenhum parâmetro
		- -30   // Capa não é JPEG
		- -31   // Problema com permissão da pasta	
		- -32 	// Não conseguiu mover o arquivo pra pasta
*/

/* Defines */
define ('UPLOAD_DIR', 'images/');
?>
<?php
	include("connection.php");

	if(isset($_POST['existe']))
	{
		if($_POST['existe'] == 'true')
		{
			$isbn = $_POST['isbn'];
			$productData1 = mysqli_query($con,"SELECT p.quantidade, n.codigo FROM produto p INNER JOIN livronovo n ON p.codigo = n.codigo WHERE n.isbn = $isbn");
			$productData2 = mysqli_query($con,"SELECT p.quantidade, u.codigo FROM produto p INNER JOIN livrousado u ON p.codigo = u.codigo WHERE u.isbn = $isbn");	// Não tem sentido esse pois só vai aumentar os livros novos e cada usado será tratado como único
			if($productData1)
				$thisBook = mysqli_fetch_row($productData1);
			else
				$thisBook = mysqli_fetch_row($productData2);

			$qtdAtual = $thisBook[0] + $_POST['quantidade'];

			$update_product = "UPDATE produto SET quantidade = $qtdAtual WHERE codigo = $thisBook[1]";
			if (!mysqli_query($con,$update_product))
  				die('0');
				
  			header("location: admin/produtos.php#add_product");
		}
		else
		{
			$isbn = $_POST['isbn'];
			$titulo = utf8_decode($_POST['titulo']);
			$autor = utf8_decode($_POST['autor']);
			$editora = utf8_decode($_POST['editora']);
			$preco = $_POST['preco'];
			$quantidade = $_POST['quantidade'];

			$query = "INSERT INTO produto VALUES ('', '$quantidade', '$preco')";
  			if (!mysqli_query($con,$query))
  				die('Erro na inserção de produto'.mysql_error($con));

  			$query = "INSERT INTO livro VALUES ('$isbn', '$titulo', '$autor', '$editora')";
  			if (!mysqli_query($con,$query))
  				die('Erro na inserção de livro'.mysql_error($con));

  			$query = "INSERT INTO livronovo VALUES (LAST_INSERT_ID(), '$isbn')";	// Tem que ser por último porque possui uma foreign key para o livro.
  			if (!mysqli_query($con,$query))
  				die('Erro na inserção de livro novo'.mysql_error($con));

			// Uploads the cover
			$filename = $isbn.".jpg";
			$imageinfo = getimagesize($_FILES['foto']['tmp_name']);
			if($imageinfo[2] != IMAGETYPE_JPEG)
				die('-30');
			if(!is_writable(UPLOAD_DIR))
				die('-31');
			if(!move_uploaded_file($_FILES['foto']['tmp_name'], UPLOAD_DIR.$filename))
				die('-32');
				
  			header("location: admin/produtos.php");
		}
	}
	/*	$username = $_POST['username'];
		$order = explode("/",$_POST['pedido']);

		$order_query = "INSERT INTO pedido VALUES ('', now(), '$username', '".utf8_decode("Aguardando confirmação")."')";
		if (!mysqli_query($con,$order_query))
  			die('0');

  		for($i=0;$i<count($order);$i++)
  		{
  			$product = explode("x",$order[$i]);

  			$productData = mysqli_fetch_row(mysqli_query($con,"SELECT quantidade, preco FROM produto WHERE codigo = $product[0]"));
  			
  			$product_query = "INSERT INTO produtopedido VALUES (LAST_INSERT_ID(), '$product[0]', '$product[1]', '$productData[1]')";
  			if (!mysqli_query($con,$product_query))
  				die('-1');

  			$qtdAtual = $productData[0]-$product[1];

  			$product_query = "UPDATE produto SET quantidade = '$qtdAtual' WHERE codigo = $product[0]";
  			if (!mysqli_query($con,$product_query))
  				die('-1');
  		}
  		echo 1;	// Deu certo
	}*/
	else
	{
		echo -2;	// Nenhum parâmetro
	}
?>