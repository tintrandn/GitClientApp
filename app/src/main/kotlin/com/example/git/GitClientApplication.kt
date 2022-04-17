package com.example.git

import android.app.Activity
import android.app.Application
import android.app.Service
import com.example.git.common.AppComponent
import com.example.git.common.AppModule
import com.example.git.common.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject


open class GitClientApplication : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        buildAppComponent()
    }

    private fun buildAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        appComponent.inject(this)
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>

}
