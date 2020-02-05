package com.example.ilcarro.ui.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.ActivityNavigator
import com.example.ilcarro.R
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.databinding.ActivityUserBinding
import com.example.ilcarro.ui.viewModels.userFlow.UserControlActivityViewModel
import javax.inject.Inject

class UserActivity : BaseActivity<UserControlActivityViewModel, ActivityUserBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(mBinding.appBarUser.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_close)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        ActivityNavigator.applyPopAnimationsToPendingTransition(this)
    }

    override fun getLayoutResID(): Int = R.layout.activity_user

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}