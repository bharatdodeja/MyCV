package com.bharatdodeja.mycv.detail.model.repository.datasource

import com.bharatdodeja.mycv.detail.model.api.CVApiService
import com.bharatdodeja.mycv.detail.model.data.CVDataModel
import io.reactivex.Flowable

/**
 * Remote data source implementation for [CVDataSource]
 */
class CVRemoteDataSource(private val apiService: CVApiService) : CVDataSource {
    override fun getCV(userId: String): Flowable<CVDataModel> {
        return apiService.getCV(userId)
    }
}