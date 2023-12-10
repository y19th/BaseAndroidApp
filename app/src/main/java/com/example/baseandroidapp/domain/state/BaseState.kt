package com.example.baseandroidapp.domain.state

interface BaseState {

    fun onErrorHandle(e: Throwable)
}