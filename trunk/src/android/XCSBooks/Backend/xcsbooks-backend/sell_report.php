<?php
/*
	Descrição: Relatório de compras
	INPUTs: nada
	OUTPUTs:
		- [echo JSON] { pedidos : [{ nome, username, email, datahora, estado, produtos [{isbn, titulo, quantidade, preco, usado}], total }]}
		- -1 	// Nada encontrado
*/
?>
<?php
	include("connection.php");

	$found = false;
	$books = "<table cellspacing='0' cellpadding='0' border='1'>";
	$twiceMore = false;
	$twiceMore2 = false;
	$total_price = 0;

	$search_query = "SELECT c.nome, c.username, c.email, ped.datahora, ped.estado, ped.id FROM pedido ped INNER JOIN cliente c ON ped.cliente = c.username ORDER BY ped.estado ASC, ped.datahora DESC";
	$result_query = mysqli_query($con,$search_query);

	if($result_query)
	{
		while($result = mysqli_fetch_row($result_query))
		{
			if(utf8_encode($result[4]) != "Enviado")
				$books .= "<form method='GET' action='../approveOrder.php'>";
			$books .= "<tr style='background-color: #d8d7c6; border-top: 8px solid #444;'>
				<th>Nome:</th><td>".utf8_encode($result[0])."</td>
				<th>Username:</th><td>".utf8_encode($result[1])."</td>
				<th>E-mail:</th><td>".utf8_encode($result[2])."</td>
				<th>Data e hora:</th><td>$result[3]</td>
				<th>Estado:</th><td>";
				if(utf8_encode($result[4]) != "Enviado")
				{
					$books .= "<select name='estado'>
						<option value='Aguardando confirmação' ";
					if(utf8_encode($result[4]) == "Aguardando confirmação")
						$books .= "selected";
					$books .= ">Aguardando Confirmação</option>
						<option value='Aprovado' ";
					if(utf8_encode($result[4]) == "Aprovado")
						$books .= "selected";
					$books .= ">Aprovado</option>";
					$books .= "<option value='Enviado'>Enviado</option>";
				}
				else
					$books .= utf8_encode($result[4]);
				$books .= "</td>";

			if(utf8_encode($result[4]) != "Enviado")
				$books .= "<td><input type='hidden' name='codigo' value='$result[5]' /><input type='submit' value='Atualizar' /></td></tr></form>";
			$books .= "<tr><th colspan='2'>Produtos:</th><th>ISBN</th><th colspan='3'>Título</th><th>Quantidade</th><th>Preço</th><th>Preço x quantidade</th><th>Usado</th></tr>";

			// Livros novos
			$search_query2 = "SELECT n.isbn, l.titulo, prodped.quantidade, prodped.preco FROM produtopedido prodped INNER JOIN livronovo n ON prodped.codigo = n.codigo INNER JOIN livro l ON n.isbn = l.isbn WHERE prodped.pedido = $result[5]";
			$result_query2 = mysqli_query($con,$search_query2);
			if($result_query2)
			{
				while($result2 = mysqli_fetch_row($result_query2))
				{
					$qtdxpreco = $result2[2]*$result2[3];
					$books .= "<tr><td colspan='2'>&nbsp;</td>
						<td>$result2[0]</td>
						<td colspan='3'>".utf8_encode($result2[1])."</td>
						<td>$result2[2]</td>
						<td>$result2[3]</td>
						<td>R\$".$qtdxpreco."</td>
						<td>Não</td>
					</tr>";
					$total_price += $qtdxpreco;
				}
			}

			// Livros velhos
			$search_query2 = "SELECT u.isbn, l.titulo, prodped.quantidade, prodped.preco FROM produtopedido prodped INNER JOIN livrousado u ON prodped.codigo = u.codigo INNER JOIN livro l ON u.isbn = l.isbn WHERE prodped.pedido = $result[5]";
			$result_query2 = mysqli_query($con,$search_query2);
			if($result_query2)
			{
				while($result2 = mysqli_fetch_row($result_query2))
				{
					$qtdxpreco = $result2[2]*$result2[3];
					$books .= "<tr><td colspan='2'>&nbsp;</td>
						<td>$result2[0]</td>
						<td colspan='3'>".utf8_encode($result2[1])."</td>
						<td>$result2[2]</td>
						<td>$result2[3]</td>
						<td>R\$".$qtdxpreco."</td>
						<td>Sim</td>
					</tr>";
					$total_price += $qtdxpreco;
				}
			}

			$books .= "<tr><th colspan='8' style='text-align: right;'>Total: </th><td>R\$$total_price</td><td>&nbsp;</td></tr>";

			$found = true;
			$total_price = 0;
		}
	}

	$books .= "</table>";
	if($found)
		echo $books;
	else
		echo "Nada encontrado";	// Nada encontrado
?>