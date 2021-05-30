package com.afrakhteh.sweetlandapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.data.model.FaveModel
import com.afrakhteh.sweetlandapp.util.Constants
import com.afrakhteh.sweetlandapp.util.getProgressDrawable
import com.afrakhteh.sweetlandapp.util.loadingImage
import com.afrakhteh.sweetlandapp.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : BaseFragment() {

    private var sweetId = 0
    private lateinit var sweetName: String
    private lateinit var sweetImage: String
    private lateinit var sweetRecipe: String
    private lateinit var sweetDesc: String
    private lateinit var sweetTime: String
    private lateinit var faveModel: FaveModel

    private lateinit var viewModel: DetailViewModel

    override var bottomNavigationViewVisibility = View.GONE

    private var isItFave = true

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBundles()
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.fetchData(sweetId, sweetDesc, sweetImage, sweetName, sweetRecipe, sweetTime)

        faveModel = FaveModel(sweetId, sweetDesc, sweetImage, sweetName, sweetRecipe, sweetTime)

        showViewModelData()
        setCliCk(view)


    }

    private fun setCliCk(view: View) {
        fra_sweet_rece_back_btn.setOnClickListener {
            backProcess(view)
        }

        fra_sweet_rece_fave_btn.setOnClickListener {
            faveItem()
        }
    }

    private fun backProcess(view: View) {
        val action = DetailFragmentDirections.actionDetailFragmentToHomeFragment()
        Navigation.findNavController(view).navigate(action)
    }

    private fun faveItem() {

        if (isItFave) {
            viewModel.addToFave(faveModel)
            fra_sweet_rece_fave_btn.setImageResource(R.drawable.ic_heart_full)
            isItFave = false
            Toast.makeText(context,getString(R.string.add_to_fave),Toast.LENGTH_LONG).show()
        }
        else if (!isItFave){
            viewModel.removeFave(sweetId)
            fra_sweet_rece_fave_btn.setImageResource(R.drawable.ic_heart)
            isItFave = true

            Toast.makeText(context,getString(R.string.remove_to_fave),Toast.LENGTH_LONG).show()
        }

    }

    private fun showViewModelData() {
        viewModel.detail.observe(viewLifecycleOwner, Observer { model ->
            model?.let {

                sweet_fragment_recepie_image.loadingImage(sweetImage, getProgressDrawable(requireContext()))
                fra_sweet_rece_name.text = sweetName
                fra_sweet_rece_time_show.text = sweetTime
                fra_sweet_rece_material_input.text = sweetRecipe
                fra_sweet_rece_making.text = sweetDesc

            }
        })
    }

    private fun getBundles() {
        sweetId = arguments?.getInt(Constants.ID)!!
        sweetName = arguments?.getString(Constants.NAME)!!
        sweetImage = arguments?.getString(Constants.IMAGE)!!
        sweetDesc = arguments?.getString(Constants.DESC)!!
        sweetRecipe = arguments?.getString(Constants.RECIPE)!!
        sweetTime = arguments?.getString(Constants.TIME)!!
    }

}