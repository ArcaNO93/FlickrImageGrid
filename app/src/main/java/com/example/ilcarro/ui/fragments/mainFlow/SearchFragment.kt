package com.example.ilcarro.ui.fragments.mainFlow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ilcarro.R
import com.example.ilcarro.databinding.FragmentSearchBinding
import com.example.ilcarro.ui.fragments.BaseFragment
import com.example.ilcarro.ui.viewModels.mainFlow.SearchViewModel
import com.example.ilcarro.utils.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mViewModel.text.observe(viewLifecycleOwner, Observer {
            mBinding.textSearch.text = it
        })
         return mBinding.root
    }

    override fun getLayoutID(): Int  = R.layout.fragment_search
}
