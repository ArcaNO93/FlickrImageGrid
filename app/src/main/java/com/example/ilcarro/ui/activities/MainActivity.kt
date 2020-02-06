package com.example.ilcarro.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.ListView
import androidx.appcompat.view.menu.MenuBuilder
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.ilcarro.R
import com.example.ilcarro.databinding.ActivityMainBinding
import com.example.ilcarro.ui.viewModels.mainFlow.MainActivityViewModel
import com.google.android.material.internal.NavigationMenuView

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private var isLogged: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(mBinding.appBarMain.toolbar)
        isLogged = mViewModel.isLogged()
        navController = findNavController(R.id.nav_host_fragment)
        mBinding.appBarMain.toolbar.setLogo(R.drawable.logo_light)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.searchFragment, R.id.favoritesFragment, R.id.profileFragment
            ), mBinding.drawer
        )
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            mBinding.navView.menu.setGroupVisible(R.id.logged_in, isLogged)
            mBinding.navView.menu.setGroupVisible(R.id.logged_out, !isLogged)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onResume() {
        super.onResume()
        mBinding.navView.menu.setGroupVisible(R.id.logged_in, isLogged)
        mBinding.navView.menu.setGroupVisible(R.id.logged_out, !isLogged)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.searchFragment, R.id.favoritesFragment, R.id.profileFragment
            ), mBinding.drawer
        )
        mBinding.navView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onBackPressed() {
        if(mBinding.drawer.isDrawerOpen(GravityCompat.START))
            mBinding.drawer.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    override fun getLayoutResID() = R.layout.activity_main
}
