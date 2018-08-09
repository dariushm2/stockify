package com.dariushm2.stockify.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.dariushm2.stockify.R
import com.dariushm2.stockify.model.Quote
import com.dariushm2.stockify.view.watchList.WatchListFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(),
        WatchListFragment.OnWatchListInteractionListener
//NavigationView.OnNavigationItemSelectedListener
{

    var isAddSymbolActive = false

    override fun onWatchListInteraction(item: Quote?) {

    }
//    override fun onBooksListInteraction(book: Book?) {
//        fabAddBook.visibility = View.VISIBLE
//    }

    val ADD_STOCK = 10
    var activeFragment: ActiveFragment = ActiveFragment.WatchListFragment

    enum class ActiveFragment {
        AddSymbolFragment,
        WatchListFragment
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        Log.e("Activity", "onCreate()")


        //val fabAddSymbol = findViewById<FloatingActionButton>(R.id.fabAddSymbol)
        val navController = findNavController(R.id.navHostFragment)
        Navigation.setViewNavController(fabAddSymbol, navController)
        fabAddSymbol.setOnClickListener { v ->

            //            val ft = supportFragmentManager.beginTransaction()
//            ft.replace(R.id.frgMain, AddSymbolFragment(), "AddSymbolFragment")
//                    .addToBackStack("AddSymbolFragment")
//                    .commit()
//
//            fabAddBook.visibility = View.GONE
//            val intent = Intent(this, AddSymbolActivity::class.java)
//            startActivityForResult(intent, ADD_STOCK)

            isAddSymbolActive = true
            //searchView.visibility = View.VISIBLE
            navController.navigate(R.id.addSymbolFragment)
        }

        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        //navView.setNavigationItemSelectedListener(this)
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
//    override fun onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START)
//        } else {
//            super.onBackPressed()
//        }
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        //findViewById<SearchView>(R.id.searchView).visibility = View.VISIBLE

        //menu?.findItem(R.id.searchView)?.isVisible = isAddSymbolActive
        Log.e("Prepare Options Menu", "Invoked")
        return super.onPrepareOptionsMenu(menu)
    }



//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        when (item.itemId) {
//            R.id.action_settings -> return true
//            else -> return super.onOptionsItemSelected(item)
//        }
//    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        // Handle navigation view item clicks here.
//        when (item.itemId) {
//            R.id.nav_camera -> {
//                // Handle the camera action
//            }
//            R.id.nav_gallery -> {
//
//            }
//            R.id.nav_slideshow -> {
//
//            }
//            R.id.nav_manage -> {
//
//            }
//            R.id.nav_share -> {
//
//            }
//            R.id.nav_send -> {
//
//            }
//
//        }
//
//        drawerLayout.closeDrawer(GravityCompat.START)
//        return true
//    }

    override fun onSupportNavigateUp(): Boolean {
        Log.e("onSupportNavigateUp", "Invoked")
        isAddSymbolActive = false
        //return Navigation.findNavController(this, R.id.navHostFragment).navigateUp()
        return true
    }


}
