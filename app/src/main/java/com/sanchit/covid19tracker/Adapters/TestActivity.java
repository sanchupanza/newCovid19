package com.sanchit.covid19tracker.Adapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;

import com.sanchit.covid19tracker.R;
import com.sanchit.covid19tracker.databinding.ActivityTestBinding;

public class TestActivity extends AppCompatActivity {

    private ActivityTestBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_test);
        context = this;

        MyCustomAdapter adapter = new MyCustomAdapter(context);
        binding.creativeViewPagerView.setCreativeViewPagerAdapter(adapter);

    }
}
