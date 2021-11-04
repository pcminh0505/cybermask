package com.example.cybermask;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<String> symbol;
    ArrayList<String> name;
    ArrayList<Integer> image;
    ArrayList<String> price;
    ArrayList<String> priceChange;

    Context context;

    public MyAdapter (Context ct,ArrayList<String> symbol) {
        context = ct;
        this.symbol = symbol;

        // Map Symbol to Name and Image
        populateName(symbol,this.name);
        populateImage(ct,symbol,this.image);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.my_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tokenSymbol.setText((symbol.get(position)));
        holder.tokenName.setText((name.get(position)));
        holder.tokenIcon.setImageResource(image.get(position));
        holder.tokenPrice.setText((price.get(position)));
        holder.tokenPriceChange.setText((priceChange.get(position)));
    }

    @Override
    public int getItemCount() {
        return symbol.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tokenName, tokenSymbol, tokenPrice, tokenPriceChange;
        ImageView tokenIcon;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tokenName = itemView.findViewById(R.id.name_text);
            tokenSymbol = itemView.findViewById(R.id.symbol_text);
            tokenIcon = itemView.findViewById(R.id.token_icon);
            tokenPriceChange = itemView.findViewById(R.id.price_change_text);
            tokenPrice = itemView.findViewById(R.id.price_text);
        }
    }

    private void populateName (ArrayList<String> symbol, ArrayList<String> name) {
        final Map<String, String> TOKEN_DICTIONARY = new HashMap<String, String>() {{
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

        for (int i = 0; i < symbol.size(); i++) {
            name.add(TOKEN_DICTIONARY.get(symbol.get(i)));
        }
    }

    private void populateImage (Context c, ArrayList<String> symbol, ArrayList<Integer> image) {
        for (int i = 0; i < symbol.size(); i++) {
            int id = Resources.getSystem().getIdentifier(symbol.get(i).toLowerCase(), "drawable", c.getPackageName());;
            image.add(id);
        }
    }

}
