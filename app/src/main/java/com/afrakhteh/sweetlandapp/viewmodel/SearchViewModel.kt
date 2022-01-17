package com.afrakhteh.sweetlandapp.viewmodel

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.model.useCase.sweets.SearchSweetsUseCase
import com.afrakhteh.sweetlandapp.util.NetworkResponse
import com.afrakhteh.sweetlandapp.util.SingleEvent
import com.afrakhteh.sweetlandapp.view.main.fragment.search.adapter.SearchDiffUtilCallBack
import com.afrakhteh.sweetlandapp.view.main.state.SearchState
import com.afrakhteh.sweetlandapp.view.main.state.SweetsState
import com.jakewharton.rxbinding2.widget.textChanges
import com.jakewharton.rxrelay3.PublishRelay
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SearchViewModel @Inject constructor(
    private val searchSweetsUseCase: SearchSweetsUseCase
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val pSearchState = MutableLiveData<SearchState>()
    val searchState: LiveData<SearchState> get() = pSearchState

    private val pFilterList = MutableLiveData<List<SweetsEntity>>()
    val filterList: LiveData<List<SweetsEntity>> get() = pFilterList

    init {
        loadingData()
    }

    private fun loadingData() {
        searchSweetsUseCase()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                when (response) {
                    is NetworkResponse.Error -> {
                        pSearchState.postValue(
                            SearchState
                                (
                                error = SingleEvent("خطایی در دریافت اطلاعات رخ داده است")
                            )
                        )
                    }
                    is NetworkResponse.Loading -> {
                        pSearchState.postValue(SearchState(loading = true))
                    }
                    is NetworkResponse.Success -> {
                        pSearchState.postValue(
                            SearchState(
                                listOfSweets = response.data as MutableList<SweetsEntity>
                            )
                        )
                    }
                }

            }.addTo(disposable)
    }


    // custom completable object which notifies our recyclerview of any changes in the list
    fun search(query: String): Completable = Completable.create {
        val wanted = pSearchState.value?.listOfSweets?.filter {sweet ->
            sweet.name!!.contains(query)
        }?.toList()
        pFilterList.postValue(requireNotNull(wanted))
        it.onComplete()
    }

        override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
