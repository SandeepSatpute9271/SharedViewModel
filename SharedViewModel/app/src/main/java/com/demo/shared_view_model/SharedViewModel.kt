package com.demo.shared_view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/*
 * Created by Sandeep(Techno Learning) on 10,July,2022
 */
class SharedViewModel : ViewModel() {
    private val mutableMovie = MutableLiveData<String>()
    fun setMessage(message: String) {
        mutableMovie.value = message
    }

    fun getMessage(): MutableLiveData<String> {
        return mutableMovie
    }
}