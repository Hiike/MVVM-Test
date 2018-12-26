package com.example.hiike.mvvmtest.ui.post

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import com.example.hiike.mvvmtest.R
import com.example.hiike.mvvmtest.databinding.ActivityPostListBinding
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class PostListActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var postListViewModelFactory: PostListViewModelFactory

    private lateinit var binding: ActivityPostListBinding
    private lateinit var viewModel: PostListViewModel

    private val adapter = PostListAdapter()
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_list)
        binding.postList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.postList.adapter = this.adapter

        viewModel = ViewModelProviders.of(this, postListViewModelFactory)
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