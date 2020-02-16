package com.y_hori.minimum_todo.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.lelloman.identicon.view.ClassicIdenticonView
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("app:hash")
fun ClassicIdenticonView.setHash(hash: String) {
    this.hash = hash.hashCode()
}

@BindingAdapter("app:deadline")
fun TextView.setDeadline(deadlineSecond:Long) {
    val millis: Long = deadlineSecond * 1000
    val date = Date(millis)
    val sdf = SimpleDateFormat("yyyy/MMdd HH:mm", Locale.getDefault())

    this.text = sdf.format(date)
}
