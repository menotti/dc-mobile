<?php
/*
	Descrição: Lista produtos
	INPUTs: nada
	OUTPUTs:
		- Lista de produtos cadastrados
*/
?>
<?php
	include("connection.php");

	$found = false;
	$books = "<table cellspacing='0' cellpadding='0' border='1'>";
	$books .= "<tr style='background-color: #d8d7c6; border-top: 8px solid #444;'><th colspan='7'>Livros Novos</th></tr>";
	$books .= "<tr style='background-color: #d8d7c6;'><th>Código</th><th>ISBN</th><th>Título</th><th>Autor</th><th>Editora</th><th>Quantidade</th><th>Preço</th></tr>";

	$search_query = "SELECT l.isbn, l.titulo, l.autor, l.editora, p.quantidade, p.preco, p.codigo FROM livro l INNER JOIN livronovo n ON l.isbn = n.isbn INNER JOIN produto p ON n.codigo = p.codigo ORDER BY p.codigo DESC";
	$result_query = mysqli_query($con,$search_query);

	if($result_query)
	{
		while($result = mysqli_fetch_row($result_query))
		{
			$books .= "<tr>
				<td>$result[6]</td>
				<td>$result[0]</td>
				<td>".utf8_encode($result[1])."</td>
				<td>".utf8_encode($result[2])."</td>
				<td>".utf8_encode($result[3])."</td>
				<td>$result[4]</td>
				<td>R\$$result[5]</td>
				</tr>";
			$found = true;
		}
	}

	$books .= "<tr style='background-color: #d8d7c6; border-top: 8px solid #444;'><th colspan='7'>Livros Usados</th></tr>";
	$books .= "<tr style='background-color: #d8d7c6;'><th>Código</th><th>ISBN</th><th>Título</th><th>Autor</th><th>Editora</th><th>Quantidade</th><th>Preço</th></tr>";

	$search_query = "SELECT l.isbn, l.titulo, l.autor, l.editora, p.quantidade, p.preco, p.codigo FROM livro l INNER JOIN livrousado u ON l.isbn = u.isbn INNER JOIN produto p ON u.codigo = p.codigo ORDER BY p.codigo DESC";
	$result_query = mysqli_query($con,$search_query);

	if($result_query)
	{
		while($result = mysqli_fetch_row($result_query))
		{
			$books .= "<tr>
				<td>$result[6]</td>
				<td>$result[0]</td>
				<td>".utf8_encode($result[1])."</td>
				<td>".utf8_encode($result[2])."</td>
				<td>".utf8_encode($result[3])."</td>
				<td>$result[4]</td>
				<td>R\$$result[5]</td>
				</tr>";
			$found = true;
		}
	}
	
		$books .= "</table>";
		if($found)
			echo $books;
		else
			echo "Nenhum produto cadastrado!";	// Nada encontrado
?>