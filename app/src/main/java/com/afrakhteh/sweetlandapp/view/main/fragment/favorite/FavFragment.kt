package com.afrakhteh.sweetlandapp.view.main.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.model.entities.FavoriteEntity
import com.afrakhteh.sweetlandapp.util.Constants
//import com.afrakhteh.sweetlandapp.viewmodel.FaveViewModel
import kotlinx.android.synthetic.main.fragment_fav.*

class FavFragment : Fragment() {

   /* private var uid: Int = 0
    private lateinit var faveAdapter: FaveAdapter
    private var faveList: List<FavoriteEntity> = ArrayList()

    //private lateinit var viewModel: FaveViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        uid = arguments?.getInt(Constants.ID)!!

      //  viewModel = ViewModelProviders.of(this).get(FaveViewModel::class.java)
        faveAdapter = FaveAdapter(requireContext(), faveList)
        getAllData()
        setupRecyclerView()

    }

    private fun getAllData() {
    *//* viewModel.showAllFaves().observe(
                viewLifecycleOwner, Observer { fave ->
            fave?.let {
                faveAdapter.getAllData(fave)
            }
        }
        )*//*
    }

    private fun setupRecyclerView() {
        fav_fragment_recycler_showfavlist.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = faveAdapter
            faveAdapter.notifyDataSetChanged()
            hasFixedSize()
        }
    }*/
}