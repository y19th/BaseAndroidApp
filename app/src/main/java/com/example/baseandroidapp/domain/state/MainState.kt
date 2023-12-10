package com.example.baseandroidapp.domain.state

data class MainState(
    val isError: Boolean = false,
    val count: Int = 0
): ViewState("firstState")