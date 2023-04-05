package uk.ac.cs3mdd.shoppingassistant;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class ShoppingListTab extends Fragment {

    ArrayList<ShoppingListModel> shoppingListModels = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_list_tab, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        RequestQueue queue = Volley.newRequestQueue(getContext());
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

                                for (int i = 0; i < shoppingListData.length(); i++){

                                    JSONObject product = shoppingListData.getJSONObject(i);
                                    String name = product.getString("name");

                                    String image = product.getString("productImage");

                                    ShoppingListModel shoppingListModel = new ShoppingListModel(name, image);
                                    shoppingListModels.add(shoppingListModel);
                                }

                                RecyclerView recyclerView = getView().findViewById(R.id.shoppingListRecyclerView);

                                ShoppingListAdapter adapter = new ShoppingListAdapter(getContext(), shoppingListModels);

                                recyclerView.setAdapter(adapter);

                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


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