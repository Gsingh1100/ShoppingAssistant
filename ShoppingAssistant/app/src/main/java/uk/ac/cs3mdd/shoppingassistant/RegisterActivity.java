package uk.ac.cs3mdd.shoppingassistant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {



    //Declaring objects - inputs and the button
    //Type for storing the input for email and password
    TextInputEditText emailInput;
    TextInputEditText passwordInput;

    //Type for the register button
    Button registerBtn;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Initialising the objects
        //emailInput is used to store the text stored within the email text box
        emailInput = findViewById(R.id.enterEmailInputText);

        //passwordInput is used to store the password set in the password text box
        passwordInput = findViewById(R.id.enterPasswordInputText);

        //registerBtn will be used to store the id of the register button ready for onClick use
        registerBtn = findViewById(R.id.registerBtn);



        //creates a link to move to login page, if account is already made
        //onClick listener to start a new activity - LoginActivity
        View loginLink = findViewById(R.id.loginHereLink);
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });



        //setting an onClick listener for the button to take action when selected:
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //We read the inputs within the text boxes which is of type String:
                String email = String.valueOf(emailInput.getText());
                String password = String.valueOf(passwordInput.getText());
                //if the user has left the email unfilled then we will output a message
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterActivity.this, "Email is missing", Toast.LENGTH_SHORT).show();
                    return;
                }

                //if the user has left the password unfilled then we will output a message
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this, "Password is missing", Toast.LENGTH_SHORT).show();
                    return;
                }


                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.1.145/shoppingAssistantPHP/register.php";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("qwe", "response: " + response);
                                response = response.trim();
                                if(response.equals("success")){
                                    Log.d("qwe", "success");
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else if ("error".equals(response)){
                                    Toast.makeText(RegisterActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                                    Log.d("qwe", "error1");
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("qwe", "error2 + " + error.toString());
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> data = new HashMap<>();

                        TextInputEditText username = findViewById(R.id.enterUsernameInputText);
                        TextInputEditText email = findViewById(R.id.enterEmailInputText);
                        TextInputEditText password = findViewById(R.id.enterPasswordInputText);
                        TextInputEditText firstName = findViewById(R.id.enterFirstnameInputText);
                        TextInputEditText lastName = findViewById(R.id.enterLastnameInputText);

                        data.put("username", username.getText().toString());
                        data.put("email", email.getText().toString());
                        data.put("password", password.getText().toString());
                        data.put("firstName", firstName.getText().toString());
                        data.put("lastName", lastName.getText().toString());
                        return data;
                    }
                };

                // Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        });


    }
}