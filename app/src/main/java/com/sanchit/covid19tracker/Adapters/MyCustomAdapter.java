package com.sanchit.covid19tracker.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.sanchit.covid19tracker.R;
import com.sanchit.covid19tracker.Response.AllData.Statewise;
import com.sanchit.covid19tracker.Response.Districswise.DistrictDatum;
import com.sanchit.covid19tracker.Response.Districswise.DistrictWiseResponse;
import com.tbuonomo.creativeviewpager.adapter.CreativePagerAdapter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyCustomAdapter implements CreativePagerAdapter {

    private Context context;
    private List<CustomePojo> customePojoList;
    private List<DistrictWiseResponse> districsList;
    private List<Statewise> statewiseList;

    public MyCustomAdapter(Context context, List<CustomePojo> customePojoList, List<DistrictWiseResponse> districsList, List<Statewise> statewiseList) {
        this.context = context;
        this.customePojoList = customePojoList;
        this.districsList = districsList;
        this.statewiseList = statewiseList;
    }



    @Override
    public int getCount() {
        return statewiseList.size();
    }


    @NotNull
    @Override
    public View instantiateContentItem(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup viewGroup, int i) {

        View view = layoutInflater.inflate(R.layout.item_creative_header_profile, viewGroup, false);

        TextView district = (TextView) view.findViewById(R.id.tv_img);

        district.setText(String.valueOf(statewiseList.get(i).getStatecode()));


        return view;

    }

    @SuppressLint("StringFormatInvalid")
    @NotNull
    @Override
    public View instantiateHeaderItem(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup viewGroup, int i) {


        View view = layoutInflater.inflate(R.layout.item_creative_content_nature, viewGroup, false);

        PieChart pieChart = (PieChart) view.findViewById(R.id.pieChart);
        RecyclerView districts = (RecyclerView) view.findViewById(R.id.rv_districs);
        TextView statename = (TextView) view.findViewById(R.id.state_name);
        TextView Stateconfirmcount = (TextView) view.findViewById(R.id.tvStateconfirmcount);
        TextView Stateactivecount = (TextView) view.findViewById(R.id.tvStateactivecount);
        TextView Staterecoveredcount = (TextView) view.findViewById(R.id.tvStaterecoveredcount);
        TextView Statedesecedcount = (TextView) view.findViewById(R.id.tvStatedesecedcount);


        Stateconfirmcount.setText(String.valueOf(statewiseList.get(i).getConfirmed()));
        Stateactivecount.setText(String.valueOf(statewiseList.get(i).getActive()));
        Staterecoveredcount.setText(String.valueOf(statewiseList.get(i).getRecovered()));
        Statedesecedcount.setText(String.valueOf(statewiseList.get(i).getDeaths()));



        statename.setText(statewiseList.get(i).getState());

        setPieData(pieChart,i);
        setRecycleviewData(districts,i);

     //   ImageView imageView = (ImageView) view.findViewById(R.id.itemCreativeNatureImage);

    //    TextView textView  = (TextView) view.findViewById(R.id.tv_state);

    //    textView.setText(customePojoList.get(i).stateName);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
         //   imageView.setImageDrawable(context.getResources().getDrawable(NatureItem.values()[i].getNatureDrawable()));
        //    imageView.setImageResource(customePojoList.get(i).nature);
        }
        return view;
    }

    private void setRecycleviewData(RecyclerView districts, int i) {


        String stateName = statewiseList.get(i).getState();

        List<DistrictDatum> selectedDistrictList = new ArrayList<>();
        for (int j = 0; j < districsList.size(); j++) {
            if (stateName.equals(districsList.get(j).getState())) {
                selectedDistrictList = districsList.get(j).getDistrictData();
                break;
            }
        }



        districts.setLayoutManager(new LinearLayoutManager(context));
        Collections.sort(selectedDistrictList,DistrictDatum::compareTo);
        Collections.reverse(selectedDistrictList);
        DistrictAdapter adapter = new DistrictAdapter(context,selectedDistrictList);
        districts.setItemViewCacheSize(selectedDistrictList.size());
        districts.setAdapter(adapter);

    }

    private void setPieData(PieChart pieChart, int i) {

        ArrayList<Entry> pieValues = new ArrayList<>();



        pieValues.add(new Entry(Float.valueOf(statewiseList.get(i).getConfirmed()),0));
        pieValues.add(new Entry(Float.valueOf(statewiseList.get(i).getActive()),1));
        pieValues.add(new Entry(Float.valueOf(statewiseList.get(i).getRecovered()),2));
        pieValues.add(new Entry(Float.valueOf(statewiseList.get(i).getDeaths()),3));


      /*  pieValues.add(new Entry(Float.parseFloat("34"),0));
        pieValues.add(new Entry(Float.parseFloat("67"),1));
        pieValues.add(new Entry(Float.parseFloat("87"),2));
        pieValues.add(new Entry(Float.parseFloat("33"),3));*/



        PieDataSet dataSet = new PieDataSet(pieValues, "");
        dataSet.setDrawValues(false);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(context.getResources().getColor(R.color.dark_red));
        colors.add(context.getResources().getColor(R.color.dark_blue));
        colors.add(context.getResources().getColor(R.color.dark_green));
        colors.add(context.getResources().getColor(R.color.dark_grey));

        dataSet.setColors(colors);

        if(statewiseList.get(i).getState().equals("Kerala"))
        {
            dataSet.setSliceSpace(0.8f);
        }else
        {
            dataSet.setSliceSpace(1f);
        }



        ArrayList<String> dataList = new ArrayList<>();
        dataList.add("");
        dataList.add("");
        dataList.add("");
        dataList.add("");

        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);


        PieData data = new PieData(dataList,dataSet);
        pieChart.animateX(1000);
        pieChart.setData(data);
        pieChart.setDescription("");
        pieChart.setBackgroundColor(Color.TRANSPARENT);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(context.getResources().getColor(R.color.white));
        pieChart.invalidate();



    }

    @Override
    public boolean isUpdatingBackgroundColor() {
        return true;
    }

    @Nullable
    @Override
    public Bitmap requestBitmapAtPosition(int i) {
       // return BitmapFactory.decodeResource(context.getResources(),NatureItem.values()[i].getNatureDrawable());
        return BitmapFactory.decodeResource(context.getResources(),customePojoList.get(0).nature);

    }
}
