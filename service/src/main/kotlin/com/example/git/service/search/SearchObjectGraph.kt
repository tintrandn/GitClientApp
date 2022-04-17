package com.example.git.service.search

import retrofit2.Retrofit

class SearchObjectGraph(retrofit: Retrofit) {

  private val component = DaggerSearchServiceComponent.builder()
    .searchServiceModule(SearchServiceModule(retrofit))
    .build()

  fun searchService(): SearchService {
    return component.searchService()
  }
}
