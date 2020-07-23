package com.fahed.tagassignment.Data.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//room table name
@Entity(tableName = "news")
public class News implements Parcelable {

    //for room database
    @PrimaryKey
    @NonNull
    private Long id;

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    //SerializedName is for name of the field in the api
    @SerializedName("nws_title")
    private String title;

    @SerializedName("nws_body")
    private String description;

    @SerializedName("nws_img_url")
    private String image;

    @SerializedName("nws_date")
    private String date;

    //this constructor is for testing method
    public News(String title, String date) {
        this.title = title;
        this.date = date;
        this.description = "<p>tesstt</p><h1>title</h1>";
        this.image = "https://media.tagorg.com//Upload/image/July2020/AIMICTParticipatesinRochestonREINVENTCybersecurityInternationalConference.jpg";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    //this function to return the date formatted
    public String getFormat() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.forLanguageTag("EN"));
        Date date = dateFormat.parse(getDate());
        dateFormat.applyPattern("E, dd-MMM-yyyy");
        return (dateFormat.format(date));
    }

    //for parcel
    protected News(Parcel in) {
        title = in.readString();
        description = in.readString();
        image = in.readString();
        date = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(image);
        dest.writeString(date);
    }
}
