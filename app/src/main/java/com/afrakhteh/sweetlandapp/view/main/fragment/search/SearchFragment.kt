package com.afrakhteh.sweetlandapp.view.main.fragment.search

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.constants.Strings
import com.afrakhteh.sweetlandapp.databinding.FragmentSearchBinding
import com.afrakhteh.sweetlandapp.di.builder.ViewModelComponentBuilder
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.view.main.fragment.search.adapter.SearchAdapter
import com.afrakhteh.sweetlandapp.view.main.fragment.search.adapter.SearchDiffUtilCallBack
import com.afrakhteh.sweetlandapp.view.main.interfaces.NavigationVisibility
import com.afrakhteh.sweetlandapp.view.main.state.SearchState
import com.afrakhteh.sweetlandapp.view.main.state.SweetsState
import com.afrakhteh.sweetlandapp.viewmodel.SearchViewModel
import com.jakewharton.rxbinding2.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.internal.Util
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory
    private val viewModel: SearchViewModel by viewModels { viewModelProvider }

    private lateinit var searchAdapter: SearchAdapter

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        ViewModelComponentBuilder.getInstance().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.searchState.observe(viewLifecycleOwner, ::renderAllList)
        searchAdapter = SearchAdapter(::itemClick, viewModel.repository)
        setupSearch()
        binding.searchFragmentBackTv.setOnClickListener(::goToHome)
    }

    private fun goToHome(view: View?) {
        val action = R.id.action_searchFragment_to_homeFragment
        Navigation.findNavController(requireView()).navigate(action)
    }

    private fun itemClick(sweetsEntity: SweetsEntity) {
        val action = R.id.action_searchFragment_to_detailFragment
        val bundle = Bundle()
        bundle.apply {
            putInt(Strings.POSITION_KEY, sweetsEntity.id!!)
            putString(Strings.DESC_KEY, sweetsEntity.description)
            putString(Strings.NAME_KEY, sweetsEntity.name)
            putString(Strings.RECIPE_KEY, sweetsEntity.recipe)
            putString(Strings.TIME_KEY, sweetsEntity.time)
            putString(Strings.Url_KEY, sweetsEntity.image)
        }
        Navigation.findNavController(requireView()).navigate(action, bundle)
    }

    private fun renderAllList(state: SearchState?) {
        state?.error?.ifNotHandled { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
            binding.searchFragmentProgress.visibility = View.GONE
        }
        if (state!!.loading) {
            binding.searchFragmentProgress.visibility = View.VISIBLE
        } else {
            binding.searchFragmentRecycler.adapter = searchAdapter
            binding.searchFragmentRecycler.setItemViewCacheSize(20)
            searchAdapter.submitList(ArrayList(state.listOfSweets))
            binding.searchFragmentProgress.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as NavigationVisibility).apply {
            bottomNavigationVisible(View.GONE)
        }
    }

    @SuppressLint("CheckResult")
    private fun setupSearch() {
        binding.searchEdit.textChanges()
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribe {
                viewModel
                    .search(it.toString())
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        searchAdapter.submitList(viewModel.filterList.value)
                    }
            }
    }
}
