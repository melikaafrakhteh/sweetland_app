package com.afrakhteh.sweetlandapp.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.data.model.SweetsModel
import com.afrakhteh.sweetlandapp.util.Sizes
import com.afrakhteh.sweetlandapp.view.adapter.HomeListAdapter
import com.afrakhteh.sweetlandapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var homeAdapter: HomeListAdapter
    private val sweetList: ArrayList<SweetsModel> = ArrayList()

    override var bottomNavigationViewVisibility = View.VISIBLE

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
        setupViewModel()
        setupSearchBar()
    }

    private fun setupSearchBar() {
        home_fragment_tv_searchbar.setOnClickListener {

            val action = R.id.action_homeFragment_to_searchFragment
            Navigation.findNavController(it).navigate(action)

        }

    }

    private fun setupViewModel() {
        viewModel.sweetList.observe(viewLifecycleOwner, Observer { sweets ->
            sweets?.let {
                home_fragment_rvRecycler.visibility = View.VISIBLE
                homeAdapter.updateList(sweets)
            }
        })

        viewModel.loadingError.observe(viewLifecycleOwner, Observer {

            sweet ->
            sweet?.let {
                home_fragment_tv_error.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { sweet ->
            sweet?.let {
                home_fragment_progressbar_loading.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    home_fragment_rvRecycler.visibility = View.GONE
                    home_fragment_tv_error.visibility = View.GONE
                }
            }
        })
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