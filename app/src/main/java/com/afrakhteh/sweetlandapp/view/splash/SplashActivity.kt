package com.afrakhteh.sweetlandapp.view.splash

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.addListener
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.constants.Numerals
import com.afrakhteh.sweetlandapp.databinding.ActivitySplashBinding
import com.afrakhteh.sweetlandapp.view.main.MainActivity


class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFullScreen()
        initialiseAnimationValue()
    }

    private fun initialiseAnimationValue() {
        val transactionYMainTextAnimate =
                ObjectAnimator.ofFloat(
                        binding.splashMainTextTv, "translationY", 60f, 40f
                )
        val alphaMainTextAnimate =
                ObjectAnimator.ofFloat(
                        binding.splashMainTextTv, "alpha", 0.2f, 1f
                )
        val alphaPicAnimate =
                ObjectAnimator.ofFloat(
                        binding.splashConstraintLayout, "alpha", 0.6f, 1f
                )
        setupAnimation(transactionYMainTextAnimate,alphaMainTextAnimate,alphaPicAnimate)
    }


    private fun setupAnimation(
            transactionYMainTextAnimate: ObjectAnimator,
            alphaMainTextAnimate: ObjectAnimator,
            alphaPicAnimate: ObjectAnimator
    ) {
        val set = AnimatorSet()
        set.playTogether(
                transactionYMainTextAnimate,
                alphaMainTextAnimate,
                alphaPicAnimate
        )
        set.duration = Numerals.SPLASH_SCREEN_ANIMATION_DURATION
        set.addListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) {startApp()}
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationRepeat(animation: Animator?) {}
        })
        set.start()
    }

    private fun startApp() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun setupFullScreen() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
        window.decorView.apply {
            systemUiVisibility =
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }
}