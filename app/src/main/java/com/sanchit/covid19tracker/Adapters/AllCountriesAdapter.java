package com.sanchit.covid19tracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.blongho.country_data.World;
import com.sanchit.covid19tracker.R;
import com.sanchit.covid19tracker.Response.Countries.Country;
import com.sanchit.covid19tracker.databinding.CountryDetailedItemBinding;

import java.text.DecimalFormat;
import java.util.List;

public class AllCountriesAdapter extends RecyclerView.Adapter<AllCountriesAdapter.MyViewHolder> {

    private Context context;
    private List<Country> countriesList;

    public AllCountriesAdapter(Context context, List<Country> newCountryList) {
        this.context = context;
        this.countriesList = newCountryList;
    }

    public void updateList(List<Country> newCountryList) {
        this.countriesList = newCountryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AllCountriesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CountryDetailedItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.country_detailed_item,parent,false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCountriesAdapter.MyViewHolder holder, int position) {

        if(countriesList.get(position).getCountry().trim().equals("United States of America"))
        {
            int flag = World.getFlagOf("United States".toLowerCase());
            holder.binding.ivFlag.setImageResource(flag);
            holder.binding.countryName.setText("USA");
        }else
        if(countriesList.get(position).getCountry().trim().equals("Iran, Islamic Republic of"))
        {
            int flag = World.getFlagOf("Iran".toLowerCase());
            holder.binding.ivFlag.setImageResource(flag);
            holder.binding.countryName.setText("Iran");
        }else
        if(countriesList.get(position).getCountry().trim().equals("Russian Federation"))
        {
            int flag = World.getFlagOf("Russia".toLowerCase());
            holder.binding.ivFlag.setImageResource(flag);
            holder.binding.countryName.setText("Russia");
        }else
        if(countriesList.get(position).getCountry().trim().equals("Korea (North)"))
        {
            int flag = World.getFlagOf("North Korea".toLowerCase());
            holder.binding.ivFlag.setImageResource(flag);
            holder.binding.countryName.setText("North Korea");
        }else
        if(countriesList.get(position).getCountry().trim().equals("Korea (South)"))
        {
            int flag = World.getFlagOf("South Korea".toLowerCase());
            holder.binding.ivFlag.setImageResource(flag);
            holder.binding.countryName.setText("South Korea");
        }else
        if(countriesList.get(position).getCountry().trim().equals("Czech Republic"))
        {
            int flag = World.getFlagOf("Czechia".toLowerCase());
            holder.binding.ivFlag.setImageResource(flag);
            holder.binding.countryName.setText("Czechia");
        }else
        {
            int flag = World.getFlagOf(countriesList.get(position).getCountry().toLowerCase().trim());
            holder.binding.ivFlag.setImageResource(flag);
            holder.binding.countryName.setText(countriesList.get(position).getCountry());
        }


        DecimalFormat formatter = new DecimalFormat("#,##,###");
        String conformedCases = formatter.format(countriesList.get(position).getTotalConfirmed());
        String newConfirmded = formatter.format(countriesList.get(position).getNewConfirmed());

        holder.binding.tvcountrytotalconfirmed.setText(conformedCases);
        holder.binding.tvcountrytotaldeath.setText("+"+newConfirmded);


    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CountryDetailedItemBinding binding;
        public MyViewHolder(@NonNull CountryDetailedItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
