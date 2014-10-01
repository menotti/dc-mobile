<?php
/*
	Descrição: Pega uma imagem no servidor e envia como 
	INPUTs: [GET] isb
	OUTPUTs:
		- [echo BYTES] imagem (caso encontre a imagem)
		- -1 	// Arquivo não existe
		- -2 	// Parâmetro vazio ou sem parâmetro
*/
?>
<?php
	if(!empty($_GET['isbn']))
	{
		$isbn = $_GET['isbn'];

		if(file_exists("images/".$isbn.".jpg"))
		{
			$filename = "images/".$isbn.".jpg";
			$file = fopen($filename, "rb");
			$contents = fread($file, filesize($filename));
			fclose($file);

			echo '{ "resposta" : 0, "image" :"'.base64_encode($contents).'" }';
		}
		else
			echo '{ "resposta" : -1 }';	// Arquivo não existe
	}
	else
		echo '{ "resposta" : -2 }';	// Parâmetro incorreto ou inexistente
?>