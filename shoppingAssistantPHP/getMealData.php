<?php

    //creates connection to the db
    include "databaseconnection.php";

    //sql query to get all meals from the db 
    $sql1 = "SELECT * FROM meals WHERE mealCategory='Breakfast'";

    $sql2 = "SELECT * FROM meals WHERE mealCategory='Lunch'";

    $sql3 = "SELECT * FROM meals WHERE mealCategory='Dinner'";


    //executes query to get all pieces of data
    $sql1_results = $mysqli->query($sql1);
    if($sql1_results-> num_rows>0){
        $breakfastMeals = $sql1_results->fetch_all(MYSQLI_ASSOC);
        //print_r($breakfastMeals);
    }

    $sql2_results = $mysqli->query($sql2);
    if($sql2_results-> num_rows>0){
        $lunchMeals = $sql2_results->fetch_all(MYSQLI_ASSOC);
        //print_r($lunchMeals);
    }

    $sql3_results = $mysqli->query($sql3);
    if($sql3_results-> num_rows>0){
        $dinnerMeals = $sql3_results->fetch_all(MYSQLI_ASSOC);
        //print_r($dinnerMeals);
    }


    $data = new stdClass();
    $Breakfast=array();
    $Lunch=array();
    $Dinner=array();
    


    //loops through each category and returns the name, image and meal id
    foreach($breakfastMeals as $meal){
        $tempMeal=new stdClass();
        $tempMeal->name=$meal["name"];
        $tempMeal->mealImage=$meal["mealImage"];
        $tempMeal->meal_id=$meal["meal_id"];
        $tempMeal->mealCategory=$meal["mealCategory"];

        array_push($Breakfast, $tempMeal);
    }

    foreach($lunchMeals as $meal){
        $tempMeal=new stdClass();
        $tempMeal->name=$meal["name"];
        $tempMeal->mealImage=$meal["mealImage"];
        $tempMeal->meal_id=$meal["meal_id"];
        $tempMeal->mealCategory=$meal["mealCategory"];


        array_push($Lunch, $tempMeal);
    }

    foreach($dinnerMeals as $meal){
        $tempMeal=new stdClass();
        $tempMeal->name=$meal["name"];
        $tempMeal->mealImage=$meal["mealImage"];
        $tempMeal->meal_id=$meal["meal_id"];
        $tempMeal->mealCategory=$meal["mealCategory"];


        array_push($Dinner, $tempMeal);
    }

    $data->message="success";

    $data->Breakfast=$Breakfast;
    $data->Lunch=$Lunch;
    $data->Dinner=$Dinner;

    $data = json_encode($data);
    
    echo $data;


?>
