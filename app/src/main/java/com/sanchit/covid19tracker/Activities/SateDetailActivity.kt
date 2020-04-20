package com.sanchit.covid19tracker.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanchit.covid19tracker.Adapters.NatureCreativePagerAdapter
import com.sanchit.covid19tracker.R
import kotlinx.android.synthetic.main.activity_sate_detail.*

class SateDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sate_detail)
        creativeViewPagerView.setCreativeViewPagerAdapter(NatureCreativePagerAdapter(this))


    }
}
