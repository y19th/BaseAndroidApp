package com.example.baseandroidapp.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidapp.domain.state.ViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Suppress("PropertyName")
open class BaseViewModel<T: ViewState>(
    state: T
): ViewModel() {

    protected val _state = MutableStateFlow(state)
    val state = _state.asStateFlow()

    protected val TAG = state.tag

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        state.onErrorHandle(throwable)
    }


    protected fun launch(content: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(
            context = coroutineExceptionHandler + Dispatchers.IO,
            block = content
        )
    }

    protected fun launch(dispatcher: CoroutineDispatcher,content: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(
            context = coroutineExceptionHandler + dispatcher,
            block = content
        )
    }


}