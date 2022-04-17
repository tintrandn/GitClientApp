package com.example.git.provider.searchuser

import com.example.git.model.User

sealed class SearchUserResult {

    data class Success(val userList: List<User>) : SearchUserResult()

    object SuccessEmpty : SearchUserResult()

    object GenericFailure : SearchUserResult()

}