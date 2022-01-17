package com.afrakhteh.sweetlandapp.view.main.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.constants.Strings
import com.afrakhteh.sweetlandapp.databinding.FragmentHomeBinding
import com.afrakhteh.sweetlandapp.di.builder.ViewModelComponentBuilder
import com.afrakhteh.sweetlandapp.model.entities.ArticleEntity
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.view.main.fragment.home.adapter.articleList.ArticleAdapter
import com.afrakhteh.sweetlandapp.view.main.fragment.home.adapter.sweetsList.HomeListAdapter
import com.afrakhteh.sweetlandapp.view.main.interfaces.NavigationVisibility
import com.afrakhteh.sweetlandapp.view.main.state.ArticlesState
import com.afrakhteh.sweetlandapp.view.main.state.SweetsState
import com.afrakhteh.sweetlandapp.viewmodel.HomeViewModel
import javax.inject.Inject

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory
    private val viewModel: HomeViewModel by viewModels { viewModelProvider }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewModelComponentBuilder.getInstance().inject(this)
        viewModel.sweetState.observe(requireActivity(), this::renderSweetsList)
        viewModel.articlesState.observe(requireActivity(), this::renderArticlesList)
        binding.homeFragmentSearchIcon.setOnClickListener(::openSearchFragment)
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as NavigationVisibility).apply {
            bottomNavigationVisible(View.VISIBLE)
        }
    }

    private fun renderArticlesList(articlesState: ArticlesState) {
        articlesState.errorMessage?.ifNotHandled { error ->
            binding.homeFragmentTvError.text = error
        }
        setUpArticlesRecycler(articlesState.data)
    }

    private fun setUpArticlesRecycler(data: List<ArticleEntity>) {
        val articleAdapter = ArticleAdapter(data, viewModel.repository, ::goToArticleDetail)
        binding.homeFragmentRvArticle.apply {
            adapter = articleAdapter
        }
    }


    private fun renderSweetsList(sweetsState: SweetsState) {
        sweetsState.error?.ifNotHandled { errorMessage ->
            binding.homeFragmentTvError.text = errorMessage
            checkSweetsViewsVisibility(View.GONE, View.VISIBLE, View.GONE)
        }
        if (sweetsState.loading) {
            checkSweetsViewsVisibility(View.VISIBLE, View.GONE, View.GONE)
        } else {
            setupRecycler(sweetsState.listOfSweets)
            checkSweetsViewsVisibility(View.GONE, View.GONE, View.VISIBLE)
        }
    }

    private fun setupRecycler(listOfSweets: List<SweetsEntity>) {
        val homeAdapter = HomeListAdapter(listOfSweets, ::goToRecipeDetail)
        binding.homeFragmentRecycler.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false
            )
            adapter = homeAdapter
        }
    }


    private fun goToArticleDetail(model: ArticleEntity) {
        val action = R.id.action_homeFragment_to_articleDetailFragment
        val bundle = Bundle()
        bundle.apply {
            putInt(Strings.POSITION_ARTICLE_KEY, model.id.toInt())
            putString(Strings.DESC_ARTICLE_KEY, model.description)
            putString(Strings.NAME_ARTICLE_KEY, model.title)
            putString(Strings.SOURCE_ARTICLE_KEY, model.source)
        }
        Navigation.findNavController(requireView()).navigate(action, bundle)
    }

    private fun goToRecipeDetail(model: SweetsEntity) {
        val action = R.id.action_homeFragment_to_detailFragment
        val bundle = Bundle()
        bundle.apply {
            putInt(Strings.POSITION_KEY, model.id!!)
            putString(Strings.DESC_KEY, model.description)
            putString(Strings.NAME_KEY, model.name)
            putString(Strings.RECIPE_KEY, model.recipe)
            putString(Strings.TIME_KEY, model.time)
        }
        Navigation.findNavController(requireView()).navigate(action, bundle)

    }


    private fun openSearchFragment(view: View) {
        val action = R.id.action_homeFragment_to_searchFragment
        Navigation.findNavController(view).navigate(action)
    }


    private fun checkSweetsViewsVisibility(
        progressVisibility: Int,
        errorVisibility: Int,
        recyclerVisibility: Int
    ) {
        with(binding) {
            homeFragmentProgressbarLoading.visibility = progressVisibility
            homeFragmentTvError.visibility = errorVisibility
            homeFragmentRecycler.visibility = recyclerVisibility
        }
    }
}