package com.example.git.ui.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.git.GitClientApplication
import com.example.git.R
import com.example.git.databinding.ActivityMainBinding
import com.example.git.model.User
import com.example.git.ui.BaseActivity
import com.example.git.ui.detail.DetailActivity
import javax.inject.Inject
import com.google.android.material.snackbar.Snackbar


class MainActivity : BaseActivity<ActivityMainBinding>(), MainContract.View,
    UserAdapter.OnUserClickedCallback {

    @Inject
    lateinit var presenter: MainContract.Presenter

    private lateinit var binding: ActivityMainBinding

    private val userAdapter: UserAdapter = UserAdapter(this)

    override fun getToolbarTitle(): Int = R.string.app_name

    override fun getViewBinding(): ActivityMainBinding {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as GitClientApplication).getAppComponent().inject(this)
        presenter.setView(this)
        binding.userList.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.userList.adapter = userAdapter
        binding.btnSearch.setOnClickListener {
            searchUserStart()
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchUserStart()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun searchUserStart() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.searchView.windowToken, 0)
        presenter.onSearchClick(binding.searchView.query.toString())
    }

    override fun showUserList(userList: List<User>) {
        binding.noResult.visibility = GONE
        userAdapter.submitList(userList)
    }

    override fun showNoResult(userList: List<User>) {
        binding.noResult.visibility = VISIBLE
        userAdapter.submitList(userList)
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
        binding.noResult.visibility = GONE
        binding.progressBar.visibility = VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressBar.visibility = GONE
    }

    override fun onUserClickedCallback(userName: String) {
        DetailActivity.startInstance(this, userName)
    }
}
