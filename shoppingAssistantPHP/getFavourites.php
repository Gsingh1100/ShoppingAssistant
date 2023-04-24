<?php

    //creates connection to the db
    include "databaseconnection.php";

    if(isset($_POST["user_id"])){

        $user_id = ($_POST["user_id"]);

        //sql query to get all meals from the db 
        $sql1 = "SELECT * FROM meals";

        $sql2 = "SELECT * FROM favourites WHERE user_id=$user_id";



        //executes query to get all pieces of data
        $sql1_results = $mysqli->query($sql1);
        if($sql1_results-> num_rows>0){
            $meals = $sql1_results->fetch_all(MYSQLI_ASSOC);
            //print_r($breakfastMeals);
        }

        $sql2_results = $mysqli->query($sql2);
        if($sql2_results-> num_rows>0){
            $favourites = $sql2_results->fetch_all(MYSQLI_ASSOC);
            //print_r($breakfastMeals);
        }

        $data = new stdClass();
        $favouriteMeals=array();

        if($sql2_results->num_rows > 0){

            for($i=0; $i<count($meals); $i++){
            
                $meals[$i]["favourite"] = 0;
                
    
            }
    
    
            foreach($favourites as $f){
    
                for($i=0; $i<count($meals); $i++ ){
    
                    if($meals[$i]["meal_id"] == $f["meal_id"]){
                        $meals[$i]["favourite"] = 1;
                        break;
    
                    } 
                }
    
            }
    
            //loops through each category and returns the name, image and meal id
            foreach($meals as $meal){
                $tempMeal=new stdClass();
                $tempMeal->name=$meal["name"];
                $tempMeal->mealImage=$meal["mealImage"];
                $tempMeal->meal_id=$meal["meal_id"];
                $tempMeal->favourite=$meal["favourite"];
    
    
    
                array_push($favouriteMeals, $tempMeal);
            }

        } else {
            for($i=0; $i<count($meals); $i++){
            
                $meals[$i]["favourite"] = 0;
                
    
            }
    
            //loops through each category and returns the name, image and meal id
            foreach($meals as $meal){
                $tempMeal=new stdClass();
                $tempMeal->name=$meal["name"];
                $tempMeal->mealImage=$meal["mealImage"];
                $tempMeal->meal_id=$meal["meal_id"];
                $tempMeal->favourite=$meal["favourite"];
    
    
    
                array_push($favouriteMeals, $tempMeal);
            }

        }


        


        $data->message="success";

        $data->favourites=$favouriteMeals;

        $data = json_encode($data);
        
        echo $data;
    } else {
        echo "error";
    }

?>
