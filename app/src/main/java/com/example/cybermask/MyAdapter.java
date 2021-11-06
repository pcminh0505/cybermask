package com.example.cybermask;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Token> tokens;


    public MyAdapter (Context ct,ArrayList<Token> tokens) {
        context = ct;
        this.tokens = tokens;
    }

    // Map the custom row layout in the RecyclerView
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.my_row, parent,false);
        return new MyViewHolder(view);
    }

    // Set the text to the component inside the custom layout
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tokenSymbol.setText((tokens.get(position).getSymbol()));
        holder.tokenName.setText((tokens.get(position).getName()));
        holder.tokenIcon.setImageResource((tokens.get(position).getImage()));
        holder.tokenPrice.setText((tokens.get(position).getPrice()));
        holder.tokenPriceChange.setText((tokens.get(position).getPriceChange()));

        if (tokens.get(position).isBull()) {
            holder.tokenPriceChange.setTextColor(Color.parseColor("#5B6A27"));
        } else {
            holder.tokenPriceChange.setTextColor(Color.parseColor("#DC3C30"));
        }
    }

    // Keep track of the size
    @Override
    public int getItemCount() {
        return tokens.size();
    }

    // Wire the element in the custom view (Row) to the Recyclerview
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
}
