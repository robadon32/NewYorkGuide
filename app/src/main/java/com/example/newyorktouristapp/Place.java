package com.example.newyorktouristapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "place_table")
public class Place {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "title")
    private String locTitle;

    @ColumnInfo(name = "description")
    private String locDescription;

    @ColumnInfo(name = "review")
    private String locReview;

    @ColumnInfo(name = "number")
    private String locNumber;

    @ColumnInfo(name = "address")
    private String locAddress;

    @ColumnInfo(name = "link")
    private String locUrl;

    @ColumnInfo(name = "image")
    private int locImage;

    @Ignore
    public Place(int locImage, String locTitle, String locDescription, String locReview, String locNumber, String locAddress, String locUrl) {
        this.locImage = locImage;
        this.locTitle = locTitle;
        this.locDescription = locDescription;
        this.locReview = locReview;
        this.locNumber = locNumber;
        this.locAddress = locAddress;
        this.locUrl = locUrl;
    }

    @Ignore
    public Place(int locImage, String locTitle, String locDescription, String locNumber, String locAddress, String locUrl) {
        this.locImage = locImage;
        this.locTitle = locTitle;
        this.locDescription = locDescription;
        this.locNumber = locNumber;
        this.locAddress = locAddress;
        this.locUrl = locUrl;
    }

    public Place(String locTitle, String locNumber) {
        this.locTitle = locTitle;
        this.locNumber = locNumber;
    }

    public int getLocImage() {
        return locImage;
    }

    public String getLocNumber() {
        return locNumber;
    }

    public String getLocDescription() {
        return locDescription;
    }

    public String getLocTitle() {
        return locTitle;
    }

    public String getLocUrl() {
        return locUrl;
    }

    public String getLocAddress() {
        return locAddress;
    }

    public String getLocReview() {
        return locReview;
    }

    public void setLocTitle(@NonNull String locTitle) {
        this.locTitle = locTitle;
    }

    public void setLocDescription(String locDescription) {
        this.locDescription = locDescription;
    }

    public void setLocReview(String locReview) {
        this.locReview = locReview;
    }

    public void setLocNumber(String locNumber) {
        this.locNumber = locNumber;
    }

    public void setLocAddress(String locAddress) {
        this.locAddress = locAddress;
    }

    public void setLocUrl(String locUrl) {
        this.locUrl = locUrl;
    }

    public void setLocImage(int locImage) {
        this.locImage = locImage;
    }


}
