package com.afrakhteh.sweetlandapp.view.clasess

import androidx.recyclerview.widget.DiffUtil
import com.afrakhteh.sweetlandapp.data.model.SearchModel


/*DiffUtil has the responsibility of calculating the difference of each search iteration with the
previous one and devising a series of action to convert the old list to the new one*/

class SearchDiffUtilCallBack(
    private val oldList: List<SearchModel>,
    private val newList: List<SearchModel>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = true
}