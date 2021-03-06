package com.afrakhteh.sweetlandapp.view.main.fragment.detail

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Notification
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.constants.Strings
import com.afrakhteh.sweetlandapp.databinding.FragmentArticleDetailBinding
import com.afrakhteh.sweetlandapp.di.builder.ViewModelComponentBuilder
import com.afrakhteh.sweetlandapp.util.resize
import com.afrakhteh.sweetlandapp.util.toBitmap
import com.afrakhteh.sweetlandapp.view.main.interfaces.NavigationVisibility
import com.afrakhteh.sweetlandapp.viewmodel.ArticleDetailViewModel
import javax.inject.Inject

class ArticleDetailFragment : Fragment() {
    private lateinit var binding: FragmentArticleDetailBinding

    @Inject lateinit var viewModelProvider: ViewModelProvider.Factory
    private val viewModel: ArticleDetailViewModel by viewModels {viewModelProvider}

    private lateinit var title: String
    private lateinit var source: String
    private lateinit var desc: String
    private lateinit var imageUrl: String
    private var position: Int = 0

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        ViewModelComponentBuilder.getInstance().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBundles()
        setAnimation()
        viewModel.getImage(imageUrl)
        setViews()
        viewModel.images.observe(viewLifecycleOwner, ::showImage)
    }

    @SuppressLint("Recycle")
    private fun setAnimation() {
        val alpha = ObjectAnimator.ofFloat(
            binding.detailArticleFragmentSourceTitleTv, "alpha",0f,1f
        )
        alpha.duration = 2000
        alpha.start()
    }

    private fun showImage(bytes: ByteArray?) {
        binding.detailArticleFragmentImage.setImageBitmap(
            bytes?.toBitmap()?.resize()
        )
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as NavigationVisibility).apply {
            bottomNavigationVisible(View.GONE)
        }
    }

    private fun setViews() {
        with(binding) {
            detailArticleFragmentTitleTv.text = title
            detailArticleFragmentDescTv.text = desc.replace(".", "\n")

            detailArticleFragmentBackImage.setOnClickListener(::goBack)
            detailArticleFragmentSourceTitleTv.setOnClickListener(::openLink)
        }
    }

    private fun openLink(view: View?) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(source)))
    }

    private fun goBack(view: View?) {
        val action = R.id.action_articleDetailFragment_to_homeFragment
        Navigation.findNavController(requireNotNull(view)).navigate(action)
    }

    private fun getBundles() {
        title = arguments?.getString(Strings.NAME_ARTICLE_KEY, "")!!
        desc = arguments?.getString(Strings.DESC_ARTICLE_KEY, "")!!
        source = arguments?.getString(Strings.SOURCE_ARTICLE_KEY, "")!!
        position = arguments?.getInt(Strings.POSITION_ARTICLE_KEY, 0)!!
        imageUrl = arguments?.getString(Strings.SOURCE_URL_KEY, "")!!
    }
}