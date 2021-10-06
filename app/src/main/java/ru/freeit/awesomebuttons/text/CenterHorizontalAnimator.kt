package ru.freeit.awesomebuttons.text

import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Paint

class CenterHorizontalAnimator(viewWidth: Float, viewHeight: Float) : AbstractValueAnimator(viewWidth, viewHeight) {

    private var x1 = viewWidth / 2f
    private var x2 = viewWidth / 2f

    override fun changeAnimation() {
        x1 = viewWidth / 2f
        x2 = viewWidth / 2f
    }

    override fun animator(onInvalidate: () -> Unit, duration: Long): ValueAnimator = ValueAnimator.ofFloat(
        viewWidth / 2f,
        0f
    ).apply {
        addUpdateListener {
            x1 = it.animatedValue as Float
            x2 = viewWidth - x1
            onInvalidate()
        }
        this.duration = duration
        start()
    }

    override fun reset() {
        x1 = viewWidth / 2f
        x2 = viewWidth / 2f
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        canvas.drawRect(x1, 0f, viewWidth / 2, viewHeight, paint)
        canvas.drawRect(viewWidth / 2f, 0f, x2, viewHeight, paint)
    }


}