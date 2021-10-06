package ru.freeit.awesomebuttons.text

import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

class LeftToRightAnimator(viewWidth: Float, viewHeight: Float) : AbstractValueAnimator(viewWidth, viewHeight) {

    private var animWidth = 0f

    override fun changeAnimation() {}

    override fun animator(onInvalidate: () -> Unit, duration: Long): ValueAnimator = ValueAnimator.ofFloat(
        0f,
        viewWidth
    ).apply {
            addUpdateListener {
                animWidth = it.animatedValue as Float
                onInvalidate()
            }
            this.duration = duration
            start()
    }

    override fun reset() { animWidth = 0f }

    override fun draw(canvas: Canvas, paint: Paint) {
        val rectF3 = RectF(0f, 0f, animWidth, viewHeight)
        canvas.drawRect(rectF3, paint)
    }

}