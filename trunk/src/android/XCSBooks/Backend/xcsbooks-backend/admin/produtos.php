<?php
  session_start();

  if (!isset($_SESSION['id']))
    header("location: login.php");

  $admin_user = $_SESSION['id'];
?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="docs-assets/ico/favicon.png">

    <title>XCS Books</title>

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

    <script>
    function verify() {
      var ok = true;
      if($(".depende").attr("disabled") !== 'disabled')
      {
        $(".filled").each( function() {
          if($(this).val() == '')
            ok = false;
        });
      }
      else
      {
        if($("#isbn").val() == '' || $("#quantidade").val() == '')
            ok = false;
      }
      if(ok)
        $("#form_adicionar").submit();
      else
        alert("Você deve preencher todos os campos!");
    }
    </script>

    <!-- Bootstrap core CSS -->
    <link href="dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="offcanvas.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
    <div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">XCS Books</a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="logoff.php">Logoff</a></li>
          </ul>
        </div><!-- /.nav-collapse -->
      </div><!-- /.container -->
    </div><!-- /.navbar -->

    <div class="container">

      <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
          <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          <div class="jumbotron">
            <h1>XCSBooks</h1>
            <p>Adicionar e remover produtos</p>
          </div>
          <div class="row">
              <h2>Adicionar produtos</h2>
              <p>Para adicionar novos produtos, basta preencher o formulário abaixo:</p>
              <p>Caso queira apenas agregar/subtrair unidades de um produto existente, escolha "sim", coloque o isbn e digite a quantidade (número positivo adiciona, negativo subtrai)</p>
              <form method="POST" action="../insert_products.php" enctype="multipart/form-data" id="form_adicionar">
                <label>Já existe o produto:</label> Sim <input type="radio" name="existe" value="true" onclick="$('.depende').attr('disabled', true).val('');"> / Não <input type="radio" name="existe" value="false" checked onclick="$('.depende').attr('disabled', false);"><br />
                <input type="text" name="isbn" id="isbn" placeholder="ISBN" size="15" class="filled" />&nbsp;&nbsp;&nbsp;
                <input type="text" name="titulo" placeholder="Título" size="70" class="depende filled" /><br /><br />
                <input type="text" name="autor" placeholder="Autor" size="31" class="depende filled" />&nbsp;&nbsp;&nbsp;
                <input type="text" name="editora" placeholder="Editora" size="31" class="depende filled" />&nbsp;&nbsp;&nbsp;
                <input type="text" name="preco" placeholder="Preco" size="3" class="depende filled" />&nbsp;&nbsp;&nbsp;
                <input type="text" name="quantidade" id="quantidade" placeholder="Qtd" size="2" class="filled" /><br /><br />
                <label for="foto">
                  Capa: <input type="file" name="foto" id="foto" accept="image/*" class="depende">
                </label>&nbsp;&nbsp;&nbsp;
                <input type="button" onclick="verify();" value="Enviar">
              </form>
              <hr>
              <h2>Produtos cadastrados</h2>
              <p>Abaixo segue a lista de produtos existentes no estoque.</p>
              <?php include("../list_products.php"); ?>
           </div><!--/row-->
        </div><!--/span-->

        <div class="col-xs-7 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
          <div class="list-group">
            <a href="index.php" class="list-group-item">Home</a>
            <a href="produtos.php" class="list-group-item active">Adicionar/Remover Produtos</a>
            <a href="relatorio.php" class="list-group-item">Ver relatório de compras</a>
            <a href="pedidos.php" class="list-group-item">Verificar pedidos de venda</a>
          </div>
        </div><!--/span-->
      </div><!--/row-->

      <hr>

      <footer>
        <p>&copy; XCS 2013</p>
      </footer>

    </div><!--/.container-->



    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="dist/js/bootstrap.min.js"></script>
    <script src="offcanvas.js"></script>
  </body>
</html>
