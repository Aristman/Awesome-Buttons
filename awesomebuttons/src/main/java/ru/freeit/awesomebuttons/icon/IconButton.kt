package ru.freeit.awesomebuttons.icon

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import androidx.annotation.AttrRes
import androidx.appcompat.widget.AppCompatImageView
import ru.freeit.awesomebuttons.R

class IconButton @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(ctx, attrs, defStyleAttr) {

    private fun Context.themeColor(@AttrRes attrRes: Int): Int {
        val typedValue = TypedValue()
        theme.resolveAttribute (attrRes, typedValue, true)
        return typedValue.data
    }

    private var initial: Float = 0f

    private var pointX = 0f
    private var pointY = 0f

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val rippleAnimator = RippleAnimator(0f, 350L)

    init {
        isClickable = true
        clipToOutline = true
        context.theme.obtainStyledAttributes(attrs, R.styleable.IconButton, 0, 0).apply {
            try {
                changeRippleColor(getColor(R.styleable.IconButton_rippleColor, context.themeColor(android.R.attr.colorPrimary)))
                changeRadius(getDimensionPixelSize(R.styleable.IconButton_cornerRadius, 100).toFloat())
                changeBgColor(getColor(R.styleable.IconButton_bgColor, Color.TRANSPARENT))
            } finally {
                recycle()
            }
        }
    }

    fun changeRadius(radius: Float) {
        background = GradientDrawable().apply {
            cornerRadius = radius
        }
    }

    fun changeBgColor(color: Int) {
        background = (background as GradientDrawable).apply {
            setColor(color)
        }
    }

    fun changeRippleDuration(duration: Long) {
        rippleAnimator.changeDelay(duration)
    }

    fun changeRippleColor(color: Int) {
        paint.color = Color.argb(90, Color.red(color), Color.green(color), Color.blue(color))
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rippleAnimator.changeRippleRadius(w * 2f)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                pointX = event.x
                pointY = event.y

                rippleAnimator.start {
                    initial = it.animatedValue as Float
                    invalidate()
                }

            }
            MotionEvent.ACTION_UP -> {
                rippleAnimator.stop {
                    initial = 0f
                    invalidate()
                }
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(pointX, pointY, initial, paint)
    }

}
