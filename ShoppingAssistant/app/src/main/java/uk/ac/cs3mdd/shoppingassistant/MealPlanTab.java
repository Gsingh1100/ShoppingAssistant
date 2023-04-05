package uk.ac.cs3mdd.shoppingassistant;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.DateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MealPlanTab extends Fragment {


    ArrayList<Meal> breakfastMeals = new ArrayList<>();
    ArrayList<Meal> lunchMeals = new ArrayList<>();
    ArrayList<Meal> dinnerMeals = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal_plan_tab, container, false);
    }



    //display the current date
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //volley request to get data from the database, before the meal plan tab is loaded
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "http://192.168.1.145/shoppingAssistantPHP/getMealData.php";
        String url2 = "http://192.168.1.145/shoppingAssistantPHP/getWeeklyPlan.php";



        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, url2,
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
                                JSONArray weeklyPlannerData = jsonData.getJSONArray("weeklyplanner");

                                HashMap<String, DayMeal> breakfastWeeklyPlanner = new HashMap<>();
                                HashMap<String, DayMeal> lunchWeeklyPlanner = new HashMap<>();
                                HashMap<String, DayMeal> dinnerWeeklyPlanner = new HashMap<>();



                                for (int i = 0; i < weeklyPlannerData.length(); i++){


                                    //getting the json data (gets each meal individually)
                                    JSONObject mealData = weeklyPlannerData.getJSONObject(i);
                                    Log.d("qwe", "mealData: " + mealData);


                                    String id = mealData.optString("meal_id");
                                    String name = mealData.optString("name");
                                    String category = mealData.optString("mealCategory");
                                    String mealImage = mealData.optString("mealImage");
                                    String date = mealData.optString("date");


                                    DayMeal meal = new DayMeal(id, category, name, mealImage, date);

                                    Log.d("qwe", "date: " + date);

                                    if (category.equals("Breakfast")){
                                        breakfastWeeklyPlanner.put(date, meal);

                                    } else if (category.equals("Lunch")){
                                        lunchWeeklyPlanner.put(date, meal);

                                    } else if (category.equals("Dinner")){
                                        dinnerWeeklyPlanner.put(date, meal);
                                    }

                                }


                                Calendar cal = Calendar.getInstance();

                                int day = cal.get(Calendar.DAY_OF_MONTH);
                                int month = cal.get(Calendar.MONTH);
                                int year = cal.get(Calendar.YEAR);

                                String dayString = "";
                                String monthString = "";
                                String yearString = String.valueOf(year);

                                if (day<= 9 ){
                                    dayString = "0" + day;
                                } else {
                                    dayString = String.valueOf(day);
                                }


                                if (month<= 9 ){
                                    monthString = "0" + month;
                                } else {
                                    monthString = String.valueOf(month);
                                }

                                String dateString = yearString + "-" + monthString + "-" + dayString;

                                ///////////////////////////////////////////////////// images and set text for first three


                                try {
                                    Log.d("qwe", "date2: " + dateString);
                                    TextView breakfast1 = getView().findViewById(R.id.breakfast1);
                                    breakfast1.setText(breakfastWeeklyPlanner.get(dateString).getName());

                                    ImageView breakfastIMG1 = getView().findViewById(R.id.breakfastImage1);
                                    int imageID1 = getResources().getIdentifier(breakfastWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    breakfastIMG1.setImageResource(imageID1);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                try {
                                    TextView lunch1 = getView().findViewById(R.id.lunch1);
                                    lunch1.setText(lunchWeeklyPlanner.get(dateString).getName());

                                    ImageView lunchIMG1 = getView().findViewById(R.id.lunchImage1);
                                    int imageID2 = getResources().getIdentifier(lunchWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    lunchIMG1.setImageResource(imageID2);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                try {
                                    TextView dinner1 = getView().findViewById(R.id.dinner1);
                                    dinner1.setText(dinnerWeeklyPlanner.get(dateString).getName());

                                    ImageView dinnerIMG1 = getView().findViewById(R.id.imageView4);
                                    int imageID3 = getResources().getIdentifier(dinnerWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    dinnerIMG1.setImageResource(imageID3);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                cal.add(Calendar.DAY_OF_WEEK,1);
                                day = cal.get(Calendar.DAY_OF_MONTH);
                                month = cal.get(Calendar.MONTH);
                                year = cal.get(Calendar.YEAR);

                                dayString = "";
                                monthString = "";
                                yearString = String.valueOf(year);

                                if (day<= 9 ){
                                    dayString = "0" + day;
                                } else {
                                    dayString = String.valueOf(day);
                                }


                                if (month<= 9 ){
                                    monthString = "0" + month;
                                } else {
                                    monthString = String.valueOf(month);
                                }

                                dateString = yearString + "-" + monthString + "-" + dayString;


                                ///////////////////////////////////////////////////// images and set text for second three
                                try {
                                    Log.d("qwe", "date2: " + dateString);
                                    TextView breakfast2 = getView().findViewById(R.id.breakfast2);
                                    breakfast2.setText(breakfastWeeklyPlanner.get(dateString).getName());

                                    ImageView breakfastIMG2 = getView().findViewById(R.id.imageView5);
                                    int imageID4 = getResources().getIdentifier(breakfastWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    breakfastIMG2.setImageResource(imageID4);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                try {
                                    TextView lunch2 = getView().findViewById(R.id.lunch2);
                                    lunch2.setText(lunchWeeklyPlanner.get(dateString).getName());

                                    ImageView lunchIMG2 = getView().findViewById(R.id.imageView7);
                                    int imageID5 = getResources().getIdentifier(lunchWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    lunchIMG2.setImageResource(imageID5);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                try {
                                    TextView dinner2 = getView().findViewById(R.id.dinner2);
                                    dinner2.setText(dinnerWeeklyPlanner.get(dateString).getName());

                                    ImageView dinnerIMG2 = getView().findViewById(R.id.imageView8);
                                    int imageID6 = getResources().getIdentifier(dinnerWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    dinnerIMG2.setImageResource(imageID6);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                cal.add(Calendar.DAY_OF_WEEK,1);
                                day = cal.get(Calendar.DAY_OF_MONTH);
                                month = cal.get(Calendar.MONTH);
                                year = cal.get(Calendar.YEAR);

                                dayString = "";
                                monthString = "";
                                yearString = String.valueOf(year);

                                if (day<= 9 ){
                                    dayString = "0" + day;
                                } else {
                                    dayString = String.valueOf(day);
                                }


                                if (month<= 9 ){
                                    monthString = "0" + month;
                                } else {
                                    monthString = String.valueOf(month);
                                }

                                dateString = yearString + "-" + monthString + "-" + dayString;





                                ///////////////////////////////////////////////////// images and set text for third three
                                try {
                                    Log.d("qwe", "date2: " + dateString);
                                    TextView breakfast3 = getView().findViewById(R.id.breakfast3);
                                    breakfast3.setText(breakfastWeeklyPlanner.get(dateString).getName());

                                    ImageView breakfastIMG3 = getView().findViewById(R.id.imageView9);
                                    int imageID7 = getResources().getIdentifier(breakfastWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    breakfastIMG3.setImageResource(imageID7);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                try {
                                    TextView lunch3 = getView().findViewById(R.id.lunch3);
                                    lunch3.setText(lunchWeeklyPlanner.get(dateString).getName());

                                    ImageView lunchIMG3 = getView().findViewById(R.id.imageView10);
                                    int imageID8 = getResources().getIdentifier(lunchWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    lunchIMG3.setImageResource(imageID8);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                try {
                                    TextView dinner3 = getView().findViewById(R.id.dinner3);
                                    dinner3.setText(dinnerWeeklyPlanner.get(dateString).getName());

                                    ImageView dinnerIMG3 = getView().findViewById(R.id.imageView12);
                                    int imageID9 = getResources().getIdentifier(dinnerWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    dinnerIMG3.setImageResource(imageID9);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                cal.add(Calendar.DAY_OF_WEEK,1);
                                day = cal.get(Calendar.DAY_OF_MONTH);
                                month = cal.get(Calendar.MONTH);
                                year = cal.get(Calendar.YEAR);

                                dayString = "";
                                monthString = "";
                                yearString = String.valueOf(year);

                                if (day<= 9 ){
                                    dayString = "0" + day;
                                } else {
                                    dayString = String.valueOf(day);
                                }


                                if (month<= 9 ){
                                    monthString = "0" + month;
                                } else {
                                    monthString = String.valueOf(month);
                                }

                                dateString = yearString + "-" + monthString + "-" + dayString;




                                ///////////////////////////////////////////////////// images and set text for fourth three
                                try {
                                    Log.d("qwe", "date2: " + dateString);
                                    TextView breakfast4 = getView().findViewById(R.id.breakfast4);
                                    breakfast4.setText(breakfastWeeklyPlanner.get(dateString).getName());

                                    ImageView breakfastIMG4 = getView().findViewById(R.id.imageView13);
                                    int imageID10 = getResources().getIdentifier(breakfastWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    breakfastIMG4.setImageResource(imageID10);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                try {
                                    TextView lunch4 = getView().findViewById(R.id.lunch4);
                                    lunch4.setText(lunchWeeklyPlanner.get(dateString).getName());

                                    ImageView lunchIMG4 = getView().findViewById(R.id.imageView14);
                                    int imageID11 = getResources().getIdentifier(lunchWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    lunchIMG4.setImageResource(imageID11);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                try {
                                    TextView dinner4 = getView().findViewById(R.id.dinner4);
                                    dinner4.setText(dinnerWeeklyPlanner.get(dateString).getName());

                                    ImageView dinnerIMG4 = getView().findViewById(R.id.imageView15);
                                    int imageID12 = getResources().getIdentifier(dinnerWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    dinnerIMG4.setImageResource(imageID12);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                cal.add(Calendar.DAY_OF_WEEK,1);
                                day = cal.get(Calendar.DAY_OF_MONTH);
                                month = cal.get(Calendar.MONTH);
                                year = cal.get(Calendar.YEAR);

                                dayString = "";
                                monthString = "";
                                yearString = String.valueOf(year);

                                if (day<= 9 ){
                                    dayString = "0" + day;
                                } else {
                                    dayString = String.valueOf(day);
                                }


                                if (month<= 9 ){
                                    monthString = "0" + month;
                                } else {
                                    monthString = String.valueOf(month);
                                }

                                dateString = yearString + "-" + monthString + "-" + dayString;




                                ///////////////////////////////////////////////////// images and set text for fifth three
                                try {
                                    Log.d("qwe", "date2: " + dateString);
                                    TextView breakfast5 = getView().findViewById(R.id.breakfast5);
                                    breakfast5.setText(breakfastWeeklyPlanner.get(dateString).getName());

                                    ImageView breakfastIMG5 = getView().findViewById(R.id.imageView16);
                                    int imageID13 = getResources().getIdentifier(breakfastWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    breakfastIMG5.setImageResource(imageID13);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                try {
                                    TextView lunch5 = getView().findViewById(R.id.lunch5);
                                    lunch5.setText(lunchWeeklyPlanner.get(dateString).getName());

                                    ImageView lunchIMG5 = getView().findViewById(R.id.imageView17);
                                    int imageID14 = getResources().getIdentifier(lunchWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    lunchIMG5.setImageResource(imageID14);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                try {
                                    TextView dinner5 = getView().findViewById(R.id.dinner5);
                                    dinner5.setText(dinnerWeeklyPlanner.get(dateString).getName());

                                    ImageView dinnerIMG5 = getView().findViewById(R.id.imageView18);
                                    int imageID15 = getResources().getIdentifier(dinnerWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    dinnerIMG5.setImageResource(imageID15);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                cal.add(Calendar.DAY_OF_WEEK,1);
                                day = cal.get(Calendar.DAY_OF_MONTH);
                                month = cal.get(Calendar.MONTH);
                                year = cal.get(Calendar.YEAR);

                                dayString = "";
                                monthString = "";
                                yearString = String.valueOf(year);

                                if (day<= 9 ){
                                    dayString = "0" + day;
                                } else {
                                    dayString = String.valueOf(day);
                                }


                                if (month<= 9 ){
                                    monthString = "0" + month;
                                } else {
                                    monthString = String.valueOf(month);
                                }

                                dateString = yearString + "-" + monthString + "-" + dayString;



                                ///////////////////////////////////////////////////// images and set text for sixth three
                                try {
                                    Log.d("qwe", "date2: " + dateString);
                                    TextView breakfast6 = getView().findViewById(R.id.breakfast6);
                                    breakfast6.setText(breakfastWeeklyPlanner.get(dateString).getName());

                                    ImageView breakfastIMG6 = getView().findViewById(R.id.imageView19);
                                    int imageID16 = getResources().getIdentifier(breakfastWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    breakfastIMG6.setImageResource(imageID16);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                try {
                                    TextView lunch6 = getView().findViewById(R.id.lunch6);
                                    lunch6.setText(lunchWeeklyPlanner.get(dateString).getName());

                                    ImageView lunchIMG6 = getView().findViewById(R.id.imageView20);
                                    int imageID17 = getResources().getIdentifier(lunchWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    lunchIMG6.setImageResource(imageID17);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                try {
                                    TextView dinner6 = getView().findViewById(R.id.dinner6);
                                    dinner6.setText(dinnerWeeklyPlanner.get(dateString).getName());

                                    ImageView dinnerIMG6 = getView().findViewById(R.id.imageView21);
                                    int imageID18 = getResources().getIdentifier(dinnerWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    dinnerIMG6.setImageResource(imageID18);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                cal.add(Calendar.DAY_OF_WEEK,1);
                                day = cal.get(Calendar.DAY_OF_MONTH);
                                month = cal.get(Calendar.MONTH);
                                year = cal.get(Calendar.YEAR);

                                dayString = "";
                                monthString = "";
                                yearString = String.valueOf(year);

                                if (day<= 9 ){
                                    dayString = "0" + day;
                                } else {
                                    dayString = String.valueOf(day);
                                }


                                if (month<= 9 ){
                                    monthString = "0" + month;
                                } else {
                                    monthString = String.valueOf(month);
                                }

                                dateString = yearString + "-" + monthString + "-" + dayString;



                                ///////////////////////////////////////////////////// images and set text for seventh three
                                try {
                                    Log.d("qwe", "date2: " + dateString);
                                    TextView breakfast7 = getView().findViewById(R.id.breakfast7);
                                    breakfast7.setText(breakfastWeeklyPlanner.get(dateString).getName());

                                    ImageView breakfastIMG7 = getView().findViewById(R.id.imageView22);
                                    int imageID19 = getResources().getIdentifier(breakfastWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    breakfastIMG7.setImageResource(imageID19);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                try {
                                    TextView lunch7 = getView().findViewById(R.id.lunch7);
                                    lunch7.setText(lunchWeeklyPlanner.get(dateString).getName());

                                    ImageView lunchIMG7 = getView().findViewById(R.id.imageView23);
                                    int imageID20 = getResources().getIdentifier(lunchWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    lunchIMG7.setImageResource(imageID20);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                try {
                                    TextView dinner7 = getView().findViewById(R.id.dinner7);
                                    dinner7.setText(dinnerWeeklyPlanner.get(dateString).getName());

                                    ImageView dinnerIMG7 = getView().findViewById(R.id.imageView24);
                                    int imageID21 = getResources().getIdentifier(dinnerWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    dinnerIMG7.setImageResource(imageID21);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }



                            } else if (responseMessage.equals("error")){
                                Toast.makeText(getContext(), "Error Occurred", Toast.LENGTH_SHORT).show();
                                Log.d("qwe", "error1");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("qwe", "error2 + " + error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();


                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
                String userID = sharedPreferences.getString("user_id", null);

                data.put("user_id", userID);

                return data;
            }
        };


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
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

                                JSONArray breakfastData = jsonData.getJSONArray("Breakfast");
                                JSONArray lunchData = jsonData.getJSONArray("Lunch");
                                JSONArray dinnerData = jsonData.getJSONArray("Dinner");
                                Log.d("qwe", "breakfastData: " + breakfastData.toString());

                                ArrayList<String> breakfastNames = new ArrayList<>();
                                ArrayList<String> lunchNames = new ArrayList<>();
                                ArrayList<String> dinnerNames = new ArrayList<>();

                                breakfastNames.add("");
                                lunchNames.add("");
                                dinnerNames.add("");


                                //loop through the breakfast json data (items)
                                //makes a meal object for each meal in the json data and adds that to the breakfast meals list
                                for (int i = 0; i < breakfastData.length(); i++){


                                    //getting the json data (gets each meal individually)
                                    JSONObject mealData = breakfastData.getJSONObject(i);
                                    Log.d("qwe", "mealData: " + mealData);


                                    String id = mealData.optString("meal_id");
                                    String name = mealData.optString("name");
                                    String category = mealData.optString("mealCategory");
                                    String mealImage = mealData.optString("mealImage");

                                    breakfastNames.add(name);

                                    Meal meal = new Meal(id, name, category, mealImage);
                                    breakfastMeals.add(meal);
                                }


                                for (int i = 0; i < lunchData.length(); i++){
                                    //getting the json data (gets each meal individually)
                                    JSONObject mealData = lunchData.getJSONObject(i);

                                    String id = mealData.optString("meal_id");
                                    String name = mealData.optString("name");
                                    String category = mealData.optString("mealCategory");
                                    String mealImage = mealData.optString("mealImage");

                                    lunchNames.add(name);

                                    Meal meal = new Meal(id, name, category, mealImage);
                                    lunchMeals.add(meal);
                                }


                                for (int i = 0; i < dinnerData.length(); i++){
                                    //getting the json data (gets each meal individually)
                                    JSONObject mealData = dinnerData.getJSONObject(i);

                                    String id = mealData.optString("meal_id");
                                    String name = mealData.optString("name");
                                    String category = mealData.optString("mealCategory");
                                    String mealImage = mealData.optString("mealImage");

                                    dinnerNames.add(name);

                                    Meal meal = new Meal(id, name, category, mealImage);
                                    dinnerMeals.add(meal);
                                }

                                ArrayAdapter<String> breakfastAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, breakfastNames);
                                ArrayAdapter<String> lunchAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, lunchNames);
                                ArrayAdapter<String> dinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, dinnerNames);

                                breakfastAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                lunchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                dinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                Spinner spinner1 = getView().findViewById(R.id.spinnerMeal1);
                                spinner1.setAdapter(breakfastAdapter);

                                Spinner spinner2 = getView().findViewById(R.id.spinnerMeal2);
                                spinner2.setAdapter(lunchAdapter);

                                Spinner spinner3 = getView().findViewById(R.id.spinnerMeal3);
                                spinner3.setAdapter(dinnerAdapter);

                                Spinner spinner4 = getView().findViewById(R.id.spinnerMeal4);
                                spinner4.setAdapter(breakfastAdapter);

                                Spinner spinner5 = getView().findViewById(R.id.spinnerMeal5);
                                spinner5.setAdapter(lunchAdapter);

                                Spinner spinner6 = getView().findViewById(R.id.spinnerMeal6);
                                spinner6.setAdapter(dinnerAdapter);

                                Spinner spinner7 = getView().findViewById(R.id.spinnerMeal7);
                                spinner7.setAdapter(breakfastAdapter);

                                Spinner spinner8 = getView().findViewById(R.id.spinnerMeal8);
                                spinner8.setAdapter(lunchAdapter);

                                Spinner spinner9 = getView().findViewById(R.id.spinnerMeal9);
                                spinner9.setAdapter(dinnerAdapter);

                                Spinner spinner10 = getView().findViewById(R.id.spinnerMeal10);
                                spinner10.setAdapter(breakfastAdapter);

                                Spinner spinner11 = getView().findViewById(R.id.spinnerMeal11);
                                spinner11.setAdapter(lunchAdapter);

                                Spinner spinner12 = getView().findViewById(R.id.spinnerMeal12);
                                spinner12.setAdapter(dinnerAdapter);

                                Spinner spinner13 = getView().findViewById(R.id.spinnerMeal13);
                                spinner13.setAdapter(breakfastAdapter);

                                Spinner spinner14 = getView().findViewById(R.id.spinner14);
                                spinner14.setAdapter(lunchAdapter);

                                Spinner spinner15 = getView().findViewById(R.id.spinner15);
                                spinner15.setAdapter(dinnerAdapter);

                                Spinner spinner16 = getView().findViewById(R.id.spinner16);
                                spinner16.setAdapter(breakfastAdapter);

                                Spinner spinner17 = getView().findViewById(R.id.spinner17);
                                spinner17.setAdapter(lunchAdapter);

                                Spinner spinner18 = getView().findViewById(R.id.spinner18);
                                spinner18.setAdapter(dinnerAdapter);

                                Spinner spinner19 = getView().findViewById(R.id.spinner19);
                                spinner19.setAdapter(breakfastAdapter);

                                Spinner spinner20 = getView().findViewById(R.id.spinner20);
                                spinner20.setAdapter(lunchAdapter);

                                Spinner spinner21 = getView().findViewById(R.id.spinner21);
                                spinner21.setAdapter(dinnerAdapter);



                            } else if (responseMessage.equals("error")){
                                Toast.makeText(getContext(), "Error Occurred", Toast.LENGTH_SHORT).show();
                                Log.d("qwe", "error1");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("qwe", "error2 + " + error.toString());
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest2);

        queue.add(stringRequest);



        //get an instance of calendar
        //the calendar variable holds the current date
        Calendar cal = Calendar.getInstance();


        //take the current date, extract it and save it as a String
        //DateFormat.FULL will format the date to the full date inc. day, date, month, year

        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(cal.getTime());


        //gets the text view id and sets the text of the text View to the current date.
        TextView textViewDate = getView().findViewById(R.id.currentDateTextView);
        textViewDate.setText(currentDate);


        //sets the date for the next day by adding 1 to the day
        cal.add(Calendar.DAY_OF_WEEK,1);
        String dayTwo = DateFormat.getDateInstance(DateFormat.FULL).format(cal.getTime());
        TextView textViewDateTomorrow = getView().findViewById(R.id.dayTextView2);
        textViewDateTomorrow.setText(dayTwo);



        //sets the date for the third day by adding 1 to the value of the previous date
        cal.add(Calendar.DAY_OF_WEEK,1);
        String dayThree = DateFormat.getDateInstance(DateFormat.FULL).format(cal.getTime());
        TextView thirdDay = getView().findViewById(R.id.dayTextView3);
        thirdDay.setText(dayThree);


        //sets the date for the fourth day by adding 1 to the value of the previous date
        cal.add(Calendar.DAY_OF_WEEK,1);
        String dayFour = DateFormat.getDateInstance(DateFormat.FULL).format(cal.getTime());
        TextView fourthDay = getView().findViewById(R.id.dayTextView4);
        fourthDay.setText(dayFour);



        //sets the date for the fifth day by adding 1 to the value of the previous date
        cal.add(Calendar.DAY_OF_WEEK,1);
        String dayFive = DateFormat.getDateInstance(DateFormat.FULL).format(cal.getTime());
        TextView fifthDay = getView().findViewById(R.id.dayTextView5);
        fifthDay.setText(dayFive);



        //sets the date for the sixth day by adding 1 to the value of the previous date
        cal.add(Calendar.DAY_OF_WEEK,1);
        String daySix = DateFormat.getDateInstance(DateFormat.FULL).format(cal.getTime());
        TextView sixthDay = getView().findViewById(R.id.dayTextView6);
        sixthDay.setText(daySix);



        //sets the date for the seventh day by adding 1 to the value of the previous date
        cal.add(Calendar.DAY_OF_WEEK,1);
        String daySeven = DateFormat.getDateInstance(DateFormat.FULL).format(cal.getTime());
        TextView seventhDay = getView().findViewById(R.id.dayTextView7);
        seventhDay.setText(daySeven);






        //save the meal plan
        //update images and title
        Button saveMealPlan = getView().findViewById(R.id.saveMealPlanButton);
        saveMealPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Boolean validate = false;
                Spinner spinner1 = getView().findViewById(R.id.spinnerMeal1);
                if (spinner1.getSelectedItemPosition() == 0){
                    validate = true;
                }


                Spinner spinner2 = getView().findViewById(R.id.spinnerMeal2);
                if (spinner2.getSelectedItemPosition() == 0){
                    validate = true;
                }

                Spinner spinner3 = getView().findViewById(R.id.spinnerMeal3);
                if (spinner3.getSelectedItemPosition() == 0){
                    validate = true;
                }


                Spinner spinner4 = getView().findViewById(R.id.spinnerMeal4);
                if (spinner4.getSelectedItemPosition() == 0){
                    validate = true;
                }

                Spinner spinner5 = getView().findViewById(R.id.spinnerMeal5);
                if (spinner5.getSelectedItemPosition() == 0){
                    validate = true;
                }

                Spinner spinner6 = getView().findViewById(R.id.spinnerMeal6);
                if (spinner6.getSelectedItemPosition() == 0){
                    validate = true;
                }


                Spinner spinner7 = getView().findViewById(R.id.spinnerMeal7);
                if (spinner7.getSelectedItemPosition() == 0){
                    validate = true;
                }

                Spinner spinner8 = getView().findViewById(R.id.spinnerMeal8);
                if (spinner8.getSelectedItemPosition() == 0){
                    validate = true;
                }

                Spinner spinner9 = getView().findViewById(R.id.spinnerMeal9);
                if (spinner9.getSelectedItemPosition() == 0){
                    validate = true;
                }

                Spinner spinner10 = getView().findViewById(R.id.spinnerMeal7);
                if (spinner10.getSelectedItemPosition() == 0){
                    validate = true;
                }

                Spinner spinner11 = getView().findViewById(R.id.spinnerMeal8);
                if (spinner11.getSelectedItemPosition() == 0){
                    validate = true;
                }

                Spinner spinner12 = getView().findViewById(R.id.spinnerMeal9);
                if (spinner12.getSelectedItemPosition() == 0){
                    validate = true;
                }


                Spinner spinner13 = getView().findViewById(R.id.spinnerMeal7);
                if (spinner13.getSelectedItemPosition() == 0){
                    validate = true;
                }

                Spinner spinner14 = getView().findViewById(R.id.spinnerMeal8);
                if (spinner14.getSelectedItemPosition() == 0){
                    validate = true;
                }

                Spinner spinner15 = getView().findViewById(R.id.spinnerMeal9);
                if (spinner15.getSelectedItemPosition() == 0){
                    validate = true;
                }

                Spinner spinner16 = getView().findViewById(R.id.spinnerMeal7);
                if (spinner16.getSelectedItemPosition() == 0){
                    validate = true;
                }

                Spinner spinner17 = getView().findViewById(R.id.spinnerMeal8);
                if (spinner17.getSelectedItemPosition() == 0){
                    validate = true;
                }

                Spinner spinner18 = getView().findViewById(R.id.spinnerMeal9);
                if (spinner18.getSelectedItemPosition() == 0){
                    validate = true;
                }

                Spinner spinner19 = getView().findViewById(R.id.spinnerMeal7);
                if (spinner19.getSelectedItemPosition() == 0){
                    validate = true;
                }

                Spinner spinner20 = getView().findViewById(R.id.spinnerMeal8);
                if (spinner20.getSelectedItemPosition() == 0){
                    validate = true;
                }

                Spinner spinner21 = getView().findViewById(R.id.spinnerMeal9);
                if (spinner21.getSelectedItemPosition() == 0){
                    validate = true;
                }

                if (validate){
                    Toast.makeText(getActivity(), "please fill in all fields", Toast.LENGTH_SHORT).show();
                    Log.d("qwe", "please fill all fields");
                    return;
                }




                //volley request to get data from the database, before the meal plan tab is loaded
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(getContext());
                String url = "http://192.168.1.145/shoppingAssistantPHP/saveWeeklyPlanner.php";


                // Request a string response from the provided URL.
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

                                        //reloads the fragment in onResponse to get saved data from the db to upload images and title
                                        ((MainActivity)getActivity()).replaceFragment(new MealPlanFragment());

                                    } else if (responseMessage.equals("error")){
                                        Toast.makeText(getContext(), "Error Occurred", Toast.LENGTH_SHORT).show();
                                        Log.d("qwe", "error1");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("qwe", "error2 + " + error.toString());
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> data = new HashMap<>();


                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
                        String userID = sharedPreferences.getString("user_id", null);


                        ArrayList<DayPlan> weeklyPlan = new ArrayList<>();

                        Calendar cal = Calendar.getInstance();
                        //take the current date, extract it and save it as a String
                        //DateFormat.FULL will format the date to the full date inc. day, date, month, year
                        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(cal.getTime());

                        String dateString = cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DAY_OF_MONTH);


                        Spinner spinner1 = getView().findViewById(R.id.spinnerMeal1);
                        Meal meal1 = breakfastMeals.get(spinner1.getSelectedItemPosition()-1);
                        DayPlan dayPlan = new DayPlan(meal1.getId(), dateString, meal1.getCategory());
                        weeklyPlan.add(dayPlan);


                        Spinner spinner2 = getView().findViewById(R.id.spinnerMeal2);
                        Meal meal2 = lunchMeals.get(spinner2.getSelectedItemPosition()-1);
                        weeklyPlan.add(new DayPlan(meal2.getId(), dateString, meal2.getCategory()));

                        Spinner spinner3 = getView().findViewById(R.id.spinnerMeal3);
                        Meal meal3 = dinnerMeals.get(spinner3.getSelectedItemPosition()-1);
                        weeklyPlan.add(new DayPlan(meal3.getId(), dateString, meal3.getCategory()));

                        cal.add(Calendar.DAY_OF_WEEK,1);
                        dateString = cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DAY_OF_MONTH);




                        Spinner spinner4 = getView().findViewById(R.id.spinnerMeal4);
                        Meal meal4 = breakfastMeals.get(spinner4.getSelectedItemPosition()-1);
                        weeklyPlan.add(new DayPlan(meal4.getId(), dateString, meal4.getCategory()));

                        Spinner spinner5 = getView().findViewById(R.id.spinnerMeal5);
                        Meal meal5 = lunchMeals.get(spinner5.getSelectedItemPosition()-1);
                        weeklyPlan.add(new DayPlan(meal5.getId(), dateString, meal5.getCategory()));

                        Spinner spinner6 = getView().findViewById(R.id.spinnerMeal6);
                        Meal meal6 = dinnerMeals.get(spinner6.getSelectedItemPosition()-1);
                        weeklyPlan.add(new DayPlan(meal6.getId(), dateString, meal6.getCategory()));

                        cal.add(Calendar.DAY_OF_WEEK,1);
                        dateString = cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DAY_OF_MONTH);




                        Spinner spinner7 = getView().findViewById(R.id.spinnerMeal7);
                        Meal meal7 = breakfastMeals.get(spinner7.getSelectedItemPosition()-1);
                        weeklyPlan.add(new DayPlan(meal7.getId(), dateString, meal7.getCategory()));

                        Spinner spinner8 = getView().findViewById(R.id.spinnerMeal8);
                        Meal meal8 = lunchMeals.get(spinner8.getSelectedItemPosition()-1);
                        weeklyPlan.add(new DayPlan(meal8.getId(), dateString, meal8.getCategory()));

                        Spinner spinner9 = getView().findViewById(R.id.spinnerMeal9);
                        Meal meal9 = dinnerMeals.get(spinner9.getSelectedItemPosition()-1);
                        weeklyPlan.add(new DayPlan(meal9.getId(), dateString, meal9.getCategory()));

                        cal.add(Calendar.DAY_OF_WEEK,1);
                        dateString = cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DAY_OF_MONTH);




                        Spinner spinner10 = getView().findViewById(R.id.spinnerMeal7);
                        Meal meal10 = breakfastMeals.get(spinner10.getSelectedItemPosition()-1);
                        weeklyPlan.add(new DayPlan(meal10.getId(), dateString, meal10.getCategory()));

                        Spinner spinner11 = getView().findViewById(R.id.spinnerMeal8);
                        Meal meal11 = lunchMeals.get(spinner11.getSelectedItemPosition()-1);
                        weeklyPlan.add(new DayPlan(meal11.getId(), dateString, meal11.getCategory()));

                        Spinner spinner12 = getView().findViewById(R.id.spinnerMeal9);
                        Meal meal12 = dinnerMeals.get(spinner12.getSelectedItemPosition()-1);
                        weeklyPlan.add(new DayPlan(meal12.getId(), dateString, meal12.getCategory()));

                        cal.add(Calendar.DAY_OF_WEEK,1);
                        dateString = cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DAY_OF_MONTH);




                        Spinner spinner13 = getView().findViewById(R.id.spinnerMeal7);
                        Meal meal13 = breakfastMeals.get(spinner13.getSelectedItemPosition()-1);
                        weeklyPlan.add(new DayPlan(meal13.getId(), dateString, meal13.getCategory()));

                        Spinner spinner14 = getView().findViewById(R.id.spinnerMeal8);
                        Meal meal14 = lunchMeals.get(spinner14.getSelectedItemPosition()-1);
                        weeklyPlan.add(new DayPlan(meal14.getId(), dateString, meal14.getCategory()));

                        Spinner spinner15 = getView().findViewById(R.id.spinnerMeal9);
                        Meal meal15 = dinnerMeals.get(spinner15.getSelectedItemPosition()-1);
                        weeklyPlan.add(new DayPlan(meal15.getId(), dateString, meal15.getCategory()));

                        cal.add(Calendar.DAY_OF_WEEK,1);
                        dateString = cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DAY_OF_MONTH);




                        Spinner spinner16 = getView().findViewById(R.id.spinnerMeal7);
                        Meal meal16 = breakfastMeals.get(spinner16.getSelectedItemPosition()-1);
                        weeklyPlan.add(new DayPlan(meal16.getId(), dateString, meal16.getCategory()));

                        Spinner spinner17 = getView().findViewById(R.id.spinnerMeal8);
                        Meal meal17 = lunchMeals.get(spinner17.getSelectedItemPosition()-1);
                        weeklyPlan.add(new DayPlan(meal17.getId(), dateString, meal17.getCategory()));

                        Spinner spinner18 = getView().findViewById(R.id.spinnerMeal9);
                        Meal meal18 = dinnerMeals.get(spinner18.getSelectedItemPosition()-1);
                        weeklyPlan.add(new DayPlan(meal18.getId(), dateString, meal18.getCategory()));

                        cal.add(Calendar.DAY_OF_WEEK,1);
                        dateString = cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DAY_OF_MONTH);




                        Spinner spinner19 = getView().findViewById(R.id.spinnerMeal7);
                        Meal meal19 = breakfastMeals.get(spinner19.getSelectedItemPosition()-1);
                        weeklyPlan.add(new DayPlan(meal19.getId(), dateString, meal19.getCategory()));

                        Spinner spinner20 = getView().findViewById(R.id.spinnerMeal8);
                        Meal meal20 = lunchMeals.get(spinner20.getSelectedItemPosition()-1);
                        weeklyPlan.add(new DayPlan(meal20.getId(), dateString, meal20.getCategory()));

                        Spinner spinner21 = getView().findViewById(R.id.spinnerMeal9);
                        Meal meal21 = dinnerMeals.get(spinner21.getSelectedItemPosition()-1);
                        weeklyPlan.add(new DayPlan(meal21.getId(), dateString, meal21.getCategory()));


                        ArrayList<JSONObject> weeklyPlanData = new ArrayList<>();

                        for (int i = 0; i < weeklyPlan.size(); i++){

                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject.put("mealID", weeklyPlan.get(i).getMealID());
                                jsonObject.put("mealType", weeklyPlan.get(i).getMealType());
                                jsonObject.put("date", weeklyPlan.get(i).getDate());

                                weeklyPlanData.add(jsonObject);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        JSONArray jsonArray = new JSONArray(weeklyPlanData);
                        data.put("user_id", userID);
                        data.put("weeklyPlan", jsonArray.toString());

                        Log.d("qwe", "492: " + data.toString());





//                        data.put("username", username.getText().toString());
//                        data.put("password", password.getText().toString());
                        return data;
                    }
                };

                // Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        });

    }
}