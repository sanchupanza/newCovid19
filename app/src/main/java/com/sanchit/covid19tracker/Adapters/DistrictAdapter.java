package com.sanchit.covid19tracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sanchit.covid19tracker.R;
import com.sanchit.covid19tracker.Response.Districswise.DistrictDatum;
import com.sanchit.covid19tracker.databinding.SingleDistrictLayoutBinding;

import java.util.List;

public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.MyViewHolder> {

    private List<DistrictDatum> list;
    private Context context;
    public DistrictAdapter(Context context, List<DistrictDatum> selectedDistrictList) {
        this.context = context;
        this.list = selectedDistrictList;
    }

    @NonNull
    @Override
    public DistrictAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        SingleDistrictLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.single_district_layout,parent,false);
        return new DistrictAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DistrictAdapter.MyViewHolder holder, int position) {
        holder.binding.tvCityName.setText(list.get(position).getDistrict());
        holder.binding.tvConfirmCount.setText(String.valueOf(list.get(position).getConfirmed()));
        if(list.get(position).getDelta().getConfirmed() !=0)
        {
            holder.binding.tvDeltaCount.setText("[+"+list.get(position).getDelta().getConfirmed()+"]");
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SingleDistrictLayoutBinding binding;

        public MyViewHolder(@NonNull SingleDistrictLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
