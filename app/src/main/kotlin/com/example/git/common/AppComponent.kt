package com.example.git.common

import com.example.git.GitClientApplication
import com.example.git.ui.detail.DetailActivity
import com.example.git.ui.detail.DetailModule
import com.example.git.ui.main.MainModule
import com.example.git.ui.main.MainActivity
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainModule::class,
        DetailModule::class
    ]
)
interface AppComponent {

    fun inject(gitClientApplication: GitClientApplication)

    fun inject(mainActivity: MainActivity)

    fun inject(detailActivity: DetailActivity)
}
