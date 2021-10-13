package ru.freeit.awesomebuttons.text

import android.animation.ValueAnimator

abstract class AbstractValueAnimator(protected var viewWidth: Float, protected var viewHeight: Float) :
    TextButtonAnimator {
    private val animator = mutableListOf<ValueAnimator>()

    override fun start(onInvalidate: () -> Unit, duration: Long) {
        animator.firstOrNull()?.cancel()
        animator.clear()
        animator.add(animator(onInvalidate, duration))
    }

    override fun changeSize(viewWidth: Float, viewHeight: Float) {
        this.viewWidth = viewWidth
        this.viewHeight = viewHeight
        changeAnimation()
    }

    override fun cancel() {
        animator.firstOrNull()?.cancel()
        animator.clear()
        reset()
    }

    abstract fun changeAnimation()
    abstract fun animator(onInvalidate: () -> Unit, duration: Long) : ValueAnimator
    abstract fun reset()
}