package com.sanchit.covid19tracker.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanchit.covid19tracker.R;
import com.tbuonomo.creativeviewpager.adapter.CreativePagerAdapter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MyCustomAdapter implements CreativePagerAdapter {

    private Context context;

    public MyCustomAdapter(Context context) {
        this.context = context;
    }



    @Override
    public int getCount() {
        return NatureItem.values().length;
    }


    @NotNull
    @Override
    public View instantiateContentItem(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup viewGroup, int i) {

        View view = layoutInflater.inflate(R.layout.item_creative_header_profile, viewGroup, false);

        ImageView imageView = (ImageView)view.findViewById(R.id.itemCreativeImage);


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
            imageView.setImageDrawable(context.getResources().getDrawable(NatureItem.values()[i].getUserDrawable()));
        }


        return view;

    }

    @SuppressLint("StringFormatInvalid")
    @NotNull
    @Override
    public View instantiateHeaderItem(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup viewGroup, int i) {


        View view = layoutInflater.inflate(R.layout.item_creative_content_nature, viewGroup, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.itemCreativeNatureImage);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
            imageView.setImageDrawable(context.getResources().getDrawable(NatureItem.values()[i].getNatureDrawable()));
        }
        return view;
    }

    @Override
    public boolean isUpdatingBackgroundColor() {
        return true;
    }

    @Nullable
    @Override
    public Bitmap requestBitmapAtPosition(int i) {
        return BitmapFactory.decodeResource(context.getResources(),NatureItem.values()[i].getNatureDrawable());

    }
}
