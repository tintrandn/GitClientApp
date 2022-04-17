package com.example.git.service.get

import retrofit2.Retrofit

class GetUserObjectGraph(retrofit: Retrofit) {

    private val component = DaggerGetUserServiceComponent.builder()
        .getUserServiceModule(GetUserServiceModule(retrofit))
        .build()

    fun getUserService(): GetUserService {
        return component.getUserService()
    }
}
