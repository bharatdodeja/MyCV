package com.bharatdodeja.mycv.detail.di

import com.bharatdodeja.mycv.BuildConfig
import com.bharatdodeja.mycv.detail.CVDetailContract
import com.bharatdodeja.mycv.detail.model.api.CVApiService
import com.bharatdodeja.mycv.detail.model.repository.CVRepository
import com.bharatdodeja.mycv.detail.model.repository.datasource.CVRemoteDataSource
import com.bharatdodeja.mycv.detail.presenter.CVDetailPresenter
import com.bharatdodeja.mycv.detail.view.CVDetailActivity
import com.bharatdodeja.mycv.framework.rx.DisposableManager
import com.bharatdodeja.mycv.framework.rx.SchedulerProvider
import com.bharatdodeja.mycv.framework.util.NetworkUtils
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Provides dependencies for [CVDetailActivity]
 */
object Injection {
    fun providePresenter(activity: CVDetailActivity): CVDetailContract.Presenter {

        val timeOut: Long = 10

        val gson = GsonBuilder().setLenient().create()
        val converterFactory = GsonConverterFactory.create(gson)

        val callAdapterFactory = RxJava2CallAdapterFactory.create()

        val client = OkHttpClient.Builder()
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .writeTimeout(timeOut, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder().baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .client(client)
            .build()

        val apiService = retrofit.create(CVApiService::class.java)
        val remoteDataSource = CVRemoteDataSource(apiService)
        val repository = CVRepository(remoteDataSource)

        val disposableManager = DisposableManager()
        val networkUtils = NetworkUtils(activity.applicationContext)
        val schedulerProvider = SchedulerProvider.getInstance()

        return CVDetailPresenter(activity, repository, disposableManager, networkUtils,
            schedulerProvider)
    }
}
