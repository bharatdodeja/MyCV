package com.bharatdodeja.mycv.framework.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Provides different types of schedulers.
 */
class SchedulerProvider : BaseSchedulerProvider {
    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    companion object {
        private lateinit var INSTANCE: SchedulerProvider
        private var needsNewInstance = true

        @JvmStatic
        fun getInstance(): SchedulerProvider {
            if (needsNewInstance) {
                INSTANCE = SchedulerProvider()
                needsNewInstance = false
            }
            return INSTANCE
        }
    }
}