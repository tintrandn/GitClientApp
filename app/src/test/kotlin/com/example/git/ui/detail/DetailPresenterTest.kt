package com.example.git.ui.detail

import com.example.git.model.DetailUser
import com.example.git.provider.getuser.GetUserProvider
import com.example.git.provider.getuser.GetUserResult
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
class DetailPresenterTest {

    @InjectMocks
    private lateinit var subject: DetailPresenter

    @Mock
    private lateinit var view: DetailContract.View

    @Mock
    private lateinit var getUserProvider: GetUserProvider

    @Mock
    private lateinit var compositeDisposable: CompositeDisposable

    @Mock
    private lateinit var successResult: GetUserResult.Success

    @Mock
    private lateinit var errorResult: GetUserResult.GenericFailure

    @Mock
    private lateinit var detailUser: DetailUser

    @Before
    fun setUp() {
        subject.setView(view)
    }

    @Test
    fun onViewCreate_givenSuccessGetUserResult_verifyShowDetailUser() {
        `when`(getUserProvider.getUser("Test"))
            .thenReturn(Observable.just(successResult))
        `when`(successResult.detailUser).thenReturn(detailUser)
        subject.onViewCreate("Test")

        verify(view).showProgressBar()
        verify(view).hideProgressBar()
        verify(view).showDetailUser(detailUser)
    }

    @Test
    fun onSearchClick_givenGenericFailureResult_verifyViewShowError() {
        `when`(getUserProvider.getUser("Test"))
            .thenReturn(Observable.just(errorResult))
        subject.onViewCreate("Test")

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