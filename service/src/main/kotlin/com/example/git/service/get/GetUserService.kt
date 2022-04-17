package com.example.git.service.get

import com.example.git.service.VersionConfig
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GetUserService {

    @GET("users/{userName}")
    @Headers(VersionConfig.ACCEPT + ":" + VersionConfig.GITHUB_VERSION)
    fun getDetailUser(
        @Path("userName") userName: String
    ): Observable<GetUserResponse>

}
