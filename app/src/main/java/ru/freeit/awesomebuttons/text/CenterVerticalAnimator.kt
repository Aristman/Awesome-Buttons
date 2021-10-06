package ru.freeit.awesomebuttons.text

import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Paint

class CenterVerticalAnimator(viewWidth: Float, viewHeight: Float) : AbstractValueAnimator(viewWidth, viewHeight) {

    private var y1 = viewHeight / 2f
    private var y2 = viewHeight / 2f

    override fun changeAnimation() {
        y1 = viewHeight / 2f
        y2 = viewHeight / 2f
    }

    override fun animator(onInvalidate: () -> Unit, duration: Long): ValueAnimator = ValueAnimator.ofFloat(
        viewHeight / 2f,
        0f
    ).apply {
        addUpdateListener {
            y1 = it.animatedValue as Float
            y2 = viewHeight - y1
            onInvalidate()
        }
        this.duration = duration
        start()
    }

    override fun reset() {
        y1 = viewHeight / 2f
        y2 = viewHeight / 2f
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        canvas.drawRect(0f, y1, viewWidth, viewHeight / 2, paint)
        canvas.drawRect(0f, y2, viewWidth, viewHeight / 2, paint)
    }


}