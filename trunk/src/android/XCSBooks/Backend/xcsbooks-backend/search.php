<?php
/*
	Descrição: Sistema de busca simples por isbn, titulo, autor ou editora
	INPUTs: [GET] s		// Palavra de pesquisa
	OUTPUTs:
		- [echo JSON] { livros : [{ isbn : _isbn_ , titulo : _titulo_ , autor : _autor_ , editora : _editora_}]}	// ID da sessão
		- -1 	// Nada encontrado
		- -2 	// Parâmetros vazios
*/
?>
<?php
	include("connection.php");

	if(isset($_GET['s']))
	{
		$keyword = explode(" ", $_GET['s']);
		$found = false;
		$books = "{ \"resposta\" : 0, \"livros\" : [";
		$twiceMore = false;

		for($i=0;$i<count($keyword);$i++)
		{
			$search_query = "SELECT l.isbn, l.titulo, l.autor, l.editora, p.quantidade, p.preco, p.codigo FROM livro l INNER JOIN livronovo n ON l.isbn = n.isbn INNER JOIN produto p ON n.codigo = p.codigo WHERE l.isbn LIKE '%$keyword[$i]%' OR l.titulo LIKE '%$keyword[$i]%' OR l.autor LIKE '%$keyword[$i]%' OR l.editora LIKE '%$keyword[$i]%'";
			$result_query = mysqli_query($con,$search_query);

			if($result_query)
			{
				while($result = mysqli_fetch_row($result_query))
				{
					if($twiceMore)		// Se passar mais de uma vez coloca vírgula
						$books .= ",";
					$books .= "{
						\"isbn\" : \"$result[0]\" ,
						\"titulo\" : \"".utf8_encode($result[1])."\" ,
						\"autor\" : \"".utf8_encode($result[2])."\" ,
						\"editora\" : \"".utf8_encode($result[3])."\" ,
						\"quantidade\" : \"$result[4]\",
						\"preco\" : \"$result[5]\",
						\"codigo\" : \"$result[6]\",
						\"usado\" : \"nao\"
					}";
					$found = true;
					$twiceMore = true;
				}
			}

			$search_query = "SELECT l.isbn, l.titulo, l.autor, l.editora, p.quantidade, p.preco, p.codigo, u.estado FROM livro l INNER JOIN livrousado u ON l.isbn = u.isbn INNER JOIN produto p ON u.codigo = p.codigo WHERE (l.isbn LIKE '%$keyword[$i]%' OR l.titulo LIKE '%$keyword[$i]%' OR l.autor LIKE '%$keyword[$i]%' OR l.editora LIKE '%$keyword[$i]%') AND u.estado NOT LIKE 'Aguardando' AND p.quantidade > 0";
			$result_query = mysqli_query($con,$search_query);

			if($result_query)
			{
				while($result = mysqli_fetch_row($result_query))
				{
					if($twiceMore)		// Se passar mais de uma vez coloca vírgula
						$books .= ",";
					$books .= "{
						\"isbn\" : \"$result[0]\" ,
						\"titulo\" : \"".utf8_encode($result[1])."\" ,
						\"autor\" : \"".utf8_encode($result[2])."\" ,
						\"editora\" : \"".utf8_encode($result[3])."\" ,
						\"quantidade\" : \"$result[4]\",
						\"preco\" : \"$result[5]\",
						\"codigo\" : \"$result[6]\",
						\"usado\" : \"$result[7]\"
					}";
					$found = true;
					$twiceMore = true;
				}
			}
		}

		$books .= "]}";
		if($found)
			echo $books;
		else
			echo '{ "resposta" : -1 }';	// Nada encontrado
	}
	else
	{
		echo '{ "resposta" : -2 }';	// Nenhum parâmetro
	}
?>
