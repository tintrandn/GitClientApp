package com.example.git.provider.searchuser

import com.example.git.model.User
import com.example.git.provider.searchuser.SearchUserResult.*
import com.example.git.service.search.SearchResponse
import com.example.git.service.search.SearchService
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchUserProviderTest {

    @InjectMocks
    private lateinit var subject: SearchUserProvider

    @Mock
    private lateinit var searchService: SearchService

    @Mock
    private lateinit var searchUserMapper: SearchUserMapper

    @Mock
    private lateinit var searchUserResponse: SearchResponse

    @Mock
    private lateinit var successResult: Success

    @Test
    fun searchUser_givenSuccessSearchResponse_returnSuccessSearchResult() {
        `when`(searchService.getSearchUser("Test"))
            .thenReturn(Observable.just(searchUserResponse))
        `when`(searchUserMapper.convert(searchUserResponse)).thenReturn(successResult)
        `when`(successResult.userList)
            .thenReturn(
                listOf(
                    User("1", "Test1", "Test1AvatarUrl", 10),
                    User("2", "Test2", "Test2AvatarUrl", 20)
                )
            )

        val actual = subject.searchUser("Test").test()

        actual.assertValue { it is Success }
        actual.assertValue {
            val result = it as Success
            result.userList == successResult.userList
        }
    }

    @Test
    fun searchUser_givenOnErrorResponse_returnGenericFailureResult() {
        `when`(searchService.getSearchUser("Test"))
            .thenReturn(Observable.error(Throwable()))

        val actual = subject.searchUser("Test").test()

        actual.assertValue { it is GenericFailure }
    }

}