package com.example.customprogressbar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.View

class VerticalProgressBar(context: Context, attrs: AttributeSet) : View(context, attrs) {

    companion object {
        private const val DEFAULT_RECT_BACKGROUND_COLOR = Color.BLUE
        private const val DEFAULT_PROGRESS_COLOR = Color.RED
        private const val DEFAULT_BORDER_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_WIDTH = 8.0f
        private const val DEFAULT_CURRENT_VALUE = 50
        private const val DEFAULT_MAX_VALUE = 100
        private const val DEFAULT_MIN_VALUE = 0
    }

    private var rectBackgroundColor = DEFAULT_RECT_BACKGROUND_COLOR
    private var progressColor = DEFAULT_PROGRESS_COLOR
    private var borderColor = DEFAULT_BORDER_COLOR
    private var borderWidth = DEFAULT_BORDER_WIDTH

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var pbWidth = 0
    private var pbHeight = 0

    var min = DEFAULT_MIN_VALUE
        set(state) {
            field = state
            invalidate()
        }

    var max = DEFAULT_MAX_VALUE
        set(state) {
            field = state
            invalidate()
        }
    var current = DEFAULT_CURRENT_VALUE
        set(state) {
            field = state
            invalidate()
        }

    init {
        setupAttributes(attrs)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawProgressBar(canvas)
        drawProgress(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        pbHeight = measuredHeight.coerceAtMost(measuredHeight)
        pbWidth = measuredWidth.coerceAtMost(measuredWidth)
    }

    override fun onSaveInstanceState(): Parcelable {
        val bundle = Bundle()
        bundle.putInt("min", min)
        bundle.putInt("max", max)
        bundle.putInt("current", current)
        bundle.putParcelable("superState", super.onSaveInstanceState())

        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        var viewState = state

        if (viewState is Bundle) {
            min = viewState.getInt("min", DEFAULT_MIN_VALUE)
            max = viewState.getInt("max", DEFAULT_MAX_VALUE)
            current = viewState.getInt("current", DEFAULT_CURRENT_VALUE)
            viewState = viewState.getParcelable("superState")
        }
        super.onRestoreInstanceState(viewState)
    }

    private fun drawProgressBar(canvas: Canvas) {
        paint.color = rectBackgroundColor
        paint.style = Paint.Style.FILL

        val rect = Rect(0, 0, pbWidth, pbHeight)

        canvas.drawRect(rect, paint)

        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth

        val rectBorder = Rect(0, 0, pbWidth, pbHeight)
        canvas.drawRect(rectBorder, paint)
    }

    private fun drawProgress(canvas: Canvas){
        paint.color = progressColor
        paint.style = Paint.Style.FILL

        val progressHeight= (pbHeight * (current - min)) / (max - min)

        val rect = Rect(0 , pbHeight - progressHeight, pbWidth, pbHeight)
        canvas.drawRect(rect, paint)
    }

    private fun setupAttributes(attrs: AttributeSet) {
        val typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.VerticalProgressBar, 0, 0)
        rectBackgroundColor = typedArray.getColor(
            R.styleable.VerticalProgressBar_rectBackgroundColor,
            DEFAULT_RECT_BACKGROUND_COLOR
        )
        progressColor = typedArray.getColor(
            R.styleable.VerticalProgressBar_progressColor,
            DEFAULT_PROGRESS_COLOR)
        borderColor = typedArray.getColor(
            R.styleable.VerticalProgressBar_borderColor,
            DEFAULT_BORDER_COLOR
        )
        borderWidth = typedArray.getDimension(
            R.styleable.VerticalProgressBar_borderWidth,
            DEFAULT_BORDER_WIDTH
        )

        min = typedArray.getInt(
            R.styleable.VerticalProgressBar_min,
            DEFAULT_MIN_VALUE)

        max = typedArray.getInt(
            R.styleable.VerticalProgressBar_max,
            DEFAULT_MAX_VALUE)

        current = typedArray.getInt(
            R.styleable.VerticalProgressBar_current,
            DEFAULT_CURRENT_VALUE)

        typedArray.recycle()
    }

}