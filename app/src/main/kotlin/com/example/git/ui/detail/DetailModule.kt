package com.example.git.ui.detail

import dagger.Binds
import dagger.Module

@Module
abstract class DetailModule {

    @Binds
    abstract fun providesDetailPresenter(detailPresenter: DetailPresenter): DetailContract.Presenter

}
