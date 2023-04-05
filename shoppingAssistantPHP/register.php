<?php
//check to see if the data has been set
if(isset($_POST['username']) && isset($_POST['email']) && isset($_POST['password']) && isset($_POST['firstName']) && isset($_POST['lastName'])){

    //sets up a connection to the database and is a required file
    include "databaseconnection.php";

    //takes function from the validate file to validate data inputs
    require_once "validate.php";

    //takes each piece of data, validates it and stores it as a variable
    $username = validate($_POST['username']);
    $email = validate($_POST['email']);
    $password = validate($_POST['password']);
    $firstName = validate($_POST['firstName']);
    $lastName = validate($_POST['lastName']);


    //encrypting the password
    $password = password_hash($password, PASSWORD_DEFAULT);

    //sql query
    //when registering, all data fields are required and will update the users table
    $sql = "INSERT INTO users (username, email, password, firstName, lastName) VALUES('$username', '$email', '$password', '$firstName', '$lastName')";




    //output success if succesful, otherwise return "error" - on data entry
    //executes and sends the data into the database 
    if(!$mysqli->query($sql)){
        echo "error";

    }else{
        echo "success";
    }
} else {
    echo "error";
}
?>


