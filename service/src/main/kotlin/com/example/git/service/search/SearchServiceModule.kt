package com.example.git.service.search

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class SearchServiceModule(private val retrofit: Retrofit) {

  @Provides
  internal fun providesSearchService(): SearchService {
    return retrofit.create(SearchService::class.java)
  }
}
