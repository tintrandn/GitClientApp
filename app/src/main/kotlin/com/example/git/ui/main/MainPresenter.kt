package com.example.git.ui.main

import com.example.git.provider.searchuser.SearchUserProvider
import com.example.git.provider.searchuser.SearchUserResult
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val searchUserProvider: SearchUserProvider,
    private val compositeDisposable: CompositeDisposable
) : MainContract.Presenter {

    private lateinit var view: MainContract.View

    override fun setView(view: MainContract.View) {
        this.view = view
    }

    override fun onSearchClick(userName: String) {
        val disposable = searchUserProvider.searchUser(userName)
            .doOnSubscribe { view.showProgressBar() }
            .doOnTerminate { view.hideProgressBar() }
            .subscribe {
                when (it) {
                    is SearchUserResult.Success -> view.showUserList(it.userList)
                    is SearchUserResult.SuccessEmpty -> view.showNoResult(listOf())
                    is SearchUserResult.GenericFailure -> view.showError()
                }
            }
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
    }

}
