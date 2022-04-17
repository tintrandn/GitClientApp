package com.example.git.provider.getuser

import com.example.git.service.get.GetUserService
import io.reactivex.Observable
import javax.inject.Inject

class GetUserProvider @Inject constructor(
    private val getUserService: GetUserService,
    private val getUserMapper: GetUserMapper
) {

    fun getUser(userName: String): Observable<GetUserResult> {
        return getUserService.getDetailUser(userName).map {
            return@map getUserMapper.mapFrom(it) as GetUserResult
        }.onErrorReturn {
            GetUserResult.GenericFailure
        }
    }
}