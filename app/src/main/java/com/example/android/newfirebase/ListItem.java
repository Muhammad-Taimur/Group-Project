//Name : Muhammad Taimur
//Student ID: 15416

// This is Java class file.
//This is the a model class for all the variable which i storing the data coming from rhe Api.
//In this class I do the following things:
// 1) Assigning Variable
// 2) Make Constructor for all variable
// 3) Make getter for all vraiable.
// 4) Give teh connection of the Interent USING VOLLEY LIBRARY. found about Volley in GOOGLE.
// 5) In this class inside the volley. I created listItemListner which is having an interface. And
// inside interface i created a method on complete and call the method in the very frist activity, So
// user opens the app data start fetching from the server.

package com.example.android.newfirebase;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Taimur on 27/02/2018.
 */
//model class for items

public class ListItem {

    //assign the list Item for our arraylsit.
//    private static final String TAG = "ListItem";


    //this is the variable stored the api
    public static final String URL_DATA = "https://api.jcdecaux.com/vls/v1/stations/?apiKey=a3a9557d412bdd112686191d64eaf956fc504218&contract=dublin";


    private String head;               //assign the variable
    private String desc;
    private String status;
    private String lat;
    private String lng;
    private String bike_stands;
    private String available_bikes;

    private ArrayList<ListItem> arrayList = new ArrayList<>();    //assign arraylist containing our list items.
    private static ListItem instance; //create a variable for listItelm instance


//    get the instance iof listitem and set the condition if its nothing return

    public static ListItem getInstance() {
        if (instance == null)
            return instance = new ListItem();
        else
            return instance;
    }

    public ArrayList<ListItem> getArrayList() {
        return arrayList;
    }

    //    constructor for  Empty listItem
    public ListItem() {
    }

    public ListItem(String head, String desc, String status, String lat, String lng, String bike_stands, String available_bikes) {
        this.head = head;
        this.desc = desc;
        this.status = status;
        this.lat = lat;
        this.lng = lng;
        this.bike_stands = bike_stands;
        this.available_bikes = available_bikes;

    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    public String getStatus() {
        return status;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }


    public String getBike_stands() {
        return bike_stands;
    }

    public String getAvailable_bikes() {
        return available_bikes;
    }

    public void loadRecyclerViewData(Activity context, final ListItemListener listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA,

                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
//                        Log.d(TAG," TEST onResponse: "+s);
                        try {
                            JSONArray jsonArray = new JSONArray(s);


                            for (int i = 0; i < jsonArray.length(); i++) {

//                              Parsing json data array
                                JSONObject jsonPart = jsonArray.getJSONObject(i);

//                                Parsing json object
                                JSONObject jsonObject2 = jsonPart.getJSONObject("position");

                                //these 2 object to get the lat and lng for the next activity to get markers
                                jsonObject2.getString("lat");
                                jsonObject2.getString("lng");

//                                Adding all data is a list
                                arrayList.add(new ListItem(

                                        "" + jsonPart.getString("name"),
                                        "" + jsonPart.getString("bike_stands"),
                                        "" + jsonPart.getString("status"),
                                        "" + jsonObject2.getString("lat"),
                                        "" + jsonObject2.getString("lng"),
                                        "" + jsonPart.getString("available_bike_stands"),
                                        "" + jsonPart.getString("available_bikes")

                                ));


                                if (listener != null) {
                                    listener.onComplete();
                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },

                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        //Volley request queue object
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        //queuing the request by passing the parameter string request
        requestQueue.add(stringRequest);
    }

    //put the interface in the listener and calling this method in the main activity as main activity start
    //data start fetcing.
    interface ListItemListener {
        void onComplete();
    }
}
