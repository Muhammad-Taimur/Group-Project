package com.example.android.newfirebase;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Taimur on 01/04/2018.
 */

public class OldAdapter extends RecyclerView.Adapter<OldAdapter.ViewHolder> {


    private List<ListItem> listItems;
    private Context context;

    public OldAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//to fetch the layout
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//keep repeatign the layout
        View rootView = inflater.inflate(R.layout.main_list_item, parent, false);


        //Have to create a new list_item xml file to link this list item whichi s red.


        return new ViewHolder(rootView);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final ListItem listItem = listItems.get(position);

        holder.textViewHead.setText(listItem.getHead());
        holder.textViewDesc.setText(listItem.getDesc());
        holder.textViewStatus.setText(listItem.getStatus());
//        holder.textViewLat.setText(listItem.getLat());
//        holder.textViewLat.setText(listItem.getLng());
        holder.textView_bike_stands.setText(listItem.getBike_stands());
        holder.textView_available_bikes.setText(listItem.getAvailable_bikes());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "You Clicked " + listItem.getHead(), Toast.LENGTH_LONG).show();


                Intent intent = new Intent(context, startendActivity.class);
                intent.putExtra("Address", listItem.getHead());

                //I put the context here because its dont know where we trying to start the activity from because
                // we are inside the Recycler View adapter

                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView textViewHead;
        public TextView textViewDesc;
        public TextView textViewStatus;
        public TextView textView_available_bikes;
        public TextView textView_bike_stands;


        public LinearLayout linearLayout;


        public ViewHolder(View itemView) {
            super(itemView);


            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            textViewStatus = (TextView) itemView.findViewById(R.id.textViewStatus);
//            textViewLat = (TextView) itemView.findViewById(R.id.textViewLat);
            textView_bike_stands = (TextView) itemView.findViewById(R.id.textView_bike_stands);
            textView_available_bikes = (TextView) itemView.findViewById(R.id.textView_available_bikes);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);


        }
    }

}


