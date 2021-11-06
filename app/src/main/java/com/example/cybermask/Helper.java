package com.example.cybermask;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Helper {

    // This class is only use to store supported method to make to code cleaner.

    // Get the data from API and set to price & priceChange24h
    protected static void getAPIData(Context context, ArrayList<Token> token) {
        RequestQueue mQueue = Volley.newRequestQueue((Context) context);
        for (Token t : token) {
            String url = String.format("https://api2.binance.com/api/v3/ticker/24hr?symbol=%sUSDT",t.getSymbol());
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                double price = response.getDouble("lastPrice");
                                double priceChange = response.getDouble("priceChangePercent");
                                t.setPrice(String.valueOf(price));
                                t.setPriceChange(String.valueOf(priceChange));
                                t.setBull(priceChange >= 0); // Determine whether the token is bullish or not

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            mQueue.add(request);
        }
    }

    // Get the ID resource image from @drawable
    public static int getResourceImage(String symbol, Context context) {
        return context.getResources().getIdentifier(symbol.toLowerCase() , "drawable", context.getPackageName());
    }

    // Extract the token symbol for passing from Main -> AddToken Activity
    public static ArrayList<String> createSymbolList (ArrayList<Token> tokens) {
        ArrayList<String> symbol = new ArrayList<>();
        for (Token t : tokens ) {
            symbol.add(t.getSymbol());
        }
        return symbol;
    }
}
