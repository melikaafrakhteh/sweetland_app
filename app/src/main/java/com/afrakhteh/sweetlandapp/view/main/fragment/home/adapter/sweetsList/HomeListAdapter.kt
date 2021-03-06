package com.afrakhteh.sweetlandapp.view.main.fragment.home.adapter.sweetsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afrakhteh.sweetlandapp.databinding.SweetListRowBinding
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.model.repository.network.MainRepository
import com.afrakhteh.sweetlandapp.model.useCase.favorite.IsSweetLikedUseCase


class HomeListAdapter(
        private val sweetList: List<SweetsEntity>,
        private val onClick: (SweetsEntity) -> Unit,
        private val faveClick: (SweetsEntity) -> Unit,
        private val repository: MainRepository
) : RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SweetListRowBinding.inflate(layoutInflater,parent,false)
        return HomeViewHolder(binding, onClick , faveClick, repository)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val model: SweetsEntity = sweetList[position]
        holder.bind(model)
    }

    override fun getItemCount(): Int {
        return if (sweetList.isEmpty()) 0 else sweetList.size
    }

    override fun onViewAttachedToWindow(holder: HomeViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.loadImaged(sweetList[holder.absoluteAdapterPosition].image!!)
    }

    override fun onViewDetachedFromWindow(holder: HomeViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.disposeLoadImage()
    }
}