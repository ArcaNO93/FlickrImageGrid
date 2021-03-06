package com.example.ilcarro.ui.fragments.mainFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.ilcarro.R
import com.example.ilcarro.data.dto.user.ui.UpdateUserUI
import com.example.ilcarro.databinding.FragmentFavoritesBinding
import com.example.ilcarro.ui.fragments.BaseFragment
import com.example.ilcarro.ui.viewModels.mainFlow.FavoritesViewModel

class FavoritesFragment : BaseFragment<FavoritesViewModel, FragmentFavoritesBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        initView()
        initListeners()
        return mBinding.root
    }

    override fun getLayoutID(): Int = R.layout.fragment_favorites

    override fun initView() {
        mBinding.hostViewModel = mViewModel
        mBinding.updatedUser = UpdateUserUI("", "", "", "")
    }

    override fun initListeners() {
        mViewModel.mUpdateComplete.observe(viewLifecycleOwner, Observer {
            showToast(it.toString())
        })
    }
}