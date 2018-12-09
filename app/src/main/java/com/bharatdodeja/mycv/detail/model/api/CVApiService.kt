package com.bharatdodeja.mycv.detail.model.api

import com.bharatdodeja.mycv.detail.model.data.CVDataModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * API service to fetch CV data from server
 */
interface CVApiService {
    @GET("/{userId}.json")
    fun getCV(@Path("userId") userId: String): Flowable<CVDataModel>
}