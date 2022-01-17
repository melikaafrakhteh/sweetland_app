package com.afrakhteh.sweetlandapp.view.main.fragment.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.afrakhteh.sweetlandapp.databinding.SweetSecondItemLayoutBinding
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import okhttp3.internal.Util


class SearchAdapter(
private val onClick: (SweetsEntity) -> Unit
) : ListAdapter<SweetsEntity, SearchViewHolder> (SearchDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SweetSecondItemLayoutBinding.inflate(inflater, parent, false)
        return SearchViewHolder(binding,onClick)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
       holder.bind(getItem(position))
    }
}
