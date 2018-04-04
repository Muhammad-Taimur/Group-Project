package com.example.android.newfirebase;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Taimur on 01/04/2018.
 */

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);


        String startPoint = getIntent().getStringExtra("startTrip");
        String endPoint = getIntent().getStringExtra("endTrip");
        String date = getIntent().getStringExtra("date");
        String comment = getIntent().getStringExtra("comment");
        String imageUrl= getIntent().getStringExtra("image");

        Log.d(TAG, "onCreate: "+startPoint);
        Log.d(TAG, "onCreate: "+endPoint);
        Log.d(TAG, "onCreate: "+date);
        Log.d(TAG, "onCreate: "+comment);
        Log.d(TAG, "onCreate: "+imageUrl);


        ImageView iv = (ImageView)findViewById(R.id.trip_image);
        Picasso.with(this)
                .load(imageUrl)
                .into(iv);

        TextView title_tv= (TextView)findViewById(R.id.title_tv);
        TextView date_tv= (TextView)findViewById(R.id.date_tv);
        TextView comment_tv= (TextView)findViewById(R.id.comment_tv);
        TextView end_tv= (TextView)findViewById(R.id.end_tv);



        title_tv.setText(startPoint);
        end_tv.setText(endPoint);
        date_tv.setText(date);
        comment_tv.setText(comment);


    }
}
