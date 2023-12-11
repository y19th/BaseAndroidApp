package com.example.baseandroidapp.presentation.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.baseandroidapp.databinding.FragmentMainBinding
import com.example.baseandroidapp.domain.state.MainState
import com.example.baseandroidapp.domain.viewmodel.MainViewModel
import com.example.baseandroidapp.presentation.BaseFragment
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class FragmentMain: BaseFragment<FragmentMainBinding>() {

    private val viewModel: MainViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    attachState(it)
                }
            }
        }
    }

    private fun attachState(data : MainState) {
        binding.apply {
            text.text = data.count.toString()
            button.setOnClickListener {
                showLoader()
                object : CountDownTimer(5000,1000) {
                    override fun onTick(millisUntilFinished: Long) {
                    }

                    override fun onFinish() {
                        hideLoader()
                    }

                }.start()
            }
        }
    }
}