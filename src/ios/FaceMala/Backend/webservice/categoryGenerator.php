<?php
   //include("conect_db.php");
   $found=FALSE;
 
   /* $link=mysql_connect("localhost", "root", "")
       or die ("<h1>Cannot connect on server !</h1>".mysql_error());
   $bd=mysql_select_db("facemala", $link)
       or die ("<h1>BD does not exist !</h1><BR><BR>".mysql_error());
   */
   include("conect_db.php");

   $fb_id = $_GET['fb_id'];
   //echo $fb_id;
   //echo "<BR><BR>";

   //category
   $categorySearch=mysql_query ("Select * From tbl_category")
       or die ("<center><BR><BR><BR><BR><b><h2>Error on searching for existing category</center></b></h2>");

$contador=0;

echo "[";

   while($regCategory=mysql_fetch_assoc($categorySearch)){
   /* echo "id: " .$regCategory["id"];
    echo "<BR>";
    echo "name: " .$regCategory["name"];
    echo "<BR>"; */

    $category_id_preference = $regCategory["id"];

    $preferenceSearch=mysql_query ("Select * From tbl_user_preference where category_id='$category_id_preference' and fb_id='$fb_id' order by id desc ")
       or die ("<center><BR><BR><BR><BR><b><h2>Error on searching for existing category</center></b></h2>");
    $rows = mysql_num_rows($preferenceSearch);
   /*
    echo "<br> category :";
    echo $category_id_suggestion;
    echo "<br> rows: ";
    echo $rows;
  */
    if($rows==0){
      //suggestion OK SUCESS
      //echo "AEW HUE SUCESS BRBRBRBR !!! <br>";
      
              if($contador!=0){
               echo ",";
              }
              echo "{\"post_id\":";
              echo "\"";
              echo $regCategory["id"];
              echo "\"";

              echo ",";

              echo "\"post_name\":";
              echo "\"";
              echo $regCategory["name"];
              echo "\"}";
              $contador++;
    }
   }

echo "]";

    mysql_free_result(categorySearch);
    mysql_free_result(preferenceSearch);
    mysql_free_result(postSearch);
    mysql_free_result(categorySearchForName);

    mysql_close ($link);
?>

