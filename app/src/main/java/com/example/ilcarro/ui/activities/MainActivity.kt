package com.example.ilcarro.ui.activities

import android.os.Bundle
import android.view.Menu
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.ilcarro.R
import com.example.ilcarro.databinding.ActivityMainBinding
import com.example.ilcarro.ui.viewModels.mainFlow.MainActivityViewModel

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(mBinding.appBarMain.toolbar)
        navController = findNavController(R.id.nav_host_fragment)
        mBinding.appBarMain.toolbar.setLogo(R.drawable.logo_light)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.searchFragment, R.id.favoritesFragment, R.id.profileFragment
            ), mBinding.drawer
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            mViewModel.getIsLogged()
        }

        mViewModel.mIsLogged.observe(this, Observer {
            mBinding.navView.menu.clear()
            when(it) {
                true -> mBinding.navView.inflateMenu(R.menu.menu_logged_in)
                false -> mBinding.navView.inflateMenu(R.menu.menu_logged_out)
            }
        })

        mBinding.navView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onRestart() {
        super.onRestart()
        mViewModel.getIsLogged()
    }

    override fun onBackPressed() {
        if(mBinding.drawer.isDrawerOpen(GravityCompat.START))
            mBinding.drawer.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    override fun getLayoutResID() = R.layout.activity_main
}
