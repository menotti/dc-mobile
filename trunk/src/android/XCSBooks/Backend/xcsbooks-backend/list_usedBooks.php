<?php
/*
	Descrição: Lista solicitações de livros usados
	INPUTs: nada
	OUTPUTs:
		- Lista de produtos cadastrados
*/
?>
<?php
	include("connection.php");

	$found = false;
	$aguardando = 0;
	$books = "<table cellspacing='0' cellpadding='0' border='1'>";

	$search_query = "SELECT l.isbn, l.titulo, l.autor, l.editora, u.estado, p.preco, p.codigo FROM livro l INNER JOIN livrousado u ON l.isbn = u.isbn INNER JOIN produto p ON u.codigo = p.codigo ORDER BY u.estado ASC, p.codigo DESC";
	$result_query = mysqli_query($con,$search_query);

	if($result_query)
	{
		while($result = mysqli_fetch_row($result_query))
		{
			$books .= "<form method='POST' action='../approveUsedBook.php'>";
			if(utf8_encode($result[4]) == "Aguardando" && $aguardando == 0)
			{
				$books .= "<tr style='background-color: #d8d7c6; border-top: 8px solid #444;'><th colspan='7'>Livros em espera de avaliação</th></tr>";
				$books .= "<tr style='background-color: #d8d7c6;'><th>Código</th><th>ISBN</th><th>Título</th><th>Autor</th><th>Editora</th><th>Preço</th><th>Estado</th></tr>";
				$aguardando = 1;
			}
			else if (utf8_encode($result[4]) != "Aguardando" && $aguardando !== 2)
			{
				$books .= "<tr style='background-color: #d8d7c6; border-top: 8px solid #444;'><th colspan='7'>Livros avaliados</th></tr>";
				$books .= "<tr style='background-color: #d8d7c6;'><th>Código</th><th>ISBN</th><th>Título</th><th>Autor</th><th>Editora</th><th>Preço</th><th>Estado</th></tr>";
				$aguardando = 2;
			}
			$books .= "<tr>
				<td>$result[6]</td>
				<td>$result[0]</td>
				<td>".utf8_encode($result[1])."</td>
				<td>".utf8_encode($result[2])."</td>
				<td>".utf8_encode($result[3])."</td>
				<td>R\$$result[5]</td>
				<td><select name='estado'>
					<option value='Aguardando' ";
			if(utf8_encode($result[4]) == "Aguardando")
				$books .= "selected";
			$books .= ">Aguardando</option>
					<option value='Quase novo' ";
			if(utf8_encode($result[4]) == "Quase novo")
				$books .= "selected";
			$books .= ">Quase novo</option>
					<option value='Bom' ";
			if(utf8_encode($result[4]) == "Bom")
				$books .= "selected";
			$books .= ">Bom</option>
					<option value='Ruim' ";
			if(utf8_encode($result[4]) == "Ruim")
				$books .= "selected";
			$books .= ">Ruim</option>
					<option value='Péssimo' ";
			if(utf8_encode($result[4]) == "Péssimo")
				$books .= "selected";
			$books .= ">Péssimo</option>
				</select></td>
				<td><input type='hidden' value='$result[6]' name='codigo' /><input type='submit' value='Atualizar' /></td>
				</tr></form>";
			$found = true;
		}
	}
	
	$books .= "</table>";
	if($found)
		echo $books;
	else
		echo "Nenhum produto cadastrado!";	// Nada encontrado
?>