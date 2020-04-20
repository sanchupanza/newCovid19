package com.sanchit.covid19tracker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.eftimoff.androidplayer.Player;
import com.eftimoff.androidplayer.actions.property.PropertyAction;
import com.sanchit.covid19tracker.R;
import com.sanchit.covid19tracker.databinding.ActivityAnalysisBinding;

public class AnalysisActivity extends AppCompatActivity {

    ActivityAnalysisBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_analysis);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



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
