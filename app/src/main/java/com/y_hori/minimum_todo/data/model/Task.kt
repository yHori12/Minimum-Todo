package com.y_hori.minimum_todo.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.util.*

@JsonClass(generateAdapter = true)
@Parcelize
class Task(
    var id: Long = 0,
    var title: String = "",
    var description: String = "",
    val timeStamp: Long = Date().time,
    var isCompleted: Boolean = false
) : Parcelable {

    override fun toString(): String = title
}
