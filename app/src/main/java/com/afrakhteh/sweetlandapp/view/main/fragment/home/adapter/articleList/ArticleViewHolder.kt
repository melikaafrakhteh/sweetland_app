package com.afrakhteh.sweetlandapp.view.main.fragment.home.adapter.articleList

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.afrakhteh.sweetlandapp.databinding.SweetSecondItemLayoutBinding
import com.afrakhteh.sweetlandapp.model.entities.ArticleEntity
import com.afrakhteh.sweetlandapp.model.repository.network.MainRepository
import com.afrakhteh.sweetlandapp.util.resize
import com.afrakhteh.sweetlandapp.util.toBitmap
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ArticleViewHolder(
    private val binding: SweetSecondItemLayoutBinding,
    private val repository: MainRepository,
    private val onClick: (ArticleEntity) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private val disposable = CompositeDisposable()

    fun bind(data: ArticleEntity) {
        with(binding) {
            secondLayoutNameTv.text = data.title
            secondItemCard.setOnClickListener { onClick.invoke(data) }
        }
    }

    @SuppressLint("CheckResult")
    fun loadImaged(image: String) {
        repository.getImages(image)
            .subscribeOn(Schedulers.computation())
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