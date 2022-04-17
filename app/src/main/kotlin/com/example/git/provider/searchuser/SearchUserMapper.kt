package com.example.git.provider.searchuser

import com.example.git.model.User
import com.example.git.service.search.SearchResponse
import javax.inject.Inject

class SearchUserMapper @Inject constructor() {
    fun convert(searchResponse: SearchResponse): SearchUserResult {
        val userList = mutableListOf<User>()
        searchResponse.items.forEach { item ->
            userList.add(User(item.id.toString(), item.login, item.avatar_url, item.score))
        }
        return SearchUserResult.Success(userList)
    }
}