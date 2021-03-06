package com.example.ilcarro.ui.fragments.userFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment

import com.example.ilcarro.R
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.databinding.FragmentGetStartedBinding
import com.example.ilcarro.ui.fragments.BaseFragment
import com.example.ilcarro.ui.viewModels.userFlow.GetStartedViewModel
import javax.inject.Inject

class GetStartedFragment : BaseFragment<GetStartedViewModel, FragmentGetStartedBinding>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        initListeners()
        return mBinding.root
    }


    override fun getLayoutID() = R.layout.fragment_get_started

    override fun initView() {
        mBinding.viewModel = mViewModel
    }

    override fun initListeners() {
        mViewModel.mNavigation.observe(viewLifecycleOwner, Observer { it ->
            it.getContentIfNotHandled()?.let {
                NavHostFragment.findNavController(this).navigate(it)
            }
        })
    }
}
