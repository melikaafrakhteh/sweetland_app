package com.afrakhteh.sweetlandapp.view.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.data.model.FaveModel
import com.afrakhteh.sweetlandapp.util.Constants
import com.afrakhteh.sweetlandapp.util.getProgressDrawable
import com.afrakhteh.sweetlandapp.util.loadingImage
import kotlinx.android.synthetic.main.sweet_second_item_layout.view.*

class FaveAdapter(private val context: Context, var faveList: List<FaveModel> = mutableListOf())
    : RecyclerView.Adapter<FaveAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sweet_second_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = faveList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = faveList[position]
        holder.setData(model)
    }

    public fun getAllData(fave: List<FaveModel>){
        faveList = fave
        notifyDataSetChanged()
    }


    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {


        fun setData(model: FaveModel) {

            itemView.second_layout_name_tv.text = model.name
            itemView.second_layout_time_tv.text = model.time

            itemView.second_layout_image_iv.loadingImage(model.image, getProgressDrawable(context))

            itemView.second_item_card.setOnClickListener {
                val action = R.id.action_favFragment_to_detailFragment
                val faveID = model.id
                val bundle = Bundle()
                bundle.getInt(Constants.ID, faveID)
                Navigation.findNavController(it).navigate(action, bundle)
            }
        }

    }
}