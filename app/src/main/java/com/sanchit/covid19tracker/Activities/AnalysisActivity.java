package com.sanchit.covid19tracker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import com.eftimoff.androidplayer.Player;
import com.eftimoff.androidplayer.actions.property.PropertyAction;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.sanchit.covid19tracker.Network.SoleInstance;
import com.sanchit.covid19tracker.R;
import com.sanchit.covid19tracker.Response.AllData.CasesTimeSeries;
import com.sanchit.covid19tracker.Response.AllData.DataResponse;
import com.sanchit.covid19tracker.Response.AllData.Statewise;
import com.sanchit.covid19tracker.databinding.ActivityAnalysisBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnalysisActivity extends AppCompatActivity {

    private ActivityAnalysisBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_analysis);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        context = this;

        fetchAllData();



        animate(binding.constraintLayout2,binding.constraintLayout3,binding.constraintLayout4,binding.clweekanalysis);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.tvglobal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.tvindia.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                binding.tvindia.setTextColor(getResources().getColor(R.color.pink));
                binding.tvglobal.setBackground(getResources().getDrawable(R.drawable.btn_tab));
                binding.tvglobal.setTextColor(getResources().getColor(R.color.white));

            }
        });


        binding.tvindia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.tvglobal.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                binding.tvglobal.setTextColor(getResources().getColor(R.color.pink));
                binding.tvindia.setBackground(getResources().getDrawable(R.drawable.btn_tab));
                binding.tvindia.setTextColor(getResources().getColor(R.color.white));

            }
        });


    }

    private void fetchAllData() {

        Call<DataResponse> call = SoleInstance.getApiServiceInstance().getAllData();

        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {

                if (response != null) {
                    if (response.body() != null) {

                     String lastUpdateTime  = response.body().getStatewise().get(0).getLastupdatedtime().trim();

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
                        setBarGraph(graphlist);

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

    private void setBarGraph(List<CasesTimeSeries> graphlist) {

        for(int i=0; i<graphlist.size();i++)
        {

            if(graphlist.size()!=7)
            {
                graphlist.remove(i);
                i--;
            }

        }

        List<String> dates = new ArrayList<>();
        List<Float> dateValues = new ArrayList<>();
        for(int i= 0; i<graphlist.size(); i++)
        {
            dates.add(graphlist.get(i).getDate());
            dateValues.add(Float.parseFloat(graphlist.get(i).getDailyconfirmed()));
        }


        ArrayList<BarEntry> barEntries = new ArrayList<>();
        //     ArrayList<Entry> lineEntry = new ArrayList<>();


        for(int i = 0; i<dateValues.size(); i++)
        {
            if(dateValues.get(i)!=0.0)
            {
                barEntries.add(new BarEntry(dateValues.get(i),i));
                //     lineEntry.add(new Entry(dateValues.get(i),i));
            }

        }




        BarDataSet dataSet1 = new BarDataSet(barEntries,"");

        dataSet1.setBarSpacePercent(80f);
        dataSet1.setColor(getResources().getColor(R.color.new_red));
        dataSet1.setValueTextColor(getResources().getColor(R.color.new_red));


        XAxis xAxis = binding.barGraph.getXAxis();
      //  xAxis.setDrawGridLines(false);
        xAxis.setTextColor(getResources().getColor(R.color.new_red));
        YAxis yAxis = binding.barGraph.getAxisLeft();
     //   yAxis.setDrawGridLines(false);
        yAxis.setTextColor(getResources().getColor(R.color.background));

        binding.barGraph.getAxisRight().setTextColor(getResources().getColor(R.color.background));


        //   LineDataSet dataSet2 = new LineDataSet(lineEntry,"Dates");

        BarData barData = new BarData(dates,dataSet1);


        //   LineData lineData = new LineData(dates,dataSet2);


        Legend legend = binding.barGraph.getLegend();
        legend.setEnabled(false);

     /*   binding.barGraph.setVisibleXRangeMaximum(10);
        binding.barGraph.moveViewToX(30);
        binding.barGraph.setMinimumWidth(5000);*/
        binding.barGraph.setTouchEnabled(true);
        binding.barGraph.setDragEnabled(true);
        binding.barGraph.setDrawBorders(true);

        binding.barGraph.getAxisRight().setDrawGridLines(false);
        binding.barGraph.getAxisLeft().setDrawGridLines(false);
        binding.barGraph.getXAxis().setDrawGridLines(false);

      /*  BarChart myBarChart=binding.barGraph;
        CustomBarChartRender roundedBarChartRenderer= new CustomBarChartRender(myBarChart,myBarChart.getAnimator(),myBarChart.getViewPortHandler());
        roundedBarChartRenderer.setmRadius(20f);
        myBarChart.setRenderer(roundedBarChartRenderer);*/


     //   binding.barGraph.setBackgroundColor(getResources().getColor(R.color.background));
     //   binding.barGraph.setBorderColor(getResources().getColor(R.color.background));
/*        binding.barGraph.setDrawGridBackground(false);
        binding.barGraph.setDrawBorders(false);
        binding.barGraph.setDrawMarkerViews(false);
      //  binding.barGraph.setDrawValueAboveBar(false);*/
        binding.barGraph.setGridBackgroundColor(getResources().getColor(R.color.background));

        binding.barGraph.animateX(1000);
        binding.barGraph.setDescription("");
     //   binding.barGraph.setBackgroundColor(Color.TRANSPARENT);
        binding.barGraph.setExtraOffsets(10,10,10,5);
        binding.barGraph.setData(barData);
        binding.barGraph.invalidate();

    }

    private void setStatesData(List<Statewise> statewiseList) {


        binding.statename1.setText(statewiseList.get(1).getState());
        binding.statcount1.setText(String.valueOf(statewiseList.get(1).getConfirmed()));
        binding.statename2.setText(statewiseList.get(2).getState());
        binding.statcount2.setText(String.valueOf(statewiseList.get(2).getConfirmed()));
        binding.statename3.setText(statewiseList.get(3).getState());
        binding.statcount3.setText(String.valueOf(statewiseList.get(3).getConfirmed()));
        binding.statename4.setText(statewiseList.get(4).getState());
        binding.statcount4.setText(String.valueOf(statewiseList.get(4).getConfirmed()));
    }

    private void setPieData(String confirmed, String active, String recovered, String death) {


        binding.tvconfirmcount.setText(confirmed);
        binding.tvactivecount.setText(active);
        binding.tvRecoveredcount.setText(recovered);
        binding.tvdesecedcount.setText(death);

        ArrayList<Entry> pieValues = new ArrayList<>();

        pieValues.add(new Entry(Float.parseFloat(confirmed),0));
        pieValues.add(new Entry(Float.parseFloat(active),1));
        pieValues.add(new Entry(Float.parseFloat(recovered),2));
        pieValues.add(new Entry(Float.parseFloat(death),3));


        PieDataSet dataSet = new PieDataSet(pieValues, "");
        dataSet.setDrawValues(false);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.dark_red));
        colors.add(getResources().getColor(R.color.dark_blue));
        colors.add(getResources().getColor(R.color.dark_green));
        colors.add(getResources().getColor(R.color.dark_grey));

        dataSet.setColors(colors);
        dataSet.setSliceSpace(2f);



        ArrayList<String> dataList = new ArrayList<>();
        dataList.add("");
        dataList.add("");
        dataList.add("");
        dataList.add("");

        Legend legend = binding.pieChart.getLegend();
        legend.setEnabled(false);
        PieData data = new PieData(dataList,dataSet);
        binding.pieChart.animateX(1000);
        binding.pieChart.setData(data);
        binding.pieChart.setDescription("");
        binding.pieChart.setBackgroundColor(Color.TRANSPARENT);
        binding.pieChart.setExtraOffsets(5,10,5,5);
        binding.pieChart.setDrawHoleEnabled(true);
        binding.pieChart.setHoleColor(getResources().getColor(R.color.background));
        binding.pieChart.invalidate();

    }

    private void animate(ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout clweekanalysis) {

        final PropertyAction fabAction1 = PropertyAction.newPropertyAction(constraintLayout2).scaleX(0).scaleY(0).duration(400).interpolator(new AccelerateDecelerateInterpolator()).build();
        final PropertyAction fabAction2 = PropertyAction.newPropertyAction(constraintLayout3).scaleX(0).scaleY(0).duration(400).interpolator(new AccelerateDecelerateInterpolator()).build();
        final PropertyAction fabAction3 = PropertyAction.newPropertyAction(constraintLayout4).scaleX(0).scaleY(0).duration(400).interpolator(new AccelerateDecelerateInterpolator()).build();
        final PropertyAction fabAction4 = PropertyAction.newPropertyAction(clweekanalysis).scaleX(0).scaleY(0).duration(400).interpolator(new AccelerateDecelerateInterpolator()).build();

        Player.init().
                animate(fabAction1).
                then().
                animate(fabAction2).
                then().
                animate(fabAction3).
                then().
                animate(fabAction4).
                play();
    }
}
