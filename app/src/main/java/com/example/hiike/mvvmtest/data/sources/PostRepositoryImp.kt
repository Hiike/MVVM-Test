package com.example.hiike.mvvmtest.data.sources

import com.example.hiike.mvvmtest.data.Post
import com.example.hiike.mvvmtest.data.sources.local.PostDao
import com.example.hiike.mvvmtest.data.sources.remote.PostApi
import com.example.hiike.mvvmtest.domain.PostRepository
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

class PostRepositoryImp @Inject constructor(val postApi: PostApi, val postDao: PostDao) : PostRepository {

    override fun getPosts() : Observable<List<Post>> {
        val observableFromApi = getPostFromApi()
        val observableFromDb = getPostFromDb()
        return Observable.concatArrayEager(observableFromApi, observableFromDb)
    }

    private fun getPostFromApi(): Observable<List<Post>> {
        return postApi.getPosts()
                .doOnNext{
                    Timber.tag("Repository API : ").i(it.size.toString())
                    postDao.deleteAllPosts()
                    postDao.insertAll(*it.toTypedArray())
                }
    }

    private fun getPostFromDb(): Observable<List<Post>> {
        return postDao.all
                .toObservable()
                .doOnNext{
                    Timber.tag("Repository DB : ").i(it.size.toString())
                }
    }

}