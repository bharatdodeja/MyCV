package com.bharatdodeja.mycv.detail.di

import com.bharatdodeja.mycv.detail.CVDetailContract
import com.bharatdodeja.mycv.detail.model.repository.CVRepository
import com.bharatdodeja.mycv.detail.model.repository.datasource.CVFakeRemoteDataSource
import com.bharatdodeja.mycv.detail.presenter.CVDetailPresenter
import com.bharatdodeja.mycv.detail.view.CVDetailActivity
import com.bharatdodeja.mycv.detail.view.CVDetailActivityTest
import com.bharatdodeja.mycv.framework.rx.DisposableManager
import com.bharatdodeja.mycv.framework.rx.SchedulerProvider
import com.bharatdodeja.mycv.framework.util.NetworkUtils

/**
 * Provides dependencies for [CVDetailActivityTest]
 */
object Injection {
    fun providePresenter(activity: CVDetailActivity): CVDetailContract.Presenter {

        val remoteDataSource = CVFakeRemoteDataSource()
        val repository = CVRepository(remoteDataSource)

        val disposableManager = DisposableManager()
        val networkUtils = NetworkUtils(activity.applicationContext)
        val schedulerProvider = SchedulerProvider.getInstance()

        return CVDetailPresenter(
            activity, repository, disposableManager, networkUtils,
            schedulerProvider
        )
    }
}
