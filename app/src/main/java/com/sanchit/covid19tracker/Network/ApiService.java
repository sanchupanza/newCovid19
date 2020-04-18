package com.sanchit.covid19tracker.Network;

import com.sanchit.covid19tracker.Response.AllData.DataResponse;
import com.sanchit.covid19tracker.Response.Countries.CountriesResponse;
import com.sanchit.covid19tracker.Response.Districswise.DistrictWiseResponse;
import com.sanchit.covid19tracker.Response.Updates.UpdatesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {


    @GET("data.json")
    Call<DataResponse> getAllData();

    @GET("updatelog/log.json")
    Call<List<UpdatesResponse>> getUpdates();

    @GET("v2/state_district_wise.json")
    Call <List<DistrictWiseResponse>> getDataDistrictwise();

    @GET("summary")
    Call<CountriesResponse> getSummary();


}
