package com.bharatdodeja.mycv.detail.presenter

import com.bharatdodeja.mycv.detail.CVDetailContract
import com.bharatdodeja.mycv.detail.framework.BasePresenter
import com.bharatdodeja.mycv.detail.framework.rx.DisposableManager
import com.bharatdodeja.mycv.detail.model.repository.CVRepository

/**
 * Presenter to communicate with CV model layer and fetch data
 */
class CVDetailPresenter(
    view: CVDetailContract.View, repository: CVRepository, disposableManager: DisposableManager
) :
    BasePresenter<CVDetailContract.View>(view, disposableManager), CVDetailContract.Presenter