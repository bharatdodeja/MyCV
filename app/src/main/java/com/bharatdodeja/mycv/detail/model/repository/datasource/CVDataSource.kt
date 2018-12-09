package com.bharatdodeja.mycv.detail.model.repository.datasource

import com.bharatdodeja.mycv.detail.model.data.CVDataModel
import io.reactivex.Flowable

/**
 * Abstract data source for CV
 */
interface CVDataSource {
    /**
     * Returns observable of type [CVDataModel] based on [userId] passed
     */
    fun getCV(userId: String): Flowable<CVDataModel>
}