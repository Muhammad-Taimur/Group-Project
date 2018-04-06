//Muhammad Taimur -15416

package com.example.android.newfirebase;

/**
 * Created by Taimur on 28/03/2018.
 */

public class TripDetail {

    String tripId;
    String startPoint;
    String endtPoint;
    String date;
    String comment;
    String mImageUrl;
boolean favourite;


    public TripDetail(){

    }

    public TripDetail(String tripId, String startPoint, String endtPoint, String date, String comment,String mImageUrl,boolean favourite) {
        this.tripId = tripId;
        this.startPoint = startPoint;
        this.endtPoint = endtPoint;
        this.date = date;
        this.comment = comment;
        this.mImageUrl=mImageUrl;
        this.favourite=favourite;
    }
    public TripDetail(String startPoint, String endtPoint, String date, String comment,String mImageUrl,boolean favourite) {
        this.startPoint = startPoint;
        this.endtPoint = endtPoint;
        this.date = date;
        this.comment = comment;
        this.mImageUrl=mImageUrl;
        this.favourite=favourite;
    }

    public boolean favourite() {
        return favourite;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getTripId() {
        return tripId;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public String getEndtPoint() {
        return endtPoint;
    }

    public String getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }
}
