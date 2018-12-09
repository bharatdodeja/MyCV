package com.bharatdodeja.mycv.detail.model.api

import com.bharatdodeja.mycv.detail.model.data.CVDataModel
import io.reactivex.Flowable

/**
 * API service to fetch CV data from server
 */
interface CVApiService {
    fun getCV(userId: String): Flowable<CVDataModel>
}