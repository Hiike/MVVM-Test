package com.example.hiike.mvvmtest.ui.post

import android.arch.lifecycle.MutableLiveData
import com.example.hiike.mvvmtest.base.BaseViewModel
import com.example.hiike.mvvmtest.model.Post
import com.example.hiike.mvvmtest.model.PostDao
import com.example.hiike.mvvmtest.network.PostApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class PostListViewModel(private val postDao: PostDao) : BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi

    private lateinit var subscription: Disposable

    val loadingVisible: MutableLiveData<Boolean> = MutableLiveData()
    val errorRetrieve: MutableLiveData<Boolean> = MutableLiveData()
    val postList: MutableLiveData<List<Post>> = MutableLiveData()

    init {
        loadPosts()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun loadPosts() {
        subscription = Observable.fromCallable { postDao.all }
                .concatMap { dbPostList ->
                    if (dbPostList.isEmpty()) {
                        postApi.getPosts().concatMap { apiPostList ->
                            postDao.insertAll(*apiPostList.toTypedArray())
                            Observable.just(apiPostList)
                        }
                    } else {
                        Observable.just(dbPostList)
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(
                        { result -> onRetrievePostListSuccess(result) },
                        { error -> onRetrievePostListError(error) }
                )
    }

    private fun onRetrievePostListStart() {
        loadingVisible.value = true
        errorRetrieve.value = false
    }

    private fun onRetrievePostListFinish() {
        loadingVisible.value = false
    }

    private fun onRetrievePostListSuccess(postList: List<Post>) {
        this.postList.value = postList
    }

    private fun onRetrievePostListError(error: Throwable) {
        errorRetrieve.value = true
        Timber.e(error)
    }

}