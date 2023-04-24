<?php

    //creates connection to the db
    include "databaseconnection.php";

    if(isset($_POST["user_id"])){

        $user_id = ($_POST["user_id"]);
        $favouriteData = json_decode($_POST["favourites"]);

        $mysqli->query("DELETE FROM favourites WHERE user_id=$user_id");


        for($i=0; $i<count($favouriteData);$i++){
            if($favouriteData[$i]->favourite == "1"){

                $meal_id=$favouriteData[$i]->mealID;
                $mysqli->query("INSERT INTO favourites(meal_id, user_id) VALUES ('$meal_id', '$user_id')");
            }

        }

        $data = new stdClass();

        $data->message="success";


        $data = json_encode($data);
        
        echo $data;
    } else {
        echo "error";
    }

?>
