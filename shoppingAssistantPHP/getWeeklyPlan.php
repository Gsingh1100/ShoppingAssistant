<?php

include "databaseconnection.php";

if(isset($_POST["user_id"])){

    $user_id = ($_POST["user_id"]);
    $results = $mysqli->query("SELECT * FROM weeklyplanner WHERE user_id = $user_id");

    // $results = $results->fetch_all(MYSQLI_ASSOC);

    $weeklyPlanner = array();

    for($i = 0; $i < $results->num_rows; $i++){

        $meal = $results->fetch_object();

        $meal_id = $meal->meal_id;

        $results2 = $mysqli->query("SELECT * FROM meals WHERE meal_id = $meal_id");
        $results2 = $results2->fetch_object();

        $results2->date=$meal->date;

        array_push($weeklyPlanner, $results2);
    }

    $data = new stdClass();

    $data->message="success";

    $data->weeklyplanner=$weeklyPlanner;

    $data = json_encode($data);
    echo $data;


} else {
    echo "error".$_POST["user_id"];
}


?>