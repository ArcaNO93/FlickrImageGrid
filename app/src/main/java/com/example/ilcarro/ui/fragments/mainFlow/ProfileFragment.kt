package com.example.ilcarro.ui.fragments.mainFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.example.ilcarro.R
import com.example.ilcarro.adapters.CarsUIAdapter
import com.example.ilcarro.databinding.FragmentProfileBinding
import com.example.ilcarro.ui.fragments.BaseFragment
import com.example.ilcarro.ui.viewModels.mainFlow.ProfileViewModel
import com.example.ilcarro.utils.STATUS

class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val adapter = CarsUIAdapter()
        mBinding.ownedCars.adapter = adapter
        mBinding.viewModel = mViewModel
        mBinding.errorMessageProfile.viewModel = mViewModel
        mViewModel.getUserData()

        mViewModel.mUserDataLoadingStatus.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                STATUS.LOADING -> showHideView(mBinding.progressBar.progressBarFrame, true)
                STATUS.LOADED -> showHideView(mBinding.progressBar.progressBarFrame, false)
                STATUS.FAIL -> showHideView(mBinding.progressBar.progressBarFrame, false)
            }
        })

        mViewModel.mErrorMessageShown.observe(viewLifecycleOwner, Observer {
            showHideView(mBinding.errorMessageProfile.errorMessage, it)
        })

        mViewModel.mUserData.observe(viewLifecycleOwner, Observer {
            mBinding.user = it
            if(it.ownedCars.isEmpty())
                showHideView(mBinding.noCarLayout.noCarsText, true)
            else
                adapter.setCars(it.ownedCars)
        })

        mViewModel.getDestination().observe(viewLifecycleOwner, Observer { it ->
            it.getContentIfNotHandled()?.let {
                NavHostFragment.findNavController(this@ProfileFragment).navigate(it)
            }
        })

        return mBinding.root
    }

    override fun getLayoutID(): Int = R.layout.fragment_profile
}
