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

    @ColumnInfo(name = "boolean")
    private boolean isFavorite;

    @ColumnInfo(name = "category")
    private String placeType;

    @Ignore
    public Place(int locImage, String locTitle, String locDescription,
                 String locReview, String locNumber, String locAddress,
                 String locUrl, boolean isFavorite, String placeType) {
        this.locImage = locImage;
        this.locTitle = locTitle;
        this.locDescription = locDescription;
        this.locReview = locReview;
        this.locNumber = locNumber;
        this.locAddress = locAddress;
        this.locUrl = locUrl;
        this.isFavorite = isFavorite;
        this.placeType = placeType;
    }

    @Ignore
    public Place(int locImage, String locTitle, String locDescription,
                 String locNumber, String locAddress, String locUrl,
                 boolean isFavorite, String placeType) {
        this.locImage = locImage;
        this.locTitle = locTitle;
        this.locDescription = locDescription;
        this.locNumber = locNumber;
        this.locAddress = locAddress;
        this.locUrl = locUrl;
        this.isFavorite = isFavorite;
        this.placeType = placeType;
    }

    public Place(String locTitle, String locNumber, boolean isFavorite, String placeType) {
        this.locTitle = locTitle;
        this.locNumber = locNumber;
        this.isFavorite = isFavorite;
        this.placeType = placeType;
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

    public boolean getIsFavorite() { return isFavorite; }

    public String getPlaceType() { return placeType; }

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

    public void setFavorite(boolean isFavorite) { this.isFavorite = isFavorite; }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }
}
