package com.afrakhteh.sweetlandapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afrakhteh.sweetlandapp.di.scope.ViewModelScope
import com.afrakhteh.sweetlandapp.model.repository.network.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ViewModelScope
class ArticleDetailViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val pImage = MutableLiveData<ByteArray?>()
    val images: LiveData<ByteArray?> get() = pImage

    fun getImage(url: String) {
        repository.getImages(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                if (it == null) {
                    return@subscribeBy
                } else {
                    pImage.postValue(it)
                }
            }
    }

}