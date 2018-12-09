package com.bharatdodeja.mycv.framework.di

import com.bharatdodeja.mycv.detail.di.CVDetailModule
import com.bharatdodeja.mycv.detail.view.CVDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * We want Dagger.Android to create a Sub component which has a parent Component of whichever module
 * ActivityBindingModule is on, in our case that will be AppComponent. The beautiful part about
 * this setup is that you never need to tell ApplicationComponent that it is going to have all these
 * sub components nor do you need to tell these sub components that AppComponent exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include
 * the specified modules and be aware of a scope annotation @ActivityScoped
 * When Dagger Android annotation processor runs, it will create 4 sub components for us.
 */
@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [CVDetailModule::class])
    internal abstract fun cvDetailActivity(): CVDetailActivity
}
