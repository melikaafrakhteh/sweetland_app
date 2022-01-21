package com.afrakhteh.sweetlandapp.view.main.fragment.favorite.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.afrakhteh.sweetlandapp.databinding.SweetSecondItemLayoutBinding
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity


class FaveAdapter(
    private val onClick:(SweetsEntity) -> Unit
   ): ListAdapter<SweetsEntity, FaveViewHolder>(FaveDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaveViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SweetSecondItemLayoutBinding.inflate(inflater, parent, false)
        return FaveViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: FaveViewHolder, position: Int) {
        holder.binding(getItem(position))
    }
}