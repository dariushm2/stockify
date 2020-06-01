package com.dariushm2.stockify.view

import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.dariushm2.stockify.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val navController = findNavController(R.id.navHostFragment)



        Navigation.setViewNavController(searchView, navController)

        searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                navController.navigate(R.id.addSymbolFragment)
                println("Stockify: onQueryTextFocusChange")
            } else {
                // searchView not expanded
            }
        }


        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        val navigationView = findViewById<NavigationView>(R.id.navView)
        navigationView.setNavigationItemSelectedListener(this)


    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun biometricPrompt() {
        //val promptInfo = BiometricPrompt.Builder(this)


        val authenticationCallback =  //@RequiresApi(Build.VERSION_CODES.P)
                object : BiometricPrompt.AuthenticationCallback() {

                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                        super.onAuthenticationError(errorCode, errString)
                        Log.e(MyApp.TAG, "Error")
                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                        super.onAuthenticationSucceeded(result)
                        Log.e(MyApp.TAG, "Unlock")
                    }
                }


        BiometricPrompt.Builder(this)
                .setTitle("Hello")
                .setDescription("Finger")
                .build()
                .authenticate(
                        CancellationSignal(),
                        ContextCompat.getMainExecutor(this),
                        authenticationCallback
                )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        //menuInflater.inflate(R.menu.main, menu)
        return true
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        println("Stockify: onNavigationItemSelected")
        when (item.itemId) {
            R.id.disclaimer -> {
                println("Stockify: onNavigationItemSelected: disclaimer")
                val navController = findNavController(R.id.navHostFragment)
                navController.navigate(R.id.disclaimerFragment)
                drawerLayout.closeDrawers()
            }
        }
        return true
    }


}
