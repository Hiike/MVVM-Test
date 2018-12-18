package com.example.hiike.mvvmtest.model

/**
 * Data classes' main purpose is to hold data.
 * In such a class some standard functionality and utility functions
 * are often mechanically derivable from the data
 */

data class Post(val userId: Int, val id: Int, val title: String, val body: String)