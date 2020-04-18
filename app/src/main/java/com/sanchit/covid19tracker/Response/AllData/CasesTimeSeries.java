package com.sanchit.covid19tracker.Response.AllData;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CasesTimeSeries implements Parcelable {

    @SerializedName("dailyconfirmed")
    @Expose
    private String dailyconfirmed;
    @SerializedName("dailydeceased")
    @Expose
    private String dailydeceased;
    @SerializedName("dailyrecovered")
    @Expose
    private String dailyrecovered;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("totalconfirmed")
    @Expose
    private String totalconfirmed;
    @SerializedName("totaldeceased")
    @Expose
    private String totaldeceased;
    @SerializedName("totalrecovered")
    @Expose
    private String totalrecovered;

    protected CasesTimeSeries(Parcel in) {
        dailyconfirmed = in.readString();
        dailydeceased = in.readString();
        dailyrecovered = in.readString();
        date = in.readString();
        totalconfirmed = in.readString();
        totaldeceased = in.readString();
        totalrecovered = in.readString();
    }

    public static final Creator<CasesTimeSeries> CREATOR = new Creator<CasesTimeSeries>() {
        @Override
        public CasesTimeSeries createFromParcel(Parcel in) {
            return new CasesTimeSeries(in);
        }

        @Override
        public CasesTimeSeries[] newArray(int size) {
            return new CasesTimeSeries[size];
        }
    };

    public String getDailyconfirmed() {
        return dailyconfirmed;
    }

    public void setDailyconfirmed(String dailyconfirmed) {
        this.dailyconfirmed = dailyconfirmed;
    }

    public String getDailydeceased() {
        return dailydeceased;
    }

    public void setDailydeceased(String dailydeceased) {
        this.dailydeceased = dailydeceased;
    }

    public String getDailyrecovered() {
        return dailyrecovered;
    }

    public void setDailyrecovered(String dailyrecovered) {
        this.dailyrecovered = dailyrecovered;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalconfirmed() {
        return totalconfirmed;
    }

    public void setTotalconfirmed(String totalconfirmed) {
        this.totalconfirmed = totalconfirmed;
    }

    public String getTotaldeceased() {
        return totaldeceased;
    }

    public void setTotaldeceased(String totaldeceased) {
        this.totaldeceased = totaldeceased;
    }

    public String getTotalrecovered() {
        return totalrecovered;
    }

    public void setTotalrecovered(String totalrecovered) {
        this.totalrecovered = totalrecovered;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(dailyconfirmed);
        parcel.writeString(dailydeceased);
        parcel.writeString(dailyrecovered);
        parcel.writeString(date);
        parcel.writeString(totalconfirmed);
        parcel.writeString(totaldeceased);
        parcel.writeString(totalrecovered);
    }
}
