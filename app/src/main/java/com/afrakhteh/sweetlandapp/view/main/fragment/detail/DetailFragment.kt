package com.afrakhteh.sweetlandapp.view.main.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment

import androidx.navigation.Navigation
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.constants.Strings
import com.afrakhteh.sweetlandapp.databinding.FragmentDetailBinding

import com.afrakhteh.sweetlandapp.view.main.interfaces.NavigationVisibility


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private var sweetId = 0
    private lateinit var sweetName: String
    private lateinit var sweetImage: String
    private lateinit var sweetRecipe: String
    private lateinit var sweetDesc: String
    private lateinit var sweetTime: String

    // private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBundles()
        showViewData()
        setCliCk(view)

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
    }

}