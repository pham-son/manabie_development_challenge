package com.mobile.todoapplication.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.todoapplication.entities.Error
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/***
 * Created by Phạm Sơn on 6/11/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
open class BaseViewModel : ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val _errorLiveData: MutableLiveData<Error?> = MutableLiveData()

    fun getLoading(): MutableLiveData<Boolean> = _loadingLiveData
    fun getError(): MutableLiveData<Error?> = _errorLiveData

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun clearDisposables() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        clearDisposables()
    }
}