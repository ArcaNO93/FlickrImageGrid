package com.example.ilcarro.ui.fragments.mainFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.ilcarro.R
import com.example.ilcarro.adapters.CarsUIAdapter
import com.example.ilcarro.databinding.FragmentHomeBinding
import com.example.ilcarro.ui.fragments.BaseFragment
import com.example.ilcarro.ui.viewModels.mainFlow.HomeViewModel
import com.example.ilcarro.utils.STATUS
import com.example.ilcarro.utils.pageTransformers.DepthPageTransformer

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    private lateinit var mAdapter: CarsUIAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        initListeners()
        return mBinding.root
    }

    override fun getLayoutID()  = R.layout.fragment_home

    private fun init() {
        mAdapter = CarsUIAdapter()
        mBinding.topThreeList.adapter = mAdapter
        mBinding.errorMessageHome.viewModel = mViewModel
        mBinding.topThreeList.setPageTransformer(DepthPageTransformer())
        mViewModel.getTopCars()
    }

    private fun initListeners() {
        mViewModel.mLoadingStatus.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                STATUS.LOADING -> showHideView(mBinding.progressBar.progressBarFrame, true)
                STATUS.LOADED -> showHideView(mBinding.progressBar.progressBarFrame, false)
                STATUS.FAIL -> showHideView(mBinding.progressBar.progressBarFrame, false)
            }
        })
        mViewModel.mErrorMessageShown.observe(viewLifecycleOwner, Observer {
            showHideView(mBinding.errorMessageHome.errorMessage, it)
        })
        mViewModel.mShowCars.observe(viewLifecycleOwner, Observer {
            mAdapter.setCars(it)
        })
    }
}
