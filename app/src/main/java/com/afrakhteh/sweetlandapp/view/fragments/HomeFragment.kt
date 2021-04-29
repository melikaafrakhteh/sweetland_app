package com.afrakhteh.sweetlandapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.data.model.SweetsModel
import com.afrakhteh.sweetlandapp.util.Sizes
import com.afrakhteh.sweetlandapp.view.adapter.HomeListAdapter
import com.afrakhteh.sweetlandapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var homeAdapter: HomeListAdapter
    private val sweetList: ArrayList<SweetsModel> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.refreshList()

        setupRecycler()
    }

    private fun setupRecycler() {
        home_fragment_rvRecycler.apply {
            hasFixedSize()
            layoutManager = GridLayoutManager(context, Sizes.RECYCLER_SPAN_COUNT)
            homeAdapter = HomeListAdapter(context, sweetList)
            adapter = homeAdapter
        }

    }
}