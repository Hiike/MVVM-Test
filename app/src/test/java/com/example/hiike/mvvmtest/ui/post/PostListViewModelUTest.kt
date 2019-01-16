package com.example.hiike.mvvmtest.ui.post

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.hiike.mvvmtest.data.Post
import com.example.hiike.mvvmtest.domain.PostRepository
import com.example.hiike.mvvmtest.utils.ImmediateSchedulerRule
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import io.reactivex.subscribers.TestSubscriber




class PostListViewModelUTest {

    // A JUnit Test Rule that swaps the background executor used by
    // the Architecture Components with a different one which executes each task synchronously.
    // You can use this rule for your host side tests that use Architecture Components.
    @Rule
    @JvmField
    val mockitoRule = InstantTaskExecutorRule()

    // Test rule for making the RxJava to run synchronously in unit test
    companion object {
        @ClassRule
        @JvmField
        val schedulers = ImmediateSchedulerRule()
    }

    @Mock
    lateinit var postRepository: PostRepository

    val viewmodel by lazy { PostListViewModel(postRepository) }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getPostListSuccess() {
        // Given
        val postList: List<Post> = listOf(Post(1, 1, "title", "body"), Post(2, 2, "title", "body"))
        Mockito.doReturn(Single.just(postList).toObservable())
                .`when`(postRepository.getPosts())

        // When
        viewmodel.loadPosts()

        // Verify
        assertEquals("Post Lists is not matching", postList, viewmodel.postList.value)

    }

    @Test
    fun getPostListError() {
        // Given
        Mockito.doReturn(Observable.error<Exception>(Exception()))
                .`when`(postRepository).getPosts()

        // When
        viewmodel.loadPosts()

        // Verify
        assertTrue("Post Lists should have thrown an exception", viewmodel.errorRetrieve.value!!)
    }

    @Test
    fun getPostListOnSubscribed() {
        TODO()
    }

    @Test
    fun getPostListOnTerminated() {
        TODO()
    }
}