package com.afrakhteh.sweetlandapp.view.main.fragment.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.afrakhteh.sweetlandapp.databinding.SweetSecondItemLayoutBinding
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.model.repository.network.MainRepository
import com.afrakhteh.sweetlandapp.view.main.fragment.home.adapter.articleList.ArticleViewHolder
import okhttp3.internal.Util


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
