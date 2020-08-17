package com.hendra.myviewmodel

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat

class MyEditText : AppCompatEditText {
    internal lateinit var mClearButtonImage: Drawable

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, deffStyle: Int) : super(context, attrs, deffStyle) {
        init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        hint = "Masukkan data"
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    private fun init() {
        mClearButtonImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_close_24, null) as Drawable

        setOnTouchListener(OnTouchListener { v, event ->
            if (compoundDrawablesRelative[2] != null) {
                val clearButtonStart : Float
                val clearButtonEnd : Float
                var isClearButtonClicked = false
                when(layoutDirection) {
                    View.LAYOUT_DIRECTION_LTR -> {
                        clearButtonEnd = (mClearButtonImage.intrinsicWidth + paddingStart).toFloat()
                        when {
                            event.x < clearButtonEnd -> isClearButtonClicked = true
                        }
                    }
                    else -> {
                        clearButtonStart = (width - paddingEnd - mClearButtonImage.intrinsicWidth).toFloat()
                        when {
                            event.x > clearButtonStart -> isClearButtonClicked = true
                        }
                    }
                }
                when {
                    isClearButtonClicked -> when {
                        event.action == MotionEvent.ACTION_DOWN -> {
                            mClearButtonImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_close_24, null) as Drawable
                            showClearButton()
                            return@OnTouchListener true
                        }
                        event.action == MotionEvent.ACTION_UP -> {
                            mClearButtonImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_close_24, null) as Drawable
                            when {
                                text != null -> text?.clear()
                            }
                            hideClearButton()
                            return@OnTouchListener true
                        }
                        else -> return@OnTouchListener false
                     }
                    else -> return@OnTouchListener false
                }
            }
            false
        })

        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                when {
                    !s.toString().isEmpty() -> showClearButton()
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }

    private fun showClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, mClearButtonImage, null)
    }

    private fun hideClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null)
    }
}