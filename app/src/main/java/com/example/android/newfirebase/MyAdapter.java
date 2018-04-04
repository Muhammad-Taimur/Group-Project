//Name : Muhammad Taimur
//Student ID: 15416

//This is the adapter class which extends the recycler View. In this activity i do the following things.
// 1). assing the variable for lisitem and context anc create the constructor for my Adapter
// 2). In onCreate method Using the layout inflator to get the xml layouts.
// 3). In OnbindViewHolder setting our textview's in holder to show the data.
// 4). Create onClick listner in each TripDetail and passign the value of Intent
// 5). Item count method will go to the size of list and repeating the data till the list Item finish.
// 6). In public class of view holder which extends the holder class. I assign the varible which are the id's
// in out xml file.


//LayoutInflater is used to manipulate Android screen using predefined XML layouts.
// This class is used to instantiate layout XML file into its corresponding View objects
package com.example.android.newfirebase;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.bumptech.glide.Glide;

//import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Taimur on 04/03/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    private List<TripDetail> TripDetails;
    private Context context;

    public MyAdapter(List<TripDetail> TripDetails, Context context) {
        this.TripDetails = TripDetails;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//to fetch the layout
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//keep repeatign the layout
        View rootView = inflater.inflate(R.layout.list_item, parent, false);


        //Have to create a new list_item xml file to link this list item whichi s red.


        return new ViewHolder(rootView);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final TripDetail tD = TripDetails.get(position);

        holder.titleTv.setText(tD.getStartPoint() + " to " + tD.getEndtPoint());
        holder.dateTv.setText(tD.getDate());



        if (tD.favourite ==  false) {
            holder.starImage.setImageResource(android.R.drawable.star_big_off);
        }else{
            holder.starImage.setImageResource(android.R.drawable.star_big_on);
        }

        Picasso.with(context)
                .load(tD.getmImageUrl())
                .into(holder.imageView);

        holder.setItemOnCLickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TripDetail t = TripDetails.get(position);
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("startTrip", t.getStartPoint());
                intent.putExtra("endTrip", t.getEndtPoint());
                intent.putExtra("date", t.getDate());
                intent.putExtra("image", t.getmImageUrl());
                intent.putExtra("comment", t.getComment());

                //I put the context here because its dont know where we trying to start the activity from because
                // we are inside the Recycler View adapter

                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return TripDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titleTv;
        TextView dateTv;
        ImageView imageView, starImage;
        ItemClickListener itemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);

            titleTv = itemView.findViewById(R.id.title_tv);
            dateTv = itemView.findViewById(R.id.date_tv);
            imageView = itemView.findViewById(R.id.trip_image);
            starImage= itemView.findViewById(R.id.starImage);
            itemView.setOnClickListener(this);
        }

        public void setItemOnCLickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(view, this.getLayoutPosition());
        }
    }

}

interface ItemClickListener {
    void onItemClick(View view, int position);
}