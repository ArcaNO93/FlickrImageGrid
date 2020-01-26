package com.example.ilcarro.ui.fragments.userFlow

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ilcarro.R
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.dto.user.ui.LoginUserUI
import com.example.ilcarro.databinding.FragmentLogInBinding
import com.example.ilcarro.ui.fragments.BaseFragment
import com.example.ilcarro.ui.viewModels.userFlow.LogInViewModel
import com.example.ilcarro.utils.ComponentProvider
import com.example.ilcarro.utils.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@FragmentScope
class LogInFragment @Inject constructor() : BaseFragment<LogInViewModel, FragmentLogInBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mBinding.logInViewModel = mViewModel
        mBinding.loginUser = LoginUserUI("", "")

        mViewModel.mLoginStatus.observe(this, Observer {
            showToast(it.toString())
        })

        return mBinding.root
    }

    override fun getLayoutID(): Int = R.layout.fragment_log_in
}