package com.example.git.service.get

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(GetUserServiceModule::class)])
interface GetUserServiceComponent {

  fun getUserService(): GetUserService

}

