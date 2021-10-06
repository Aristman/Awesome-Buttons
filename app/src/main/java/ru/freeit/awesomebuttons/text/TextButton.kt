package ru.freeit.awesomebuttons.text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import androidx.annotation.AttrRes
import androidx.appcompat.widget.AppCompatTextView
import ru.freeit.awesomebuttons.R


class TextButton @JvmOverloads constructor(ctx: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : AppCompatTextView(ctx, attrs, defStyleAttr) {

    private fun Context.themeColor(@AttrRes attrRes: Int): Int {
        val typedValue = TypedValue()
        theme.resolveAttribute (attrRes, typedValue, true)
        return typedValue.data
    }

    private var borderColor = context.themeColor(R.attr.colorPrimary)
    private var bgColor = Color.TRANSPARENT
    private var hoverColor = context.themeColor(R.attr.colorPrimary)

    private var borderRadius = 5f
    private var borderWidth = 5f
    
    private var clickListener = mutableListOf<() -> Unit>()

    fun changeBorderColor(color: Int) {
        borderColor = color
        invalidate()
    }

    fun bgColor(color: Int) {
        this.bgColor = color
        invalidate()
    }

    fun changeBorderWidth(borderWidth: Float) {
        this.borderWidth = borderWidth
        background = bg()
        invalidate()
    }

    fun changeRadius(radius: Float) {
        this.borderRadius = radius
        background = bg()
        invalidate()
    }

    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = borderColor
        strokeWidth = borderWidth
        style = Paint.Style.STROKE
    }

    private fun bg() = GradientDrawable().apply {
        cornerRadius = borderRadius + borderWidth / 2f
    }

    private val animator : AbstractValueAnimator

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.TextButton, 0, 0).apply {
            try {
                bgColor = getColor(R.styleable.TextButton_bgColor, Color.TRANSPARENT)
                borderColor = getColor(R.styleable.TextButton_borderColor, context.themeColor(R.attr.colorPrimary))
                hoverColor = getColor(R.styleable.TextButton_hoverColor, context.themeColor(R.attr.colorPrimary))
                borderRadius = getDimensionPixelSize(R.styleable.TextButton_borderRadius, 5).toFloat()
                borderWidth = getDimensionPixelSize(R.styleable.TextButton_borderWidth, 5).toFloat()
                borderPaint.strokeWidth = borderWidth
                val animation = getInt(R.styleable.TextButton_animation, 0)
                animator = when (animation) {
                    0 -> LeftToRightAnimator(width.toFloat(), height.toFloat())
                    1 -> RightToLeftAnimator(width.toFloat(), height.toFloat())
                    2 -> CenterHorizontalAnimator(width.toFloat(), height.toFloat())
                    3 -> CenterVerticalAnimator(width.toFloat(), height.toFloat())
                    4 -> DiagonalBottomLeftTopRightAnimator(width.toFloat(), height.toFloat())
                    else -> LeftToRightAnimator(width.toFloat(), height.toFloat())
                }
            } finally {
                recycle()
            }
        }
        clipToOutline = true
        background = bg()
        textAlignment = TEXT_ALIGNMENT_CENTER
        isAllCaps = true
    }

    private val bgPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = bgColor
        style = Paint.Style.FILL
    }

    private val hoverPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = hoverColor
        style = Paint.Style.FILL
    }

    fun onClick(onClick: () -> Unit) {
        clickListener.clear()
        clickListener.add(onClick)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        animator.changeSize(w.toFloat(), h.toFloat())
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                animator.start({ invalidate() }, 400L)
                true
            }
            MotionEvent.ACTION_UP -> {
                clickListener.firstOrNull()?.invoke()
                animator.cancel()
                invalidate()
                true
            }
            else -> super.onTouchEvent(event)
        }
    }

    override fun onDraw(canvas: Canvas) {
        val rectF1 = RectF(0f, 0f, width.toFloat(), height.toFloat())
        canvas.drawRoundRect(rectF1, borderRadius, borderRadius, bgPaint)

        val rectF2 = RectF(borderWidth / 2f, borderWidth / 2f, width - borderWidth / 2f, height - borderWidth / 2f)
        canvas.drawRoundRect(rectF2, borderRadius, borderRadius, borderPaint)

        animator.draw(canvas, hoverPaint)

        super.onDraw(canvas)
    }

}