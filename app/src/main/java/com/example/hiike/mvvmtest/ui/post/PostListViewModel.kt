package com.example.hiike.mvvmtest.ui.post

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.hiike.mvvmtest.data.Post
import com.example.hiike.mvvmtest.domain.PostRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class PostListViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {

    val loadingVisible: MutableLiveData<Boolean> = MutableLiveData()
    val errorRetrieve: MutableLiveData<Boolean> = MutableLiveData()
    val postList: MutableLiveData<List<Post>> = MutableLiveData()

    lateinit var subscription: DisposableObserver<List<Post>>

    override fun onCleared() {
        super.onCleared()
        if (!subscription.isDisposed) {
            subscription.dispose()
        }
    }

    fun loadPosts() {
        subscription = object : DisposableObserver<List<Post>>() {
            override fun onComplete() {
                // Nothing to do
            }

            override fun onNext(postList: List<Post>) {
                onRetrievePostListSuccess(postList)
            }

            override fun onError(e: Throwable) {
                onRetrievePostListError(e)
            }

        }

        postRepository.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(subscription)
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