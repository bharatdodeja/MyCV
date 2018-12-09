package com.bharatdodeja.mycv.detail.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bharatdodeja.mycv.R
import com.bharatdodeja.mycv.detail.CVDetailContract

class CVDetailActivity : AppCompatActivity(), CVDetailContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cv_detail)
    }
}