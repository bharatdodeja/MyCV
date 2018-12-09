package com.bharatdodeja.mycv.detail.presenter

import com.bharatdodeja.mycv.detail.CVDetailContract
import com.bharatdodeja.mycv.detail.framework.BasePresenter
import com.bharatdodeja.mycv.detail.framework.NetworkUtils
import com.bharatdodeja.mycv.detail.framework.rx.DisposableManager
import com.bharatdodeja.mycv.detail.model.repository.CVRepository

/**
 * Presenter to communicate with CV model layer and fetch data
 */
class CVDetailPresenter(
    private val view: CVDetailContract.View, private val repository: CVRepository,
    disposableManager: DisposableManager, private val networkUtils: NetworkUtils
) : BasePresenter<CVDetailContract.View>(view, disposableManager), CVDetailContract.Presenter {

    //hardcoded user id, later it can be user input to make it dynamic
    private val userId: String = "thomasdavis"

    override fun getCVDetail() {
        if (networkUtils.isConnected()) {
            addDisposable(
                repository.getCV(userId)
                    .doOnSubscribe { view.showLoading() }
                    .doOnTerminate { view.hideLoading() }
                    .subscribe(view::showCVDetail, view::showError)
            )
        } else {
          view.showNoNetworkError()
        }
    }

    override fun refreshCVDetail() {
        getCVDetail()
    }
}