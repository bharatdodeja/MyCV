package com.bharatdodeja.mycv.detail.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bharatdodeja.mycv.R
import com.bharatdodeja.mycv.detail.CVDetailContract
import com.bharatdodeja.mycv.detail.di.Injection
import com.bharatdodeja.mycv.detail.model.data.CVDataModel
import com.bharatdodeja.mycv.framework.util.action
import com.bharatdodeja.mycv.framework.util.snackBar
import kotlinx.android.synthetic.main.activity_cv_detail.*

class CVDetailActivity : AppCompatActivity(), CVDetailContract.View {

    //hardcoded user id, later it can be user input to make it dynamic
    private val userId: String = "thomasdavis"

    private lateinit var presenter: CVDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cv_detail)

        presenter = Injection.providePresenter(this)
        presenter.getCVDetail(userId)

        swipeRefreshLayout.setOnRefreshListener { presenter.getCVDetail(userId) }
    }

    override fun showCVDetail(cvDataModel: CVDataModel) {
        //TODO Show CV detail in proper format on UI
        txtCVDetail.text = cvDataModel.toString()
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
        txtCVDetail.text = ""
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showError(error: Throwable) {
        txtCVDetail.text = error.toString()
        hideLoading()
    }

    override fun showNoNetworkError() {
        txtCVDetail.snackBar(R.string.network_error) {
            action("Retry") { presenter.getCVDetail(userId) }
        }
    }
}