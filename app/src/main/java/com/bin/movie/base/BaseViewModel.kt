package com.bin.movie.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel()  {

    protected val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    protected val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    protected fun showLoading() {
        _loading.value = true
    }

    protected fun hideLoading() {
        if (_loading.value == true) {
            _loading.value = false
        }
    }

}
