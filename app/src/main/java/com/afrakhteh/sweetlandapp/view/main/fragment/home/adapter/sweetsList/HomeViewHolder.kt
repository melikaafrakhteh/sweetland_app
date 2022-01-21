package com.afrakhteh.sweetlandapp.view.main.fragment.home.adapter.sweetsList

import androidx.recyclerview.widget.RecyclerView
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.databinding.SweetListRowBinding
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.model.useCase.favorite.IsSweetLikedUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewHolder (
       private val binding: SweetListRowBinding,
       private val onClick: (SweetsEntity) -> Unit,
       private val faveClick: (SweetsEntity) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun bind(data: SweetsEntity) {
        with(binding) {
            sweetsRowName.text = data.name
            sweetsRowTime.text = data.time
            itemCard.setOnClickListener { onClick.invoke(data) }
            sweetsRowLikedTgl.isChecked = data.isFave
            sweetsRowLikedTgl.setOnClickListener { faveClick.invoke(data) }
        }
    }

}