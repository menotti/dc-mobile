<?php
   //include("conect_db.php");
   $found=FALSE;
 
   /* $link=mysql_connect("localhost", "root", "92553211Mauro")
       or die ("<h1>Cannot connect on server !</h1>".mysql_error());
   $bd=mysql_select_db("facemala", $link)
       or die ("<h1>BD does not exist !</h1><BR><BR>".mysql_error());
   */
   include("conect_db.php");

   $fb_id = $_GET['fb_id'];
   //echo $fb_id;

   //category
   $categorySearch=mysql_query ("Select * From tbl_category")
       or die ("<center><BR><BR><BR><BR><b><h2>Error on searching for existing category</center></b></h2>");
      
   while($regCategory=mysql_fetch_assoc($categorySearch)){
   /* echo "id: " .$regCategory["id"];
    echo "<BR>";
    echo "name: " .$regCategory["name"];
    echo "<BR>"; */

    $category_id_suggestion = $regCategory["id"];

    $preferenceSearch=mysql_query ("Select * From tbl_user_preference where category_id='$category_id_suggestion' and fb_id='$fb_id' order by id desc ")
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

       $postSearch=mysql_query ("Select * From tbl_posts where category_id='$category_id_suggestion' order by id desc ")
        or die ("<center><BR><BR><BR><BR><b><h2>Error on searching for existing posts</center></b></h2>");

       $regPosts=mysql_fetch_assoc($postSearch);
       
       $post_id        = $regPosts['id'];
       $post_name      = $regPosts['name'];
       $post_caption   = $regPosts['caption'];
       $post_description = $regPosts['description'];
       $post_link      = $regPosts['link'];
       $post_picture   = $regPosts['picture'];

       $temp = $category_id_suggestion;
       $categorySearchForName=mysql_query ("Select * From tbl_category where id= '$temp' ")
        or die ("<center><BR><BR><BR><BR><b><h2>Error on searching for existing posts</center></b></h2>");
       $categoryReg=mysql_fetch_assoc($categorySearchForName);
       $post_category = $categoryReg['name'];

       $found = TRUE;
    }

   }

   if($found==FALSE){
       $post_id        =   0;
       $post_name      =   "Oh";
       $post_caption   =   "no posts";
       $post_description = "for you :(";
       $post_link      = "http://mobile.dc.ufscar.br";
       $post_picture   = "https://cdn4.iconfinder.com/data/icons/windev-contacts-2/512/cry-512.png";
   }
?>

{"post_id":<?php echo $post_id ?>,
"post_name":"<?php echo $post_name ?>",
"post_caption":"<?php echo $post_caption ?>",
"post_description":"<?php echo $post_description ?>",
"post_link":"<?php echo $post_link ?>",
"post_picture":"<?php echo $post_picture ?>",
"post_category":"<?php echo $post_category ?>",
"post_category_id":<?php echo $temp ?>}