package com.example.ilcarro.ui.fragments.mainFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.ilcarro.R
import com.example.ilcarro.data.dto.user.ui.UpdateUserUI
import com.example.ilcarro.databinding.FragmentHostBinding
import com.example.ilcarro.ui.fragments.BaseFragment
import com.example.ilcarro.ui.viewModels.mainFlow.HostViewModel

class HostFragment : BaseFragment<HostViewModel, FragmentHostBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mBinding.hostViewModel = mViewModel
        mBinding.updatedUser = UpdateUserUI("", "", "", "")

        mViewModel.mUpdateComplete.observe(this, Observer {
            showToast(it.toString())
        })

        return mBinding.root
    }

    override fun getLayoutID(): Int = R.layout.fragment_host
}