package com.example.ilcarro.ui.fragments.userFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.example.ilcarro.R
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.databinding.FragmentLogInAndSignUpBinding
import com.example.ilcarro.ui.fragments.BaseFragment
import com.example.ilcarro.ui.viewModels.userFlow.LogInAndSignUpViewModel
import javax.inject.Inject

@FragmentScope
class LogInAndSignUpFragment @Inject constructor() : BaseFragment<LogInAndSignUpViewModel, FragmentLogInAndSignUpBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mBinding.viewModel = mViewModel

        mViewModel.getDestination().observe(viewLifecycleOwner, Observer { it ->
            it.getContentIfNotHandled()?.let {
                NavHostFragment.findNavController(this@LogInAndSignUpFragment).navigate(it)
            }
        })

        return mBinding.root
    }

    override fun getLayoutID(): Int = R.layout.fragment_log_in_and_sign_up
}