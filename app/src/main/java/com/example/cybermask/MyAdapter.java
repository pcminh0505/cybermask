package com.example.cybermask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String data1[], data2[];
    int images[];
    Context context;

    public MyAdapter (Context ct, String name[], String code[], int img[]) {
        context = ct;
        data1 = name;
        data2 = code;
        images = img ;
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
        holder.name_text.setText((data1[position]));
        holder.code_text.setText((data2[position]));
        holder.token_icon.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name_text,code_text;
        ImageView token_icon;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_text = itemView.findViewById(R.id.name_text);
            code_text = itemView.findViewById(R.id.code_text);
            token_icon = itemView.findViewById(R.id.token_icon);
        }
    }
}