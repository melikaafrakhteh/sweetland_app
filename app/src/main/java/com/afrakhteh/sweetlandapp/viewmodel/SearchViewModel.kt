package com.afrakhteh.sweetlandapp.viewmodel

import androidx.lifecycle.ViewModel
import com.afrakhteh.sweetlandapp.data.api.SweetApiService
import com.afrakhteh.sweetlandapp.data.model.SearchModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SearchViewModel : ViewModel() {

    private val service = SweetApiService()
    private val disposable = CompositeDisposable()

    private var originList = listOf<SearchModel>()
    var searchList: MutableList<SearchModel> = mutableListOf()
    var filterList: MutableList<SearchModel> = mutableListOf()

    fun loadingData() {

        disposable.add(service.search()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<SearchModel>>() {

                    override fun onSuccess(t: List<SearchModel>) {
                        originList = t
                        searchList.addAll(t)

                    }

                    override fun onError(e: Throwable) {
                        e.stackTraceToString()
                    }

                }))

    }

    // custom completable object which notifies our recyclerview of any changes in the list
    fun search(query: String): Completable = Completable.create {
        val wanted = originList.filter {
            it.name.contains(query)
        }.toList()

        filterList.clear()
        filterList.addAll(wanted)
        it.onComplete()
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}