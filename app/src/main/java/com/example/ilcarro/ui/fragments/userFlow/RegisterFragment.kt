package com.example.ilcarro.ui.fragments.userFlow

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.ilcarro.R
import com.example.ilcarro.data.dto.user.ui.RegisterUserUI
import com.example.ilcarro.databinding.FragmentRegisterBinding
import com.example.ilcarro.ui.fragments.BaseFragment
import com.example.ilcarro.ui.viewModels.userFlow.RegisterViewModel
import com.example.ilcarro.utils.STATUS

class RegisterFragment : BaseFragment<RegisterViewModel, FragmentRegisterBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        initView()
        initListeners()
        return mBinding.root
    }

    override fun getLayoutID(): Int = R.layout.fragment_register

    override fun initView() {
        mBinding.viewModel = mViewModel
        mBinding.newUser = RegisterUserUI("", "", "", "")
        mBinding.progressBar.progressBarFrame.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorPrimaryHalfTransparent))
        mBinding.agreeCheckboxText.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun initListeners() {
        mViewModel.mRegisterComplete.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                STATUS.LOADING -> {
                    showHideView(mBinding.progressBar.progressBarFrame, true)
                    hideKeyboardIfOpened()
                }
                STATUS.LOADED -> {
                    showHideView(mBinding.progressBar.progressBarFrame, false)
                    showToast("Successfully registered")
                }
                STATUS.FAIL -> {
                    showHideView(mBinding.progressBar.progressBarFrame, false)
                    showToast(it.msg!!)
                }
            }
        })
        mViewModel.mLoginComplete.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                STATUS.LOADING -> showHideView(mBinding.progressBar.progressBarFrame, true)
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
        mViewModel.mButtonClickability.observe(viewLifecycleOwner, Observer {
            val clickability = it[0] && it[1] && it[2] && it[3] && it[4]
            mBinding.signUpButton.backgroundTintList = when(clickability) {
                true -> ContextCompat.getColorStateList(requireContext(), R.color.colorRed)
                false -> ContextCompat.getColorStateList(requireContext(), R.color.colorPrimaryDark)
            }
            mBinding.signUpButton.isEnabled = clickability
        })

        addValidListener(mViewModel.mFirstNameValid, mBinding.registerFirstNameEnter)
        addValidListener(mViewModel.mSecondNameValid, mBinding.registerLastNameEnter)
        addValidListener(mViewModel.mLoginValid, mBinding.registerEmailEnter)
        addValidListener(mViewModel.mPasswordValid, mBinding.registerPasswordEnter)

        addOnFocusChangeListener(mBinding.registerFirstNameEnter)
        addOnFocusChangeListener(mBinding.registerLastNameEnter)
        addOnFocusChangeListener(mBinding.registerEmailEnter)
        addOnFocusChangeListener(mBinding.registerPasswordEnter)

        mBinding.registerFirstNameEnter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mViewModel.validateUserFirstName(s.toString())
            }
            override fun afterTextChanged(s: Editable) {}
        })

        mBinding.registerLastNameEnter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mViewModel.validateUserLastName(s.toString())
            }
            override fun afterTextChanged(s: Editable) {}
        })

        mBinding.registerEmailEnter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mViewModel.validateUserEmail(s.toString())
            }
            override fun afterTextChanged(s: Editable) {}
        })

        mBinding.registerPasswordEnter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mViewModel.validateUserPassword(s.toString())
            }
            override fun afterTextChanged(s: Editable) {}
        })
    }
}