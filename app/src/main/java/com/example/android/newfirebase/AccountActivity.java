package com.example.android.newfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class AccountActivity extends AppCompatActivity {


    Button btnListView;
    Button btnMaps;
    Button saveTrip;
    Button startTrip;
    Button fav;
    Button about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);


    btnListView = (Button) findViewById(R.id.btnListView);
    btnMaps= (Button) findViewById(R.id.btnMaps);
    saveTrip= (Button) findViewById(R.id.saveTrip);
    startTrip= (Button) findViewById(R.id.startTrip);
    fav= (Button) findViewById(R.id.fav);
    about= (Button) findViewById(R.id.about);


    btnListView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(AccountActivity.this,MainListViewActivity.class);
            startActivity(intent);

        }
    });

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AccountActivity.this,MapsActivity.class);
                startActivity(intent);

            }
        });

        saveTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AccountActivity.this,ListView.class);
                startActivity(intent);

            }
        });

        startTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this,startendActivity.class);
                startActivity(intent);

            }
        });

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AccountActivity.this,ListView.class);
                startActivity(intent);

            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AccountActivity.this,AboutActivity.class);
                startActivity(intent);


            }
        });

        Button sign_out = (Button) findViewById(R.id.sign_out);

        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(AccountActivity.this, LoginActivity.class));
            }
        });



    }
}
