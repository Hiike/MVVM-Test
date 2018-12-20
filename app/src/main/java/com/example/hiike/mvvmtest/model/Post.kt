package com.example.hiike.mvvmtest.model

import androidx.room.Entity
import androidx.room.PrimaryKey

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