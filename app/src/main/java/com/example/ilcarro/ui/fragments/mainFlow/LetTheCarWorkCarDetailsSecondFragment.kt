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
import com.example.ilcarro.databinding.FragmentLetTheCarWorkCarDetailsSecondBinding
import com.example.ilcarro.ui.fragments.BaseFragment
import com.example.ilcarro.ui.viewModels.mainFlow.LetTheCarWorkCarDetailsSecondViewModel
import com.example.ilcarro.utils.SpinnerLists

class LetTheCarWorkCarDetailsSecondFragment : BaseFragment<LetTheCarWorkCarDetailsSecondViewModel, FragmentLetTheCarWorkCarDetailsSecondBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        initView()
        initListeners()
        return mBinding.root
    }

    override fun getLayoutID() = R.layout.fragment_let_the_car_work_car_details_second

    override fun initView() {
        mBinding.viewModel = mViewModel
        mBinding.WDEnter.setAdapter(ArrayAdapter<String>(requireActivity(), R.layout.dropdown_list_row, SpinnerLists.wheelsDriveList))
        mBinding.classEnter.setAdapter(ArrayAdapter<String>(requireActivity(), R.layout.dropdown_list_row, SpinnerLists.carClassesList))
    }

    override fun initListeners() {
        mViewModel.getDestination().observe(viewLifecycleOwner, Observer { it ->
            it.getContentIfNotHandled()?.let {
                hideKeyboardIfOpened()
                NavHostFragment.findNavController(this@LetTheCarWorkCarDetailsSecondFragment).navigate(it)
            }
        })

        mViewModel.mButtonClickability.observe(viewLifecycleOwner, Observer {
            val clickability = it[0] && it[1] && it[2] && it[3] && it[4] && it[5] && it[6] && it[7]
            mBinding.buttonNext.backgroundTintList = when(clickability) {
                true -> ContextCompat.getColorStateList(requireContext(), R.color.colorRed)
                false -> ContextCompat.getColorStateList(requireContext(), R.color.colorPrimaryDark)
            }
            mBinding.buttonNext.isEnabled = clickability
        })

        addTextChangeListener(mBinding.WDEnter, mViewModel.mWDValid)
        addTextChangeListener(mBinding.horsepowerEnter, mViewModel.mHorsePowerValid)
        addTextChangeListener(mBinding.torqueEnter, mViewModel.mTorqueValid)
        addTextChangeListener(mBinding.fuelConsumptionEnter, mViewModel.mFuelConsumptionValid)
        addTextChangeListener(mBinding.distanceIncludedEnter, mViewModel.mDistanceIncludedValid)
        addTextChangeListener(mBinding.doorsEnter, mViewModel.mDoorsValid)
        addTextChangeListener(mBinding.seatsEnter, mViewModel.mSeatsValid)
        addTextChangeListener(mBinding.classEnter, mViewModel.mClassValid)
        addValidListener(mViewModel.mWDValid, mBinding.WDEnter)
        addValidListener(mViewModel.mHorsePowerValid, mBinding.horsepowerEnter)
        addValidListener(mViewModel.mTorqueValid, mBinding.torqueEnter)
        addValidListener(mViewModel.mFuelConsumptionValid, mBinding.fuelConsumptionEnter)
        addValidListener(mViewModel.mDistanceIncludedValid, mBinding.distanceIncludedEnter)
        addValidListener(mViewModel.mDoorsValid, mBinding.doorsEnter)
        addValidListener(mViewModel.mSeatsValid, mBinding.seatsEnter)
        addValidListener(mViewModel.mClassValid, mBinding.classEnter)
        addOnFocusChangeListener(mBinding.WDEnter)
        addOnFocusChangeListener(mBinding.horsepowerEnter)
        addOnFocusChangeListener(mBinding.torqueEnter)
        addOnFocusChangeListener(mBinding.fuelConsumptionEnter)
        addOnFocusChangeListener(mBinding.distanceIncludedEnter)
        addOnFocusChangeListener(mBinding.doorsEnter)
        addOnFocusChangeListener(mBinding.seatsEnter)
        addOnFocusChangeListener(mBinding.classEnter)
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
