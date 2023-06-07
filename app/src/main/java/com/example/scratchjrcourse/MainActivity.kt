package com.example.scratchjrcourse

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.scratchjrcourse.databinding.ActivityMainBinding
import com.example.scratchjrcourse.features.units.presentation.ui.detail.UnitViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val unitViewModel: UnitViewModel by viewModel()
    //    private val viewBinding: ActivityMainBinding by viewBinding()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate: $unitViewModel")

        val botNavView: BottomNavigationView = binding.bottomNavigation

        val navController = findNavController(R.id.navHostFragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.unitsListFragment,
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        botNavView.setupWithNavController(navController)
    }

    companion object {
        private val TAG = this::class.java.simpleName
    }
}