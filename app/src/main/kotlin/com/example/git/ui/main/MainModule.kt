package com.example.git.ui.main

import com.example.git.provider.searchuser.SearchUserProvider
import dagger.Binds
import dagger.Module

@Module
abstract class MainModule {

    @Binds
    abstract fun providesMainPresenter(mainPresenter: MainPresenter): MainContract.Presenter

}
