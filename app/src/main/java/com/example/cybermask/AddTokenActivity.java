package com.example.cybermask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class AddTokenActivity extends AppCompatActivity {

    // Define the dictionary for token symbol - name since no API support this (Eg. BTC - Bitcoin).
    // For the simplicity, only 16 tokens will be mentioned.
    public final ArrayList<String> TOKEN_LIST = new ArrayList<>(Arrays.asList("ADA","BNB","BTC","CAKE","CELO","DOGE","DOT","ETH","FTM","FTT","MANA","NEAR","SHIB","SOL","UNI","XRP"));

    // Initialize variables for CreateAcitivity
    EditText tokenNameText;
    String tokenSymbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_token);

        tokenNameText = findViewById(R.id.add_token_text);



        Button addToken = (Button) findViewById(R.id.add_token_button);
        addToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get the input text
                tokenSymbol = tokenNameText.getText().toString();

                // Set a flag to check in the HashMap database
                boolean isExisted = false;
                for (String key : TOKEN_LIST) {
                    if (key.equals(tokenSymbol.toUpperCase())) {
                        isExisted = true;
                        break;
                    }
                }

                // If existed, send back the token's symbol to MainActivity. Else keep "toasting"
                if (isExisted) {
                    Intent i = new Intent(AddTokenActivity.this,MainActivity.class);
                    i.putExtra("tokenSymbol", tokenSymbol);
                    setResult(RESULT_OK, i);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Sorry the token isn't available in the database. Please try other token.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}