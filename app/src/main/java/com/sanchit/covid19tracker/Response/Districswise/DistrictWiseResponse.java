package com.sanchit.covid19tracker.Response.Districswise;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DistrictWiseResponse {

    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("districtData")
    @Expose
    private List<DistrictDatum> districtData = null;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<DistrictDatum> getDistrictData() {
        return districtData;
    }

    public void setDistrictData(List<DistrictDatum> districtData) {
        this.districtData = districtData;
    }
}
