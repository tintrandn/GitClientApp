package com.example.git.service.get

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class GetUserServiceModule(private val retrofit: Retrofit) {

  @Provides
  internal fun providesGetUserService(): GetUserService {
    return retrofit.create(GetUserService::class.java)
  }
}
