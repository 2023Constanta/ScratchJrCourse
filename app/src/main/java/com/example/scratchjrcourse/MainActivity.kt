package com.example.scratchjrcourse

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.scratchjrcourse.databinding.ActivityMainBinding
import com.example.scratchjrcourse.features.units.presentation.ui.detail.UnitViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val unitViewModel: UnitViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth


        val botNavView: BottomNavigationView = binding.bottomNavigation

        navController = findNavController(R.id.navHostFragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.unitsListFragment,
                R.id.profileFragment
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, dest, _ ->
            Handler(Looper.getMainLooper()).post {
                when (dest.id) {
                    R.id.unitsListFragment,
                    R.id.profileFragment -> {
                        botNavView.visibility = View.VISIBLE
                    }

                    else -> {
                        botNavView.visibility = View.GONE
                    }
                }
            }
        }
        botNavView.setupWithNavController(navController)
    }

    override fun onStart() {
        super.onStart()
        setupStartDest()
    }

    override fun onBackPressed() {
        when (navController.currentDestination?.id) {
            R.id.unitsListFragment,
            R.id.authFragment -> {
                exitProcess()
            }

            else -> super.onBackPressed()
        }
    }

    private fun setupStartDest() {
        val user = auth.currentUser
        val navGraph = navController.navInflater.inflate(R.navigation.nav_main)
        navController

        if (user != null) {
            navGraph.setStartDestination(R.id.unitsListFragment)
        } else {
            navGraph.setStartDestination(R.id.authFragment)
        }
    }

    private fun exitProcess() {
        val dialog = AlertDialog.Builder(this, R.style.CustomDialog)
            .setMessage(getString(R.string.exit_from_app_question))
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                finish()
            }
            .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                dialog.cancel()
            }
            .create()
//            .show()

        dialog.show()
    }

    companion object {
        private val TAG = this::class.java.simpleName
    }
}