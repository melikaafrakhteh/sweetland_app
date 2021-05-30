package com.afrakhteh.sweetlandapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.data.database.FaveDataBase
import com.afrakhteh.sweetlandapp.data.model.FaveModel
import com.afrakhteh.sweetlandapp.repository.FaveRepository
import com.afrakhteh.sweetlandapp.util.Constants
import com.afrakhteh.sweetlandapp.view.adapter.FaveAdapter
import com.afrakhteh.sweetlandapp.viewmodel.FaveViewModel
import com.afrakhteh.sweetlandapp.viewmodel.FaveViewModelFactory
import kotlinx.android.synthetic.main.fragment_fav.*

class FavFragment : BaseFragment() {

    override var bottomNavigationViewVisibility = View.VISIBLE

    private var uid: Int = 0
    private lateinit var faveAdapter: FaveAdapter
    private var faveList: List<FaveModel> = ArrayList()


    private val db = FaveDataBase(requireContext())
    private val repository = FaveRepository(db)
    private val factory = FaveViewModelFactory(repository)
    private lateinit var viewModel: FaveViewModel

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

        viewModel = ViewModelProviders.of(this,factory).get(FaveViewModel::class.java)

        setupRecyclerView()
        getAllData()

    }

    private fun getAllData() {
        viewModel.showAllFaves.observe(
            viewLifecycleOwner, Observer { fave ->
                fave?.let {
                    faveAdapter.getAllData(fave)
                }
            }
        )
    }

    private fun setupRecyclerView() {
        fav_fragment_recycler_showfavlist.apply {
            layoutManager = LinearLayoutManager(context)
            faveAdapter = FaveAdapter(context, faveList)
            adapter = faveAdapter
            faveAdapter.notifyDataSetChanged()
            hasFixedSize()
        }
    }
}