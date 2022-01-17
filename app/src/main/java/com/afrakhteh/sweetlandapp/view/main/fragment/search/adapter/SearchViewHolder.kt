package com.afrakhteh.sweetlandapp.view.main.fragment.search.adapter

import androidx.recyclerview.widget.RecyclerView
import com.afrakhteh.sweetlandapp.databinding.SweetSecondItemLayoutBinding
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import okhttp3.internal.Util

class SearchViewHolder(
    private val binding: SweetSecondItemLayoutBinding,
    private val onClick: (SweetsEntity) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun bind (data: SweetsEntity) {
        binding.secondLayoutNameTv.text = data.name
        binding.secondItemCard.setOnClickListener{onClick.invoke(data)}
    }
}