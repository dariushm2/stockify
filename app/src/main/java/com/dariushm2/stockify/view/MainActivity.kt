package com.dariushm2.stockify.view

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.dariushm2.stockify.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener
        /*,  BooksListFragment.OnBooksListListener */ {


//    override fun onBooksListInteraction(book: Book?) {
//        fabAddBook.visibility = View.VISIBLE
//    }

    val ADD_STOCK = 10
    var activeFragment: ActiveFragment = ActiveFragment.WatchListFragment

    enum class ActiveFragment{
        AddSymbolFragment,
        WatchListFragment
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        Log.e("Activity", "onCreate()")


        //val navHostFragment = supportFragmentManager.findFragmentById(R.id.watchListFragment) as NavHostFragment
        fabAddBook.setOnClickListener { v ->

//            val ft = supportFragmentManager.beginTransaction()
//            ft.replace(R.id.frgMain, AddSymbolFragment(), "AddSymbolFragment")
//                    .addToBackStack("AddSymbolFragment")
//                    .commit()
//
//            fabAddBook.visibility = View.GONE
//            val intent = Intent(this, AddSymbolActivity::class.java)
//            startActivityForResult(intent, ADD_STOCK)

//            val navController = Navigation.findNavController(this, R.id.fabAddBook)
//            navController.navigate(R.id.addSymbolActivity)

            //Navigation.findNavController(v).navigate(R.id.addSymbolActivity)
            //Navigation.findNavController(v).navigate(R.id.addSymbolActivity)
            val navController = findNavController(supportFragmentManager.findFragmentByTag(ActiveFragment.WatchListFragment.name)!!)
            Navigation.setViewNavController(v, navController)
            //Navigation.createNavigateOnClickListener(R.id.actionWatchListToAddSymbol, null)
        }





        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_STOCK)
            if (resultCode == AppCompatActivity.RESULT_OK) {

            }
    }
 


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBoolean("Frag", true)


    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Activity", "onDestroy()")
        //supportFragmentManager.fragments.forEach { it.onDestroy() }
    }
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
