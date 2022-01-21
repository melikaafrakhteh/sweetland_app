package com.afrakhteh.sweetlandapp.view.main.fragment.favorite.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.afrakhteh.sweetlandapp.databinding.SweetSecondItemLayoutBinding
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.model.repository.network.MainRepository
import com.afrakhteh.sweetlandapp.view.main.fragment.home.adapter.articleList.ArticleViewHolder


class FaveAdapter(
    private val onClick:(SweetsEntity) -> Unit,
    private val repository: MainRepository
   ): ListAdapter<SweetsEntity, FaveViewHolder>(FaveDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaveViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SweetSecondItemLayoutBinding.inflate(inflater, parent, false)
        return FaveViewHolder(binding, onClick, repository)
    }

    override fun onBindViewHolder(holder: FaveViewHolder, position: Int) {
        holder.binding(getItem(position))
    }

    override fun onViewAttachedToWindow(holder: FaveViewHolder) {
        holder.loadImaged(getItem(holder.absoluteAdapterPosition).image!!)
        super.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: FaveViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.disposeLoadImage()
    }
}