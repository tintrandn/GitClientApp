package com.example.git.common

import android.content.Context
import android.content.SharedPreferences
import com.example.git.GitClientApplication
import com.example.git.network.NetworkLibrary
import com.example.git.service.get.GetUserObjectGraph
import com.example.git.service.get.GetUserService
import com.example.git.service.search.SearchObjectGraph
import com.example.git.service.search.SearchService
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

@Module
class AppModule constructor(private val application: GitClientApplication) {

    @Provides
    fun providesApplication(): GitClientApplication = application

    @Provides
    fun providesContext(): Context = application.applicationContext

    @Provides
    fun providesNetworkLibrary(networkConfigurationImpl: NetworkConfigurationImpl): NetworkLibrary {
        return NetworkLibrary(networkConfigurationImpl)
    }

    @Provides
    fun providesNetworkConfiguration(context: Context) = NetworkConfigurationImpl(context)

    @Provides
    fun providesRetrofit(
        networkLibrary: NetworkLibrary
    ): Retrofit {
        val okHttpBuilder = networkLibrary.okHttpClient()
            .newBuilder()

        val okHttpClient = okHttpBuilder
            .build()
        return networkLibrary.retrofit().newBuilder().client(okHttpClient).build()
    }

    @Provides
    fun providesCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun providesSettingsPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences("example_git_client_app", Context.MODE_PRIVATE)
    }

    @Provides
    fun providesSearchService(searchObjectGraph: SearchObjectGraph): SearchService {
        return searchObjectGraph.searchService()
    }

    @Provides
    fun providesSearchObjectGraph(retrofit: Retrofit): SearchObjectGraph {
        return SearchObjectGraph(retrofit)
    }


    @Provides
    fun providesGetUserService(getUserObjectGraph: GetUserObjectGraph): GetUserService {
        return getUserObjectGraph.getUserService()
    }

    @Provides
    fun providesGetUserObjectGraph(retrofit: Retrofit): GetUserObjectGraph {
        return GetUserObjectGraph(retrofit)
    }
}
