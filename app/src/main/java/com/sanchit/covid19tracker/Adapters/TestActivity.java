package com.sanchit.covid19tracker.Adapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.sanchit.covid19tracker.R;
import com.sanchit.covid19tracker.databinding.ActivityTestBinding;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private ActivityTestBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_test);
        context = this;



        TextDrawable drawable = TextDrawable.builder().buildRect("A", Color.RED);


        List<CustomePojo> customePojoList = new ArrayList<>();
        customePojoList.add(new CustomePojo(R.drawable.user_000, R.drawable.nature_001,"Maharashta"));
        customePojoList.add(new CustomePojo(R.drawable.user_001, R.drawable.nature_001,"Delhi"));
        customePojoList.add(new CustomePojo(R.drawable.user_002, R.drawable.nature_002, "Punjab"));
        customePojoList.add(new CustomePojo(R.drawable.user_003, R.drawable.nature_003,"Karnataka"));
        customePojoList.add(new CustomePojo(R.drawable.user_004, R.drawable.nature_004, "Andhra Pradesh"));
        customePojoList.add(new CustomePojo(R.drawable.user_005, R.drawable.nature_005,"Goa"));
        customePojoList.add(new CustomePojo(R.drawable.user_006, R.drawable.nature_006,"Rajasthan"));
        customePojoList.add(new CustomePojo(R.drawable.user_007, R.drawable.nature_007,"Gujarat"));


        MyCustomAdapter adapter = new MyCustomAdapter(context,customePojoList);
        binding.creativeViewPagerView.setCreativeViewPagerAdapter(adapter);

    }
}
