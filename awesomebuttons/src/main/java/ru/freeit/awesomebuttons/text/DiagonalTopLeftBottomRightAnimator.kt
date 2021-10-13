package ru.freeit.awesomebuttons.text

import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Paint
import kotlin.math.asin
import kotlin.math.sqrt

class DiagonalTopLeftBottomRightAnimator(viewWidth: Float, viewHeight: Float) : AbstractValueAnimator(viewWidth, viewHeight) {
    private var y1 = 0f
    private var y2 = 0f

    override fun changeAnimation() {}

    override fun animator(onInvalidate: () -> Unit, duration: Long): ValueAnimator = ValueAnimator.ofFloat(
        0f,
        viewHeight * 2f
    ).apply {
        addUpdateListener {
            y1 = it.animatedValue as Float
            y2 = -(it.animatedValue as Float)
            onInvalidate()
        }
        this.duration = duration
        start()
    }

    override fun reset() {
        y1 = 0f
        y2 = 0f
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        canvas.save()

        val sinA = viewHeight / sqrt((viewWidth * viewWidth + viewHeight * viewHeight))

        val angle = Math.toDegrees(asin(sinA).toDouble())

        canvas.rotate(-angle.toFloat())

        canvas.drawRect(0f, viewHeight, viewWidth * 1.5f, viewHeight + y1, paint)
        canvas.drawRect(-20f, viewHeight, viewWidth * 1.5f, viewHeight + y2, paint)

        canvas.restore()
    }
}