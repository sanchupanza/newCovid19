package com.sanchit.covid19tracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.blongho.country_data.World;
import com.sanchit.covid19tracker.R;
import com.sanchit.covid19tracker.Response.Countries.Country;
import com.sanchit.covid19tracker.databinding.SingleContryItemBinding;

import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder> {


    private List<Country> countriesList;
    private Context context;


    public CountriesAdapter(List<Country> countriesList, Context context) {

        this.context = context;
        this.countriesList = countriesList;
    }


    @NonNull
    @Override
    public CountriesAdapter.CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SingleContryItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.single_contry_item,parent,false);

        return new CountriesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesAdapter.CountriesViewHolder holder, int position) {

        World.init(context);

        if(countriesList.get(position).getCountry().toLowerCase().trim().equals("United States of America"))
        {
            int flag = World.getFlagOf("usa");
            holder.binding.flag.setImageResource(flag);
        }else
        {
            int flag = World.getFlagOf(countriesList.get(position).getCountry().toLowerCase().trim());
            holder.binding.flag.setImageResource(flag);

        }

        holder.binding.countryName.setText(countriesList.get(position).getCountry());
        holder.binding.totalCount.setText(String.valueOf(countriesList.get(position).getTotalConfirmed()));
    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    public class CountriesViewHolder extends RecyclerView.ViewHolder {


        private SingleContryItemBinding binding;
        public CountriesViewHolder(@NonNull SingleContryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
