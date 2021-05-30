package com.afrakhteh.sweetlandapp.view.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.data.model.SearchModel
import com.afrakhteh.sweetlandapp.util.Constants
import com.afrakhteh.sweetlandapp.util.getProgressDrawable
import com.afrakhteh.sweetlandapp.util.loadingImage

class SearchAdapter(
    private val context: Context,
    private val searchList: ArrayList<SearchModel>
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {


    fun updateSearch(newList: List<SearchModel>) {
        searchList.clear()
        searchList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = searchList.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(
           LayoutInflater.from(context)
               .inflate(R.layout.sweet_second_item_layout,parent,false)
       )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = searchList[position]
        holder.setItem(model, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.findViewById<TextView>(R.id.second_layout_name_tv)
        private val time = itemView.findViewById<TextView>(R.id.second_layout_time_tv)
        private val imageView = itemView.findViewById<ImageView>(R.id.second_layout_image_iv)
        private val card = itemView.findViewById<CardView>(R.id.second_item_card)


        fun setItem(model: SearchModel, position: Int) {
            title.text = model.name
            time.text = model.time

            imageView.loadingImage(model.image, getProgressDrawable(context))

            card.setOnClickListener {
                val action: Int = R.id.action_searchFragment_to_detailFragment
                val bundle = Bundle()

                bundle.getInt(Constants.ID, position)
                bundle.putString(Constants.NAME, model.name)
                bundle.putString(Constants.IMAGE, model.image)
                bundle.putString(Constants.DESC, model.description)
                bundle.putString(Constants.RECIPE, model.recipe)
                bundle.putString(Constants.TIME, model.time)

                it.findNavController().navigate(action, bundle)
            }


        }

    }


}