package com.afrakhteh.sweetlandapp.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.data.model.SearchModel
import com.afrakhteh.sweetlandapp.view.adapter.SearchAdapter
import com.afrakhteh.sweetlandapp.view.clasess.SearchDiffUtilCallBack
import com.afrakhteh.sweetlandapp.viewmodel.SearchViewModel
import com.jakewharton.rxbinding2.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.concurrent.TimeUnit


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

        setupRecycler()
        setupSearch()

    }

    @SuppressLint("CheckResult")
    private fun setupSearch() {
        search_edit.
                textChanges()
            .debounce(500,TimeUnit.MILLISECONDS)
            .subscribe {
                viewModel
                    .search(it.toString())
                    .subscribeOn(Schedulers.computation())

                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        val diffResult = DiffUtil.calculateDiff(
                            SearchDiffUtilCallBack(viewModel.searchList,viewModel.filterList)
                        )
                        viewModel.searchList.clear()

                        viewModel.searchList.addAll(viewModel.filterList)
                        diffResult.dispatchUpdatesTo(search_fragment_recycler_showfavlist.adapter!!)
                    }
            }
    }

    private fun setupRecycler() {
        search_fragment_recycler_showfavlist.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
            searchAdapter = SearchAdapter(requireContext(), viewModel.searchList)
            adapter = searchAdapter

        }
    }
}