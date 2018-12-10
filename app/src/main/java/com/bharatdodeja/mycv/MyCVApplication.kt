package com.bharatdodeja.mycv

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector

class MyCVApplication : Application(), HasActivityInjector {

    private var activityInjector: DispatchingAndroidInjector<Activity>? = null

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityInjector
    }
}