package com.afrakhteh.sweetlandapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afrakhteh.sweetlandapp.di.scope.ViewModelScope
import com.afrakhteh.sweetlandapp.model.entities.ArticleEntity
import com.afrakhteh.sweetlandapp.model.repository.MainRepository
import com.afrakhteh.sweetlandapp.model.useCase.article.GetAllArticlesUseCase
import com.afrakhteh.sweetlandapp.model.useCase.sweets.GetAllSweetsUseCase
import com.afrakhteh.sweetlandapp.util.NetworkResponse
import com.afrakhteh.sweetlandapp.util.SingleEvent
import com.afrakhteh.sweetlandapp.view.main.state.ArticlesState
import com.afrakhteh.sweetlandapp.view.main.state.SweetsState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ViewModelScope
class HomeViewModel @Inject constructor(
    private val getAllSweetsUseCase: GetAllSweetsUseCase,
    private val getAllArticlesUseCase: GetAllArticlesUseCase,
    val repository: MainRepository
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val pSweetState = MutableLiveData<SweetsState>()
    val sweetState: LiveData<SweetsState> get() = pSweetState

    private val pArticlesState = MutableLiveData<ArticlesState>()
    val articlesState: LiveData<ArticlesState> get() = pArticlesState

    init {
        fetchSweetsListFromServer()
        fetchArticlesFromServer()
    }

    private fun fetchArticlesFromServer() {
        getAllArticlesUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                when (result) {
                    is NetworkResponse.Error -> {
                        pArticlesState.postValue(
                            ArticlesState(
                                errorMessage = SingleEvent(
                                    "خطایی در دریافت لیست مقاله ها رخ داده است"
                                )
                            )
                        )
                    }
                    is NetworkResponse.Success -> {
                        pArticlesState.postValue(
                            ArticlesState(
                                data = result.data ?: emptyList()
                            )
                        )
                    }
                    else -> {
                    }
                }

            }.addTo(disposable)
    }

    private fun fetchSweetsListFromServer() {
        getAllSweetsUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                when (response) {
                    is NetworkResponse.Loading -> {
                        pSweetState.postValue(
                            SweetsState(loading = true)
                        )
                    }
                    is NetworkResponse.Error -> {
                        pSweetState.postValue(
                            SweetsState(
                                error = SingleEvent("خطایی در دریافت اطلاعات رخ داده است")
                            )
                        )
                    }
                    is NetworkResponse.Success -> {
                        pSweetState.postValue(
                            SweetsState(listOfSweets = response.data ?: emptyList())
                        )
                    }
                }
            }.addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}