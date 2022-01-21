package com.afrakhteh.sweetlandapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afrakhteh.sweetlandapp.di.scope.ViewModelScope
import com.afrakhteh.sweetlandapp.model.data.database.FaveDataBase
import com.afrakhteh.sweetlandapp.model.entities.FavoriteEntity
import com.afrakhteh.sweetlandapp.model.repository.network.MainRepository
import com.afrakhteh.sweetlandapp.model.useCase.favorite.ShowAllFaveListUseCase
import com.afrakhteh.sweetlandapp.view.main.state.ArticlesState
import com.afrakhteh.sweetlandapp.view.main.state.FaveState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@ViewModelScope
class FaveViewModel @Inject constructor(
    private val showAllFaveListUseCase: ShowAllFaveListUseCase,
    val repository: MainRepository
) : ViewModel() {

    private var job : Job? = null
    private val pFaveState = MutableLiveData<FaveState> ()
    val faveState: LiveData<FaveState> get() = pFaveState

      fun fetchAllFaveList() {
        job = CoroutineScope(Dispatchers.IO).launch {
            showAllFaveListUseCase().let {
                pFaveState.postValue(FaveState(data = it))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}

