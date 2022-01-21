package com.afrakhteh.sweetlandapp.view.main.fragment.favorite.adapter

import androidx.recyclerview.widget.RecyclerView
import com.afrakhteh.sweetlandapp.databinding.SweetSecondItemLayoutBinding
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity

class FaveViewHolder(
    private val binding: SweetSecondItemLayoutBinding,
    private val onClick: (SweetsEntity) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun binding(sweetsEntity: SweetsEntity) {
        with(binding) {
            secondLayoutNameTv.text = sweetsEntity.name
            secondLayoutNameTv.setOnClickListener { onClick.invoke(sweetsEntity) }
        }
    }
}