package com.example.git.ui.detail

import com.example.git.model.DetailUser

class DetailContract {

    interface View {

        fun showDetailUser(detailUser: DetailUser)

        fun showError()

        fun showProgressBar()

        fun hideProgressBar()
    }

    interface Presenter {

        fun setView(view: View)

        fun onViewCreate(userName: String)

        fun onDestroy()
    }
}