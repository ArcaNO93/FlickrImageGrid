package com.example.ilcarro.ui.fragments.mainFlow

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment

import com.example.ilcarro.R
import com.example.ilcarro.data.dto.car.ui.addCarUI.AddCarUICarDetailsLastChunk
import com.example.ilcarro.databinding.FragmentLetTheCarWorkCarDetailsLastBinding
import com.example.ilcarro.ui.fragments.BaseFragment
import com.example.ilcarro.ui.viewModels.mainFlow.LetTheCarWorkCarDetailsLastViewModel
import com.example.ilcarro.utils.STATUS

class LetTheCarWorkCarDetailsLastFragment : BaseFragment<LetTheCarWorkCarDetailsLastViewModel, FragmentLetTheCarWorkCarDetailsLastBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        initView()
        initListeners()
        return mBinding.root
    }

    override fun getLayoutID() = R.layout.fragment_let_the_car_work_car_details_last

    override fun initView() {
        mBinding.viewModel = mViewModel
        mBinding.progressBar.progressBarFrame.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorPrimaryHalfTransparent))
    }

    override fun initListeners() {
        mViewModel.getDestination().observe(viewLifecycleOwner, Observer { it ->
            it.getContentIfNotHandled()?.let {
                hideKeyboardIfOpened()
                NavHostFragment.findNavController(this@LetTheCarWorkCarDetailsLastFragment)
                    .navigate(it)
            }
        })

        mViewModel.mButtonClickability.observe(viewLifecycleOwner, Observer {
            val clickability = it[0] && it[1]
            mBinding.buttonNext.backgroundTintList = when(clickability) {
                true -> ContextCompat.getColorStateList(requireContext(), R.color.colorRed)
                false -> ContextCompat.getColorStateList(requireContext(), R.color.colorPrimaryDark)
            }
            mBinding.buttonNext.isEnabled = clickability
        })

        addValidListener(mViewModel.mAboutValid, mBinding.aboutEnter)
        addValidListener(mViewModel.mPricePerDayValid, mBinding.priceEnter)
        addOnFocusChangeListener(mBinding.aboutEnter)
        addOnFocusChangeListener(mBinding.priceEnter)
        addTextChangeListener(mBinding.aboutEnter, mViewModel.mAboutValid)
        addTextChangeListener(mBinding.priceEnter, mViewModel.mPricePerDayValid)

        mViewModel.mCarAddingStatus.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                STATUS.LOADING -> {
                    showHideView(mBinding.progressBar.progressBarFrame, true)
                    hideKeyboardIfOpened()
                }
                STATUS.LOADED -> {
                    showHideView(mBinding.progressBar.progressBarFrame, false)
                    showToast("CarAdded")
                    Navigation.findNavController(mBinding.root).navigate(R.id.profileFragment)
                }
                STATUS.FAIL -> {
                    showHideView(mBinding.progressBar.progressBarFrame, false)
                    showToast(it.msg!!)
                }
            }
        })
    }

    private fun <T : EditText> addTextChangeListener(editTextField: T, liveData: LiveData<Pair<Boolean, String?>>) {
        editTextField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mViewModel.validateIfEmpty(s.toString(), liveData as MutableLiveData<Pair<Boolean, String?>>)
            }
            override fun afterTextChanged(s: Editable) {}
        })
    }
}
