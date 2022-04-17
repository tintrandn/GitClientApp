package com.example.git.provider.searchuser

import com.example.git.provider.searchuser.SearchUserResult.*
import com.example.git.service.search.SearchService
import io.reactivex.Observable
import javax.inject.Inject

class SearchUserProvider @Inject constructor(
    private val searchService: SearchService,
    private val searchUserMapper: SearchUserMapper
) {

    fun searchUser(userName: String): Observable<SearchUserResult> {
        return searchService.getSearchUser(userName).map {
            if (it.items.isEmpty()) return@map SuccessEmpty
            else searchUserMapper.convert(it) as SearchUserResult
        }.onErrorReturn {
            GenericFailure
        }
    }
}