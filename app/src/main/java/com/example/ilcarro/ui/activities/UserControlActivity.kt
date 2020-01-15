package com.example.ilcarro.ui.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.ActivityNavigator
import com.example.ilcarro.R
import com.example.ilcarro.databinding.ActivityUserControlBinding
import com.example.ilcarro.ui.viewModels.userFlow.UserControlActivityViewModel

class UserControlActivity : BaseActivity<UserControlActivityViewModel, ActivityUserControlBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        ActivityNavigator.applyPopAnimationsToPendingTransition(this)
    }

    override fun getLayoutResID(): Int = R.layout.activity_user_control
}