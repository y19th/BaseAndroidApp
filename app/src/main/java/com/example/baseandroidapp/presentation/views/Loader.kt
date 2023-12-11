package com.example.baseandroidapp.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.get
import com.example.baseandroidapp.R
import com.google.android.material.progressindicator.CircularProgressIndicator


class Loader : LinearLayoutCompat {

    constructor(context: Context,attrs: AttributeSet) : super(context, attrs) {
        initial(context, attrs)
    }
    constructor(context: Context, isVisible: Boolean = false): super(context) {
        initial(context,isVisible)
    }

    private val progressIndex = 0

    private var _isShown: Boolean = false
    var shown get() = _isShown
        set(value) {
            _isShown = value
            (this[progressIndex] as CircularProgressIndicator).isIndeterminate = value
            changeVisibility()
            invalidate()
            requestLayout()
        }

    private fun initial(context: Context,attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.Loader, 0, 0
        ).apply {
            try {
                _isShown = getBoolean(R.styleable.Loader_is_shown,false)
                val progress = CircularProgressIndicator(context, attrs).apply {
                    generateViewId()
                    layoutParams = LayoutParams(
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT
                    )
                    isIndeterminate = _isShown
                    background = AppCompatResources.getDrawable(context,R.drawable.item_background_loader)
                    this.setPadding(32,32,32,32)

                }
                setBackgroundColor(context.getColor(R.color.background_blur))
                gravity = Gravity.CENTER
                elevation = 4f
                this@Loader.addView(progress,progressIndex)
                changeVisibility()
            } finally {
                recycle()
            }
        }
    }

    private fun initial(context: Context, isVisible: Boolean) {
        _isShown = isVisible
        this.id = generateViewId()
        val progress = CircularProgressIndicator(context).apply {
            generateViewId()
            layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            )
            isIndeterminate = false
            background = AppCompatResources.getDrawable(context,R.drawable.item_background_loader)
            this.setPadding(32,32,32,32)

        }
        setBackgroundColor(context.getColor(R.color.background_blur))
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT)
        gravity = Gravity.CENTER
        elevation = 4f
        this@Loader.addView(progress,progressIndex)
        changeVisibility()
    }


    private fun changeVisibility() {
        visibility = if(shown) View.VISIBLE else View.GONE
        invalidate()
        requestLayout()
    }

}