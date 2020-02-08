package com.example.ilcarro.ui.fragments.mainFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.example.ilcarro.ui.viewModels.mainFlow.LetTheCarWorkViewModel
import com.example.ilcarro.R
import com.example.ilcarro.databinding.FragmentLetTheCarWorkLocationBinding
import com.example.ilcarro.ui.fragments.BaseFragment

class LetTheCarWorkLocationFragment : BaseFragment<LetTheCarWorkViewModel, FragmentLetTheCarWorkLocationBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        initView()
        initListeners()
        return mBinding.root
    }

    override fun getLayoutID() = R.layout.fragment_let_the_car_work_location

    override fun initView() {
        mBinding.viewModel = mViewModel
        //TODO: add car POJO
    }

    override fun initListeners() {
        mViewModel.getDestination().observe(viewLifecycleOwner, Observer { it ->
            it.getContentIfNotHandled()?.let {
                hideKeyboardIfOpened()
                NavHostFragment.findNavController(this@LetTheCarWorkLocationFragment).navigate(it)
            }
        })
    }
}
