package com.example.baseandroidapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.baseandroidapp.R
import com.example.baseandroidapp.extension.attach
import com.example.baseandroidapp.extension.disableGroup
import com.example.baseandroidapp.extension.enableGroup
import com.example.baseandroidapp.presentation.views.Loader

@Suppress("PropertyName")
open class BaseFragment<T: ViewBinding>: Fragment() {

    companion object {
        const val TAG = "BaseFragment"
    }

    protected var _binding: T? = null
    protected val binding get() = requireNotNull(_binding)

    private var loader: Loader? = null

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        loader = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachLoader()
    }

    private fun attachLoader() {
        try {
            loader = Loader(requireContext(),false)
            binding.root.findViewById<ConstraintLayout>(R.id.main_layout).apply {
                addView(loader)
                attach(loader)
            }
        } catch (e: Exception) {
            loader = null
            Log.i(TAG,"throwed ${e.message}")
        }
    }

    protected fun showLoader() {
        loader?.let { it.shown = true }
        requireView().disableGroup()
    }

    protected fun hideLoader() {
        loader?.let { it.shown = false }
        requireView().enableGroup()
    }
}