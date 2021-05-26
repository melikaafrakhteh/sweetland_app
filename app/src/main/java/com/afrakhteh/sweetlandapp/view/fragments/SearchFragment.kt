package com.afrakhteh.sweetlandapp.view.fragments

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afrakhteh.sweetlandapp.R
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : BaseFragment() {

    override var bottomNavigationViewVisibility  = View.GONE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
    }

    private fun setupRecycler() {
        search_fragment_recycler_showfavlist.apply {
            hasFixedSize()

        }
    }
}