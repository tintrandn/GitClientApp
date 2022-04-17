package com.example.git.provider.getuser

import com.example.git.model.DetailUser
import com.example.git.service.get.GetUserResponse
import com.example.git.service.get.GetUserService
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetUserProviderTest {

    @InjectMocks
    private lateinit var subject: GetUserProvider

    @Mock
    private lateinit var getUserService: GetUserService

    @Mock
    private lateinit var getUserMapper: GetUserMapper

    @Mock
    private lateinit var getUserResponse: GetUserResponse

    @Mock
    private lateinit var successResult: GetUserResult.Success

    @Test
    fun getUser_givenSuccessGetUserResponse_returnSuccessGetUserResult() {
        `when`(getUserService.getDetailUser("Test"))
            .thenReturn(Observable.just(getUserResponse))
        `when`(getUserMapper.mapFrom(getUserResponse)).thenReturn(successResult)
        `when`(successResult.detailUser)
            .thenReturn(
                DetailUser("1", "Test1", "Test1AvatarUrl", "10", "20")
            )

        val actual = subject.getUser("Test").test()

        actual.assertValue { it is GetUserResult.Success }
        actual.assertValue {
            val result = it as GetUserResult.Success
            result.detailUser == successResult.detailUser
        }
    }

    @Test
    fun getUser_givenOnErrorResponse_returnGenericFailureResult() {
        `when`(getUserService.getDetailUser("Test"))
            .thenReturn(Observable.error(Throwable()))

        val actual = subject.getUser("Test").test()

        actual.assertValue { it is GetUserResult.GenericFailure }
    }
}