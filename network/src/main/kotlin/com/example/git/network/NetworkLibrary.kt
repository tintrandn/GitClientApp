package com.example.git.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit

class NetworkLibrary constructor(configuration: NetworkConfiguration) {

    private var component: NetworkComponent = DaggerNetworkComponent.builder()
            .networkModule(NetworkModule(configuration))
            .build()

    fun retrofit(): Retrofit {
        return component.retrofit()
    }

    fun okHttpClient(): OkHttpClient {
        return component.okHttpClient()
    }
}