package com.bharatdodeja.mycv.framework.di

import com.bharatdodeja.mycv.MyCVApplication
import com.bharatdodeja.mycv.detail.framework.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivityBindingModule::class,
        NetworkModule::class]
)
interface ApplicationComponent {
    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MyCVApplication): ApplicationComponent.Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: MyCVApplication)
}