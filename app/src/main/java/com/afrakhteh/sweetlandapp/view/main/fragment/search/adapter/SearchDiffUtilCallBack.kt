package com.afrakhteh.sweetlandapp.view.main.fragment.search.adapter

import androidx.recyclerview.widget.DiffUtil
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity

class SearchDiffUtilCallBack : DiffUtil.ItemCallback<SweetsEntity>() {
    override fun areItemsTheSame(oldItem: SweetsEntity, newItem: SweetsEntity): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: SweetsEntity, newItem: SweetsEntity): Boolean {
        return oldItem == newItem
    }

}