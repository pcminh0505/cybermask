package com.example.cybermask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WatchListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    String name[],code[];
    int images[] = {R.drawable.ada, R.drawable.bnb, R.drawable.btc, R.drawable.cake,
            R.drawable.celo, R.drawable.doge, R.drawable.dot, R.drawable.eth,
            R.drawable.ftm, R.drawable.ftt, R.drawable.mana, R.drawable.near,
            R.drawable.shib, R.drawable.sol, R.drawable.uni, R.drawable.xrp
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);

        Intent i = getIntent();
        String watchlistName = (String) i.getExtras().get("watchlist_name");

        TextView watchlist = (TextView) findViewById(R.id.watchlist);
        watchlist.setText(watchlistName);

        Button deleteButton= (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);

        name = getResources().getStringArray(R.array.token_name);
        code = getResources().getStringArray(R.array.token_code);

        MyAdapter myAdapter = new MyAdapter(this, name,code,images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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