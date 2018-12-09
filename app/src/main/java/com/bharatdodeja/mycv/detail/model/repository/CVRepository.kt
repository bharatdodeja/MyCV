package com.bharatdodeja.mycv.detail.model.repository

import com.bharatdodeja.mycv.detail.model.data.CVDataModel
import com.bharatdodeja.mycv.detail.model.repository.datasource.CVDataSource
import io.reactivex.Flowable

/**
 * Repository pattern to fetch CV data from data source
 */
class CVRepository(private val remoteDataSource: CVDataSource) : CVDataSource {
    override fun getCV(userId: String): Flowable<CVDataModel> {
        return remoteDataSource.getCV(userId)
    }
}