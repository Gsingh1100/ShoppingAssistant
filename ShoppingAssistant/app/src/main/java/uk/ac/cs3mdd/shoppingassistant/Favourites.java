package uk.ac.cs3mdd.shoppingassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
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

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Favourites extends AppCompatActivity {

    ArrayList<FavouritesModel> favouritesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        //volley request to get data from the database, before the meal plan tab is loaded
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.145/shoppingAssistantPHP/getFavourites.php";


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
                                Log.d("qwe", "onResponse: " + jsonData.toString());
                                JSONArray favouritesData = jsonData.getJSONArray("favourites");

                                HashMap<String, DayMeal> breakfastWeeklyPlanner = new HashMap<>();
                                HashMap<String, DayMeal> lunchWeeklyPlanner = new HashMap<>();
                                HashMap<String, DayMeal> dinnerWeeklyPlanner = new HashMap<>();



                                for (int i = 0; i < favouritesData.length(); i++){


                                    //getting the json data (gets each meal individually)
                                    JSONObject mealData = favouritesData.getJSONObject(i);
                                    Log.d("qwe", "mealData: " + mealData);


                                    String id = mealData.optString("meal_id");
                                    String name = mealData.optString("name");
                                    String mealImage = mealData.optString("mealImage");
                                    String favourite = mealData.optString("favourite");


                                    FavouritesModel meal = new FavouritesModel(id, name, mealImage, favourite);
                                    favouritesList.add(meal);


                                }

                                RecyclerView recyclerView = findViewById(R.id.favouritesRv);

                                FavouritesAdapter favouritesAdapter = new FavouritesAdapter(Favourites.this, favouritesList);
                                recyclerView.setAdapter(favouritesAdapter);

                                recyclerView.setLayoutManager(new LinearLayoutManager(Favourites.this));




                            } else if (responseMessage.equals("error")){
                                Toast.makeText(Favourites.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                                Log.d("qwe", "error1");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Favourites.this, error.toString(), Toast.LENGTH_SHORT).show();
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

        Button save = findViewById(R.id.saveBtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(Favourites.this);
                String url = "http://192.168.1.145/shoppingAssistantPHP/saveFavourites.php";


                // Request a string response from the provided URL.
                StringRequest stringRequest2 = new StringRequest(Request.Method.POST, url,
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

                                        //reloads the fragment in onResponse to get saved data from the db to upload images and title
                                        finish();
                                    } else if (responseMessage.equals("error")){
                                        Toast.makeText(Favourites.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                                        Log.d("qwe", "error1");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Favourites.this, error.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("qwe", "error2 + " + error.toString());
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> data = new HashMap<>();


                        SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
                        String userID = sharedPreferences.getString("user_id", null);

                        RecyclerView recyclerView = findViewById(R.id.favouritesRv);
                        ArrayList<FavouritesModel> favouritesList = ((FavouritesAdapter)recyclerView.getAdapter()).getData();


                        ArrayList<JSONObject> favouritesListData = new ArrayList<>();


                        for (int i = 0; i < favouritesList.size(); i++){

                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject.put("mealID", favouritesList.get(i).getId());
                                jsonObject.put("favourite", favouritesList.get(i).getFavourite());


                                favouritesListData.add(jsonObject);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }



                        JSONArray jsonArray = new JSONArray(favouritesListData);


                        data.put("user_id", userID);
                        data.put("favourites", jsonArray.toString());

                        Log.d("qwe", "208: " + jsonArray.toString());

                        return data;
                    }
                };

                // Add the request to the RequestQueue.
                queue.add(stringRequest2);
            }
        });




    }






}