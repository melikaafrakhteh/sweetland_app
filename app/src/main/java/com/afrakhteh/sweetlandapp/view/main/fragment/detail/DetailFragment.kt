package com.afrakhteh.sweetlandapp.view.main.fragment.detail

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope

import androidx.navigation.Navigation
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.constants.Strings
import com.afrakhteh.sweetlandapp.databinding.FragmentDetailBinding
import com.afrakhteh.sweetlandapp.di.builder.ViewModelComponentBuilder
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.util.resize
import com.afrakhteh.sweetlandapp.util.toBitmap

import com.afrakhteh.sweetlandapp.view.main.interfaces.NavigationVisibility
import com.afrakhteh.sweetlandapp.viewmodel.DetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    @Inject
    lateinit var viewModelProviders: ViewModelProvider.Factory
    private val viewModel: DetailViewModel by viewModels { viewModelProviders }

    private var sweetId = 0
    private lateinit var sweetName: String
    private lateinit var sweetRecipe: String
    private lateinit var sweetDesc: String
    private lateinit var sweetTime: String
    private lateinit var sweetImageUrl: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        ViewModelComponentBuilder.getInstance().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBundles()
        viewModel.getImage(sweetImageUrl)
        showViewData()
        checkThisSweetIsFavorite()
        viewModel.images.observe(viewLifecycleOwner,::showImage)
        setCliCk(view)

    }

    private fun showImage(bytes: ByteArray?) {
        binding.sweetFragmentRecipeImage.setImageBitmap(
            bytes?.toBitmap()?.resize()
        )
    }

    private fun checkThisSweetIsFavorite() {
        lifecycleScope.launch(Dispatchers.IO) {
            val checked = viewModel.isSweetsLiked(sweetName)
            withContext(Dispatchers.Main) {
                binding.sweetFragmentRecipeFavoriteTgBtn.isChecked = checked
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as NavigationVisibility).apply {
            bottomNavigationVisible(View.GONE)
        }
    }

    private fun setCliCk(view: View) {
        with(binding) {
            sweetFragmentRecipeBackImage.setOnClickListener(::backProcess)
            sweetFragmentRecipeFavoriteTgBtn.setOnClickListener(::checkFavoriteBtn)
        }
    }

    private fun checkFavoriteBtn(view: View?) {
        val sweet = SweetsEntity(
            sweetId,
            sweetDesc,
            sweetImageUrl,
            sweetName,
            sweetRecipe,
            sweetTime
        )
        if (binding.sweetFragmentRecipeFavoriteTgBtn.isChecked) {
            viewModel.addToFavoriteList(sweet)
            binding.sweetFragmentRecipeFavoriteTgBtn.isChecked = true
        } else {
            viewModel.deleteFromFavorite(sweet)
            binding.sweetFragmentRecipeFavoriteTgBtn.isChecked = false
        }
    }

    private fun backProcess(view: View) {
        val action = DetailFragmentDirections.actionDetailFragmentToHomeFragment()
        Navigation.findNavController(view).navigate(action)
    }

    private fun showViewData() {
        with(binding) {
            sweetFragmentRecipeNameTv.text = sweetName
            sweetFragmentRecipeTimerTv.text = sweetTime
            sweetFragmentRecipeMaterialInputTv.text = sweetRecipe.replace(",", "\n")
            sweetFragmentRecipeCookingInputTv.text = sweetDesc.replace(".", "\n")
        }
    }

    private fun getBundles() {
        sweetId = arguments?.getInt(Strings.POSITION_KEY)!!
        sweetName = arguments?.getString(Strings.NAME_KEY)!!
        sweetDesc = arguments?.getString(Strings.DESC_KEY)!!
        sweetRecipe = arguments?.getString(Strings.RECIPE_KEY)!!
        sweetTime = arguments?.getString(Strings.TIME_KEY)!!
        sweetImageUrl = arguments?.getString(Strings.Url_KEY)!!
    }
}