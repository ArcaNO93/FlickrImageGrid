package com.example.ilcarro.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewTreeObserver
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ilcarro.utils.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseActivity<VM : ViewModel, DB: ViewDataBinding> : DaggerAppCompatActivity() {

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory
    lateinit var mViewModel: VM
    lateinit var mBinding: DB

    @LayoutRes
    abstract fun getLayoutResID(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this, mViewModelFactory).get(getViewModelClass())
        mBinding = DataBindingUtil.setContentView(this, getLayoutResID())
    }

    @Suppress("UNCHECKED_CAST")
    private fun getViewModelClass(): Class<VM> {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        return type as Class<VM>
    }

    fun showHideMenuItems(menu: Menu, menuItem: Int, visible: Boolean) {
        menu.findItem(menuItem).isVisible = visible
    }
}