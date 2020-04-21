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
import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyCustomAdapter implements CreativePagerAdapter {

    private Context context;
    private List<CustomePojo> customePojoList;

    public MyCustomAdapter(Context context, List<CustomePojo> customePojoList) {
        this.context = context;
        this.customePojoList = customePojoList;
    }



    @Override
    public int getCount() {
        return customePojoList.size();
    }


    @NotNull
    @Override
    public View instantiateContentItem(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup viewGroup, int i) {

        View view = layoutInflater.inflate(R.layout.item_creative_header_profile, viewGroup, false);

        CircleImageView imageView = (CircleImageView) view.findViewById(R.id.itemCreativeImage);


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
          //  imageView.setImageDrawable(context.getResources().getDrawable(NatureItem.values()[i].getUserDrawable()));
            imageView.setImageResource(customePojoList.get(i).user);
        }


        return view;

    }

    @SuppressLint("StringFormatInvalid")
    @NotNull
    @Override
    public View instantiateHeaderItem(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup viewGroup, int i) {


        View view = layoutInflater.inflate(R.layout.item_creative_content_nature, viewGroup, false);

     //   ImageView imageView = (ImageView) view.findViewById(R.id.itemCreativeNatureImage);

    //    TextView textView  = (TextView) view.findViewById(R.id.tv_state);

    //    textView.setText(customePojoList.get(i).stateName);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
         //   imageView.setImageDrawable(context.getResources().getDrawable(NatureItem.values()[i].getNatureDrawable()));
    //        imageView.setImageResource(customePojoList.get(i).nature);
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
       // return BitmapFactory.decodeResource(context.getResources(),NatureItem.values()[i].getNatureDrawable());
        return BitmapFactory.decodeResource(context.getResources(),customePojoList.get(i).nature);

    }
}
