package com.example.ilcarro.ui.fragments.mainFlow

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment

import com.example.ilcarro.R
import com.example.ilcarro.databinding.FragmentLetTheCarWorkCarDetailsFirstBinding
import com.example.ilcarro.ui.fragments.BaseFragment
import com.example.ilcarro.ui.viewModels.mainFlow.LetTheCarWorkCarDetailsFirstViewModel
import com.example.ilcarro.utils.SpinnerLists

class LetTheCarWorkCarDetailsFirstFragment : BaseFragment<LetTheCarWorkCarDetailsFirstViewModel, FragmentLetTheCarWorkCarDetailsFirstBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        initView()
        initListeners()
        return mBinding.root
    }

    override fun getLayoutID() = R.layout.fragment_let_the_car_work_car_details_first

    override fun initView() {
        mBinding.viewModel = mViewModel
        mBinding.transmissionEnter.setAdapter(ArrayAdapter<String>(requireActivity(), R.layout.dropdown_menu_popup_item, SpinnerLists.transmissionList))
        mBinding.fuelEnter.setAdapter(ArrayAdapter<String>(requireActivity(), R.layout.dropdown_menu_popup_item, SpinnerLists.fuelList))
    }

    override fun initListeners() {
        mViewModel.getDestination().observe(viewLifecycleOwner, Observer { it ->
            it.getContentIfNotHandled()?.let {
                hideKeyboardIfOpened()
                NavHostFragment.findNavController(this@LetTheCarWorkCarDetailsFirstFragment).navigate(it)
            }
        })

        mViewModel.mButtonClickability.observe(viewLifecycleOwner, Observer {
            val clickability = it[0] && it[1] && it[2] && it[3] && it[4] && it[5]
            mBinding.buttonNext.backgroundTintList = when(clickability) {
                true -> ContextCompat.getColorStateList(requireContext(), R.color.colorRed)
                false -> ContextCompat.getColorStateList(requireContext(), R.color.colorPrimaryDark)
            }
            mBinding.buttonNext.isEnabled = clickability
        })

        addValidListener(mViewModel.mMakeValid, mBinding.makeEnter)
        addValidListener(mViewModel.mModelValid, mBinding.modelEnter)
        addValidListener(mViewModel.mYearValid, mBinding.yearEnter)
        addValidListener(mViewModel.mEngineValid, mBinding.engineEnter)
        addValidListener(mViewModel.mFuelValid, mBinding.fuelEnter)
        addValidListener(mViewModel.mTransmissionValid, mBinding.transmissionEnter)
        addOnFocusChangeListener(mBinding.makeEnter)
        addOnFocusChangeListener(mBinding.modelEnter)
        addOnFocusChangeListener(mBinding.yearEnter)
        addOnFocusChangeListener(mBinding.engineEnter)
        addOnFocusChangeListener(mBinding.fuelEnter)
        addOnFocusChangeListener(mBinding.transmissionEnter)
        addTextChangeListener(mBinding.makeEnter, mViewModel.mMakeValid)
        addTextChangeListener(mBinding.modelEnter, mViewModel.mModelValid)
        addTextChangeListener(mBinding.yearEnter, mViewModel.mYearValid)
        addTextChangeListener(mBinding.engineEnter, mViewModel.mEngineValid)
        addTextChangeListener(mBinding.fuelEnter, mViewModel.mFuelValid)
        addTextChangeListener(mBinding.transmissionEnter, mViewModel.mTransmissionValid)
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
