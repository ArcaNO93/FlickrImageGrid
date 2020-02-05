package com.example.ilcarro.ui.fragments.mainFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.example.ilcarro.R
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.databinding.FragmentProfileBinding
import com.example.ilcarro.ui.fragments.BaseFragment
import com.example.ilcarro.ui.viewModels.mainFlow.ProfileViewModel

@FragmentScope
class ProfileFragment @Inject constructor() : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        activity?.actionBar?.hide()

        mBinding.viewModel = mViewModel

        mViewModel.getDestination().observe(viewLifecycleOwner, Observer { it ->
            it.getContentIfNotHandled()?.let {
                NavHostFragment.findNavController(this@ProfileFragment).navigate(it)
            }
        })

        return mBinding.root
    }

    override fun getLayoutID(): Int = R.layout.fragment_profile
}
