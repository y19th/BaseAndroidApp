package com.example.baseandroidapp.domain.viewmodel

import com.example.baseandroidapp.domain.state.MainState
import kotlinx.coroutines.flow.update

class MainViewModel: BaseViewModel<MainState>(MainState()) {

    var count = 0

    init {
        launch {
            increment()
            _state.update { it.copy(count = count) }
        }
    }

    private fun increment() {
        repeat(1000) {
            launch {
                repeat(1000) {
                    if(count > 500000) throw Throwable("overflow")
                    count++
                }
            }
        }
    }
}