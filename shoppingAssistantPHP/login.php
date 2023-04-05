<?php

//checks to see if the username and password are set
if(isset($_POST['username']) && isset($_POST['password'])){

    //includes the required files - db connection for connection & validation
    include "databaseconnection.php";
    require_once "validate.php";

    //calls validate and passes the username as a parameter & stores it as a variable
    $username = validate($_POST['username']);

    //calls validate and passes the password as a parameter & stores it as a variable
    $password = validate($_POST['password']);


    $loginMode = validate($_POST['loginMode']);




    //creating the sql query:
    //md5 (Message-Digest Algorithm) used for authentication and verification
    $sql = "SELECT * FROM users WHERE username='$username'";

    //execeutes the query
    $result = $mysqli->query($sql);
    if($result->num_rows==1){
        
        $result = $result->fetch_assoc();


        if ($loginMode == "savedLogin"){

            if($result["password"] == $password){
            
            
                $data = new stdClass();
    
                $data->message="success";
                $data->user_id=$result["user_id"];
                $data->username=$result["username"];
                $data->password=$result["password"];
                $data->email=$result["email"];
                $data->firstName=$result["firstName"];
                $data->lastName=$result["lastName"];
                $data = json_encode($data);
                echo $data;
            } else {
                echo "error1";
            }



        } else if ($loginMode == "normalLogin") {

            if(password_verify($password,$result["password"])){
            
            
                $data = new stdClass();
    
                $data->message="success";
                $data->user_id=$result["user_id"];
                $data->username=$result["username"];
                $data->password=$result["password"];
                $data->email=$result["email"];
                $data->firstName=$result["firstName"];
                $data->lastName=$result["lastName"];
                $data = json_encode($data);
                echo $data;
            } else {
                echo "error1";
            }

        }

        
    }else{
        echo "error2";

    }
} else {
    echo "error3";
}
?>