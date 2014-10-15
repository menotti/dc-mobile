<?php
   include("conect_db.php");

   $fb_id       = $_GET['fb_id'];
   $category_id = $_GET['category_id'];
   $status      = $_GET['status'];

   $suggestionSaver=mysql_query("INSERT INTO tbl_user_preference (category_id, fb_id, status, gender, age) 
        VALUES ('$category_id', '$fb_id', '$status', 1, 21)") or die(mysql_error());
?>
{"status":"1"}