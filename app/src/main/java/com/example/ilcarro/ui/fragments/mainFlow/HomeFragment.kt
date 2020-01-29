package com.example.ilcarro.ui.fragments.mainFlow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.ilcarro.R
import com.example.ilcarro.adapters.TopThreeBookedCarsAdapter
import com.example.ilcarro.databinding.FragmentHomeBinding
import com.example.ilcarro.ui.fragments.BaseFragment
import com.example.ilcarro.ui.viewModels.mainFlow.HomeViewModel
import com.example.ilcarro.utils.STATUS

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val adapter = TopThreeBookedCarsAdapter()
        mViewModel.getTopCars()
        mBinding.topThreeList.adapter = adapter
        showProgressBar(mBinding.fragmentHomeProgressBar.progressBar)

        mViewModel.mLoadingStatus.observe(viewLifecycleOwner, Observer {
            mBinding.fragmentHomeProgressBar.progressBar.visibility = when(it.status) {
                STATUS.LOADING -> View.VISIBLE
                STATUS.LOADED -> View.GONE
                STATUS.FAIL -> View.GONE
            }
        })

        mViewModel.mTopCars.observe(viewLifecycleOwner, Observer {
            adapter.setCars(it)
        })
        return mBinding.root
    }

    override fun getLayoutID(): Int  = R.layout.fragment_home
}
