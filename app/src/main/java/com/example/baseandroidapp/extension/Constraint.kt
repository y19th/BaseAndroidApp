package com.example.baseandroidapp.extension

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet


@JvmName("attachRequire")
fun ConstraintLayout.attach(attached: View) {
    ConstraintSet().apply {
        clone(this@attach)
        attach(attached,this@attach)
        applyTo(this@attach)

    }
}
@JvmName("attachNull")
fun ConstraintLayout.attach(attached: View? = null) {
    if(attached != null) attach(attached)
}

fun ConstraintSet.attach(view: View, parentView: View) {
    this.connect(view.id, ConstraintSet.TOP, parentView.id, ConstraintSet.TOP)
    this.connect(view.id, ConstraintSet.START, parentView.id, ConstraintSet.START)
    this.connect(view.id, ConstraintSet.END, parentView.id, ConstraintSet.END)
    this.connect(view.id, ConstraintSet.BOTTOM, parentView.id, ConstraintSet.BOTTOM)
}