//Muhammad Taimur -15416

package com.example.android.newfirebase;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

public class MainListViewActivity extends AppCompatActivity {




    private RecyclerView recyclerView1;
    private OldAdapter adapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_view);


        recyclerView1 = (RecyclerView) findViewById(R.id.recyclerView1);
        recyclerView1.setHasFixedSize(true);

        //Layoutmanager is using for positiong and measurin the items in recycke View.

        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        //Assigning the apdapter to get the value from.

        adapter1 = new OldAdapter(ListItem.getInstance().getArrayList(), getApplicationContext());
//        adapter= new OldAdapter(ListItem.getInstance().getArrayList(),getApplicationContext());
        recyclerView1.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();

        //these two line is enabling the back button i get from the website link below
        https://freakycoder.com/android-notes-24-how-to-add-back-button-at-toolbar-941e6577418e
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.getParentActivityIntent(MainListViewActivity.this);

            //this is the method calling this method takes user on the back activity

            onBackPressed();


            return true;
        }
        super.onOptionsItemSelected(item);
        return false;
    }

    //This is the onPressed method when button clicked user go to the next activity
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
        startActivity(intent);
        }




}
