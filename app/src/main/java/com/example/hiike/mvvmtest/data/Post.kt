package com.example.hiike.mvvmtest.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Data classes' main purpose is to hold data.
 * In such a class some standard functionality and utility functions
 * are often mechanically derivable from the data
 */

@Entity
data class Post(
        val userId: Int,
        @field:PrimaryKey
        val id: Int,
        val title: String,
        val body: String)