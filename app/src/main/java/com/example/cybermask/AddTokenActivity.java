package com.example.cybermask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class AddTokenActivity extends AppCompatActivity {

    // Define the dictionary for token symbol - name since no API support this (Eg. BTC - Bitcoin).
    // For the simplicity, only 16 tokens will be mentioned.
    public final Map<String, String> TOKEN_DICTIONARY = new HashMap<String, String>() {{
        put("ADA", "Cardano");
        put("BNB", "Binance Coin");
        put("BTC", "Bitcoin");
        put("CAKE", "Pancake Swap");
        put("CELO", "Celo");
        put("DOGE", "Dogecoin");
        put("DOT", "Polkadot");
        put("ETH", "Ethereum");
        put("FTM", "Fantom");
        put("FTT", "FTX Token");
        put("MANA", "Decentraland");
        put("NEAR", "NEAR Protocol");
        put("SHIB", "Shiba Inu");
        put("SOL", "Solana");
        put("UNI", "Uniswap");
        put("XRP", "Ripple");
    }};

    // Initialize variables for CreateAcitivity
    EditText tokenNameText;
    String tokenName;

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
                tokenName = tokenNameText.getText().toString();

                // Set a flag to check in the HashMap database
                boolean isExisted = false;
                for (String key : TOKEN_DICTIONARY.keySet()) {
                    if (key.equals(tokenName.toUpperCase())) {
                        isExisted = true;
                        break;
                    }
                }

                if (isExisted) {
                    Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Sorry the token isn't available in the database", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}