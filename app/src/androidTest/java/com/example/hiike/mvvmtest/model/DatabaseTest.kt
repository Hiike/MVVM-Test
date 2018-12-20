package com.example.hiike.mvvmtest.model

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hiike.mvvmtest.model.database.AppDatabase
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var postDao: PostDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        postDao = db.postDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writePostAndReadInList() {
        val post = Post(1, 1, "title", "body")
        postDao.insertAll(post)
        val postList = postDao.all
        assertThat(postList[0], equalTo(post))
    }
}