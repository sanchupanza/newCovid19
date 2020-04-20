package com.sanchit.covid19tracker.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tbuonomo.creativeviewpager.adapter.CreativePagerAdapter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StateAdaptor implements CreativePagerAdapter {

    Context context;

    public StateAdaptor(Context context) {
        this.context = context;
    }

    @NotNull
    @Override
    public View instantiateHeaderItem(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup viewGroup, int i) {
        return null;
    }


    @NotNull
    @Override
    public View instantiateContentItem(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }


    @Override
    public boolean isUpdatingBackgroundColor() {
        return true;
    }

    @Nullable
    @Override
    public Bitmap requestBitmapAtPosition(int i) {
        return null;
    }
}
