package com.bharatdodeja.mycv.detail

import com.bharatdodeja.mycv.detail.model.data.CVDataModel

/**
 * Contract between View and Presenter in an MVP architecture
 */
interface CVDetailContract {
    interface View {
        fun showCVDetail(cvDataModel: CVDataModel)
        fun showLoading()
        fun hideLoading()
        fun showError(error: Throwable)
        fun showNoNetworkError()
    }

    interface Presenter {
        fun getCVDetail(userId: String)
    }
}