package com.afrakhteh.sweetlandapp.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afrakhteh.sweetlandapp.di.scope.ViewModelScope
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.model.repository.network.MainRepository
import com.afrakhteh.sweetlandapp.model.useCase.article.GetAllArticlesUseCase
import com.afrakhteh.sweetlandapp.model.useCase.favorite.DeleteOneFavoriteUseCase
import com.afrakhteh.sweetlandapp.model.useCase.favorite.InsertFaveUseCase
import com.afrakhteh.sweetlandapp.model.useCase.favorite.IsSweetLikedUseCase
import com.afrakhteh.sweetlandapp.model.useCase.sweets.GetAllSweetsUseCase
import com.afrakhteh.sweetlandapp.util.NetworkResponse
import com.afrakhteh.sweetlandapp.util.SingleEvent
import com.afrakhteh.sweetlandapp.view.main.state.ArticlesState
import com.afrakhteh.sweetlandapp.view.main.state.SweetsState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import javax.inject.Inject

@ViewModelScope
class HomeViewModel @Inject constructor(
    private val getAllSweetsUseCase: GetAllSweetsUseCase,
    private val getAllArticlesUseCase: GetAllArticlesUseCase,
    private val insertFaveUseCase: InsertFaveUseCase,
    private val deleteOneFavoriteUseCase: DeleteOneFavoriteUseCase,
    private val isSweetLikedUseCase: IsSweetLikedUseCase,
    val repository: MainRepository
) : ViewModel() {

    private val disposable = CompositeDisposable()
    private var addJob: Job? = null
    private var deleteJob: Job? = null
    private var checkJob: Job? = null

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

    @SuppressLint("CheckResult")
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
                            SweetsState(
                                listOfSweets = response.data ?: emptyList()
                            )
                        )
                    }
                }
            }.addTo(disposable)
    }

    fun addToFavoriteList(sweetsEntity: SweetsEntity) {
        addJob = CoroutineScope(Dispatchers.IO).launch {
            insertFaveUseCase(sweetsEntity)
        }
    }

    fun deleteFromFavorite(sweetsEntity: SweetsEntity) {
        deleteJob = CoroutineScope(Dispatchers.IO).launch {
            deleteOneFavoriteUseCase(sweetsEntity)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
        addJob?.cancel()
        deleteJob?.cancel()
    }

    suspend fun checkIsFave(name: String): Boolean {
        return isSweetLikedUseCase(name)
    }
}