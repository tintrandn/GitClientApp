package com.example.git.ui.detail

import com.example.git.provider.getuser.GetUserProvider
import com.example.git.provider.getuser.GetUserResult
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    private val getUserProvider: GetUserProvider,
    private val compositeDisposable: CompositeDisposable
) : DetailContract.Presenter {

    private lateinit var view: DetailContract.View

    override fun setView(view: DetailContract.View) {
        this.view = view
    }

    override fun onViewCreate(userName: String) {
        val disposable = getUserProvider.getUser(userName)
            .doOnSubscribe { view.showProgressBar() }
            .doOnTerminate { view.hideProgressBar() }
            .subscribe {
                when (it) {
                    is GetUserResult.Success -> view.showDetailUser(it.detailUser)
                    is GetUserResult.GenericFailure -> view.showError()
                }
            }
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
    }

}
