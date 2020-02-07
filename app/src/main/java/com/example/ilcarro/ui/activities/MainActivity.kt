package com.example.ilcarro.ui.activities

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.ilcarro.R
import com.example.ilcarro.databinding.ActivityMainBinding
import com.example.ilcarro.databinding.ConfirmLogoutLayoutBinding
import com.example.ilcarro.ui.viewModels.mainFlow.MainActivityViewModel

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var mAlertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListeners()
        initAlertDialog()
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

    private fun initView() {
        setSupportActionBar(mBinding.appBarMain.toolbar)
        navController = findNavController(R.id.nav_host_fragment)
        mBinding.appBarMain.toolbar.setLogo(R.drawable.logo_light)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.searchFragment, R.id.favoritesFragment, R.id.profileFragment
            ), mBinding.drawer
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun initAlertDialog() {
        val binding: ConfirmLogoutLayoutBinding =
            DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.confirm_logout_layout, null, false)
        binding.viewModel = mViewModel
        mAlertDialog = AlertDialog.Builder(this).setView(binding.root).create()
    }

    private fun initListeners() {
        mViewModel.subscribeToIsLoggedChange()
        mViewModel.mIsLogged.observe(this, Observer {
            mBinding.navView.menu.clear()
            when (it) {
                true -> mBinding.navView.inflateMenu(R.menu.menu_logged_in)
                false -> mBinding.navView.inflateMenu(R.menu.menu_logged_out)
            }
        })
        mViewModel.mLogOut.observe(this, Observer {
            showToast("Successfully logged out")
            mAlertDialog.dismiss()
        })
        mBinding.navView.setNavigationItemSelectedListener {
            when (it) {
                mBinding.navView.menu.findItem(R.id.logout) -> mAlertDialog.show()
                else -> NavigationUI.onNavDestinationSelected(it, navController)
            }
            mBinding.drawer.closeDrawer(GravityCompat.START)
            true
        }
    }
}
