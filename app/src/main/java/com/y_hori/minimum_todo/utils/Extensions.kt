package com.y_hori.minimum_todo.utils

import android.graphics.Paint
import android.text.TextPaint
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView


fun ImageView.startShakeAnimation() {
    val rotateAnimation = RotateAnimation(
        Math.PI.toFloat() * -1f, Math.PI.toFloat(),
        Animation.RELATIVE_TO_SELF, 0.5f,
        Animation.RELATIVE_TO_SELF, 0.5f
    ).apply {
        duration = 100L
        repeatCount = 4
        repeatMode = Animation.REVERSE
    }
    this.startAnimation(rotateAnimation)
}

fun TextPaint.setStrike() {
    this.flags = Paint.STRIKE_THRU_TEXT_FLAG
    this.isAntiAlias = true
}
