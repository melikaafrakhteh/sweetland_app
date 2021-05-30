package com.afrakhteh.sweetlandapp.view.fragments

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.data.model.SearchModel
import com.afrakhteh.sweetlandapp.view.adapter.SearchAdapter
import com.afrakhteh.sweetlandapp.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : BaseFragment() {

    override var bottomNavigationViewVisibility = View.GONE

    private lateinit var searchAdapter: SearchAdapter
    private var searchList: ArrayList<SearchModel> = ArrayList()


    private lateinit var viewModel: SearchViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        viewModel.loadingData()

        searchAdapter = SearchAdapter(requireContext(),searchList)
        setupRecycler()
        viewModelObserver()
    }

    private fun viewModelObserver() {
        viewModel.searchList.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                searchAdapter.updateSearch(list)
            }
        })
    }

    private fun setupRecycler() {

        search_fragment_recycler_showfavlist.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)

            adapter = searchAdapter

        }
    }
}