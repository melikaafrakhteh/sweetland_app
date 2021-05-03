package com.afrakhteh.sweetlandapp.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.afrakhteh.sweetlandapp.view.activities.MainActivity

abstract class BaseFragment : Fragment() {

    protected open var bottomNavigationViewVisibility = View.VISIBLE

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity is MainActivity) {
            var mActivity = activity as MainActivity
            mActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility)
        }
    }

    override fun onResume() {
        super.onResume()

        if (activity is MainActivity){
            var mActivity = activity as MainActivity
            mActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility)
        }
    }
}