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

public class AddTokenActivity extends AppCompatActivity {

    // Define the dictionary for token symbol - name since no API support this (Eg. BTC - Bitcoin).
    // For the simplicity, only 16 tokens will be mentioned.
    public final ArrayList<String> TOKEN_LIST = new ArrayList<>(Arrays.asList("ADA","BNB","BTC","CAKE","CELO","DOGE","DOT","ETH","FTM","FTT","MANA","NEAR","SHIB","SOL","UNI","XRP"));

    // Initialize variables for AddTokenAcitivity
    EditText tokenNameText;
    String tokenSymbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_token);

        // Wire the editText on the screen
        tokenNameText = findViewById(R.id.add_token_text);

        // Get the data from MainActivity to check the existed token in the list
        Intent i = getIntent();
        ArrayList<String> existedList = i.getExtras().getStringArrayList("existedList");

        // Wire the "Add" button
        Button addToken = (Button) findViewById(R.id.add_token_button);
        addToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get the input text
                tokenSymbol = tokenNameText.getText().toString();

                // If existed, send back the token's symbol to MainActivity. Else keep "toasting"
                if (TOKEN_LIST.contains(tokenSymbol.toUpperCase()) &&
                        !existedList.contains(tokenSymbol.toUpperCase())) {
                    Intent i = new Intent(AddTokenActivity.this,MainActivity.class);
                    i.putExtra("tokenSymbol", tokenSymbol);
                    setResult(RESULT_OK, i);
                    finish();
                } else if (!TOKEN_LIST.contains(tokenSymbol.toUpperCase())) {
                    Toast.makeText(getApplicationContext(), "Sorry the token isn't available in the database. Please try other token.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "You've already saved this token.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}