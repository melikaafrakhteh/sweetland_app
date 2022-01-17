package com.afrakhteh.sweetlandapp.view.main

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.databinding.ActivityMainBinding
import com.afrakhteh.sweetlandapp.view.main.interfaces.NavigationVisibility

class MainActivity : AppCompatActivity(), NavigationVisibility {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFullScreen()
        setupNavigation()
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
    }

    private fun setupNavigation() {
        val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController =
                navHostFragment.navController
        binding.mainActivityBottomNavigationMenu.setupWithNavController(navController)
    }

    override fun bottomNavigationVisible(visibility: Int) {
        binding.mainActivityBottomNavigationMenu.visibility = visibility
    }
}