package com.example.git.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.git.GitClientApplication
import com.example.git.R
import com.example.git.databinding.ActivityDetailUserBinding
import com.example.git.model.DetailUser
import com.example.git.ui.BaseActivity
import javax.inject.Inject
import com.google.android.material.snackbar.Snackbar

class DetailActivity : BaseActivity<ActivityDetailUserBinding>(), DetailContract.View {

    @Inject
    lateinit var presenter: DetailContract.Presenter

    private lateinit var binding: ActivityDetailUserBinding

    override fun getToolbarTitle(): Int = R.string.app_name

    override fun getViewBinding(): ActivityDetailUserBinding {
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        return binding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as GitClientApplication).getAppComponent().inject(this)
        presenter.setView(this)
        presenter.onViewCreate(getUserName())
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showDetailUser(detailUser: DetailUser) {
        Glide.with(binding.detailUserImg)
            .load(detailUser.avatarUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(binding.detailUserImg)

        binding.detailUserName.text = detailUser.userName
        binding.detailUserId.text = applicationContext.getString(R.string.id, detailUser.id)
        binding.userFollowers.text = detailUser.followers
        binding.userFollowing.text = detailUser.following
    }

    override fun showError() {
        val snackBar = Snackbar.make(
            binding.root,
            applicationContext.getString(R.string.error_message),
            Snackbar.LENGTH_LONG
        )
        snackBar.show()
    }

    override fun showProgressBar() {
        binding.progressBar.visibility = VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressBar.visibility = GONE
    }

    private fun getUserName(): String = intent.getStringExtra(IntentExtras.USER_NAME)

    object IntentExtras {
        const val USER_NAME = "user_name"
    }

    companion object {
        fun startInstance(
            context: Context,
            userName: String
        ) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(IntentExtras.USER_NAME, userName)
            context.startActivity(intent)
        }
    }

}
