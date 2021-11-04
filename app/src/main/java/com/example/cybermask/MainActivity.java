package com.example.cybermask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mCryptoSymbol;

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    protected void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MyAdapter(this, mCryptoSymbol);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);

        // Setup RecyclerView
        buildRecyclerView();

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

            }
        });

        FloatingActionButton addButton = (FloatingActionButton ) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddTokenActivity.class);
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

        // Receive data from AddTokenActivity
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                String resultSymbol = data.getStringExtra("tokenSymbol").toUpperCase();
                mCryptoSymbol.add(resultSymbol);
                mAdapter.notifyItemInserted(mCryptoSymbol.size() - 1);
                Toast.makeText(getApplicationContext(), "Token " + resultSymbol + " has been added successfully!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}