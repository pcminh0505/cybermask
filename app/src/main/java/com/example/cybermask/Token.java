package com.example.cybermask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Token {
    private String name;
    private String symbol;
    private double price;
    private double price_change_24h;
    private String img_path;

    public Token (String symbol) throws IOException {

        // I'll use Binance API to fetch the data
        this.symbol = symbol.toUpperCase();

        String url = String.format("https://api1.binance.com/api/v3/ticker/24hr?symbol=%sUSDT",symbol.toUpperCase());
        URL endpoint = new URL(url);
        HttpURLConnection con = (HttpURLConnection) endpoint.openConnection();
        con.setRequestMethod("GET");

    }

    protected double getPrice() {
        return price;
    }

    protected double getPrice_change_24h() {
        return price_change_24h;
    }

    protected String img_path() {
        return img_path;
    }

    protected String getName() {
        return name;
    }

    protected String getSymbol() {
        return symbol;
    }
}
