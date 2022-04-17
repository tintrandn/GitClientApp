package com.example.git.provider.getuser

import com.example.git.model.DetailUser

sealed class GetUserResult {

    data class Success(val detailUser: DetailUser) : GetUserResult()

    object GenericFailure : GetUserResult()
}