package com.example.cybermask;

import android.content.Context;
import android.graphics.Color;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements ItemTouchHelperAdapter {
    private Context context;
    private ArrayList<Token> tokens;
    private ItemTouchHelper touchHelper;


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

    // ItemTouchHelper Implementation
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Token fromToken = tokens.get(fromPosition);
        tokens.remove(fromToken);
        tokens.add(toPosition,fromToken);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemSwiped(int position) {
        tokens.remove(position);
        Toast.makeText(context.getApplicationContext(), "Item has been deleted",Toast.LENGTH_SHORT).show();
        notifyItemRemoved(position);
    }

    public void setTouchHelper(ItemTouchHelper touchHelper) {
        this.touchHelper = touchHelper;
    }

    // Wire the element in the custom view (Row) to the Recyclerview
    public class MyViewHolder extends RecyclerView.ViewHolder implements
            View.OnTouchListener,
            GestureDetector.OnGestureListener {

        TextView tokenName, tokenSymbol, tokenPrice, tokenPriceChange;
        ImageView tokenIcon;
        GestureDetector gestureDetector;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tokenName = itemView.findViewById(R.id.name_text);
            tokenSymbol = itemView.findViewById(R.id.symbol_text);
            tokenIcon = itemView.findViewById(R.id.token_icon);
            tokenPriceChange = itemView.findViewById(R.id.price_change_text);
            tokenPrice = itemView.findViewById(R.id.price_text);
            gestureDetector = new GestureDetector(itemView.getContext(),this);

            itemView.setOnTouchListener(this);
        }

        // Implement Interface to control movement. Please note that it's only applied for LONG-PRESSED action
        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            touchHelper.startDrag(this);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return true;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            gestureDetector.onTouchEvent(event);
            return true;
        }
    }
}
