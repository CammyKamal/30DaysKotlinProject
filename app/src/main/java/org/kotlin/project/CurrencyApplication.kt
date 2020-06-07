package org.kotlin.project


import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import org.kotlin.project.di.DaggerAppComponent

/**
 * Application class
 */
class CurrencyApplication : DaggerApplication() {

    //Dagger library initialization
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().setContext(this).build()
    }

}