package com.bharatdodeja.mycv.framework.di

import android.content.Context
import com.bharatdodeja.mycv.MyCVApplication
import dagger.Module

/**
 * This is a Dagger module. We use this to bind our Application class as a Context in the ApplicationComponent
 * By using Dagger Android we do not need to pass our Application instance to any module,
 * we simply need to expose our Application as Context.
 * One of the advantages of Dagger.Android is that your
 * Application & Activities are provided into your graph for you.
 * {@link ApplicationComponent}.
 */
@Module
class ApplicationModule {
    //exposes Application as an injectable context
    internal fun bindContext(application: MyCVApplication): Context {
        return application
    }
}
