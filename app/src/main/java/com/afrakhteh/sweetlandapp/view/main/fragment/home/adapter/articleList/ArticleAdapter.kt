package com.afrakhteh.sweetlandapp.view.main.fragment.home.adapter.articleList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afrakhteh.sweetlandapp.databinding.SweetSecondItemLayoutBinding
import com.afrakhteh.sweetlandapp.model.entities.ArticleEntity
import com.afrakhteh.sweetlandapp.model.repository.MainRepository

class ArticleAdapter(
    private val list: List<ArticleEntity>,
    private val repository: MainRepository,
    private val onClick: (ArticleEntity) -> Unit
) : RecyclerView.Adapter<ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SweetSecondItemLayoutBinding.inflate(
            inflater, parent, false
        )
        return ArticleViewHolder(binding, repository, onClick)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
       holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return if (list.isEmpty()) 0 else list.size
    }

    override fun onViewAttachedToWindow(holder: ArticleViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.loadImaged((holder.absoluteAdapterPosition  + 1).toString())
    }

    override fun onViewDetachedFromWindow(holder: ArticleViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.disposeLoadImage()
    }
}