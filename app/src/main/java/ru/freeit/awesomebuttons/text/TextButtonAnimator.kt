package ru.freeit.awesomebuttons.text

import android.graphics.Canvas
import android.graphics.Paint

interface TextButtonAnimator {
    fun start(onInvalidate: () -> Unit, duration: Long)
    fun cancel()
    fun draw(canvas: Canvas, paint: Paint)
    fun changeSize(viewWidth: Float, viewHeight: Float)
}