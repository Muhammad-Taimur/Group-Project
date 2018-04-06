//Muhammad Taimur -15416

package com.example.android.newfirebase;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        //using for loop to get the data from the array list which is in the ListItem java class.
        for (int i = 0; i < ListItem.getInstance().getArrayList().size(); i++) {


//            Parsing the fetced lat lng in double because they are in String and google Map is taking the lat lng in
//            Double
            double lat = Double.parseDouble(ListItem.getInstance().getArrayList().get(i).getLat());
            double lng = Double.parseDouble(ListItem.getInstance().getArrayList().get(i).getLng());

//          Getting the address from the json and stored them in String
            String title = ListItem.getInstance().getArrayList().get(i).getHead();

//            Adding marker in different place with lat lng double implementing in the lat lng value
            marker = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title(title).icon(BitmapDescriptorFactory.fromResource((R.drawable.unnamed))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 14));
        }


        //This is on click marker method using this method to click the marker and going to the next actity
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {


//      This is the onClick listner show the new activity when user click on the markers in Google Maps
                Intent intent = new Intent(getApplicationContext(), startendActivity.class);
                intent.putExtra("title", marker.getTitle());
                startActivity(intent);
            }
        });

    }





}
