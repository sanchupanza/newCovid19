package com.sanchit.covid19tracker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.WindowManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sanchit.covid19tracker.R;
import com.sanchit.covid19tracker.databinding.ActivityResourcesDetailBinding;
import com.sanchit.covid19tracker.databinding.SortResoucesDialogBinding;

public class ResourcesDetailActivity extends AppCompatActivity {


    ActivityResourcesDetailBinding binding;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_resources_detail);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        context=this;



        binding.btnBackResourcesDetail.setOnClickListener(view -> finish());

        binding.btnFilter.setOnClickListener(view -> showSortDialog());


    }

    private void showSortDialog() {
        SortResoucesDialogBinding dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.sort_resouces_dialog,null,false);


        MaterialDialog filterDialog = new MaterialDialog.Builder(context)
                .autoDismiss(true)
                .cancelable(true)
                .canceledOnTouchOutside(true)
                .customView(dialogBinding.getRoot(), true)
                .show();

        dialogBinding.tvCancel.setOnClickListener(view -> filterDialog.dismiss());


    }
}
