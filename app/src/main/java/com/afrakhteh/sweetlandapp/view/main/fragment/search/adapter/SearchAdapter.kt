package com.afrakhteh.sweetlandapp.view.main.fragment.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.AnimationUtils.loadAnimation
import androidx.recyclerview.widget.ListAdapter
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.databinding.SweetSecondItemLayoutBinding
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.model.repository.network.MainRepository



class SearchAdapter(
private val onClick: (SweetsEntity) -> Unit,
private val repository: MainRepository
) : ListAdapter<SweetsEntity, SearchViewHolder> (SearchDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SweetSecondItemLayoutBinding.inflate(inflater, parent, false)
        return SearchViewHolder(binding,onClick, repository)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
       holder.bind(getItem(position))
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.item_animation)
    }

    override fun onViewAttachedToWindow(holder: SearchViewHolder) {
        holder.loadImaged(getItem(holder.absoluteAdapterPosition).image!!)
        super.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: SearchViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.disposeLoadImage()
    }

}
