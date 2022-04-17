package com.example.git.service.search

import com.example.git.service.VersionConfig
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchService {

    @GET("search/users")
    @Headers(VersionConfig.ACCEPT + ":" + VersionConfig.GITHUB_VERSION)
    fun getSearchUser(
        @Query("q") query: String
    ): Observable<SearchResponse>

}
