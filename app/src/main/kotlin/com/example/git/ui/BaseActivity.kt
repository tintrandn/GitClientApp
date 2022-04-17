package com.example.git.ui

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.git.R
import kotlinx.android.synthetic.main.toolbar.*

abstract class BaseActivity<VBinding : ViewBinding> : AppCompatActivity() {

    private lateinit var binding: VBinding

    protected abstract fun getViewBinding(): VBinding

    @StringRes
    protected abstract fun getToolbarTitle(): Int

    protected open fun enableBackButton(): Boolean {
        return false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)

        setSupportActionBar(toolbar)

        if (getToolbarTitle() != 0)
            supportActionBar?.title = "  " + getString(getToolbarTitle())
        else
            supportActionBar?.title = getString(R.string.app_name)

        supportActionBar?.setDisplayHomeAsUpEnabled(enableBackButton())
    }
}
