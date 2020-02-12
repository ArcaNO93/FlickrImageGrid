package com.example.ilcarro.ui.fragments.mainFlow

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.ilcarro.R
import com.example.ilcarro.adapters.FeatureAdapter
import com.example.ilcarro.adapters.PhotosListAdapter
import com.example.ilcarro.databinding.FragmentLetTheCarWorkCarDetailsLastBinding
import com.example.ilcarro.ui.fragments.BaseFragment
import com.example.ilcarro.ui.viewModels.mainFlow.LetTheCarWorkCarDetailsLastViewModel
import com.example.ilcarro.utils.STATUS
import com.example.ilcarro.utils.pageTransformers.DepthPageTransformer
import java.io.File

class LetTheCarWorkCarDetailsLastFragment : BaseFragment<LetTheCarWorkCarDetailsLastViewModel, FragmentLetTheCarWorkCarDetailsLastBinding>() {

    private lateinit var mFeatureAdapter: FeatureAdapter
    private lateinit var mPhotosAdapter: PhotosListAdapter
    private val PICK_IMAGE = 55

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFeatureAdapter = FeatureAdapter(mViewModel)
        mPhotosAdapter = PhotosListAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        initView()
        initListeners()
        return mBinding.root
    }

    override fun getLayoutID() = R.layout.fragment_let_the_car_work_car_details_last

    override fun initView() {
        mBinding.viewModel = mViewModel
        mBinding.progressBarMain.progressBarFrame.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorPrimaryHalfTransparent))
        mBinding.featuresList.layoutManager = LinearLayoutManager(requireContext())
        mBinding.featuresList.adapter = mFeatureAdapter
        mBinding.photosList.adapter = mPhotosAdapter
        mBinding.photosList.setPageTransformer(DepthPageTransformer())
        mBinding.feature = ""
        mBinding.fragment = this
    }

    override fun initListeners() {

        mViewModel.mImageUrl.observe(viewLifecycleOwner, Observer {
            Log.d("tag", it.toString())
            mPhotosAdapter.addImage(it)
        })

        mViewModel.mFeatureLiveList.observe(viewLifecycleOwner, Observer {
            mFeatureAdapter.setFeatures(it)
        })

        mViewModel.getDestination().observe(viewLifecycleOwner, Observer { it ->
            it.getContentIfNotHandled()?.let {
                hideKeyboardIfOpened()
                NavHostFragment.findNavController(this@LetTheCarWorkCarDetailsLastFragment)
                    .navigate(it)
            }
        })

        mViewModel.mListValidationError.observe(viewLifecycleOwner, Observer {
            showToast(it)
        })

        mViewModel.mSubmitButtonClickability.observe(viewLifecycleOwner, Observer {
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

        mViewModel.mImagesUrlsGettingProcess.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                STATUS.LOADING -> {
                    showHideView(mBinding.progressBarPhotos.progressBarFrame, true)
                    mBinding.buttonAddPhoto.apply {
                        backgroundTintList =
                            ContextCompat.getColorStateList(requireContext(), R.color.colorPrimaryDark)
                        isClickable = false
                    }
                }
                STATUS.LOADED -> {
                    showHideView(mBinding.progressBarPhotos.progressBarFrame, false)
                    showToast("Image added")
                    mBinding.buttonAddPhoto.apply {
                        backgroundTintList =
                            ContextCompat.getColorStateList(requireContext(), R.color.colorRed)
                        isClickable = true
                    }
                }
                STATUS.FAIL -> {
                    showHideView(mBinding.progressBarPhotos.progressBarFrame, false)
                    showToast(it.msg!!)
                    mBinding.buttonAddPhoto.apply {
                        backgroundTintList =
                            ContextCompat.getColorStateList(requireContext(), R.color.colorRed)
                        isClickable = true
                    }
                }
            }
        })

        mViewModel.mCarAddingStatus.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                STATUS.LOADING -> {
                    showHideView(mBinding.progressBarMain.progressBarFrame, true)
                    hideKeyboardIfOpened()
                }
                STATUS.LOADED -> {
                    showHideView(mBinding.progressBarMain.progressBarFrame, false)
                    showToast("Car added, check your profile")
                    Navigation.findNavController(mBinding.root).navigate(R.id.profileFragment)
                }
                STATUS.FAIL -> {
                    showHideView(mBinding.progressBarMain.progressBarFrame, false)
                    showToast(it.msg!!)
                }
            }
        })
    }

    override fun onPause() {
        super.onPause()
        mViewModel.saveChunk()
    }

    fun openGallery() {
        startActivityForResult(Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            data?.data?.let {
                mViewModel.addPhoto(it.toString(), it)
            }
        }
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
