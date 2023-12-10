package com.example.baseandroidapp.domain.state

import android.util.Log

open class ViewState(startTag: String): BaseState {
    val tag = startTag
    override fun onErrorHandle(e: Throwable) {
        Log.i(tag,"handled ${e.message}")
    }

}