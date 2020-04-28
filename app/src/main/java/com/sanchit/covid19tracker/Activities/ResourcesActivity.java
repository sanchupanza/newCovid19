package com.sanchit.covid19tracker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.eftimoff.androidplayer.Player;
import com.eftimoff.androidplayer.actions.property.PropertyAction;
import com.sanchit.covid19tracker.R;
import com.sanchit.covid19tracker.databinding.ActivityResourcesBinding;

public class ResourcesActivity extends AppCompatActivity {

    ActivityResourcesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_resources);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        binding.btnBackResources.setOnClickListener(view -> finish());

        //animate(binding.layoutHome,binding.layoutDelivery,binding.layoutTestingLab,binding.layoutFundRaisor,binding.layoutFreeFood,binding.layoutGovernmentHelpline,binding.layoutmentalsupport);
    }

    private void animate(ConstraintLayout layoutHome, ConstraintLayout layoutDelivery, ConstraintLayout layoutTestingLab, ConstraintLayout layoutFundRaisor, ConstraintLayout layoutFreeFood, ConstraintLayout layoutGovernmentHelpline, ConstraintLayout layoutmentalsupport) {

        final PropertyAction layAction1 = PropertyAction.newPropertyAction(layoutHome).scaleX(0).scaleY(0).duration(400).interpolator(new AccelerateDecelerateInterpolator()).build();
        final PropertyAction layAction2 = PropertyAction.newPropertyAction(layoutDelivery).scaleX(0).scaleY(0).duration(400).interpolator(new AccelerateDecelerateInterpolator()).build();
        final PropertyAction layAction3 = PropertyAction.newPropertyAction(layoutTestingLab).scaleX(0).scaleY(0).duration(400).interpolator(new AccelerateDecelerateInterpolator()).build();
        final PropertyAction layAction4 = PropertyAction.newPropertyAction(layoutFundRaisor).scaleX(0).scaleY(0).duration(400).interpolator(new AccelerateDecelerateInterpolator()).build();
        final PropertyAction layAction5 = PropertyAction.newPropertyAction(layoutFreeFood).scaleX(0).scaleY(0).duration(400).interpolator(new AccelerateDecelerateInterpolator()).build();
        final PropertyAction layAction6 = PropertyAction.newPropertyAction(layoutGovernmentHelpline).scaleX(0).scaleY(0).duration(400).interpolator(new AccelerateDecelerateInterpolator()).build();
        final PropertyAction layAction7 = PropertyAction.newPropertyAction(layoutmentalsupport).scaleX(0).scaleY(0).duration(400).interpolator(new AccelerateDecelerateInterpolator()).build();

        Player.init().
                animate(layAction1).
                then().
                animate(layAction2).
                then().
                animate(layAction3).
                then().
                animate(layAction4).
                then().
                animate(layAction5).
                then().
                animate(layAction6).
                then().
                animate(layAction7).
                play();


    }
}
