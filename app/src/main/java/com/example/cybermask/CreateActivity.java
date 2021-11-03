package com.example.cybermask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateActivity extends AppCompatActivity {

    // Initialize variables for CreateAcitivity
    EditText watchlist_text;
    String watchlist_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // Wire the create button
        Button create_button = (Button) findViewById(R.id.create_button);
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Wire the editText field and store to a string "watchlist_name"
                watchlist_text = findViewById(R.id.watchlist_text);
                watchlist_name = watchlist_text.getText().toString();

                // Send "watchlist_name" to "WalletActivity"
                Intent i = new Intent (CreateActivity.this, WatchListActivity.class);
                i.putExtra("watchlist_name",watchlist_name);
                startActivity(i);
                watchlist_text.setText("New Watchlist");
            }
        });
    }
}