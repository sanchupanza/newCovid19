package com.sanchit.covid19tracker.Adapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.sanchit.covid19tracker.Network.SoleInstance;
import com.sanchit.covid19tracker.R;
import com.sanchit.covid19tracker.Response.AllData.CasesTimeSeries;
import com.sanchit.covid19tracker.Response.AllData.DataResponse;
import com.sanchit.covid19tracker.Response.AllData.Statewise;
import com.sanchit.covid19tracker.Response.Districswise.DistrictDatum;
import com.sanchit.covid19tracker.Response.Districswise.DistrictWiseResponse;
import com.sanchit.covid19tracker.databinding.ActivityTestBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {

    private ActivityTestBinding binding;
    private Context context;
    private List<DistrictWiseResponse> districsList;
    private List<CustomePojo> customePojoList;
    private List<Statewise> statewiseList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_test);
        context = this;

        fetchAllData();
        getDistrictwiseData();



        TextDrawable drawable = TextDrawable.builder().buildRect("A", Color.RED);


        customePojoList = new ArrayList<>();
        customePojoList.add(new CustomePojo(R.drawable.user_000, 1230,"Maharashta"));
        customePojoList.add(new CustomePojo(R.drawable.user_001, 368,"Delhi"));
        customePojoList.add(new CustomePojo(R.drawable.user_002, 987, "Punjab"));
        customePojoList.add(new CustomePojo(R.drawable.user_003, 779,"Karnataka"));
        customePojoList.add(new CustomePojo(R.drawable.user_004, 889, "Andhra Pradesh"));
        customePojoList.add(new CustomePojo(R.drawable.user_005, 777,"Goa"));
        customePojoList.add(new CustomePojo(R.drawable.user_006, 208,"Rajasthan"));
        customePojoList.add(new CustomePojo(R.drawable.user_007, 538,"Gujarat"));






    }

    private void fetchAllData() {


        Call<DataResponse> call = SoleInstance.getApiServiceInstance().getAllData();

        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {

                if (response != null) {
                    if (response.body() != null) {



                        statewiseList = response.body().getStatewise();
                        statewiseList.remove(0);


                        int size = statewiseList.size();
                        for(int i = 0; i<size; i++)
                        {
                            if(i<statewiseList.size())
                            {
                                if(statewiseList.get(i).getConfirmed().equals("0"))
                                {
                                    statewiseList.remove(i);
                                    i--;
                                }
                            }

                        }







                    } else {
                        Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "No response", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDistrictwiseData() {
        Call<List<DistrictWiseResponse>> call = SoleInstance.getApiServiceInstance().getDataDistrictwise();

        call.enqueue(new Callback<List<DistrictWiseResponse>>() {
            @Override
            public void onResponse(Call<List<DistrictWiseResponse>> call, Response<List<DistrictWiseResponse>> response) {
                if (response != null) {
                    if (response.body() != null) {
                        districsList = response.body();

               //         String stateName = "Maharashtra";

                 /*       List<DistrictDatum> selectedDistrictList = new ArrayList<>();
                        for (int i = 0; i < districsList.size(); i++) {
                            if (stateName.equals(districsList.get(i).getState())) {
                                selectedDistrictList = districsList.get(i).getDistrictData();
                                break;
                            }
                        }*/

                        MyCustomAdapter adapter = new MyCustomAdapter(context,customePojoList,districsList,statewiseList);
                        binding.creativeViewPagerView.setCreativeViewPagerAdapter(adapter);


                    } else {
                        Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(context, "null response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DistrictWiseResponse>> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
