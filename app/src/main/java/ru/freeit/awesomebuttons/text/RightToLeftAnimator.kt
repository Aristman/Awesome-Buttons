package ru.freeit.awesomebuttons.text

import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

class RightToLeftAnimator(viewWidth: Float, viewHeight: Float) : AbstractValueAnimator(viewWidth, viewHeight) {

    private var animWidth = 0f

    override fun changeAnimation() { animWidth = viewWidth }

    override fun animator(onInvalidate: () -> Unit, duration: Long): ValueAnimator = ValueAnimator.ofFloat(
        viewWidth,
        0f
    ).apply {
        addUpdateListener {
            animWidth = it.animatedValue as Float
            onInvalidate()
        }
        this.duration = duration
        start()
    }

    override fun reset() {  animWidth = viewWidth }

    override fun draw(canvas: Canvas, paint: Paint) {
        val rectF3 = RectF(animWidth, 0f, viewWidth, viewHeight)
        canvas.drawRect(rectF3, paint)
    }

}