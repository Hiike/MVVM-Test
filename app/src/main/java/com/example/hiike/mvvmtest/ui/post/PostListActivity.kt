package com.example.hiike.mvvmtest.ui.post

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hiike.mvvmtest.R
import com.example.hiike.mvvmtest.databinding.ActivityPostListBinding
import com.example.hiike.mvvmtest.injection.ViewModelFactory

class PostListActivity: AppCompatActivity() {
    private lateinit var binding: ActivityPostListBinding
    private lateinit var viewModel: PostListViewModel

    private val adapter = PostListAdapter()
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_list)
        binding.postList.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false)
        binding.postList.adapter = this.adapter

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this))
                .get(PostListViewModel::class.java)
        viewModel.errorRetrieve.observe(this, Observer { errorRetrieve ->
            if (errorRetrieve != null && errorRetrieve) {
                showError(R.string.post_error)
            } else {
                hideError()
            }
        })
        viewModel.postList.observe(this, Observer { postList ->
            if (postList != null) {
                this.adapter.updatePostList(postList)
            }
        })
        binding.viewModel = viewModel
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry) { viewModel.loadPosts() }
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}