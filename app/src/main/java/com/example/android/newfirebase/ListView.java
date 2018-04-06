//Muhammad Taimur -15416

package com.example.android.newfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListView extends AppCompatActivity {


    private static final String TAG = "ListView";

    private RecyclerView recyclerView;
    private MyAdapter adapter;


    private DatabaseReference mDatabase;
    private ArrayList<TripDetail> arrayList=new ArrayList<>();
// ...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

//        Layoutmanager is using for positiong and measurin the items in recycke View.

        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                arrayList.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
//
                    TripDetail t = ds.getValue(TripDetail.class);
                    Log.d(TAG, "onChildAdded: " +t.getStartPoint()+" to "+t.getEndtPoint());
                    arrayList.add( new TripDetail( t.getStartPoint(),t.getEndtPoint(),t.getDate(),
                            t.getComment(),t.getmImageUrl(),t.favourite()));

                }

                adapter = new MyAdapter(arrayList, getApplicationContext());
                recyclerView.setAdapter(adapter);
                 adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //Assigning the apdapter to get the value from.




    }



}

