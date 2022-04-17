package com.example.git.ui.main

import com.example.git.provider.searchuser.SearchUserProvider
import com.example.git.provider.searchuser.SearchUserResult.*
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    @InjectMocks
    private lateinit var subject: MainPresenter

    @Mock
    private lateinit var view: MainContract.View

    @Mock
    private lateinit var searchUserProvider: SearchUserProvider

    @Mock
    private lateinit var compositeDisposable: CompositeDisposable

    @Mock
    private lateinit var successResult: Success

    @Mock
    private lateinit var successEmpty: SuccessEmpty

    @Mock
    private lateinit var errorResult: GenericFailure

    @Before
    fun setUp() {
        subject.setView(view)
    }

    @Test
    fun onSearchClick_givenSuccessSearchResult_verifyViewShowList() {
        `when`(searchUserProvider.searchUser("Test"))
            .thenReturn(Observable.just(successResult))
        `when`(successResult.userList).thenReturn(listOf())
        subject.onSearchClick("Test")

        verify(view).showProgressBar()
        verify(view).hideProgressBar()
        verify(view).showUserList(listOf())
    }

    @Test
    fun onSearchClick_givenSuccessSearchEmpty_verifyViewShowNoResult() {
        `when`(searchUserProvider.searchUser("Test"))
            .thenReturn(Observable.just(successEmpty))
        subject.onSearchClick("Test")

        verify(view).showProgressBar()
        verify(view).hideProgressBar()
        verify(view).showNoResult(listOf())
    }

    @Test
    fun onSearchClick_givenGenericFailureResult_verifyViewShowError() {
        `when`(searchUserProvider.searchUser("Test"))
            .thenReturn(Observable.just(errorResult))
        subject.onSearchClick("Test")

        verify(view).showProgressBar()
        verify(view).hideProgressBar()
        verify(view).showError()
    }

    @Test
    fun onDestroy_verifyCompositeDisposableClear() {
        subject.onDestroy()
        verify(compositeDisposable).clear()
    }

}