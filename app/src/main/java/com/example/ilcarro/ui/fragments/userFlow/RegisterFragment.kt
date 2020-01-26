package com.example.ilcarro.ui.fragments.userFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.ilcarro.R
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.dto.user.ui.RegisterUserUI
import com.example.ilcarro.databinding.FragmentRegisterBinding
import com.example.ilcarro.ui.fragments.BaseFragment
import com.example.ilcarro.ui.viewModels.userFlow.RegisterViewModel
import javax.inject.Inject

@FragmentScope
class RegisterFragment @Inject constructor() : BaseFragment<RegisterViewModel, FragmentRegisterBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mBinding.registerViewModel = mViewModel
        mBinding.newUser = RegisterUserUI("", "", "", "")

        mViewModel.mRegisterComplete.observe(this, Observer {
            showToast(it.toString())
        })

        return mBinding.root
    }

    override fun getLayoutID(): Int = R.layout.fragment_register
}