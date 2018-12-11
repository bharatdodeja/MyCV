package com.bharatdodeja.mycv.framework

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.annotation.CallSuper
import com.bharatdodeja.mycv.framework.rx.DisposableManager
import io.reactivex.disposables.Disposable

/**
 * Lifecycle aware base presenter which handles disposing of Rx subscription on its own
 */
abstract class BasePresenter<V> protected constructor(view: V, private val disposableManager: DisposableManager) :
    LifecycleObserver {

    init {
        // Initialize this presenter as a lifecycle-aware when a view is a lifecycle owner.
        if (view is LifecycleOwner) {
            (view as LifecycleOwner).lifecycle.addObserver(this)
        }
    }

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected open fun onCreate() {

    }

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected open fun onStart() {

    }

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected fun onResume() {

    }

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected fun onPause() {

    }

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected fun onStop() {

    }

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected fun onDestroy() {
        clearDisposables()
    }

    /**
     * Adds disposables into the CompositeDisposable bag so that it can be cleared automatically
     * during onDestroy()
     *
     * @param disposable Disposable to be added into CompositeDisposable bag
     */
    protected fun addDisposable(disposable: Disposable) {
        disposableManager.addDisposable(disposable)
    }

    private fun clearDisposables() {
        disposableManager.clearDisposables()
    }

    protected fun clearDisposable(disposable: Disposable) {
        disposableManager.clearDisposable(disposable)
    }
}
