package uk.ac.cs3mdd.shoppingassistant;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Login", MODE_PRIVATE);
        TextView name = getView().findViewById(R.id.NameTextView);
        name.setText(sharedPreferences.getString("firstName", null));


        Random random = new Random();

        int storeCapacity = random.nextInt(100);
        double carParkCapacity = storeCapacity * 0.75;

        TextView carParkCapacityText = getView().findViewById(R.id.carParkCapacityTextView);
        TextView storeCapacityText = getView().findViewById(R.id.storeCapacityTextView);


        carParkCapacityText.setText(carParkCapacity + "%");
        storeCapacityText.setText(storeCapacity + "%");


        Button favourites = getView().findViewById(R.id.favouritesButton);
        favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Favourites.class);
                startActivity(intent);
            }
        });

        Bundle bundle = ((MainActivity)getActivity()).getIntent().getExtras();
        if(bundle != null){
            if(bundle.getInt("Store") == 0){
                TextView textView = getView().findViewById(R.id.StoreLocationTextView);
                textView.setText("Store A");

            } else if(bundle.getInt("Store") == 1) {
                TextView textView = getView().findViewById(R.id.StoreLocationTextView);
                textView.setText("Store B");
            } else if(bundle.getInt("Store") == 2) {
                TextView textView = getView().findViewById(R.id.StoreLocationTextView);
                textView.setText("Store C");
            }

        }





        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "http://192.168.1.145/shoppingAssistantPHP/getWeeklyPlan.php";


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
                                    TextView breakfast1 = getView().findViewById(R.id.breakfastTagView);
                                    breakfast1.setText(breakfastWeeklyPlanner.get(dateString).getName());

                                    ImageView breakfastIMG1 = getView().findViewById(R.id.breakfastIMG);
                                    int imageID1 = getResources().getIdentifier(breakfastWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    breakfastIMG1.setImageResource(imageID1);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                try {
                                    TextView lunch1 = getView().findViewById(R.id.lunchTagView);
                                    lunch1.setText(lunchWeeklyPlanner.get(dateString).getName());

                                    ImageView lunchIMG1 = getView().findViewById(R.id.lunchIMG);
                                    int imageID2 = getResources().getIdentifier(lunchWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    lunchIMG1.setImageResource(imageID2);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                try {
                                    TextView dinner1 = getView().findViewById(R.id.dinnerTagView);
                                    dinner1.setText(dinnerWeeklyPlanner.get(dateString).getName());

                                    ImageView dinnerIMG1 = getView().findViewById(R.id.dinnerIMG);
                                    int imageID3 = getResources().getIdentifier(dinnerWeeklyPlanner.get(dateString).getMealImage(), "drawable" , getContext().getPackageName());
                                    dinnerIMG1.setImageResource(imageID3);
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

        queue.add(stringRequest);



    }
}