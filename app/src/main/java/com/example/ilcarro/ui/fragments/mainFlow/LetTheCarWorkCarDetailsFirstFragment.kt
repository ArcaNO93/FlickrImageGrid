package com.example.ilcarro.ui.fragments.mainFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment

import com.example.ilcarro.R
import com.example.ilcarro.databinding.FragmentLetTheCarWorkCarDetailsFirstBinding
import com.example.ilcarro.ui.fragments.BaseFragment
import com.example.ilcarro.ui.viewModels.mainFlow.LetTheCarWorkViewModel

class LetTheCarWorkCarDetailsFirstFragment : BaseFragment<LetTheCarWorkViewModel, FragmentLetTheCarWorkCarDetailsFirstBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        initView()
        initListeners()
        return mBinding.root
    }

    override fun getLayoutID() = R.layout.fragment_let_the_car_work_car_details_first

    override fun initView() {
        mBinding.viewModel = mViewModel
    }

    override fun initListeners() {
        mViewModel.getDestination().observe(viewLifecycleOwner, Observer { it ->
            it.getContentIfNotHandled()?.let {
                hideKeyboardIfOpened()
                NavHostFragment.findNavController(this@LetTheCarWorkCarDetailsFirstFragment).navigate(it)
            }
        })
    }

}
