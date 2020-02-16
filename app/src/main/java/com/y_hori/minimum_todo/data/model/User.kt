package com.y_hori.minimum_todo.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val token: String = "",
    var uid: String = ""
) : Parcelable

