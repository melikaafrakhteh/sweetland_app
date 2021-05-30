package com.afrakhteh.sweetlandapp.view.activities

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
import com.afrakhteh.sweetlandapp.R
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

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
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        setupAnimation()
    }

    private fun setupAnimation() {
        val scaleXanimate =
                ObjectAnimator.ofFloat(splash_activity_text_animation_tv, "scaleX", 1f, 2f)
        val scaleYanimate =
                ObjectAnimator.ofFloat(splash_activity_text_animation_tv, "scaleY", 1f, 2f)
        val alpha =
                ObjectAnimator.ofFloat(splash_activity_text_animation_tv, "alpha", 1f, 0.2f)

        val set = AnimatorSet()

        set.playTogether(scaleXanimate, scaleYanimate, alpha)
        set.duration = 2200
        set.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) { startApp() }

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationRepeat(animation: Animator?) {}

        })
        set.start()
    }

    private fun startApp() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}