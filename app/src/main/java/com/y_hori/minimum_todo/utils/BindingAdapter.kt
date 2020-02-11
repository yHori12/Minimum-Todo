package com.y_hori.minimum_todo.utils

import androidx.databinding.BindingAdapter
import com.lelloman.identicon.view.ClassicIdenticonView

@BindingAdapter("app:hash")
fun ClassicIdenticonView.setHash(hash: String) {
    this.hash = hash.hashCode()
}
