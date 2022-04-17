package com.example.git.ui.main

import com.example.git.model.User

class MainContract {

    interface View {

        fun showUserList(userList: List<User>)

        fun showNoResult(userList: List<User>)

        fun showError()

        fun showProgressBar()

        fun hideProgressBar()
    }

    interface Presenter {

        fun setView(view: View)

        fun onSearchClick(userName: String)

        fun onDestroy()
    }
}