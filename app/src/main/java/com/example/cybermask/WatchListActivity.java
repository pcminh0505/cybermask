package com.example.cybermask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class WatchListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    ArrayList<String> cryptoName = new ArrayList<>();
    ArrayList<String> cryptoSymbol = new ArrayList<>();
    ArrayList<Integer> cryptoImage = new ArrayList<>();


    String name[], symbol[];
    int images[] = {R.drawable.ada, R.drawable.bnb, R.drawable.btc, R.drawable.cake,
            R.drawable.celo, R.drawable.doge, R.drawable.dot, R.drawable.eth,
            R.drawable.ftm, R.drawable.ftt, R.drawable.mana, R.drawable.near,
            R.drawable.shib, R.drawable.sol, R.drawable.uni, R.drawable.xrp
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);

        // Setup RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
//        name = getResources().getStringArray(R.array.token_name);
//        symbol = getResources().getStringArray(R.array.token_symbol);

        MyAdapter myAdapter = new MyAdapter(this, cryptoName, cryptoSymbol,cryptoImage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Receive intent value for watchlist name
        Intent i = getIntent();
        String watchlistName = (String) i.getExtras().get("watchlist_name");

        // Wire watchList text and pass the value
        TextView watchlist = (TextView) findViewById(R.id.watchlist);
        watchlist.setText(watchlistName);

        // Define delete all watchlist button
        Button deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open warning dialog
                openDialog();
            }
        });

        Button refreshButton = (Button) findViewById(R.id.refresh_button);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setAdapter(myAdapter);
            }
        });





        FloatingActionButton addButton = (FloatingActionButton ) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WatchListActivity.this, AddTokenActivity.class);
                startActivityForResult(i,100);
            }
        });
    }

    protected void openDialog() {
        DeleteDialog deleteDialog = new DeleteDialog();
        deleteDialog.show(getSupportFragmentManager(),"Delete Dialog");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
}