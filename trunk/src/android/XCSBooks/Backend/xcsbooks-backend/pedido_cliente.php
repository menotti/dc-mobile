<?php
/*
	Descrição: Busca pedidos feitos por um cliente
	INPUT: [GET] cliente -> nome do cliente
	OUTPUT:
		[Objeto JSON] 
			{
				resposta: 0, pedidos:
				[ {	
					id: _idpedido_
					datahora: _datahora_,
					estado : _estado_,
					total : _precototal_,
					produtos : 	[ 	{
										isbn : _isbn_,
										titulo : _titulo_,
										quantidade : _quantidade_,
										preco : _preco_
									},
									... etc
								] 
				  }, 
				  ... etc
				]
			}
		[Objeto JSON]
			{ resposta : -1 } // Erro ou nada encontrado
			{ resposta : -3 } // Erro na segunda query
			{ resposta : -4 } // Erro na terceira query
			{ resposta : -2 } // Parâmetro vazio
*/

include("connection.php");

if(isset($_GET['cliente'])){
	$cliente = $_GET['cliente'];
	
	$query = "SELECT id, datahora, estado FROM pedido WHERE cliente = '".$cliente."' ORDER BY datahora DESC";
	
	$result = mysqli_query($con, $query);
	if(!$result){
		die("{ \"resposta\" : -1, \"erro\" : ".mysqli_error($con)." }");
	}
	
	$data = array();
	while($r = mysqli_fetch_array($result)){
		$pedido_id = $r['id'];
		$datahora = $r['datahora'];
		$estado = $r['estado'];
		$total = 0;
		
		//Query para selecionar codigo do livro, quantidade e preço
		$query = "SELECT codigo, quantidade, preco FROM produtopedido WHERE pedido = '".$pedido_id."'";
		$result2 = mysqli_query($con, $query);
		if(!$result2){
			die("{ \"resposta\" : -3, \"erro\" : ".mysqli_error($con)." }");
		}
		
		$data2 = array();
		while($r2 = mysqli_fetch_array($result2)){
			$codigo = intval($r2['codigo']);
			$quantidade = intval($r2['quantidade']);
			$preco = doubleval($r2['preco']);
			$precoqnt = $preco * $quantidade;
			$total += $precoqnt;
			
			//Query para selecionar o nome do livro e o isbn
			$query = "SELECT l.TITULO, l.ISBN FROM livro l ";
			$query = $query."LEFT JOIN livronovo ln ON ln.ISBN = l.ISBN ";
			$query = $query."LEFT JOIN livrousado lu ON lu.ISBN = l.ISBN ";
			$query = $query."WHERE ln.CODIGO = '".$codigo."' ";
			$query = $query."OR lu.CODIGO = '".$codigo."'";
					 
			$result3 = mysqli_query($con, $query);
			if(!$result3){
				die("{ \"resposta\" : -4, \"erro\" : ".mysqli_error($con)." }");
			}
			
			$r3 = mysqli_fetch_array($result3);
			$nome = $r3['TITULO'];
			$isbn = $r3['ISBN'];
			
			$tmp = array(
			"isbn" => $isbn,
			"titulo" => utf8_encode($nome),
			"quantidade" => $quantidade,
			"preco" => $preco );
			array_push($data2, $tmp);
		}
		
			$tmp2 = array(
				"id" => intval($pedido_id),
				"datahora" => $datahora,
				"estado" => utf8_encode($estado),
				"total" => doubleval($total),
				"produtos" => $data2
			);
			array_push($data, $tmp2);
	}
	
	//Retorna o json
	echo json_encode(array("resposta" => 0, "pedidos" => $data));
	
} else {
	echo "{ \"resposta\" : -2 }";
}




