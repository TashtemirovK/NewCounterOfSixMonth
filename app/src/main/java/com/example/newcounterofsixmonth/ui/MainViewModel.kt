package com.example.newcounterofsixmonth.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    private var counter = 0

    private val _counterLV = MutableLiveData<Int>()
    val counterLV = _counterLV as LiveData<Int>

    fun inc() {
        counter -= 1
        _counterLV.value = counter
    }

    fun dec() {
        counter += 1
        _counterLV.value = counter
    }
}