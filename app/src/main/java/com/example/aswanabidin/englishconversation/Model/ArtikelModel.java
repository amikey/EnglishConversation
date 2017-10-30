package com.example.aswanabidin.englishconversation.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aswanabidin on 10/20/17.
 */

public class ArtikelModel implements Parcelable {

    public String title;
    public String date;
    public String deskripsi;

    public ArtikelModel(){

    }

    public ArtikelModel(String title, String date, String deskripsi) {
        this.title = title;
        this.date = date;
        this.deskripsi = deskripsi;
    }

    public ArtikelModel(Parcel in) {
        title = in.readString();
        date = in.readString();
        deskripsi = in.readString();
    }

    public static final Creator<ArtikelModel> CREATOR = new Creator<ArtikelModel>() {
        @Override
        public ArtikelModel createFromParcel(Parcel in) {
            return new ArtikelModel(in);
        }

        @Override
        public ArtikelModel[] newArray(int size) {
            return new ArtikelModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(date);
        parcel.writeString(deskripsi);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public static Creator<ArtikelModel> getCREATOR() {
        return CREATOR;
    }
}
