package com.example.baseandroidapp.extension

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children

fun View.disableGroup() {
    this.isEnabled = false
    if(this is ViewGroup) {
        for(child in this.children) {
            child.isEnabled = false
        }
    }
}

fun View.enableGroup() {
    this.isEnabled = true
    if(this is ViewGroup) {
        for(child in this.children) {
            child.isEnabled = true
        }
    }
}