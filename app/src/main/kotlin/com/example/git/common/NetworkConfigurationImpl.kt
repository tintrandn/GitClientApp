package com.example.git.common

import android.content.Context
import com.example.git.network.NetworkConfiguration
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File

class NetworkConfigurationImpl(private val context: Context) :
    NetworkConfiguration {
    override fun mainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()

    override fun ioScheduler(): Scheduler = Schedulers.io()

    override fun getHost(): String = "https://api.github.com/"

    override fun getCacheDir(): File = context.cacheDir

    override fun getCacheSize(): Long = 2 * 1024

    override fun getTimeoutSeconds(): Long = 5000

}
