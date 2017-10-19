package com.example.aswanabidin.englishconversation.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aswanabidin on 10/12/17.
 */

public class ConversationModel implements Parcelable {

    public String tempatenglish;
    public String tempatindonesian;
    public String percakapan;
    public String url;

    public ConversationModel(){

    }

    public ConversationModel(String tempatenglish, String tempatindonesian, String percakapan, String url) {
        this.tempatenglish = tempatenglish;
        this.tempatindonesian = tempatindonesian;
        this.percakapan = percakapan;
        this.url = url;
    }

    protected ConversationModel(Parcel in) {
        tempatenglish = in.readString();
        tempatindonesian = in.readString();
        percakapan = in.readString();
        url = in.readString();
    }

    public static final Creator<ConversationModel> CREATOR = new Creator<ConversationModel>() {
        @Override
        public ConversationModel createFromParcel(Parcel in) {
            return new ConversationModel(in);
        }

        @Override
        public ConversationModel[] newArray(int size) {
            return new ConversationModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(tempatenglish);
        parcel.writeString(tempatindonesian);
        parcel.writeString(percakapan);
        parcel.writeString(url);
    }

    public String getTempatenglish() {
        return tempatenglish;
    }

    public void setTempatenglish(String tempatenglish) {
        this.tempatenglish = tempatenglish;
    }

    public String getTempatindonesian() {
        return tempatindonesian;
    }

    public void setTempatindonesian(String tempatindonesian) {
        this.tempatindonesian = tempatindonesian;
    }

    public String getPercakapan() {
        return percakapan;
    }

    public void setPercakapan(String percakapan) {
        this.percakapan = percakapan;
    }

    public static Creator<ConversationModel> getCREATOR() {
        return CREATOR;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
