package com.afrakhteh.sweetlandapp.view.main.fragment.favorite.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.databinding.SweetSecondItemLayoutBinding
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.model.repository.network.MainRepository
import com.afrakhteh.sweetlandapp.util.resize
import com.afrakhteh.sweetlandapp.util.toBitmap
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class FaveViewHolder(
    private val binding: SweetSecondItemLayoutBinding,
    private val onClick: (SweetsEntity) -> Unit,
    private val repository: MainRepository
) : RecyclerView.ViewHolder(binding.root) {

    private val disposable = CompositeDisposable()

    fun binding(sweetsEntity: SweetsEntity) {
        with(binding) {
            secondLayoutNameTv.text = sweetsEntity.name
            secondLayoutNameTv.setOnClickListener { onClick.invoke(sweetsEntity) }
            secondLayoutImageIv.setImageResource(R.drawable.cupcake)
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
                binding.secondLayoutImageIv.setImageBitmap(bitmap)
            }.addTo(disposable)
    }

    fun disposeLoadImage() {
        disposable.dispose()
    }
}