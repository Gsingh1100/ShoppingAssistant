package uk.ac.cs3mdd.shoppingassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StoreSelectionActivity extends AppCompatActivity {

    Button storeAButton;
    Button storeBButton;
    Button storeCButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_selection);

        storeAButton = findViewById(R.id.storeABtn);
        storeAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StoreSelectionActivity.this, MainActivity.class);
                intent.putExtra("Store" , 0);
                startActivity(intent);
                finish();
            }
        });

        storeBButton = findViewById(R.id.storeBBtn);
        storeBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StoreSelectionActivity.this, MainActivity.class);
                intent.putExtra("Store" , 1);
                startActivity(intent);
                finish();
            }
        });

        storeCButton = findViewById(R.id.storeCBtn);
        storeCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StoreSelectionActivity.this, MainActivity.class);
                intent.putExtra("Store" , 2);
                startActivity(intent);
                finish();
            }
        });



    }
}