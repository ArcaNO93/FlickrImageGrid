package com.example.ilcarro.ui.fragments.userFlow

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.ilcarro.R
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.dto.user.ui.LoginUserUI
import com.example.ilcarro.databinding.FragmentLogInBinding
import com.example.ilcarro.ui.fragments.BaseFragment
import com.example.ilcarro.ui.viewModels.userFlow.LogInViewModel
import javax.inject.Inject

@FragmentScope
class LogInFragment @Inject constructor() : BaseFragment<LogInViewModel, FragmentLogInBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mBinding.viewModel = mViewModel
        mBinding.loginUser = LoginUserUI("", "")

        init()

        mViewModel.mLoginStatus.observe(viewLifecycleOwner, Observer {
            showToast(it.toString())
        })

        mViewModel.mLoginValid.observe(viewLifecycleOwner, Observer {
            when(it.first) {
                true -> mBinding.logInEmailEnter.error = null
                false -> mBinding.logInEmailEnter.error = it.second
            }
        })

        mViewModel.mPasswordValid.observe(viewLifecycleOwner, Observer {
            when(it.first) {
                true -> mBinding.logInPasswordEnter.error = null
                false -> mBinding.logInPasswordEnter.error = it.second
            }
        })

        mViewModel.mButtonClickability.observe(viewLifecycleOwner, Observer {
            mBinding.buttonLogIn.isEnabled = it[0] && it[1]
        })

        return mBinding.root
    }

    override fun getLayoutID(): Int = R.layout.fragment_log_in

    private fun init() {

        mBinding.logInEmailEnter.setOnFocusChangeListener { _, hasFocus ->
            if(!hasFocus && mBinding.logInEmailEnter.text!!.isEmpty())
                mBinding.logInEmailEnter.error = null
        }

        mBinding.logInPasswordEnter.setOnFocusChangeListener { _, hasFocus ->
            if(!hasFocus && mBinding.logInPasswordEnter.text!!.isEmpty())
                mBinding.logInPasswordEnter.error = null
        }

        mBinding.logInEmailEnter.addTextChangedListener( object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mViewModel.validateUserLogin(s.toString())
            }

            override fun afterTextChanged(s: Editable) {}
        })

        mBinding.logInPasswordEnter.addTextChangedListener( object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mViewModel.validateUserPassword(s.toString())
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }
}