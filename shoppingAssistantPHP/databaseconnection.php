<?php
//connecting to the database
$mysqli = new mysqli("localhost","root","","shopping_assistant");


//if the php is not connected to the database, there will be an error message
if ($mysqli -> connect_errno) {
  echo "Error, cannot connect to the database";
  exit();
}
?>