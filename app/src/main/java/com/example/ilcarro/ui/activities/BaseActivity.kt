package com.example.ilcarro.ui.activities

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
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

    fun showToast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        val view = toast.view.findViewById<TextView>(android.R.id.message)
        view?.let {
            view.gravity = Gravity.CENTER
        }
        toast.show()
    }

    fun hideKeyboardIfOpened() {
        val imm by lazy {
            this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        }
        val windowHeightMethod = InputMethodManager::class.java.getMethod("getInputMethodWindowVisibleHeight")
        val height = windowHeightMethod.invoke(imm) as Int
        if(height > 0)
            imm.hideSoftInputFromWindow(mBinding.root.windowToken, 0)
    }
}