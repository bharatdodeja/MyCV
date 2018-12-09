package com.bharatdodeja.mycv.detail.di

import android.content.Context
import com.bharatdodeja.mycv.detail.CVDetailContract
import com.bharatdodeja.mycv.detail.model.api.CVApiService
import com.bharatdodeja.mycv.detail.model.repository.CVRepository
import com.bharatdodeja.mycv.detail.model.repository.datasource.CVRemoteDataSource
import com.bharatdodeja.mycv.detail.presenter.CVDetailPresenter
import com.bharatdodeja.mycv.detail.view.CVDetailActivity
import com.bharatdodeja.mycv.framework.di.ActivityScoped
import com.bharatdodeja.mycv.framework.di.ApplicationContext
import com.bharatdodeja.mycv.framework.rx.DisposableManager
import com.bharatdodeja.mycv.framework.util.NetworkUtils
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CVDetailModule {
    @ActivityScoped
    @Provides
    fun view(cvDetailActivity: CVDetailActivity): CVDetailContract.View {
        return cvDetailActivity
    }

    @ActivityScoped
    @Provides
    fun disposableManager(): DisposableManager {
        return DisposableManager()
    }

    @ActivityScoped
    @Provides
    fun presenter(
        view: CVDetailContract.View, repository: CVRepository,
        disposableManager: DisposableManager, networkUtils: NetworkUtils
    ): CVDetailContract.Presenter {
        return CVDetailPresenter(view, repository, disposableManager, networkUtils)
    }

    @ActivityScoped
    @Provides
    fun repository(retrofit: Retrofit): CVRepository {
        val apiService = retrofit.create(CVApiService::class.java)
        val remoteDataSource = CVRemoteDataSource(apiService)
        return CVRepository(remoteDataSource)
    }
}
