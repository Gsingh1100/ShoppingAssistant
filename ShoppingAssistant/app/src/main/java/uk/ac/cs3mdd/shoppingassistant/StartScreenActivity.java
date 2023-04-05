package uk.ac.cs3mdd.shoppingassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StartScreenActivity extends AppCompatActivity {

    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);


        //finds the id of the login button and sets it to loginButton
        loginButton = (Button) findViewById(R.id.loginButton);
        //create an on click listener to take action after the login button has been selected
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calls a method to start the loginActivity
                startLoginActivity();
            }
        });


        //finds id of the register button as sets it to registerButton
        registerButton = (Button) findViewById(R.id.registerButton);
        //create an on click listener which calls a method to open the registerActivity class
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calls startRegisterActivity method
                startRegisterActivity();
            }
        });
    }

    //method used to create an intent
    //opens the class when selected
    public void startLoginActivity(){
        //opens the loginActivityClass
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    //method used to create an intent
    //opens the class when selected
    public void startRegisterActivity(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        try {

            SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);

            if(!sharedPreferences.getString("username", null).isEmpty() && !sharedPreferences.getString("password", null).isEmpty() ){

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(StartScreenActivity.this);
                String url = "http://192.168.1.145/shoppingAssistantPHP/login.php";


                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("qwe", "response: " + response);



                                //taking the response which is json data - stores it as a JSONObject to easy to extract data
                                try{
                                    JSONObject jsonData = new JSONObject(response);
                                    String responseMessage = jsonData.getString("message");
                                    if(responseMessage.equals("success")){
                                        Log.d("qwe", "success");
                                        Log.d("qwe", "firstName: " + jsonData.getString("firstName"));


                                        //saves the user login to the phone
                                        //remembers the user when app reopens
                                        SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("user_id", jsonData.getString("user_id"));
                                        editor.putString("username", jsonData.getString("username"));
                                        editor.putString("email", jsonData.getString("email"));
                                        editor.putString("password", jsonData.getString("password"));
                                        editor.putString("firstName", jsonData.getString("firstName"));
                                        editor.putString("lastName", jsonData.getString("lastName"));

                                        editor.commit();

                                        Intent intent = new Intent(StartScreenActivity.this, StoreSelectionActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else if (responseMessage.equals("error")){
                                        Toast.makeText(StartScreenActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                                        Log.d("qwe", "error1");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(StartScreenActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("qwe", "error2 + " + error.toString());
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> data = new HashMap<>();

                        String username = sharedPreferences.getString("username", null);
                        String password = sharedPreferences.getString("password", null);

                        String loginMode = "savedLogin";

                        data.put("username", username);
                        data.put("password", password);
                        data.put("loginMode", loginMode);
                        return data;
                    }
                };

                // Add the request to the RequestQueue.
                queue.add(stringRequest);

            }

        } catch (Exception e){

        }



    }
}