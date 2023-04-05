package uk.ac.cs3mdd.shoppingassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {


    //Declaring objects - inputs and the button
    //Type for storing the input for email and password
    TextInputEditText usernameInput;
    TextInputEditText passwordInput;

    //Type for the register button
    Button loginBtn;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialising the objects
        //emailInput is used to store the text stored within the email text box
        usernameInput = findViewById(R.id.enterUsernameInputText);

        //passwordInput is used to store the password set in the password text box
        passwordInput = findViewById(R.id.enterPasswordInputText);

        //loginBtn will be used to store the id of the login button ready for onClick use
        loginBtn = findViewById(R.id.loginBtn);



        //creates a link to move to Register page, if account is not made
        //onClick listener to start a new activity - RegisterActivity
        View loginLink = findViewById(R.id.registerHereLink);
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //setting an action for when the login button is selected
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //We read the inputs within the text boxes which is of type String:
                String username = String.valueOf(usernameInput.getText());
                String password = String.valueOf(passwordInput.getText());

                //if the user has left the email unfilled then we will output a message
                if (TextUtils.isEmpty(username)){
                    Toast.makeText(LoginActivity.this, "Email is missing", Toast.LENGTH_SHORT).show();
                    return;
                }

                //if the user has left the password unfilled then we will output a message
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "Password is missing", Toast.LENGTH_SHORT).show();
                    return;
                }





                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
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

                                        Intent intent = new Intent(LoginActivity.this, StoreSelectionActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else if (responseMessage.equals("error")){
                                        Toast.makeText(LoginActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                                        Log.d("qwe", "error1");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("qwe", "error2 + " + error.toString());
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> data = new HashMap<>();

                        TextInputEditText username = findViewById(R.id.enterUsernameInputText);
                        TextInputEditText password = findViewById(R.id.enterPasswordInputText);

                        String loginMode = "normalLogin";

                        data.put("username", username.getText().toString());
                        data.put("password", password.getText().toString());
                        data.put("loginMode", loginMode);

                        return data;
                    }
                };

                // Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        });
    }
}