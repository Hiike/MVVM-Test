package com.example.hiike.mvvmtest.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.databinding.BindingAdapter
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.example.hiike.mvvmtest.utils.extension.getParentActivity

/**
 * Already available in the library by default.
 * This is for learning custom binding
 */

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Boolean>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value ->
            if (value != null && value) {
                view.visibility = View.VISIBLE
            } else {
                view.visibility = View.GONE
            }

        })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?: "" })
    }
}