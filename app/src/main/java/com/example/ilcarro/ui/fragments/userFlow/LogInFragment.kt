package com.example.ilcarro.ui.fragments.userFlow

import android.os.Bundle
import android.text.TextWatcher
import android.text.Editable
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.ilcarro.R
import com.example.ilcarro.data.dto.user.ui.LoginUserUI
import com.example.ilcarro.databinding.FragmentLogInBinding
import com.example.ilcarro.ui.fragments.BaseFragment
import com.example.ilcarro.ui.viewModels.userFlow.LogInViewModel
import com.example.ilcarro.utils.STATUS

class LogInFragment : BaseFragment<LogInViewModel, FragmentLogInBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        initView()

        mViewModel.mLoginStatus.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                STATUS.LOADING -> {
                    showHideView(mBinding.progressBar.progressBarFrame, true)
                    hideKeyboardIfOpened()
                }
                STATUS.LOADED -> {
                    showHideView(mBinding.progressBar.progressBarFrame, false)
                    showToast("Successfully logged in")
                    activity?.finish()
                }
                STATUS.FAIL -> {
                    showHideView(mBinding.progressBar.progressBarFrame, false)
                    showToast(it.msg!!)
                }
            }
        })

        return mBinding.root
    }

    override fun getLayoutID(): Int = R.layout.fragment_log_in

    private fun initView() {
        mBinding.viewModel = mViewModel
        mBinding.loginUser = LoginUserUI("", "")
        mBinding.forgotPasswordLink.movementMethod = LinkMovementMethod.getInstance()
        mBinding.progressBar.progressBarFrame.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorPrimaryHalfTransparent))
    }
}