package com.example.ilcarro.ui.fragments.mainFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ilcarro.R
import com.example.ilcarro.databinding.FragmentSearchBinding
import com.example.ilcarro.ui.fragments.BaseFragment
import com.example.ilcarro.ui.viewModels.mainFlow.SearchViewModel

class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return mBinding.root
    }

    override fun getLayoutID(): Int = R.layout.fragment_search
}
