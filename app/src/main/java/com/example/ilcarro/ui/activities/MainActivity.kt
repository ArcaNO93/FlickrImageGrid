package com.example.ilcarro.ui.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ilcarro.R
import com.example.ilcarro.databinding.ActivityMainBinding
import com.example.ilcarro.ui.viewModels.mainFlow.MainActivityViewModel
import com.example.ilcarro.utils.ViewModelFactory
import javax.inject.Inject

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(mBinding.toolbar)
        val navController = findNavController(R.id.nav_host_fragment)
        mBinding.navView.setupWithNavController(navController)
    }

    override fun getLayoutResID(): Int = R.layout.activity_main
}
