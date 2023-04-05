<?php

include "databaseconnection.php";


$user_id = $_POST["user_id"];
$weeklyPlanData = json_decode($_POST["weeklyPlan"]);

foreach ($weeklyPlanData as $plan){

    $meal_id = $plan->mealID;
    $mealType = $plan->mealType;
    $date = $plan->date;

    $results = $mysqli->query("SELECT * FROM weeklyplanner WHERE user_id = '$user_id' AND date = '$date' AND mealType = '$mealType'");

    if ($results->num_rows > 0){
        $mysqli->query("UPDATE weeklyplanner SET meal_id = $meal_id WHERE user_id = '$user_id' AND date = '$date' AND mealType = '$mealType'");
    } else {
        $mysqli->query("INSERT INTO weeklyplanner (user_id, meal_id, date, mealType) VALUES ('$user_id', '$meal_id', '$date', '$mealType') ");
    }


}

$data = new stdClass();

$data->message="success";


$data = json_encode($data);
echo $data;

?>