package com.example.ilcarro.ui.fragments.mainFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ilcarro.R
import com.example.ilcarro.databinding.FragmentTripsBinding
import com.example.ilcarro.ui.fragments.BaseFragment
import com.example.ilcarro.ui.viewModels.mainFlow.TripsViewModel
import com.example.ilcarro.utils.ComponentProvider
import com.example.ilcarro.utils.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TripsFragment : BaseFragment<TripsViewModel, FragmentTripsBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mViewModel.text.observe(viewLifecycleOwner, Observer {
            mBinding.textTrips.text = it
        })

        return mBinding.root
    }

    override fun getLayoutID(): Int = R.layout.fragment_trips
}
