<?php

include "databaseconnection.php";

if(isset($_POST["user_id"])){

    $user_id = ($_POST["user_id"]);
    $weeklyPlanner=$mysqli->query("SELECT * FROM weeklyplanner WHERE user_id=$user_id");

    $weeklyPlanner=$weeklyPlanner->fetch_all(MYSQLI_ASSOC);

    $products=array();

    for ($i=0;$i<count($weeklyPlanner);$i++){

        $meal_id=$weeklyPlanner[$i]["meal_id"];

        $productsList=$mysqli->query("SELECT p.* FROM products AS p INNER JOIN mealproducts AS mp ON p.product_id=mp.product_id AND mp.meal_id=$meal_id");

        $productsList=$productsList->fetch_all(MYSQLI_ASSOC);

        array_push($products, $productsList);

    }


    $productsCheck = array();

    for ($i=0;$i<count($products);$i++){


        for($k=0;$k<count($products[$i]);$k++){

            
            $check=false;
            
            for($a=0; $a<count($productsCheck);$a++){
                
                if($productsCheck[$a]["product_id"]==$products[$i][$k]["product_id"]){
                    
                    $check=true;
                    break;
                    
                }
            }
            
            if($check==false){
                
                array_push($productsCheck, $products[$i][$k]);
                
            }
            
        }
    }
        
    $data = new stdClass();

    $data->message="success";

    $data->products=$productsCheck;

    $data = json_encode($data);
    echo $data;


} else {
    echo "error".$_POST["user_id"];
}


?>