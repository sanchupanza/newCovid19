package com.sanchit.covid19tracker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sanchit.covid19tracker.Adapters.AllCountriesAdapter;
import com.sanchit.covid19tracker.Adapters.CountriesAdapter;
import com.sanchit.covid19tracker.R;
import com.sanchit.covid19tracker.Response.Countries.Country;
import com.sanchit.covid19tracker.Utils.TinyDB;
import com.sanchit.covid19tracker.databinding.ActivityCountryBinding;

import java.util.ArrayList;
import java.util.List;

public class CountryActivity extends AppCompatActivity implements SearchView.OnQueryTextListener  {


    private TinyDB tinyDB;
    private Context context;
    private ActivityCountryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_country);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        context = this;
        tinyDB = new TinyDB(context);
        SearchView searchView= (SearchView) findViewById(R.id.searchView);
        int id = searchView.getContext()
                .getResources()
                .getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) searchView.findViewById(id);
        textView.setTextColor(Color.WHITE);
        textView.setHintTextColor(Color.WHITE);


        Country[] countryArray = new Gson().fromJson(tinyDB.getString("cList"),Country[].class);
        List<Country> newCountryList = new ArrayList<>();

        for(Country c : countryArray)
        {
            newCountryList.add(c);
        }




        binding.rvcountry.setLayoutManager(new GridLayoutManager(this,3));
        AllCountriesAdapter adapter = new AllCountriesAdapter(context,newCountryList);
        binding.rvcountry.setAdapter(adapter);


        binding.searchView.setIconified(false);
        binding.searchView.onActionViewExpanded();

        binding.searchView.setFocusable(true);
        binding.searchView.requestFocusFromTouch();
        binding.searchView.clearFocus();
        binding.searchView.setOnQueryTextListener(CountryActivity.this);

        updateSearchList(text -> {

            List<Country> temp = new ArrayList<>();

            for (Country c : newCountryList) {
                if (c.getCountry().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(c);
                }
            }

            adapter.updateList(temp);
        });


        binding.btnBackCountry.setOnClickListener(view -> finish());






    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        searchList.search(s);
        return false;
    }

    SearchList searchList;


    public void updateSearchList(SearchList listener) {
        searchList = listener;

    }

    public interface SearchList {
        void search(String text);


    }
}
