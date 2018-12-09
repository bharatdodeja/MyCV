package com.bharatdodeja.mycv

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyCVApplication : Application(), HasActivityInjector {

    @Inject
    internal var activityInjector: DispatchingAndroidInjector<Activity>? = null

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityInjector
    }
}