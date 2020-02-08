package com.example.ilcarro.ui.fragments.mainFlow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ilcarro.R
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.databinding.FragmentMapsBinding
import com.example.ilcarro.ui.fragments.BaseFragment
import com.example.ilcarro.ui.viewModels.mainFlow.MapsViewModel
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import javax.inject.Inject

@FragmentScope
class MapsFragment @Inject constructor() : BaseFragment<MapsViewModel, FragmentMapsBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        initView()

        mBinding.mapView.onCreate(savedInstanceState)
        mBinding.mapView.onResume()
        mBinding.mapView.getMapAsync(callback)

        return mBinding.root
    }

    override fun getLayoutID(): Int = R.layout.fragment_maps

    private val callback = OnMapReadyCallback { googleMap ->

        Log.d("MY_", "callback")
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun initView() {
        mBinding.viewModel = mViewModel
    }

    override fun initListeners() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}