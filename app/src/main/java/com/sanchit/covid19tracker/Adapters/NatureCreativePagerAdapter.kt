package com.sanchit.covid19tracker.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.sanchit.covid19tracker.R
import com.tbuonomo.creativeviewpager.adapter.CreativePagerAdapter

class NatureCreativePagerAdapter (val context : Context) : CreativePagerAdapter {

    @SuppressLint("StringFormatInvalid")
    override fun instantiateHeaderItem(inflater: LayoutInflater, container: ViewGroup, position: Int): View {

        val headerRoot = inflater.inflate(R.layout.item_creative_content_nature, container, false)

        // Bind the views
      //  val title: TextView = headerRoot.findViewById(R.id.itemCreativeNatureTitle)
    //    val image: ImageView = headerRoot.findViewById(R.id.itemCreativeNatureImage)

//        title.text = context.getString(R.string.item_nature_title, position)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    //        image.setImageDrawable(context.getDrawable(NatureItem.values()[position].natureDrawable))
        }

        return headerRoot
    }

    override fun instantiateContentItem(inflater: LayoutInflater, container: ViewGroup, position: Int): View {

        val contentRoot = inflater.inflate(R.layout.item_creative_header_profile, container,
                false)

        // Bind the views
        val imageView = contentRoot.findViewById<ImageView>(R.id.itemCreativeImage)

        imageView.setImageDrawable(ContextCompat.getDrawable(context, NatureItem.values()[position].userDrawable))
        return contentRoot
    }

    override fun getCount(): Int {
        return NatureItem.values().size

    }

    override fun isUpdatingBackgroundColor(): Boolean {
        return true
    }

    override fun requestBitmapAtPosition(position: Int): Bitmap? {
        // Return the bitmap used for the position
        return BitmapFactory.decodeResource(context.resources,
                NatureItem.values()[position].natureDrawable)
    }




}