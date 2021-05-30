package com.afrakhteh.sweetlandapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afrakhteh.sweetlandapp.data.api.SweetApiService
import com.afrakhteh.sweetlandapp.data.model.SearchModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SearchViewModel :ViewModel() {

    private val service = SweetApiService()
    private val disposable = CompositeDisposable()

    var searchList = MutableLiveData<List<SearchModel>>()

    fun loadingData(){

        disposable.add(service.search()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<SearchModel>>() {

                override fun onSuccess(t: List<SearchModel>) {
                   searchList.value = t
                }

                override fun onError(e: Throwable) {
                   e.stackTraceToString()
                }

            }))

    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}