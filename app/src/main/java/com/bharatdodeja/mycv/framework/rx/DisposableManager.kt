package com.bharatdodeja.mycv.framework.rx

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * A disposable container that can hold onto multiple other disposables and
 * offers add and removal complexity.
 */
class DisposableManager {
    private val disposableBag: CompositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        disposableBag.add(disposable)
    }

    fun clearDisposables() {
        disposableBag.dispose()
    }

    fun clearDisposable(disposable: Disposable) {
        disposableBag.remove(disposable)
    }
}
