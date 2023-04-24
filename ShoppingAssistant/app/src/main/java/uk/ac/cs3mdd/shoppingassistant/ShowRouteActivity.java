package uk.ac.cs3mdd.shoppingassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShowRouteActivity extends AppCompatActivity {

    ArrayList<ArrayList <ProductLocation>> shopLayout = new ArrayList<>();

    Integer[][] displayLayout = {{R.id.tile00,R.id.tile01,R.id.tile02},
                                {R.id.tile10,R.id.tile11,R.id.tile12},
                                {R.id.tile20,R.id.tile21,R.id.tile22},
                                {R.id.tile30,R.id.tile31,R.id.tile32},
                                {R.id.tile40,R.id.tile41,R.id.tile42},
                                {R.id.tile50,R.id.tile51,R.id.tile52},
                                {R.id.tile60,R.id.tile61,R.id.tile62},
                                {R.id.tile70,R.id.tile71,R.id.tile72}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_route);

        Button back = findViewById(R.id.saveButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.145/shoppingAssistantPHP/getShoppingList.php";



        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    //response from the request
                    public void onResponse(String response) {
                        Log.d("qwe", "response: " + response);

                        //taking the response which is json data - stores it as a JSONObject to easy to extract data
                        try{
                            JSONObject jsonData = new JSONObject(response);
                            String responseMessage = jsonData.getString("message");
                            if(responseMessage.equals("success")){
                                Log.d("qwe", "shoppingList: " + jsonData.toString());
                                JSONArray shoppingListData = jsonData.getJSONArray("products");

                                ArrayList<ProductLocation> unsortedProducts = new ArrayList<>();

                                for (int i = 0; i < shoppingListData.length(); i++){

                                    JSONObject product = shoppingListData.getJSONObject(i);
                                    String name = product.getString("name");

                                    int x = product.getInt("aisle");
                                    int y = product.getInt("row");

                                    Log.d("qwe", "Product Location: " + name + "," + x + "," + y);

                                    ProductLocation productLocation = new ProductLocation(x, y, name);
                                    unsortedProducts.add(productLocation);
                                }



                                ArrayList<ProductLocation> sortedProducts = new ArrayList<>();

                                ArrayList<ArrayList<Boolean>> tiles = new ArrayList<>();

                                for (int i = 0; i < displayLayout.length; i++){

                                    tiles.add(new ArrayList<>());
                                    for (int k = 0; k < displayLayout[i].length; k++) {
                                        tiles.get(i).add(false);

                                    }

                                }



                                for(int i = 0; i < unsortedProducts.size(); i++){

                                    ProductLocation productLocation = unsortedProducts.get(i);

                                    int x = productLocation.getX();

                                    int y = productLocation.getY();

                                    int tileID = displayLayout[x][y];

                                    CardView tile = findViewById(tileID);
                                    tile.setCardBackgroundColor(ContextCompat.getColor(ShowRouteActivity.this, R.color.material_dynamic_neutral40));
                                    tiles.get(x).set(y, true);

                                }

                                //first aisle check

                                if (tiles.get(0).get(0) || tiles.get(0).get(1) || tiles.get(0).get(2) ){
                                    CardView topLine = findViewById(R.id.cardView47);
                                    CardView topRight = findViewById(R.id.cardViewtopRight);
                                    topLine.setCardBackgroundColor(ContextCompat.getColor(ShowRouteActivity.this, android.R.color.holo_red_dark));
                                    topRight.setCardBackgroundColor(ContextCompat.getColor(ShowRouteActivity.this, android.R.color.holo_red_dark));
                                } else {
                                    CardView topLeft = findViewById(R.id.cardView50);
                                    topLeft.setCardBackgroundColor(ContextCompat.getColor(ShowRouteActivity.this, android.R.color.holo_red_dark));
                                }



                                //second aisle check
                                if (tiles.get(1).get(0) || tiles.get(1).get(1) || tiles.get(1).get(2) || tiles.get(2).get(0) || tiles.get(2).get(1) || tiles.get(2).get(2) ){
                                    CardView topMiddle = findViewById(R.id.cardView51);
                                    CardView middleDown1 = findViewById(R.id.cardView53);
                                    topMiddle.setCardBackgroundColor(ContextCompat.getColor(ShowRouteActivity.this, android.R.color.holo_red_dark));
                                    middleDown1.setCardBackgroundColor(ContextCompat.getColor(ShowRouteActivity.this, android.R.color.holo_red_dark));
                                } else {
                                    CardView middleRight = findViewById(R.id.middleRight);
                                    middleRight.setCardBackgroundColor(ContextCompat.getColor(ShowRouteActivity.this, android.R.color.holo_red_dark));
                                }


                                //third aisle check
                                if (tiles.get(3).get(0) || tiles.get(3).get(1) || tiles.get(3).get(2) || tiles.get(4).get(0) || tiles.get(4).get(1) || tiles.get(4).get(2) ){
                                    CardView middleMiddle = findViewById(R.id.cardView56);
                                    CardView middleRightDown = findViewById(R.id.middleRightDown);
                                    middleMiddle.setCardBackgroundColor(ContextCompat.getColor(ShowRouteActivity.this, android.R.color.holo_red_dark));
                                    middleRightDown.setCardBackgroundColor(ContextCompat.getColor(ShowRouteActivity.this, android.R.color.holo_red_dark));
                                } else {
                                    CardView middleLeftDown = findViewById(R.id.cardView55);
                                    middleLeftDown.setCardBackgroundColor(ContextCompat.getColor(ShowRouteActivity.this, android.R.color.holo_red_dark));
                                }


                                //fourth aisle check
                                if (tiles.get(5).get(0) || tiles.get(5).get(1) || tiles.get(5).get(2) || tiles.get(6).get(0) || tiles.get(6).get(1) || tiles.get(6).get(2) ){
                                    CardView bottomMiddle = findViewById(R.id.cardView57);
                                    CardView bottomLeft = findViewById(R.id.bottomLeft);
                                    bottomMiddle.setCardBackgroundColor(ContextCompat.getColor(ShowRouteActivity.this, android.R.color.holo_red_dark));
                                    bottomLeft.setCardBackgroundColor(ContextCompat.getColor(ShowRouteActivity.this, android.R.color.holo_red_dark));
                                } else {
                                    CardView bottomRight = findViewById(R.id.cardViewMiddleDown);
                                    bottomRight.setCardBackgroundColor(ContextCompat.getColor(ShowRouteActivity.this, android.R.color.holo_red_dark));
                                }

                                //fifth aisle check
                                if (tiles.get(7).get(0) || tiles.get(7).get(1) || tiles.get(7).get(2)){
                                    CardView bottom = findViewById(R.id.bottomCard);
                                    bottom.setCardBackgroundColor(ContextCompat.getColor(ShowRouteActivity.this, android.R.color.holo_red_dark));
                                }








                            } else if (responseMessage.equals("error")){
                                Toast.makeText(ShowRouteActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                                Log.d("qwe", "error1");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ShowRouteActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("qwe", "error2 + " + error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();


                SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
                String userID = sharedPreferences.getString("user_id", null);

                data.put("user_id", userID);

                return data;
            }
        };

        queue.add(stringRequest);


    }
}