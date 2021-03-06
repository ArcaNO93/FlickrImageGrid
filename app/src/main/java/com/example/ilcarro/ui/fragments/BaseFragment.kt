package com.example.ilcarro.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ilcarro.utils.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_let_the_car_work_location.view.*
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseFragment<VM: ViewModel, DB: ViewDataBinding> : DaggerFragment() {

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory
    lateinit var mViewModel: VM
    lateinit var mBinding: DB

    @LayoutRes
    abstract fun getLayoutID(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this, mViewModelFactory).get(getViewModelClass())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutID(), container, false)
        return mBinding.root
    }

    @Suppress("UNCHECKED_CAST")
    private fun getViewModelClass(): Class<VM> {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        return type as Class<VM>
    }

    fun showToast(message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        val view = toast.view.findViewById<TextView>(android.R.id.message)
        view?.let {
            view.gravity = Gravity.CENTER
        }
        toast.show()
    }

    fun <V : View> showHideView(view: V, visibility: Boolean) {
        view.visibility = when (visibility) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }

    fun hideKeyboardIfOpened() {
        val imm by lazy {
            context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        }
        val windowHeightMethod = InputMethodManager::class.java.getMethod("getInputMethodWindowVisibleHeight")
        val height = windowHeightMethod.invoke(imm) as Int
        if(height > 0)
            imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }

    abstract fun initView()
    abstract fun initListeners()

    fun <T: EditText> addValidListener(liveData: LiveData<Pair<Boolean, String?>>, editTextField: T) {
        liveData.observe(viewLifecycleOwner, Observer {
            when(it.first) {
                true -> editTextField.error = null
                false -> editTextField.error = it.second
            }
        })
    }

    fun addOnFocusChangeListener(view: EditText) {
        view.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && view.text!!.isEmpty())
                view.error = null
        }
    }
}