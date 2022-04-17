package com.example.git.provider.getuser

import com.example.git.model.DetailUser
import com.example.git.service.get.GetUserResponse
import javax.inject.Inject

class GetUserMapper @Inject constructor() {

    fun mapFrom(getUserResponse: GetUserResponse) = getUserResponse.convert()

    private fun GetUserResponse.convert() = GetUserResult.Success(
        DetailUser(
            id.toString(),
            login,
            avatar_url,
            followers.toString(),
            following.toString()
        )
    )

}