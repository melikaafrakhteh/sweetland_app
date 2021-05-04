package com.afrakhteh.sweetlandapp.view.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.data.model.SweetsModel
import com.afrakhteh.sweetlandapp.util.Constants
import com.afrakhteh.sweetlandapp.util.getProgressDrawable
import com.afrakhteh.sweetlandapp.util.loadingImage
import com.afrakhteh.sweetlandapp.view.fragments.HomeFragmentDirections

class HomeListAdapter(private val context: Context, private val sweetList: ArrayList<SweetsModel>)
    : RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {

    fun updateList( newList: List<SweetsModel>){
        sweetList.clear()
        sweetList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.sweetlist_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: SweetsModel = sweetList[position]
        holder.setData(model, position)
    }

    override fun getItemCount() = sweetList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.itemlist_textView_showsweetname)!!
        val image = itemView.findViewById<ImageView>(R.id.itemlist_imageView_showsweetimage)!!
        val card = itemView.findViewById<CardView>(R.id.item_card)!!

        fun setData(model: SweetsModel, position: Int) {
            name.text = model.name

            image.loadingImage(model.image, getProgressDrawable(image.context))

            card.setOnClickListener{
                val action = R.id.action_homeFragment_to_detailFragment
                var itemId = model.id
                var bundle = Bundle()

                bundle.putInt(Constants.ID,itemId)
                bundle.putString(Constants.NAME,model.name)
                bundle.putString(Constants.IMAGE,model.image)
                bundle.putString(Constants.DESC, model.description)
                bundle.putString(Constants.RECIPE,model.recipe)
                bundle.putString(Constants.TIME,model.time)

                it.findNavController().navigate(action,bundle)
            }
        }

    }
}