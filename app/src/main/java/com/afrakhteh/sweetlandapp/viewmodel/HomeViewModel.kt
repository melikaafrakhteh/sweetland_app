package com.afrakhteh.sweetlandapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afrakhteh.sweetlandapp.data.api.SweetApiService
import com.afrakhteh.sweetlandapp.data.model.SweetsModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {

    private val service = SweetApiService()
    private val disposable = CompositeDisposable()

    val sweetList = MutableLiveData<List<SweetsModel>>()
    val loadingError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()


    fun refreshList() {
        fetchFromServer()
    }

    private fun fetchFromServer() {
        loading.value = true
        disposable.add(service.getSweets()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<SweetsModel>>() {
                    override fun onSuccess(t: List<SweetsModel>) {
                        loadingError.value = false
                        loading.value = false
                        sweetList.value = t
                    }

                    override fun onError(e: Throwable) {
                        loadingError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }

                }
                ))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}