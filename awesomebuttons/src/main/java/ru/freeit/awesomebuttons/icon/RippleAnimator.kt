package ru.freeit.awesomebuttons.icon

import android.animation.ValueAnimator
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart

class RippleAnimator(private var radius: Float, private var delay: Long) {

    private val animators = mutableListOf<ValueAnimator>()
    private var isRunning: Boolean = false

    fun changeRippleRadius(radius: Float) {
        this.radius = radius
    }

    fun changeDelay(delay: Long) {
        this.delay = delay
    }

    fun start(updateListener: ValueAnimator.AnimatorUpdateListener) {
        val anim = ValueAnimator.ofFloat(0f, radius)
        anim.addUpdateListener(updateListener)

        anim.doOnStart { isRunning = true }
        anim.doOnEnd { isRunning = false }
        anim.duration = delay

        animators.firstOrNull()?.cancel()
        animators.clear()

        animators.add(anim)
        anim.start()
    }

    fun stop(after: () -> Unit) {
        if (isRunning) {
            animators.firstOrNull()?.doOnEnd {
                after()
            }
        } else {
            after()
        }
    }
}