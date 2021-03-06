package com.sanchit.covid19tracker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blongho.country_data.World;
import com.eftimoff.androidplayer.Player;
import com.eftimoff.androidplayer.actions.property.PropertyAction;
import com.sanchit.covid19tracker.Adapters.UpdateAdapter;
import com.sanchit.covid19tracker.Network.SoleInstance;
import com.sanchit.covid19tracker.Network.WorldSoleInstance;
import com.sanchit.covid19tracker.R;
import com.sanchit.covid19tracker.Response.AllData.CasesTimeSeries;
import com.sanchit.covid19tracker.Response.AllData.DataResponse;
import com.sanchit.covid19tracker.Response.AllData.Statewise;
import com.sanchit.covid19tracker.Response.Countries.CountriesResponse;
import com.sanchit.covid19tracker.Response.Updates.UpdatesResponse;
import com.sanchit.covid19tracker.Utils.Constants;
import com.sanchit.covid19tracker.Utils.PreferencesUtil;
import com.sanchit.covid19tracker.databinding.ActivityHomeBinding;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    Context context;
    Animation rotateAnimation;
    private List<UpdatesResponse> updateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        context = this;
        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.white));
        }

        //     Constants.darkenStatusBar(this,R.color.white);
        Constants.darkenBottomBar(this,R.color.white);

    //    Constants.darkenStatusBar(this,R.color.white);


        World.init(context);


        List<com.blongho.country_data.Country> list = World.getAllCountries();

        int size  = list.size();


     //   getData();
        getIndiadata();
        animatesample(binding.iamge1, binding.iamge2,binding.iamge3, binding.iamge4);
        animation();
        getUpdates();


        binding.btnStatsAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,AnalysisActivity.class);
                startActivity(intent);
            }
        });

        binding.btnCovidResources.setOnClickListener(view -> startActivity(new Intent(context,ResourcesActivity.class)));



    }

    private void getIndiadata() {



        Call<DataResponse> call = SoleInstance.getApiServiceInstance().getAllData();

        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {

                if (response != null) {
                    if (response.body() != null) {


                        DecimalFormat formatter = new DecimalFormat("#,##,###");
                        String get_value = formatter.format(Integer.parseInt(response.body().getStatewise().get(0).getConfirmed()));
                        PreferencesUtil.setTotalcases(get_value);
                        binding.tvtoatalcases.setText(String.valueOf(PreferencesUtil.getTotalcases()));

             /*           String lastUpdateTime  = response.body().getStatewise().get(0).getLastupdatedtime().trim();

                        //   binding.textView2.setText("LAST UPDATED "+ Constants.getTimesAgo(lastUpdateTime).toUpperCase()+", "+lastUpdateTime);

                        String confirmed = (String.valueOf(response.body().getStatewise().get(0).getConfirmed()));
                        String active = (String.valueOf(response.body().getStatewise().get(0).getActive()));
                        String recovered = (String.valueOf(response.body().getStatewise().get(0).getRecovered()));
                        String death = (String.valueOf(response.body().getStatewise().get(0).getDeaths()));

                        setPieData(confirmed,active,recovered,death);

                        List<Statewise> statewiseList = response.body().getStatewise();
                        setStatesData(statewiseList);


                        List<CasesTimeSeries> graphlist = new ArrayList<>();

                        graphlist = response.body().getCasesTimeSeries();
                        setBarGraph(graphlist);*/

/*
                        binding.tvCDelCount.setText("+" + response.body().getStatewise().get(0).getDeltaconfirmed());
                        //     binding.tvADelCount.setText("[+"+ response.body().getStatewise().get(0).getDelta().getActive() +"]");
                        binding.tvRDelCount.setText("+" + response.body().getStatewise().get(0).getDeltarecovered());
                        binding.tvDDelCount.setText("+" + response.body().getStatewise().get(0).getDeltadeaths());

                        statewiseList = response.body().getStatewise();
                        setStatesData(statewiseList);
                        statewiseList.remove(0);
                        adapter = new StatewiseDataAdapter(statewiseList, context);
                        binding.rvStatewise.setAdapter(adapter);
                        binding.recyclerView.setItemViewCacheSize(statewiseList.size());

                        List<CasesTimeSeries> list = response.body().getCasesTimeSeries();
                        Collections.reverse(list);
                        dateList = list;
                        dateWiseAdapter = new DateWiseAdapter(list, context);
                        binding.recyclerView.setAdapter(dateWiseAdapter);
                        binding.recyclerView.setItemViewCacheSize(list.size());
                        // binding.recyclerView.scrollToPosition((response.body().getCasesTimeSeries().size()-1));*/


                    } else {
                        binding.tvtoatalcases.setText(String.valueOf(PreferencesUtil.getTotalcases()));
                    }
                } else {
                    binding.tvtoatalcases.setText(String.valueOf(PreferencesUtil.getTotalcases()));
                }

            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                binding.tvtoatalcases.setText(String.valueOf(PreferencesUtil.getTotalcases()));
            }
        });
    }

    private void getUpdates() {

        Call<List<UpdatesResponse>> call = SoleInstance.getApiServiceInstance().getUpdates();

        call.enqueue(new Callback<List<UpdatesResponse>>() {
            @Override
            public void onResponse(Call<List<UpdatesResponse>> call, Response<List<UpdatesResponse>> response) {
                if(response.body() !=null)
                {
                    if(response.body().size() > 0)
                    {
                        updateList = response.body();
                        Collections.reverse(updateList);
                        UpdateAdapter adapter;
                        adapter = new UpdateAdapter(updateList,context);
                        binding.rvUpdates.setLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        binding.rvUpdates.setAdapter(adapter);
                        binding.rvUpdates.setItemViewCacheSize(updateList.size());

                        final int speedScroll = 5000;
                        final Handler handler = new Handler();
                        final Runnable runnable = new Runnable() {
                            int count = 0;
                            boolean flag = true;
                            @Override
                            public void run() {
                                if(count < updateList.size()){
                                    if(count==updateList.size()-1){
                                        flag = false;
                                    }else if(count == 0){
                                        flag = true;
                                    }
                                    if(flag) count++;
                                    else count--;

                                    binding.rvUpdates.smoothScrollToPosition(count);
                                    handler.postDelayed(this,speedScroll);
                                }
                            }
                        };

                        handler.postDelayed(runnable,speedScroll);



                    }else
                    {
                        Toast.makeText(HomeActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    Toast.makeText(HomeActivity.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<UpdatesResponse>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void animatesample(ConstraintLayout iamge1, ConstraintLayout iamge2, LinearLayout image3, LinearLayout iamge4) {

//        final PropertyAction fabAction = PropertyAction.newPropertyAction(iamge2).scaleX(0).scaleY(0).duration(750).interpolator(new AccelerateDecelerateInterpolator()).build();
//        final PropertyAction headerAction = PropertyAction.newPropertyAction(iamge1).interpolator(new DecelerateInterpolator()).translationY(-200).duration(550).alpha(0.4f).build();
//        final PropertyAction fabAction1 = PropertyAction.newPropertyAction(image3).scaleX(0).scaleY(0).duration(400).interpolator(new AccelerateDecelerateInterpolator()).build();
//        final PropertyAction bottomAction2 = PropertyAction.newPropertyAction(iamge4).translationY(500).duration(750).alpha(0f).build();


        final PropertyAction fabAction = PropertyAction.newPropertyAction(iamge1).translationY(500).duration(750).alpha(0f).build();
        final PropertyAction headerAction = PropertyAction.newPropertyAction(iamge2).translationY(500).duration(750).alpha(0f).build();
        final PropertyAction fabAction1 = PropertyAction.newPropertyAction(image3).translationY(500).duration(750).alpha(0f).build();
        final PropertyAction bottomAction2 = PropertyAction.newPropertyAction(iamge4).translationY(500).duration(750).alpha(0f).build();

        Player.init().
                animate(fabAction).
                then().
                animate(headerAction).
                then().
                animate(fabAction1).
                then().
                animate(bottomAction2).
                play();

    }

    private void getData() {
        Call<CountriesResponse> call = WorldSoleInstance.getApiServiceInstance().getSummary();

        call.enqueue(new Callback<CountriesResponse>() {
            @Override
            public void onResponse(Call<CountriesResponse> call, Response<CountriesResponse> response) {
                if (response != null) {
                    if (response.body() != null) {

                        DecimalFormat formatter = new DecimalFormat("#,##,###");
                        String get_value = formatter.format(response.body().getGlobal().getTotalConfirmed());
                        PreferencesUtil.setTotalcases(get_value);
                        binding.tvtoatalcases.setText(String.valueOf(PreferencesUtil.getTotalcases()));


                    } else {
                        binding.tvtoatalcases.setText(String.valueOf(PreferencesUtil.getTotalcases()));
                    }
                } else {

                    Toast.makeText(HomeActivity.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CountriesResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void animation() {
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        binding.imageView7.startAnimation(rotateAnimation);

        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.reverse_rotate);
        binding.imageView8.startAnimation(rotateAnimation);

        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.reverse_rotate);
        binding.imageView5.startAnimation(rotateAnimation);

        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        binding.imageView4.startAnimation(rotateAnimation);


    }
}
