package com.bharatdodeja.mycv.detail.framework.di

import android.content.Context
import com.bharatdodeja.mycv.BuildConfig.API_BASE_URL
import com.bharatdodeja.mycv.BuildConfig.DEBUG
import com.bharatdodeja.mycv.framework.di.ActivityScoped
import com.bharatdodeja.mycv.framework.di.ApplicationContext
import com.bharatdodeja.mycv.framework.util.NetworkUtils
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Dagger module to provide API related dependencies.
 */
@Module
class NetworkModule {

    private val timeOut: Long = 30

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor = if (DEBUG)
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    else
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)

    @Provides
    fun httpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .writeTimeout(timeOut, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun converterFactory(): Converter.Factory {
        val gson = GsonBuilder().setLenient().create()
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun callAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        converterFactory: Converter.Factory, callAdapterFactory: CallAdapter.Factory,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(API_BASE_URL)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    internal fun networkUtils(context: Context): NetworkUtils {
        return NetworkUtils(context)
    }
}