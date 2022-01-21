package com.afrakhteh.sweetlandapp.view.main.fragment.favorite

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.constants.Strings
import com.afrakhteh.sweetlandapp.databinding.FragmentFavBinding
import com.afrakhteh.sweetlandapp.di.builder.ViewModelComponentBuilder
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.view.main.fragment.favorite.adapter.FaveAdapter
import com.afrakhteh.sweetlandapp.view.main.interfaces.NavigationVisibility
import com.afrakhteh.sweetlandapp.view.main.state.FaveState
import com.afrakhteh.sweetlandapp.viewmodel.FaveViewModel
import javax.inject.Inject


class FavFragment : Fragment() {
    private lateinit var binding: FragmentFavBinding

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory
    private val viewModel: FaveViewModel by viewModels { viewModelProvider }
    private lateinit var favAdapter: FaveAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        ViewModelComponentBuilder.getInstance().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchAllFaveList()
        favAdapter = FaveAdapter(::openDetailPage, viewModel.repository)
        binding.favFragmentRecycler.adapter = favAdapter
        binding.favFragmentRecycler.setItemViewCacheSize(20)
        viewModel.faveState.observe(viewLifecycleOwner, ::renderFaveList)
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as NavigationVisibility).apply {
            bottomNavigationVisible(View.VISIBLE)
        }
        viewModel.fetchAllFaveList()
    }

    private fun renderFaveList(faveState: FaveState?) {
        faveState?.errorMessage?.ifNotHandled {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
        }
        favAdapter.submitList(faveState?.data)
    }


    private fun openDetailPage(sweetsEntity: SweetsEntity) {
        val action = R.id.action_favFragment_to_detailFragment
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

}