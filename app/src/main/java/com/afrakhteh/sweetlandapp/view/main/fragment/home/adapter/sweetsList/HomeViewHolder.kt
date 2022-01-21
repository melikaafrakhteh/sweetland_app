package com.afrakhteh.sweetlandapp.view.main.fragment.home.adapter.sweetsList

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.databinding.SweetListRowBinding
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.model.repository.network.MainRepository
import com.afrakhteh.sweetlandapp.model.useCase.favorite.IsSweetLikedUseCase
import com.afrakhteh.sweetlandapp.util.resize
import com.afrakhteh.sweetlandapp.util.toBitmap
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewHolder (
       private val binding: SweetListRowBinding,
       private val onClick: (SweetsEntity) -> Unit,
       private val faveClick: (SweetsEntity) -> Unit,
       private val repository: MainRepository
): RecyclerView.ViewHolder(binding.root) {

    private val disposable = CompositeDisposable()

    fun bind(data: SweetsEntity) {
        with(binding) {
            sweetsRowName.text = data.name
            sweetsRowTime.text = data.time
            sweetsRowImage.setImageResource(R.drawable.cupcake)
            itemCard.setOnClickListener { onClick.invoke(data) }
            sweetsRowLikedTgl.isChecked = data.isFave
            sweetsRowLikedTgl.setOnClickListener { faveClick.invoke(data) }
        }
    }

    @SuppressLint("CheckResult")
    fun loadImaged(image: String) {
        repository.getImages(image)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                if (it == null) {
                    return@subscribeBy
                }
                val bitmap = it.toBitmap()?.resize()
                binding.sweetsRowImage.setImageBitmap(bitmap)
            }.addTo(disposable)
    }

    fun disposeLoadImage() {
        disposable.dispose()
    }

}