package com.example.cybermask;

import java.util.HashMap;
import java.util.Map;

public class Token {

    // Attributes
    private final String name;
    private final String symbol;
    private final int image;
    private String price;
    private String priceChange;
    private boolean isBull;

    // Temporary database for mapping name - symbol
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

    // Contructor
    public Token (String symbol, int image) {
        this.symbol = symbol;
        this.name = TOKEN_DICTIONARY.get(symbol);
        this.image = image;
    }

    // Getter & Setter
    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public String getPriceChange() {
        return priceChange;
    }

    public boolean isBull() {
        return isBull;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPriceChange(String priceChange) {
        this.priceChange = priceChange;
    }

    public void setBull(boolean bull) {
        isBull = bull;
    }
}
