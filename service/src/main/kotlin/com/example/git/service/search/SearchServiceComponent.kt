package com.example.git.service.search

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(SearchServiceModule::class)])
interface SearchServiceComponent {

  fun searchService(): SearchService

}
