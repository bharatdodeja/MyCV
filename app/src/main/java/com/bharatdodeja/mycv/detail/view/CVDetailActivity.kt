package com.bharatdodeja.mycv.detail.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bharatdodeja.mycv.R
import com.bharatdodeja.mycv.detail.CVDetailContract
import com.bharatdodeja.mycv.detail.di.Injection
import com.bharatdodeja.mycv.detail.model.data.CVDataModel

class CVDetailActivity : AppCompatActivity(), CVDetailContract.View {

    lateinit var presenter: CVDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cv_detail)

        presenter = Injection.providePresenter(this)
        presenter.getCVDetail()
    }

    override fun showCVDetail(cvDataModel: CVDataModel) {
        println(cvDataModel.toString())
        //TODO
    }

    override fun showLoading() {
        //TODO
    }

    override fun hideLoading() {
        //TODO
    }

    override fun showError(error: Throwable) {
        println(error.localizedMessage)
        //TODO
    }

    override fun showNoNetworkError() {
        //TODO
    }
}