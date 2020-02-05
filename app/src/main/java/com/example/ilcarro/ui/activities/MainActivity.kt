package com.example.ilcarro.ui.activities

import android.os.Bundle
import android.view.Menu
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ilcarro.R
import com.example.ilcarro.databinding.ActivityMainBinding
import com.example.ilcarro.ui.viewModels.mainFlow.MainActivityViewModel

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private var isLogged: Boolean = false
    private lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(mBinding.appBarMain.toolbar)
        isLogged = mViewModel.isLogged()
        navController = findNavController(R.id.nav_host_fragment)
        mBinding.appBarMain.toolbar.setLogo(R.drawable.logo_light)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.searchFragment, R.id.favoritesFragment
            ), mBinding.drawer)
        mBinding.navView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onResume() {
        menu = mBinding.navView.menu
        if(isLogged) {
            menu.clear()
            mBinding.navView.inflateMenu(R.menu.main_menu_logged_in)
            mBinding.navView.invalidate()
        }
        else {
            menu.clear()
            mBinding.navView.inflateMenu(R.menu.main_menu_logged_out)
            mBinding.navView.invalidate()
        }
        super.onResume()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        if(mBinding.drawer.isDrawerOpen(GravityCompat.START))
            mBinding.drawer.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    override fun getLayoutResID() = R.layout.activity_main
}
