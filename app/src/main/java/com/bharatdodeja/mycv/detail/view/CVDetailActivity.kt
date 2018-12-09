package com.bharatdodeja.mycv.detail.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bharatdodeja.mycv.R
import com.bharatdodeja.mycv.detail.CVDetailContract
import com.bharatdodeja.mycv.detail.model.data.CVDataModel

class CVDetailActivity : AppCompatActivity(), CVDetailContract.View {

    override fun showNoNetworkError() {
        //TODO
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cv_detail)
    }

    override fun showCVDetail(cvDataModel: CVDataModel) {
        //TODO
    }

    override fun showLoading() {
        //TODO
    }

    override fun hideLoading() {
        //TODO
    }

    override fun showError(error: Throwable) {
        //TODO
    }
}