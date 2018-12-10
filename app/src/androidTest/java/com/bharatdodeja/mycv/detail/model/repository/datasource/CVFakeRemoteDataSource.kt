package com.bharatdodeja.mycv.detail.model.repository.datasource

import com.bharatdodeja.mycv.detail.CVUITestDoubles
import com.bharatdodeja.mycv.detail.model.data.CVDataModel
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

/**
 * Fake remote data source implementation of [CVDataSource] for easy UI testing
 */
class CVFakeRemoteDataSource : CVDataSource, CVUITestDoubles() {
    override fun getCV(userId: String): Flowable<CVDataModel> {
        return Flowable.create({
            it.onNext(cvDataModel)
            it.onComplete()
        }, BackpressureStrategy.BUFFER)
    }
}